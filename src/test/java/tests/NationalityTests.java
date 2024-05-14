package tests;

import library.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.UUID;

public class NationalityTests extends TestBase {
    @Test
    public void addNewNationality() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        NationalityPage nationalityPage=new NationalityPage(driver);
        Boolean match=nationalityPage.saveNewNationality("A");
        Assert.assertTrue(match);
        System.out.println("\n");
        System.out.println("nationalityName is added successfully");
        System.out.println("\n");

    }

    @Test
    public void editNationality() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        NationalityPage nationalityPage=new NationalityPage(driver);
        Assert.assertTrue(nationalityPage.editNationality("A","AA"));
        System.out.println("\n");
        System.out.println("nationality updated successfully");
        System.out.println("\n");

    }

    @Test
    public void deleteNationality() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        NationalityPage nationalityPage=new NationalityPage(driver);
        Boolean match=nationalityPage.deleteNationality("AA");
        Assert.assertFalse(match);
        System.out.println("\n");
        System.out.println("nationalityName deleted successfully");
        System.out.println("\n");
    }

    @Test
    public void addExistingNationality() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        NationalityPage nationalityPage=new NationalityPage(driver);
        Assert.assertTrue(nationalityPage.addExistingNationality("A"));
        System.out.println("\n");
        System.out.println("Nationality already exists");
        System.out.println("\n");

    }

    @Test
    public void deleteMultipleNationality() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.NATIONALITIES);
        NationalityPage nationalityPage=new NationalityPage(driver);
        Boolean match=nationalityPage.deleteMultipleNationality("A","AA");
        Assert.assertTrue(match);
        System.out.println("\n");
        System.out.println("nationalityNames deleted successfully");
        System.out.println("\n");
    }

}
