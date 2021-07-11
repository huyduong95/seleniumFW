package PageObjectManagement;

import CoreManagement.DriverManager;
import CoreManagement.IPage;
import PageObjectManagement.ResultPage.ResultPage;
import PageObjectManagement.MainPage.MainPage;
import PageObjectManagement.MainPage.NavigationBar;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PageFactory {

    private static MainPage mainPage;
    private static NavigationBar navigationBar;
    private static ResultPage resultPage;

    public static IPage getInstance(IPage iPage, String className) {
        try {
            if (iPage == null || !iPage.getDriver().equals(DriverManager.getDriver())) {
                iPage = (IPage) Class.forName(className).getConstructor(RemoteWebDriver.class)
                        .newInstance(DriverManager.getDriver());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iPage;
    }

    public static MainPage getMainPage() {
        mainPage = (MainPage) getInstance(mainPage, MainPage.class.getName());
        return mainPage;
    }

    public static NavigationBar getNavigationBar() {
        navigationBar = (NavigationBar) getInstance(navigationBar, NavigationBar.class.getName());
        return navigationBar;
    }

    public static ResultPage getResultPage() {
        resultPage = (ResultPage) getInstance(resultPage, ResultPage.class.getName());
        return resultPage;
    }
}
