package org.example.grpcserver.service;

import io.grpc.stub.StreamObserver;
import org.javaboy.grpc.demo.Product;
import org.javaboy.grpc.demo.ProductId;
import org.javaboy.grpc.demo.ProductInfoGrpc;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2023/3/21 15:12
 */
public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    @Override
    public void addProduct(Product request, StreamObserver<ProductId> responseObserver) {
        System.out.println("request.toString() = " + request.toString());
        responseObserver.onNext(ProductId.newBuilder().setValue(request.getId()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(ProductId request, StreamObserver<Product> responseObserver) {
        responseObserver.onNext(Product.newBuilder().setId(request.getValue()).setName("三国演义").build());
        responseObserver.onCompleted();
    }
}
