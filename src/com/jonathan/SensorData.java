package com.jonathan;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class SensorData {
    private int minTemp;
    private int maxTemp;
    private int avgTemp;
    private int numOfSamples;

    public SensorData(int minTemp, int maxTemp, int avgTemp, int numOfSamples) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.avgTemp = avgTemp;
        this.numOfSamples = numOfSamples;
    }

    public SensorData() {
        this.minTemp = 0;
        this.maxTemp = 0;
        this.avgTemp = 0;
        this.numOfSamples = 0;
    }


    public int getMinTemp() {
        return minTemp;
    }

    public int getAvgTemp() {
        return avgTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void addSample(TempSample sample)
    {
        minTemp = min(sample.getTemp(), this.minTemp);
        maxTemp = max(sample.getTemp(), this.maxTemp);
        avgTemp = ((avgTemp*numOfSamples)+sample.getTemp())/(numOfSamples+1);
        numOfSamples++;
    }

}
