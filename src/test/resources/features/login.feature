@login @db
Feature: Login Functionality
  Scenario Outline: Login with valid credentials
    Given the user logged in  "<username>" and "<password>"
    When user gets username  from user fields
    Then the username should be same with database

    Examples:

    |username            |password|
    |librarian1@library   | qU9mrvur|
    |librarian2@library  |  uYrhHmmj|
    |librarian3@library |   DFvU4b1i|
    |librarian4@library   | 3lwxJ1Kk|
    |librarian5@library  |  hj65YBiE|
    |librarian6@library |   QaG7mkXA|
    |librarian7@library    |C5WUiPUP|
    |librarian8@library   | UECJkTnl|
    |librarian9@library  |  MSx8u9n4|
    |librarian10@library|    ZIkOcbCa|