package com.epam.ta.helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public class WebDriverFactory {

    public static final Dimension BROWSER_SCREEN_SIZE = new Dimension(1920, 1080);
    @Value("${headless:false}")
    private Boolean headless;
    @Value("${browser:chrome}")
    private String browserName;

    private WebDriver webDriver;

    public WebDriver getDriver() {
        if (Objects.isNull(webDriver)) {
            webDriver = setUpWebDriver();
        }
        return webDriver;
    }

    private WebDriver setUpWebDriver() {
        WebDriver driver;
        switch (browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(
                    new ChromeOptions().setHeadless(headless).addArguments("--remote-allow-origins=*"));
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(new FirefoxOptions().setHeadless(headless));
            }
            default -> throw new RuntimeException(browserName + " is not a supported browser");
        }
        driver.manage().window().setSize(BROWSER_SCREEN_SIZE);
        return driver;
    }

    public void tearDown() {
        if (Objects.nonNull(webDriver)) {
            webDriver.quit();
            webDriver = null;
        }
    }

}
