package com.github.agitolyev.analysis;

import com.github.agitolyev.AnalysisReply;
import com.github.agitolyev.AnalysisRequest;
import com.github.agitolyev.AnalysisServiceGrpc.AnalysisServiceImplBase;
import io.grpc.stub.StreamObserver;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AnalysisServiceImpl extends AnalysisServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisServiceConfig.class);

    private static final String ANONYMIZE_URL_PATTERN = "%s/api/v1/projects/%s/anonymize";

    private final OkHttpClient client;

    private final String anonymizeUrl;

    public AnalysisServiceImpl(final AnalysisServiceConfig config, final OkHttpClient client) {
        this.anonymizeUrl = String.format(ANONYMIZE_URL_PATTERN, config.getPresidioApiServiceAddr(), config.getProjectName());
        this.client = client;
    }

    @Override
    public void analyze(AnalysisRequest request, StreamObserver<AnalysisReply> responseObserver) {
        try {
            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    new AnonymizeRequest(request.getText()).anonymizeRequestJson());
            Request httpRequest = new Request.Builder()
                    .url(anonymizeUrl)
                    .post(body)
                    .build();

            Call call = client.newCall(httpRequest);
            Response anonymizedResponse = call.execute();

            AnalysisReply response = AnalysisReply.newBuilder()
                    .setText(anonymizedResponse.body().string())
                    .build();
            responseObserver.onNext(response);
        } catch (IOException | NullPointerException e) {
            logger.warn("Caught exception processing anonymize request", e);
        } finally {
            responseObserver.onCompleted();
        }
    }
}
