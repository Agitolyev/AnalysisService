package com.github.agitolyev.analysis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Map;

public class AnalyzeRequest {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String REQUEST_TEMPLATE = "{\"text\": \"%s\", \"analyzeTemplate\":{\"allFields\":true}}";

    private String text;

    public AnalyzeRequest(final String text) {
        this.text = text;
    }

    public String analyzeRequestJson() throws JsonProcessingException {
        PresidioRequest request = new PresidioRequest(this.text, Collections.singletonMap("allFields", true));
        return objectMapper.writeValueAsString(request);
    }

    private class PresidioRequest {

        String text;

        Map<String, Object> analyzeTemplate;

        public PresidioRequest(String text, Map<String, Object> analyzeTemplate) {
            this.text = text;
            this.analyzeTemplate = analyzeTemplate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Map<String, Object> getAnalyzeTemplate() {
            return analyzeTemplate;
        }

        public void setAnalyzeTemplate(Map<String, Object> analyzeTemplate) {
            this.analyzeTemplate = analyzeTemplate;
        }
    }
}
