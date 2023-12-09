package com.epam.ta.pageobjects;

import com.epam.ta.helper.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommunitiesPage {

    @FindBy(css = ".evnt-search-filter .evnt-text-fields.evnt-search")
    private WebElement searchField;

    @FindBy(css = ".evnt-community-card")
    private List<WebElement> eventCards;

    private final WebDriver webDriver;

    public CommunitiesPage(WebDriverFactory factory) {
        this.webDriver = factory.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void searchFor(String text) {
        searchField.sendKeys(text);
    }

    public List<WebElement> getEventCards() {
        System.out.println(eventCards);
        return eventCards;
    }
}
