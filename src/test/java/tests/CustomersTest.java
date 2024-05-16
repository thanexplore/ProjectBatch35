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
        Assert.assertTrue(customersPage.match);
        System.out.println("\n");
        System.out.println("Added successfully");
        System.out.println("\n");

    }

    @Test
    public void deleteSingleCustomer() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        CustomersPage customersPage = new CustomersPage(driver,null);
        customersPage.deleteCustomer("Amazon");
        Assert.assertEquals(customersPage.dlt,"Successfully Deleted", "Unsuccessful deletion");
        Assert.assertFalse(customersPage.dltSingleMatch,"Not deleted");
        System.out.println("Amazon is deleted successfully");
        System.out.println("\n");
    }

    @Test
    public void editCustomerDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        CustomersPage customersPage = new CustomersPage(driver,null);
        customersPage.saveNewCustomer("Amazon");
        customersPage.editCustomer("Amazon","Shopping site");
        Assert.assertEquals(customersPage.updt,"Successfully Updated", "Updating unsuccessful");
        Assert.assertTrue(customersPage.editMatch,"Not edited");
        System.out.println("Successfully updated");
        System.out.println("\n");
    }

    @Test
    public void deleteMultipleCustomerDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        String[] inputTexts ={"Amazon","Flipkart"};
        CustomersPage customersPage = new CustomersPage(driver,null);
        customersPage.deleteMultipleCustomers(inputTexts);
        Assert.assertEquals(customersPage.dlt,"Successfully Deleted", "Unsuccessful deletion");
        Assert.assertEquals(customersPage.matchCount,0,"Match found- Unsuccessful deletion");
        System.out.println("Given customers are deleted successfully");
        System.out.println("\n");
    }
}
