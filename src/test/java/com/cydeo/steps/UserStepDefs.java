package com.cydeo.steps;

import com.cydeo.pages.UserPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class UserStepDefs {

    UserPage userPage = new UserPage();
    String email;
    String expectedStatus;

    @When("the user clicks Edit User button")
    public void the_user_clicks_edit_user_button() {
        BrowserUtil.waitFor(2);
        userPage.editUser.click();
    }
    @When("the user changes user status {string} to {string}")
    public void the_user_changes_user_status_to(String active, String inactive) {
        BrowserUtil.waitFor(2);


        BrowserUtil.selectByVisibleText(userPage.statusDropdown,inactive);

        BrowserUtil.waitFor(1);

        email = userPage.email.getAttribute("value");
        System.out.println("email = " + email);
        expectedStatus = inactive;


        BrowserUtil.waitFor(2);



    }
    @When("the user clicks save changes button")
    public void the_user_clicks_save_changes_button() {
        userPage.saveChanges.click();
        System.out.println("--------> The User's " + email + " is deactivated");
    }
    @Then("{string} message should appear")
    public void message_should_appear(String expectedMsg) {
        BrowserUtil.waitForVisibility(userPage.toastMessage, 10);
        String actualMsg = userPage.toastMessage.getText();
        Assert.assertEquals(expectedMsg,actualMsg);
    }
    @Then("the users should see same status for related user in database")
    public void the_users_should_see_same_status_for_related_user_in_database() {
        DB_Util.runQuery("select status from users\n" +
                "where email='" + email + "'");
        String actualStatus = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedStatus, actualStatus);

    }
    @Then("the user changes current user status {string} to {string}")
    public void the_user_changes_current_user_status_to(String inactive, String active) {
        BrowserUtil.waitFor(1);
        BrowserUtil.selectByVisibleText(userPage.userStatusDropdown, inactive);
        BrowserUtil.waitFor(1);
        BrowserUtil.selectByVisibleText(userPage.numberOfUsersDropdown, "500");
        BrowserUtil.waitFor(1);
        userPage.editUser(email).click();
        BrowserUtil.selectByVisibleText(userPage.userStatusDropdown, active);
        BrowserUtil.waitFor(1);
        userPage.saveChanges.click();
        System.out.println("--------> The User's " + email + " is activated");
    }


}
