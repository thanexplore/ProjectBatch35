package tests;

import library.TestBase;
import org.testng.annotations.Test;
import pages.LoginPage;

public class ParameterizationTest extends TestBase {
    @Test(dataProvider = "LoginInfo", dataProviderClass = ParameterizationTestData.class)
    public void sampleTest(String username, String password) throws InterruptedException {
        System.out.println("Username : "+ username + " Password: "+ password);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password,true,null);
    }


}
