@FWTest @SearchWeatherV1
Feature: Search Weather

  Background:
    And I navigate to Main page
    And I wait for page load completed

  @FW-SEARCH-01
  Scenario: Search weather in Ho Chi Minh City.: FW-SEARCH-01
    And I type Thanh pho Ho Chi Minh, VN into search box then press Enter on Navigation Bar
    And I should see record displayed on Result Page
      | City name                 |
      | Thanh pho Ho Chi Minh, VN |

  @FW-SEARCH-02
  Scenario: Search weather in London City.: FW-SEARCH-02
    And I type London into search box then press Enter on Navigation Bar
    And I should see record displayed on Result Page
      | City name  |
      | London, GB |
      | London, CA |