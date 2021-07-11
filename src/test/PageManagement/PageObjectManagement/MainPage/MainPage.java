package PageObjectManagement.MainPage;

import CoreManagement.CommonPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MainPage extends CommonPage {

    public MainPage(RemoteWebDriver driver) {
        super(driver);
    }

    // Action
    public void typeIntoTextBox(String textBoxType, String value) {
        WebElement ele = waitElementDisplayByXpath("");
        typeElement(ele, value);
    }

    // Verification


}
