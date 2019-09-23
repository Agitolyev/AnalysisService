package com.github.agitolyev;

import com.github.agitolyev.analysis.AnalysisServiceConfig;
import com.github.agitolyev.analysis.AnalysisServiceImpl;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import junit.framework.TestCase;
import okhttp3.OkHttpClient;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(new AnalysisServiceImpl(AnalysisServiceConfig.fromEnv(), new OkHttpClient())).build().start());
        AnalysisServiceGrpc.AnalysisServiceBlockingStub blockingStub = AnalysisServiceGrpc.newBlockingStub(
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));
        //act
        AnalysisReply reply = blockingStub.analyze(AnalysisRequest.newBuilder().setText("").build());
        //assert
        assertEquals("Hello some text", reply.getText());
    }
}
