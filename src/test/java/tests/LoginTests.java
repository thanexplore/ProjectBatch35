package tests;


import library.TestBase;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends TestBase {
//https://github.com/thanexplore/ProjectBatch35
    @Test
    public void loginTestWithValidCredentials()  {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123",true,null);


    }

    @Test
    public void loginTestWithInValidCredentials()  {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("sumo", "blahblah", false,"Invalid credentials");
    }

    @Test
    public void loginTestWithSpaces(){
        LoginPage loginPage = new LoginPage(driver);
        //loginPage.login();

    }




}
