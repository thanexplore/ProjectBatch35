package tests;


import library.TestBase;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests2 extends TestBase {

    @Test
    public void loginTestWithValidCredentials()  {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin123",true,null);
    }

    @Test
    public void loginTestWithInValidCredentials()  {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("sumo", "blahblah", false,"Invalid credentials");
    }




}
