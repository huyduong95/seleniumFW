package CoreManagement;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static Common.CommonAction.getPropertyFromPropertiesFile;

public class AbstractPage implements IPage {

    private RemoteWebDriver driver;
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    public AbstractPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    @Override
    public RemoteWebDriver getDriver() {
        return driver;
    }

    // Driver Action
    private static int timeoutInSeconds = 60;

    public void navigateToUrl(String url) {
        getDriver().get(url);
    }

    public WebElement findElementByXpath(String xpath) {
        logger.info("Find element with xpath: " + xpath);
        WebElement ele = getDriver().findElement(By.xpath(xpath));
        return ele;
    }

    public WebElement waitElementDisplayByXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
        logger.info("Wait element by xpath: " + xpath);
        WebElement ele = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return getDriver().findElement(By.xpath(xpath));
            }
        });
        return ele;
    }

    public boolean waitUntilElementDisplayByXpath(String xpath) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
        Boolean result;
        try {
            result = wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(@Nullable WebDriver webDriver) {
                    try {
                        WebElement ele = getDriver().findElement(By.xpath(xpath));
                        if (ele != null) {
                            return true;
                        }
                    } catch (Exception ex) {
                        return false;
                    }
                    return false;
                }
            });
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public boolean waitUntilElementDisplayByXpath(String xpath, int timeOut) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
        Boolean result;
        try {
            result = wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(@Nullable WebDriver webDriver) {
                    try {
                        WebElement ele = getDriver().findElement(By.xpath(xpath));
                        if (ele != null) {
                            return true;
                        }
                    } catch (Exception ex) {
                        return false;
                    }
                    return false;
                }
            });
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public boolean waitUntilElementNotDisplayByXpath(String xpath) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
        Boolean result = wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(@Nullable WebDriver webDriver) {
                try {
                    WebElement ele = getDriver().findElement(By.xpath(xpath));
                    if (ele != null) {
                        return false;
                    }
                } catch (Exception ex) {
                    return true;
                }
                return false;
            }
        });
        return result;
    }

    public void typeElement(WebElement ele, String value) {
        if (ele.getTagName().equalsIgnoreCase("input")) {
            ele.sendKeys(value);
        }
    }

    public void pressHotkeys(WebElement ele, String hotkey) {
        if (hotkey.equalsIgnoreCase("Enter")) {
            ele.sendKeys(Keys.ENTER);
        }
    }

    // Get Function
    public String getUrlByPage(String pageName) {
        String url = "";
        switch (pageName.toLowerCase()) {
            case "main":
                url = getPropertyFromPropertiesFile("config", "MAIN_URL");
        }
        return url;
    }

    // Action
    public void navigateTo(String url) {
        navigateToUrl(url);
    }



}
