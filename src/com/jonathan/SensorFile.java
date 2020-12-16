package com.jonathan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class SensorFile {
    static private Gson gson = new GsonBuilder().create();

    public static SensorData fileToData(File file)
    {
        SensorData sensorData = null;

        try {
            String fileContents = IOUtils.toString(new FileInputStream(file), (String)null);
            sensorData = gson.fromJson(fileContents, SensorData.class);
        } catch (Throwable exception) {
            System.err.println("File not found");
        }

        return sensorData;
    }

    public static void save(File file, SensorData sensorData) throws IOException {
        file.delete();
        file.createNewFile();

        Files.write(file.toPath(), gson.toJson(sensorData).getBytes());
    }
}
