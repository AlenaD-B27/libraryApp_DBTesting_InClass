package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.sql.ResultSet;
import java.util.List;


public class DashboardStepDefs
{
    String actualUserNumbers;
    String actualBookNumbers;
    String actualBorrowedBookNumbers;
    LoginPage loginPage=new LoginPage();
    DashBoardPage dashBoardPage=new DashBoardPage();


    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginPage.login(user);
         BrowserUtil.waitFor(4);
    }
    @When("user gets all information from modules")
    public void user_gets_all_information_from_modules() {

        actualUserNumbers = dashBoardPage.usersNumber.getText();
        System.out.println("actualUserNumbers = " + actualUserNumbers);
        actualBookNumbers = dashBoardPage.booksNumber.getText();
        System.out.println("actualBookNumbers = " + actualBookNumbers);
        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);

    }

    @Then("the informations should be same with database")
    public void the_informations_should_be_same_with_database() {
        // USERS

       // 1. Make connection
    //    DB_Util.createConnection();

        // 2. Run query
        DB_Util.runQuery("select count(*) from users");

        // 3. Store Data
        String expectedUsers = DB_Util.getFirstRowFirstColumn();

        // 4. Compare and Close connection

        Assert.assertEquals(expectedUsers, actualUserNumbers);
    //    DB_Util.destroy();

        // BOOKS

        DB_Util.runQuery("select count(*) from books");
        String expectedBooks = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBooks, actualBookNumbers);



        // BORROWED BOOKS

        DB_Util.runQuery("select count(*) from book_borrow\n" +
                "where is_returned = 0");
        String expectedBooksBorrowed = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBooksBorrowed, actualBorrowedBookNumbers);
      //  DB_Util.destroy();
    }





}
