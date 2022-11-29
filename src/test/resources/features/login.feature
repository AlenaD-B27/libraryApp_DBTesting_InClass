@login @db
Feature: Login Functionality
  Scenario: Login with valid credentials
    Given the user logged in  "librarian55@library" and "67UQi3Ol"
    When user gets username  from user fields
    Then the username should be same with database