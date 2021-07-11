package PageDefinition.MainPage;

import io.cucumber.java.en.And;

import static PageObjectManagement.PageFactory.getNavigationBar;

public class NavigationBarDefinition {

    @And("^I type (.*) into search box on Navigation Bar$")
    public void typeSearchBox(String value) {
        getNavigationBar().typeIntoSearchBox(value);
    }

    @And("^I type (.*) into search box then press (Enter) on Navigation Bar$")
    public void typeSearchBox(String value, String hotKeys) {
        getNavigationBar().typeIntoSearchBoxThenPressHotKeys(value, hotKeys);
    }
}
