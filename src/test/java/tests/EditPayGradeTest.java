package tests;

import library.TestBase;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.LoginPage;
import pages.MenuOptions;
import pages.PayGradePage;

public class EditPayGradeTest extends TestBase {
    @Test
    public void editPayGradeTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin","admin123",true,null);
        PayGradePage payGradePage = new PayGradePage(driver);
        payGradePage.payGradePageNav();
        payGradePage.editPayGrade();
        payGradePage.verifyEdit();

    }

}
