package org.example.grpc.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.javaboy.grpc.demo.Product;
import org.javaboy.grpc.demo.ProductId;
import org.javaboy.grpc.demo.ProductInfoGrpc;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2023/3/21 15:30
 */
public class ProductClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        ProductInfoGrpc.ProductInfoBlockingStub stub = ProductInfoGrpc.newBlockingStub(channel);
        Product p = Product.newBuilder().setId("1")
                .setPrice(399.0f)
                .setName("项目")
                .setDescription("Spring")
                .build();
        ProductId productId = stub.addProduct(p);
        System.out.println("productId.getValue() = " + productId.getValue());
        Product product = stub.getProduct(ProductId.newBuilder().setValue("99999").build());
        System.out.println("product.name() = " + product.getName());
    }
}
