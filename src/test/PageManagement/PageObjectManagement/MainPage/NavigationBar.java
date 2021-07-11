package PageObjectManagement.MainPage;

import CoreManagement.CommonPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationBar extends CommonPage {

    private String navSearchBoxXpath = "//nav[@id='nav-website']//ul[@id='first-level-nav']//input[@type='text']";

    public NavigationBar(RemoteWebDriver driver) {
        super(driver);
    }

    // Action
    public void typeIntoSearchBox(String value) {
        WebElement ele = waitElementDisplayByXpath(navSearchBoxXpath);
        typeElement(ele, value);
    }

    public void typeIntoSearchBoxThenPressHotKeys(String value, String hotKey) {
        logger.info("== I type text into search box then press enter");
        WebElement ele = waitElementDisplayByXpath(navSearchBoxXpath);
        typeElement(ele, value);
        pressHotkeys(ele, hotKey);
    }

    // Verification

}
