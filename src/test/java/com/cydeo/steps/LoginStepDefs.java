package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    String actualUserName;
    String userName = "librarian55@library";

    @Given("the user logged in  {string} and {string}")
    public void the_user_logged_in_and(String login, String password) {
        loginPage.login(login, password);
        BrowserUtil.waitFor(2);
    }
    @When("user gets username  from user fields")
    public void user_gets_username_from_user_fields() {
        actualUserName = dashBoardPage.accountHolderName.getText();
        System.out.println("actualUserName = " + actualUserName);
    }
    @Then("the username should be same with database")
    public void the_username_should_be_same_with_database() {
        DB_Util.runQuery("select full_name from users\n" +
                "where email = '"+ userName +"'");
        String expectedUserName = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedUserName, actualUserName);
    }

}
