package com.example.service;

import com.example.GreetingServiceOuterClass;
import com.example.GreetingServiceGrpc;
import com.example.entity.Users;
import com.example.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
@GrpcService
@AllArgsConstructor

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Autowired
    UserRepository userRepository;
    @Override
    public void greeting(GreetingServiceOuterClass.IdRequest request,
                         StreamObserver<GreetingServiceOuterClass.Response> responseStreamObserver) {
        System.out.println("запрос от клиента" + request);
        Users users = userRepository.findById(request.getId()).get();
        GreetingServiceOuterClass.Response response = GreetingServiceOuterClass
                .Response
                .newBuilder()
                .setFirstName(users.getFirstName())
                .setLastName(users.getLastName())
                .setMail(users.getMail())
                .build();

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}




