package tests;


import library.TestBase;
import org.testng.annotations.Test;
import pages.EducationPage;
import pages.HeaderPage;
import pages.LoginPage;
import pages.MenuOptions;

import java.util.UUID;

public class EducationTests extends TestBase {
@Test
public void addNewEducation() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("Admin", "admin123", true, null);
    HeaderPage headerPage = new HeaderPage(driver);
    headerPage.selectMenu(MenuOptions.EDUCATION);
    EducationPage educationPage = new EducationPage(driver);
    educationPage.saveNewEducation("Level1");
   String uuid = UUID.randomUUID().toString();
    educationPage.saveNewEducation("level1" + uuid);

}
}

