package PageDefinition.ResultPage;

import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

import static PageObjectManagement.PageFactory.getResultPage;

public class ResultPageDefinition {

    @Then("^I should see record displayed on Result Page$")
    public void verifyRecordsDisplayed(List<Map<String, String>> lstRecord) {
        for(Map<String, String> record : lstRecord) {
            getResultPage().verifyRecordDisplayed(record);
        }
    }
}
