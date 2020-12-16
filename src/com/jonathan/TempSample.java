package com.jonathan;

import com.google.gson.Gson;

public class TempSample {
    private int id;
    private int temp;

    TempSample(int id, int temp)
    {
        this.id = id;
        this.temp = temp;
    }

    public static TempSample fromJson (String httpBody) {
        Gson g = new Gson();
        TempSample tempSample = g.fromJson(httpBody, TempSample.class);
        return tempSample;
    }

    public int getId()
    {
        return id;
    }

    public int getTemp()
    {
        return temp;
    }


}
