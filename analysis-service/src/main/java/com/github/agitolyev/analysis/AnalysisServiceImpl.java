package com.github.agitolyev.analysis;

import com.github.agitolyev.AnalysisReply;
import com.github.agitolyev.AnalysisRequest;
import com.github.agitolyev.AnalysisServiceGrpc.AnalysisServiceImplBase;
import com.github.agitolyev.AnonymizationReply;
import com.github.agitolyev.AnonymizationRequest;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
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

  private static final String PROTO_DECORATOR = "{\"tokens\": %s}";

  private static final String ANONYMIZE_URL_PATTERN = "http://%s/api/v1/projects/%s/anonymize";
  private static final String ANALYZE_URL_PATTERN = "http://%s/api/v1/projects/%s/analyze";

  private final OkHttpClient client;

  private final String anonymizeUrl;
  private final String analyzeUrl;

  public AnalysisServiceImpl(final AnalysisServiceConfig config, final OkHttpClient client) {
    this.anonymizeUrl = String.format(ANONYMIZE_URL_PATTERN, config.getPresidioApiServiceAddr(), config.getProjectName());
    this.analyzeUrl = String.format(ANALYZE_URL_PATTERN, config.getPresidioApiServiceAddr(), config.getProjectName());
    this.client = client;
  }

  @Override
  public void anonymize(AnonymizationRequest request, StreamObserver<AnonymizationReply> responseObserver) {
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

      AnonymizationReply response = AnonymizationReply.newBuilder()
          .setText(anonymizedResponse.body().string())
          .build();
      responseObserver.onNext(response);
    } catch (IOException | NullPointerException e) {
      logger.warn("Caught exception processing anonymize request", e);
    } finally {
      responseObserver.onCompleted();
    }
  }

  @Override
  public void analyze(AnalysisRequest request, StreamObserver<AnalysisReply> responseObserver) {
    try {
      RequestBody body = RequestBody.create(
          MediaType.parse("application/json; charset=utf-8"),
          new AnalyzeRequest(request.getText()).analyzeRequestJson());
      Request httpRequest = new Request.Builder()
          .url(analyzeUrl)
          .post(body)
          .build();

      Call call = client.newCall(httpRequest);
      Response analyzeResponse = call.execute();
      final AnalysisReply.Builder builder = AnalysisReply.newBuilder();
      JsonFormat.parser().merge(String.format(PROTO_DECORATOR, analyzeResponse.body().string()), builder);
      responseObserver.onNext(builder.build());
    } catch (IOException | NullPointerException e) {
      logger.warn("Caught exception processing analyze request", e);
    } finally {
      responseObserver.onCompleted();
    }
  }
}
