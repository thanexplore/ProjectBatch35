package tests;

import library.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Employment_Status;
import pages.HeaderPage;
import pages.LoginPage;
import pages.MenuOptions;

import java.util.UUID;

import static org.openqa.selenium.devtools.v119.log.Log.clear;

public class Employment_StatusTests extends TestBase {
    @Test
    public void AddEmpStatus() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.EMPLOYMENT_STATUS);
        Employment_Status EmpStatus= new Employment_Status(driver);
        Thread.sleep(3000);
        EmpStatus.saveNewES("Marketing Manager");
        Assert.assertTrue(EmpStatus.match);
    }

    @Test(priority = 1)
    public void EditEmpStatus() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.EMPLOYMENT_STATUS);
        Employment_Status EditStatus = new Employment_Status(driver);
        EditStatus.EditEmpStatus("Marketing Manager", "Operation Manager");
        Assert.assertTrue(EditStatus.match);

    }
    @Test(priority = 2)
    public void DeleteEmpStatus(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.EMPLOYMENT_STATUS);
        Employment_Status DeleteStatus = new Employment_Status(driver);
        DeleteStatus.deleteEmpStatus("Operation Manager");
        Assert.assertFalse(DeleteStatus.match);

    }
    @Test(priority=3)
    public void DeleteMultiEmpStats(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.EMPLOYMENT_STATUS);
        Employment_Status DeleteMultiStat = new Employment_Status(driver);
        DeleteMultiStat.deleteRandomMultipleEmpStatus();

    }
    @Test(priority = 4)
    public void CountOfRecords(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.EMPLOYMENT_STATUS);
        Employment_Status CountOfRec = new Employment_Status(driver);
        String text = CountOfRec.countOfRecord();
        System.out.println(text);
    }

}

