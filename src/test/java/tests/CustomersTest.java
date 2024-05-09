package tests;

import library.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;




public class CustomersTest extends TestBase {
    @Test
    public void addNewCustomer() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        CustomersPage customersPage = new CustomersPage(driver,null);
        customersPage.saveNewCustomer("Amazon");
        customersPage.saveNewCustomer("TATA Elxsi");
        Assert.assertTrue(customersPage.match);
        System.out.println("\n");
        System.out.println("Amazon and TATA Elxsi are added successfully");
        System.out.println("\n");

    }
    @Test(priority = 1)
    public void alreadyExistingCustomer() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        CustomersPage customersPage = new CustomersPage(driver,null);
        customersPage.saveNewCustomer("TATA Elxsi");
        Assert.assertTrue(customersPage.match);
        System.out.println("\n");
        System.out.println("TATA Elxsi is already existing");
        System.out.println("\n");
    }

    @Test(priority = 2)
    public void deleteSingleCustomer() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        CustomersPage customersPage = new CustomersPage(driver,null);
        customersPage.deleteCustomer();
        Assert.assertEquals(customersPage.dlt,"Successfully Deleted", "Unsuccessful deletion");
        System.out.println("Amazon is deleted successfully");
        System.out.println("\n");
    }

    @Test(priority = 3)
    public void editCustomerDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        CustomersPage customersPage = new CustomersPage(driver,null);
        customersPage.saveNewCustomer("Amazon");
        customersPage.editCustomer("Shopping site");
        Assert.assertEquals(customersPage.updt,"Successfully Updated", "Updating unsuccessful");
        System.out.println("Successfully updated");
        System.out.println("\n");
    }

    @Test(priority = 4)
    public void deleteMultipleCustomerDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        CustomersPage customersPage = new CustomersPage(driver,null);
        customersPage.deleteMultipleCustomers();
        Assert.assertEquals(customersPage.dlt,"Successfully Deleted", "Unsuccessful deletion");
        System.out.println("Selected customers are deleted successfully");
        System.out.println("\n");
    }
}
