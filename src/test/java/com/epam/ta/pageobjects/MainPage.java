package com.epam.ta.pageobjects;

import com.epam.ta.helper.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainPage {

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;

    @FindBy(css = ".evnt-promo-card")
    private List<WebElement> eventCards;

    @FindBy(css = ".evnt-tab-content.active")
    private WebElement activeTab;

    @FindBy(css = ".evnt-tab-item")
    private List<WebElement> allTabs;

    private final WebDriver driver;

    public MainPage(WebDriverFactory webDriverFactory) {
        this.driver = webDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }

    public List<WebElement> getEventCards() {
        return eventCards;
    }

    public WebElement getActiveTab() {
        return activeTab;
    }

    public List<WebElement> getAllTabs() {
        return allTabs;
    }
}
