package com.github.agitolyev.analysis;

public class AnonymizeRequest {

    private static final String REQUEST_TEMPLATE = "{\"text\": \"%s\", \"analyzeTemplate\":{\"allFields\":true}, \"anonymizeTemplate\":{\"allFields\":true}}";

    private String text;

    public AnonymizeRequest(final String text) {
        this.text = text;
    }

    public String anonymizeRequestJson() {
        return String.format(REQUEST_TEMPLATE, text);
    }
}
