Feature: Dashboard Page
@wip
  Scenario: Dashboard data verification
    Given the user logged in as "librarian"
    When user gets all information from modules
    Then the informations should be same with database