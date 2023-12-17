package com.epam.ta.stepdefs;

import com.epam.ta.helper.WebDriverFactory;
import com.epam.ta.pageobjects.CommunitiesPage;
import com.epam.ta.pageobjects.MainPage;
import com.epam.ta.pageobjects.VideosPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    @Autowired
    WebDriverFactory webDriverFactory;

    @Autowired
    MainPage mainPage;

    @Autowired
    CommunitiesPage communitiesPage;

    @Autowired
    VideosPage videosPage;

    @Given("the {string} page is opened")
    public void visitMainPage(String pageName) {
        switch (pageName) {
            case "Main" -> webDriverFactory.getDriver().get("https://wearecommunity.io");
            case "Communities" -> webDriverFactory.getDriver().get("https://wearecommunity.io/communities");
            case "Videos" -> webDriverFactory.getDriver().get("https://wearecommunity.io/videos");

            default -> throw new RuntimeException(pageName + "is not a defined page");
        }
    }

    @When("I close the cookie disclaimer")
    public void iCloseTheCookieDisclaimer() {
        mainPage.acceptCookies();
    }

    @Then("I can see {int} event cards")
    public void iCanSeeEventCards(int expectedCardCount) {
        int actualCardCount = mainPage.getEventCards().size();
        assertEquals(expectedCardCount, actualCardCount);
    }

    @And("the {string} tab is active")
    public void theTabIsActive(String tabName) {
        assertEquals(tabName, mainPage.getActiveTab().getText());
    }

    @And("there are {int} tabs")
    public void thereAreTabs(int expectedTabCount) {
        int actualTabCount = mainPage.getAllTabs().size();
        assertEquals(expectedTabCount, actualTabCount);
    }

    @When("I type {string} into the search field")
    public void iTypeInputIntoTheSearchField(String text) throws InterruptedException {
        Thread.sleep(1000);
        communitiesPage.searchFor(text);
    }



    @Then("I see {int} cards")
    public void iSeeNumberOfCardsCards(int expectedCardCount) {
        var driver = webDriverFactory.getDriver();

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(5))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class);
        try {
            wait.until(
                usedDriver -> communitiesPage.getEventCards().size() == expectedCardCount
            );
        } catch (TimeoutException e) {
            Assert.fail("Expected card count " + expectedCardCount + "did not match actual card count " +
                communitiesPage.getEventCards().size());
        }
    }
    // Videos page
    @When("I type {string} into the videosearch field")
    public void iTypeInputIntoTheVideosSearchField(String text) throws InterruptedException {
        Thread.sleep(1000);
        videosPage.searchFor(text);
    }
    @Then("I see {int} videocards")
    public void iSeeNumberOfVideosCards(int expectedCardCount) {
        var driver = webDriverFactory.getDriver();
        System.out.println("Előtte: " + videosPage.getEventCards().size());
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        try {
            wait.until(
                    usedDriver -> videosPage.getEventCards().size() == expectedCardCount
                    );
            System.out.println("Try ágban: " + videosPage.getEventCards().size());
        } catch (TimeoutException e) {
            Assert.fail("Expected card count " + expectedCardCount + " did not match actual card count " +
                    videosPage.getEventCards().size());
            System.out.println("Catch ágban: " + videosPage.getEventCards().size());
        }
    }
}
