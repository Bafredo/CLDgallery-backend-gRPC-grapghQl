package com.muyoma.cld_gallery;

import com.muyoma.cld_gallery.user.UserController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import io.grpc.Server;
import io.grpc.ServerBuilder;

@Component
public class GrpcServerRunner implements CommandLineRunner {

    private final UserController userService;

    public GrpcServerRunner(UserController userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the gRPC server
        Server server = ServerBuilder.forPort(9090)
                .addService(userService)  // Register your gRPC service
                .build();

        System.out.println("Starting gRPC server...");
        server.start();
        System.out.println("gRPC server started at " + server.getPort());

        // Add shutdown hook to stop the server gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server...");
            server.shutdown();
        }));

        // Keep the server running
        server.awaitTermination();
    }
}
