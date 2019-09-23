package com.github.agitolyev;

import io.grpc.stub.StreamObserver;
import okhttp3.OkHttpClient;

public class AnalysisServiceImpl extends AnalysisServiceGrpc.AnalysisServiceImplBase {

    private static final String ANONYMIZE_URL_PATTERN = "%s/api/v1/projects/%s/anonymize";

    private static final OkHttpClient client = new OkHttpClient();


    @Override
    public void analyze(AnalysisRequest request, StreamObserver<AnalysisReply> responseObserver) {
        AnalysisReply response = AnalysisReply.newBuilder()
                .setText("Hello")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
