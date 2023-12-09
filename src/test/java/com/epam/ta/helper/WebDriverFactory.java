package com.epam.ta.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

public class WebDriverFactory {

    private WebDriver webDriver;

    public WebDriver getDriver() {
        if (Objects.isNull(webDriver)) {
            webDriver = setUpWebDriver();
        }
        return webDriver;
    }

    private WebDriver setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
    }

    public void tearDown() {
        if (Objects.nonNull(webDriver)) {
            webDriver.quit();
            webDriver = null;
        }
    }

}
