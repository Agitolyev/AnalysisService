FROM python:3.7-slim
COPY requirements.txt /analysis-service-tc/
RUN pip3 install -r /analysis-service-tc/requirements.txt
COPY ./*.py /analysis-service-tc/

ENTRYPOINT ["python", "/analysis-service-tc/entrypoint.py"]
