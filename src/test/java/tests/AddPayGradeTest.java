package tests;

import library.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.LoginPage;
import pages.MenuOptions;
import pages.PayGradePage;

import java.util.UUID;

public class AddPayGradeTest extends TestBase {
    @Test
    public void addPayGrade(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin","admin123", true, null);
        PayGradePage payGradePage = new PayGradePage(driver);
        payGradePage.payGradePageNav();
        payGradePage.addNewPayGrade("Grade 6");



    }

}
