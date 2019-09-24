package com.github.agitolyev;

import com.github.agitolyev.analysis.AnalysisServiceConfig;
import com.github.agitolyev.analysis.AnalysisServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class.
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private static final String DEFAULT_PORT = "8080";
    private static final String PORT_KEY = "GRPC_PORT";

    public static void main(String[] args) throws Exception {
        final Integer port = Integer.valueOf(System.getProperty(PORT_KEY, DEFAULT_PORT));
        logger.info("Going to start server on port: {}", port);
        final Server server = ServerBuilder.forPort(port)
                .addService(new AnalysisServiceImpl(AnalysisServiceConfig.fromEnv(), new OkHttpClient())).build();
        logger.info("Starting server...");
        server.start();
        logger.info("Server started!");
        server.awaitTermination();
    }
}
