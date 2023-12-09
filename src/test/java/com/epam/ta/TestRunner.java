package com.epam.ta;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",
    glue = {"com.epam.ta"},
    plugin = {"pretty"}
)
public class CucumberTestRunner {
}
