package Runner;

import CoreManagement.DriverManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/TestSuite"}
        , glue = "PageDefinition"
        , plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}
        , stepNotifications = true
)

public class Runner {
    static Logger logger = Logger.getLogger(Runner.class.getSimpleName());

    @BeforeClass
    public static void createSession() throws IOException {
        DriverManager.initDriver();
        logger.info("Start The Test");

    }

    @AfterClass
    public static void killAllSession() {
        DriverManager.quitAllDriver();
        logger.info("End All Test");
    }
}
