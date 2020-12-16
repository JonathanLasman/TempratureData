package com.jonathan;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileLock;

public class AddSampleHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //GET requests are not handled
        if("POST".equals(exchange.getRequestMethod())) {
            String body = IOUtils.toString(exchange.getRequestBody(), (String)null);
            addSample(TempSample.fromJson(body));
        }
    }

    void addSample(TempSample sample) throws IOException {
        //Get sensor file
        File sensorFile = new File(sample.getId() + ".json");

        //Lock file
        FileInputStream fis = new FileInputStream(sensorFile);
        FileLock lock = fis.getChannel().lock();

        SensorData sensorData;
        if (!sensorFile.exists()) {
            sensorFile.createNewFile();
            sensorData = new SensorData();
        } else {
            sensorData = SensorFile.fileToData(sensorFile);
        }

        //add samples
        sensorData.addSample(sample);
        GlobalData.addSample(sample);
        SensorFile.save(sensorFile, sensorData);

        //Unlock file
        lock.release();
    }
}
