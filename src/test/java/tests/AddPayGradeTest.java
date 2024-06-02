package tests;

import library.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PayGradePage;

public class AddPayGradeTest extends TestBase {
    @Test
    public void addPayGradeTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin","admin123",true,null);
        PayGradePage payGradePage = new PayGradePage(driver);
        payGradePage.payGradePageNav();
        Assert.assertTrue(payGradePage.addNewPayGrade("Grade 6"));
        //payGradePage.addNewPayGrade("Grade 6");
    }

}
