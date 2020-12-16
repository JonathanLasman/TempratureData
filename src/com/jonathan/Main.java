package com.jonathan;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) throws IOException {
        //Setting the backlog to 10 is the first measure we take in order to handle request bursts.
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8021), 10);
        server.createContext("/add_sample", new  AddSampleHandler());
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.setExecutor(threadPoolExecutor);
        server.start();

        Thread userConsoleThread = new Thread(new UserConsole());
        userConsoleThread.start();
    }
}
