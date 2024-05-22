package tests;

import library.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.LoginPage;
import pages.MenuOptions;
import pages.customerPage;

public class customerTest extends TestBase {
    @Test
    public void addNewCustomer() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        customerPage customersPage = new customerPage(driver);
        Assert.assertTrue(customersPage.saveNewCustomer("Amazon"),"Not successfully saved");
    }

    @Test
    public void deleteSingleCustomer() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        customerPage customersPage = new customerPage(driver);
        Assert.assertTrue(customersPage.deleteCustomer("Amazon"),"Not deleted");
    }
    @Test
    public void editCustomerDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        customerPage customersPage = new customerPage(driver);
        Assert.assertTrue(customersPage.editCustomer("Amazon", "Shopping site"), "Not edited");
    }
    @Test
    public void deleteMultipleCustomerDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        String[] inputTexts ={"Flipkart","Amazon"};
        customerPage customersPage = new customerPage(driver);
        Assert.assertTrue(customersPage.deleteMultipleCustomers(inputTexts),"Unsuccessfull deletion");
    }

}
