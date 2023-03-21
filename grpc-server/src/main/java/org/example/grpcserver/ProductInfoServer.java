package org.example.grpcserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.grpcserver.service.ProductInfoImpl;

import java.io.IOException;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2023/3/21 15:23
 */
public class ProductInfoServer {

    Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        ProductInfoServer server = new ProductInfoServer();
        System.out.println("server start = " + server);
        server.start();
        server.blockUntilShutdown();
    }

    public void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new ProductInfoImpl())
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            ProductInfoServer.this.stop();
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException{
        if (server != null) {
            server.awaitTermination();
        }
    }

}
