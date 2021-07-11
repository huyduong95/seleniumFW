package PageDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

import static CoreManagement.DriverManager.getDriver;
import static PageObjectManagement.PageFactory.getMainPage;

public class AbstractPageDefinition {

    protected Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @And("^I open url: (.*)$")
    public void openURl(String url) throws Throwable {
        getMainPage().navigateTo(url);
    }

    @And("^I navigate to (Main) page$")
    public void navigateToPage(String page) throws Throwable {
        String url = getMainPage().getUrlByPage(page);
        getMainPage().navigateTo(url);
    }

    @And("^I wait for page load completed$")
    public void waitForPageLoad() {
        getMainPage().waitForPageLoadCompleted();
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException, IOException, IllegalMonitorStateException {
        if (scenario.isFailed()) {
            scenario.attach(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES), "image/png",
                    scenario.getName() + " - Failed Screen:");
        }
        logger.info("===> Finished TC: " + scenario.getName());
    }



}
