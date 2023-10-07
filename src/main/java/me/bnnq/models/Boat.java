package me.bnnq.models;

public class Boat
{
    private int seats;
    private String name;

    public Boat(String name, int seats)
    {
        this.name = name;
        this.seats = seats;
    }

    public String getName()
    {
        return name;
    }

    public int getSeats()
    {
        return this.seats;
    }

    public void acceptPassenger(Passenger passenger)
    {
        passenger.commitWaitingTime();
        this.seats--;
    }

}
