package me.bnnq.models;

import java.time.LocalTime;

public class Passenger
{
    private final LocalTime arrivalTime;
    private LocalTime waitingTime;
    private final String name;

    public Passenger(String name)
    {
        this.arrivalTime = LocalTime.now();
        this.name = name;
    }

    public LocalTime getWaitingTime()
    {
        return waitingTime;
    }

    public LocalTime getArrivalTime()
    {
        return arrivalTime;
    }

    public String getName()
    {
        return name;
    }

    public void commitWaitingTime()
    {
        this.waitingTime = LocalTime.now().minusNanos(this.arrivalTime.toNanoOfDay());
    }

}
