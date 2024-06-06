package tests;

import library.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PayGradePage;

public class DelPayGradeChkBxTest extends TestBase {
    @Test
    public void delPayGradeChkBXTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin","admin123",true,null);
        PayGradePage payGradePage = new PayGradePage(driver);
        payGradePage.payGradePageNav();
        Assert.assertFalse(payGradePage.delPayGradeChkBx("Grade 6"));
    }
}
