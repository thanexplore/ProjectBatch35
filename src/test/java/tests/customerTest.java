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
    public void deleteSingleCustomer() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CUSTOMERS);
        customerPage customersPage = new customerPage(driver);
        Assert.assertTrue(customersPage.deleteCustomer("Amazon"),"Not deleted");
    }


}
