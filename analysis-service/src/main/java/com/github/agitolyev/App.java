package com.github.agitolyev;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) throws Exception {
    Server server = ServerBuilder.forPort(8080)
        .addService(new AnalysisServiceImpl()).build();

    System.out.println("Starting server...");
    server.start();
    System.out.println("Server started!");
    server.awaitTermination();
  }
}
