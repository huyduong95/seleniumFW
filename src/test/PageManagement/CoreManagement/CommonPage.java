package CoreManagement;

import org.openqa.selenium.remote.RemoteWebDriver;

public class CommonPage extends AbstractPage{
    private String loadingIconXpath = "//div[@class='owm-loader']";

    public CommonPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void waitForPageLoadCompleted() {
        waitUntilElementDisplayByXpath(loadingIconXpath);
        waitUntilElementNotDisplayByXpath(loadingIconXpath);
    }
}
