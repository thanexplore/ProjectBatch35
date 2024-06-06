package tests;

import library.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PayGradePage;

public class DelPayGradeTest extends TestBase {
    @Test
    public void delPayGradeTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin","admin123",true,null);
        PayGradePage payGradePage = new PayGradePage(driver);
        payGradePage.payGradePageNav();
        Assert.assertFalse(payGradePage.delPayGrade("Grade 6"));

    }

}
