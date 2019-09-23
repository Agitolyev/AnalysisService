package com.github.agitolyev.configs;

public class AppConfig {

    private static final String PRESIDIO_API_SVC_ADDRESS_DEFAULT = "";

    private final String presidioApiServiceAddr;
    private final String projectName;

    public AppConfig(String presidioApiServiceAddr, String projectName) {
        this.presidioApiServiceAddr = presidioApiServiceAddr;
        this.projectName = projectName;
    }


}
