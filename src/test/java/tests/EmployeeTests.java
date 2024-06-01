package tests;


import library.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class EmployeeTests extends TestBase {
@Test(priority = 1)
public void addNewEmployee() throws InterruptedException {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("Admin", "admin123", true, null);
    EmployeePageFactory employeePageFactory = new EmployeePageFactory(driver);
    employeePageFactory.navigateToEmpLaunchPage();
    employeePageFactory.saveNewEmployee("Unmesh","Damodaran");
    Assert.assertTrue(employeePageFactory.isEmpRecordavailable("Unmesh Damodaran"));
}

@Test(priority = 2)
public void editEmpolyee() throws InterruptedException {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("Admin", "admin123", true, null);
    EmployeePageFactory employeePageFactory = new EmployeePageFactory(driver);
    employeePageFactory.navigateToEmpLaunchPage();
    employeePageFactory.editEmployee("Unmesh Damodaran","D");
}
@Test(priority = 3)
    public void deleteEmployee() throws InterruptedException{
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("Admin", "admin123", true, null);
    EmployeePageFactory employeePageFactory = new EmployeePageFactory(driver);
    employeePageFactory.searchEmployee("Unmesh Damodaran");
    employeePageFactory.deleteEmp();
    employeePageFactory.navigateToEmpLaunchPage();
    Assert.assertTrue(employeePageFactory.isEmpRecordDeleted("Unmesh Damodaran"));
}
}

