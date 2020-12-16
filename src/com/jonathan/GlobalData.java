package com.jonathan;

public class GlobalData
{
    static int globalMin = 0;
    static int globalMax = 0;
    static int globalAvg = 0;
    static int numOfSamples = 0;

    //I understand that global variables can be dangerous in terms of concurrency but we used synchronized and
    //the data type we are accessing is primitive.
    static synchronized void addSample (TempSample sample)
    {
        globalMin=Math.min(globalMin, sample.getTemp());
        globalMax=Math.max(globalMax, sample.getTemp());
        globalAvg=((globalAvg*numOfSamples)+sample.getTemp())/(numOfSamples+1);
        numOfSamples++;
    }

    public static int getGlobalMin() {
        return globalMin;
    }

    public static int getGlobalMax() {
        return globalMax;
    }

    public static int getGlobalAvg() {
        return globalAvg;
    }

    public static int getNumOfSamples() {
        return numOfSamples;
    }
}
