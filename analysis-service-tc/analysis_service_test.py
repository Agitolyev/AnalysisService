import json
import os

import grpc
import AnalysisService_pb2_grpc as analysis_service_grpc
import AnalysisService_pb2

ANALYSIS_SVC_ADDRESS_ENV_KEY = "ANALYSIS_SVC_ADDRESS"
ANALYSIS_SVC_DEFAULT = "localhost:8080"

if __name__ == '__main__':
    test_string = "my phone number is 057-555-2323 and my credit card is 4961-2765-5327-5913"
    expected_result = "my phone number is <PHONE_NUMBER> and my credit card is <CREDIT_CARD>"

    analysis_svc_addr = os.getenv(ANALYSIS_SVC_ADDRESS_ENV_KEY, ANALYSIS_SVC_DEFAULT)
    channel = grpc.insecure_channel(analysis_svc_addr)
    stub = analysis_service_grpc.AnalysisServiceStub(channel)
    analysisRequest = AnalysisService_pb2.AnalysisRequest()
    analysisRequest.text = test_string
    result = stub.Analyze(analysisRequest)
    json_result = json.loads(result.text)
    print(f"Expected: {expected_result}\nActual: {json_result['text']}\nIdentical: {expected_result == json_result['text']}")

