package com.github.agitolyev;

import com.github.agitolyev.analysis.AnalysisServiceConfig;
import com.github.agitolyev.analysis.AnalysisServiceImpl;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import junit.framework.TestCase;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for App.
 */

@RunWith(JUnit4.class)
public class AppTest extends TestCase {

    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @Test
    public void greeterImpl_analyze_ok() throws Exception {
        //arrange
        final String presidioApiServiceAddr = "test-svc:8080";
        final String projectName = "test";
        final String anonymizeUrl = String.format("http://%s/api/v1/projects/%s/anonymize", presidioApiServiceAddr, projectName);
        final AnalysisServiceConfig analysisServiceConfig = new AnalysisServiceConfig(presidioApiServiceAddr, projectName);

        final String textToAnonymize = "John Smith lives in New York. " +
                "We met yesterday morning in Seattle. " +
                "I called him before on (212) 555-1234 to verify the appointment. " +
                "He also told me that his drivers license is AC333991";
        final String anonymizeRequest = "{\n" +
                "  \"text\": \"" + textToAnonymize + "\",\n" +
                "  \"analyzeTemplate\": {\n" +
                "    \"allFields\": true\n" +
                "  },\n" +
                "  \"anonymizeTemplate\": {\n" +
                "    \"allFields\": true\n" +
                "  }\n" +
                "}";
        final String anonymizeResponse = "{\n" +
                "  \"text\": \"<PERSON> lives in <LOCATION>. We met <DATE_TIME> <DATE_TIME> in <LOCATION>. " +
                "I called him before on <PHONE_NUMBER> to verify the appointment. " +
                "He also told me that his drivers license is <US_DRIVER_LICENSE>\"\n" +
                "}";

        final RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                anonymizeRequest);
        final Request httpRequest = new Request.Builder()
                .url(anonymizeUrl)
                .post(body)
                .build();
        final Response httpResponse = new Response.Builder()
                .code(200)
                .request(httpRequest)
                .message("")
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(anonymizeResponse,
                        MediaType.parse("application/json; charset=utf-8")))
                .build();
        final Call call = mock(Call.class);
        when(call.execute()).thenReturn(httpResponse);
        final OkHttpClient mockClient = mock(OkHttpClient.class);
        // TODO: here should be the comparison of request, but it looks like guys decided not to override equals and hashcode for Request class, come up with sth
        when(mockClient.newCall(any(Request.class))).thenReturn(call);
        final AnalysisServiceImpl analysisServiceImpl = new AnalysisServiceImpl(analysisServiceConfig, mockClient);

        final String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(analysisServiceImpl).build().start());
        AnalysisServiceGrpc.AnalysisServiceBlockingStub blockingStub = AnalysisServiceGrpc.newBlockingStub(
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));
        //act
        final AnonymizationReply reply = blockingStub.anonymize(AnonymizationRequest.newBuilder().setText(textToAnonymize).build());
        //assert
        assertEquals(anonymizeResponse, reply.getText());
    }
}
