package tests;

import library.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CandidatePage;
import pages.HeaderPage;
import pages.LoginPage;
import pages.MenuOptions;

public class CandidateTests extends TestBase {

    @Test(priority = 1)
    public void search() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CANDIDATES);
        CandidatePage candidatePage = new CandidatePage(driver);
        boolean search = candidatePage.search("Associate IT Manager", "Odis Adalwin", "Job Offered", "b","Bhavya sree Dachu","2024-06-02");
        Assert.assertTrue(search,"Search Failed");
    }

    @Test
    public void add() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CANDIDATES);
        CandidatePage candidatePage = new CandidatePage(driver);
        boolean added = candidatePage.add("Deniel","Jackson","Java Developer","jackson@icloud.com","2261234765","Interview for Java Developer","o","Odis  Adalwin","2024-07-02");
        Assert.assertTrue(added, "Candidate has not been successfully added");
    }

    @Test(priority = 2)
    public void edit() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CANDIDATES);
        CandidatePage candidatePage = new CandidatePage(driver);
        boolean edit = candidatePage.edit();
        Assert.assertTrue(edit,"Candidate has not been successfully edited");
    }

    @Test(priority = 3)
    public void delete(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123", true, null);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectMenu(MenuOptions.CANDIDATES);
        CandidatePage candidatePage = new CandidatePage(driver);
        boolean deleted = candidatePage.delete();
        Assert.assertTrue(deleted,"Candidate has not been deleted");
    }
}