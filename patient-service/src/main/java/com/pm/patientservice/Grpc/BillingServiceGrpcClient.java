package com.pm.patientservice.Grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingstub;

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int serverPort
    ) {
        log.info("conneting to billing service at {}:{}", serverAddress, serverPort);
        ManagedChannel channel= ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        blockingstub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String PatientId,String name,String email){
        BillingRequest rqeusest=BillingRequest.newBuilder().setPatientId(PatientId).setName(name).setEmail(email).build();

        BillingResponse response=blockingstub.createBillingAccount(rqeusest);
        log.info("response {}", response);
        return response;
    }

}
