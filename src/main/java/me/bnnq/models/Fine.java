package me.bnnq.models;

public record Fine(String code, String reason, double amount)
{

    @Override
    public String toString()
    {
        return "Fine code: " + code + "\n" +
                "Fine reason: " + reason + "\n" +
                "Fine amount: " + amount + "\n";
    }
}
