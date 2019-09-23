package com.github.agitolyev.analysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalysisServiceConfig {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisServiceConfig.class);

    private static final String PRESIDIO_API_SVC_ADDRESS_ENV_KEY = "PRESIDIO_API_SVC_ADDRESS";
    private static final String PRESIDIO_PROJECT_NAME_DEFAULT_ENV_KEY = "PRESIDIO_PROJECT_NAME";

    private static final String PRESIDIO_API_SVC_ADDRESS_DEFAULT = "presidio-api:8080";
    private static final String PRESIDIO_PROJECT_NAME_DEFAULT = "default";

    private final String presidioApiServiceAddr;
    private final String projectName;

    public AnalysisServiceConfig(final String presidioApiServiceAddr, final String projectName) {
        if (presidioApiServiceAddr == null) {
            logger.info("Received empty presidio API service address, going to set default: {}", PRESIDIO_API_SVC_ADDRESS_DEFAULT);
            this.presidioApiServiceAddr = PRESIDIO_API_SVC_ADDRESS_DEFAULT;
        } else {
            logger.info("Setting presidio API service address to: {}", presidioApiServiceAddr);
            this.presidioApiServiceAddr = presidioApiServiceAddr;
        }
        if (projectName == null) {
            logger.info("Received empty presidio project name, going to set default: {}", PRESIDIO_PROJECT_NAME_DEFAULT);
            this.projectName = PRESIDIO_PROJECT_NAME_DEFAULT;
        } else {
            logger.info("Setting presidio project name to: {}", projectName);
            this.projectName = projectName;
        }
    }

    public static AnalysisServiceConfig fromEnv() {
        return new AnalysisServiceConfig(System.getenv(PRESIDIO_API_SVC_ADDRESS_ENV_KEY), System.getenv(PRESIDIO_PROJECT_NAME_DEFAULT_ENV_KEY));
    }

    public String getPresidioApiServiceAddr() {
        return presidioApiServiceAddr;
    }

    public String getProjectName() {
        return projectName;
    }
}
