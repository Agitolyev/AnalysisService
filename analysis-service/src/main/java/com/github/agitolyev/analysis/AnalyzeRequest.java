package com.github.agitolyev.analysis;

public class AnalyzeRequest {

    private static final String REQUEST_TEMPLATE = "{\"text\": \"%s\", \"analyzeTemplate\":{\"allFields\":true}}";

    private String text;

    public AnalyzeRequest(final String text) {
        this.text = text;
    }

    public String analyzeRequestJson() {
        return String.format(REQUEST_TEMPLATE, text);
    }
}
