#!/python3
import socket
import os
import time

ANALYSIS_SVC_ADDRESS_ENV_KEY = "ANALYSIS_SVC_ADDRESS"
ANALYSIS_SVC_DEFAULT = "localhost:8080"

if __name__ == '__main__':
    analysis_svc_host, analysis_svc_port = os.getenv(ANALYSIS_SVC_ADDRESS_ENV_KEY, ANALYSIS_SVC_DEFAULT).split(":")

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    while True:
        if sock.connect_ex((analysis_svc_host, int(analysis_svc_port))) == 0:
            print("Analysis service is up")
            break
        else:
            # TODO: add analysis service polling as rediness probe
            time.sleep(40)
    sock.close()
    os.system('python3 /analysis-service-tc/analysis_service_test.py')
