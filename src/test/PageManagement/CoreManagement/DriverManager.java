package CoreManagement;

import io.cucumber.java.vi.Cho;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static Common.CommonAction.getConfigData;
import static Common.CommonAction.startSeleniumNode;

public class DriverManager {

    private static RemoteWebDriver driver;
    private static ArrayList<RemoteWebDriver> lstDriver = new ArrayList<>();

    private static void launchLocalBrowser() {
        String browser = getConfigData("BROWSER");
        String browserVersion = getConfigData("VERSION");
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().clearPreferences();
            WebDriverManager.chromedriver().version(browserVersion).setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().clearPreferences();
            WebDriverManager.firefoxdriver().version(browserVersion).setup();
            driver = new FirefoxDriver();
        }
    }

    public static void launchRemoteBrowser() throws IOException {
        String browser = getConfigData("BROWSER");
        String browserVersion = getConfigData("VERSION");
        String remoteHubUrl = getConfigData("REMOTE_HUB_URL");
        String Node = String.format("%s/wd/hub", remoteHubUrl);
        startSeleniumNode();
        DesiredCapabilities cap = null;
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().clearPreferences();
            WebDriverManager.chromedriver().version(browserVersion).setup();
            cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().clearPreferences();
            WebDriverManager.firefoxdriver().version(browserVersion).setup();
            cap = DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");
        }
        driver = new RemoteWebDriver(new URL(Node), cap);
    }

    public static void initDriver() throws IOException {
        String runningMachine = getConfigData("TYPE");
        if (runningMachine.equalsIgnoreCase("local")) {
            launchLocalBrowser();
        } else if (runningMachine.equalsIgnoreCase("remote")) {
            launchRemoteBrowser();
        }
        driver.manage().window().maximize();
        lstDriver.add(driver);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitAllDriver() {
        for (RemoteWebDriver driver : lstDriver) {
            driver.close();
            driver.quit();
        }
    }
}
