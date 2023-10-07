package me.bnnq.utils;

public class TaskUtilities
{
    public static void sleepWithDelay(int milliseconds, int delayMilliseconds)
    {
        while ((milliseconds = milliseconds - delayMilliseconds) > 0)
            try {
                Thread.sleep(delayMilliseconds);
            } catch (InterruptedException exception) {
                System.err.println("Failed to sleep");
                throw new RuntimeException(exception);
            }
    }
}
