package me.bnnq;

import me.bnnq.abstractions.IDisposable;
import me.bnnq.models.Boat;
import me.bnnq.models.Passenger;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BoatStop implements IDisposable
{
    private final Queue<Passenger> passengers;
    private final Queue<Boat> boats;
    private final Queue<LocalTime> waitingTimeHistory;
    private final ScheduledExecutorService passengerProcessorScheduler;

    public BoatStop()
    {
        this.passengers = new LinkedList<>();
        this.boats = new LinkedList<>();
        this.waitingTimeHistory = new LinkedList<>();

        this.passengerProcessorScheduler = Executors.newScheduledThreadPool(1);
        passengerProcessorScheduler.scheduleAtFixedRate(this::processPassengerBoarding, 0, 1, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void addPassenger(Passenger passenger)
    {
        this.passengers.add(passenger);
        System.out.println("Passenger \"" + passenger.getName() + "\" arrived at " + passenger.getArrivalTime());
    }

    public void arriveBoat(Boat boat)
    {
        this.boats.add(boat);
        System.out.println("Boat \"" + boat.getName() + "\" with " + boat.getSeats() + " seats arrived at " + LocalTime.now());
    }

    private void processPassengerBoarding()
    {
        if (!this.passengers.isEmpty() && !this.boats.isEmpty())
        {
            Boat boat = boats.peek();
            assert boat != null;

            while (boat.getSeats() > 0 && !passengers.isEmpty())
            {
                Passenger passenger = this.passengers.poll();
                boat.acceptPassenger(passenger);
                System.out.println("Passenger \"" + passenger.getName() + "\" boarded boat " + boat.getName() + " at " + LocalTime.now());
                waitingTimeHistory.add(passenger.getWaitingTime());
            }

            boats.poll();
            System.out.println("Boat \"" + boat.getName() + "\" left at " + LocalTime.now());
        }
    }

    public LocalTime getAverageWaitingTime()
    {
        return LocalTime.ofSecondOfDay(Math.round(waitingTimeHistory.stream().mapToDouble(LocalTime::toSecondOfDay).sum() / waitingTimeHistory.size()));
    }

    public LocalTime calculateBoatArrivalInterval(LocalTime boatArrivalInterval, int maxPassengers) {
        // Convert the boat arrival interval to seconds
        long boatArrivalIntervalSeconds = boatArrivalInterval.toSecondOfDay();

        // Calculate the average waiting time in seconds
        double averageWaitingTimeSeconds = getAverageWaitingTime().toSecondOfDay();

        // Calculate the number of passengers that can be served per boat arrival
        double passengersPerBoatArrival = (double) getAllPassengerCount() / averageWaitingTimeSeconds * boatArrivalIntervalSeconds;

        // Calculate the new boat arrival interval in seconds to ensure that there are no more than maxPassengers at the stop
        long newBoatArrivalIntervalSeconds = (long) (boatArrivalIntervalSeconds * passengersPerBoatArrival / maxPassengers);


        return LocalTime.ofSecondOfDay(newBoatArrivalIntervalSeconds);
    }



    public int getAllPassengerCount()
    {
        return this.waitingTimeHistory.size();
    }

    @Override
    public void dispose()
    {
        this.passengerProcessorScheduler.shutdown();
        try
        {
            if (!passengerProcessorScheduler.awaitTermination(60, TimeUnit.SECONDS))
            {
                passengerProcessorScheduler.shutdownNow();
            }
        }
        catch (InterruptedException e)
        {
            passengerProcessorScheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
