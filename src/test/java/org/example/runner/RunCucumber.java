package org.example.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import io.cucumber.junit.platform.engine.Cucumber;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;


//@Suite
@Cucumber
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // le dossier des .feature
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example.steps") // package des s
public class RunCucumber {
}
