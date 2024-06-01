package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class EmployeePageFactory {

    WebDriver driver;
    ObjectUtilities objectUtilities;

    public EmployeePageFactory(WebDriver driver) throws InterruptedException {
        this.driver=driver;
        objectUtilities = new ObjectUtilities(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a")
    WebElement pmiSideMenu;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[1]/div[2]/input")
    WebElement idEmpFrstNam;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div[2]/input")
    WebElement idEmpLastNam;

    @FindBy(xpath="/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input")
    WebElement idEmp;

    @FindBy(xpath="//button[@type='submit']")
    WebElement idEmpSaveBtn;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div")
    WebElement employeeList;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")
    WebElement empIdSearch;

    @FindBy(xpath="//button[@type='submit']")
    WebElement empSearchButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[9]/div/button[1]")
    WebElement empDelete;

    @FindBy(linkText = "Employee List")
    WebElement lstEmp;

    @FindBy(linkText = "Add Employee")
    WebElement addEmp;

    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")
    WebElement dialogDelete;

    @FindBy(linkText = "Personal Details")
    WebElement empPerDet;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")
    WebElement noRecs;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[1]/div/div[1]/div/label/input")
    WebElement selectAllCheck;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/button/i")
    WebElement deleteAll;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[9]/div/button[2]/i")
    WebElement editEmp;

    @FindBy(xpath = "//input[@name='lastName']")
    WebElement editLastName;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[5]/button")
    WebElement editSaveBtn;


    public void navigateToEmpLaunchPage(){
        objectUtilities.waitforElement(10,pmiSideMenu);
        pmiSideMenu.click();
        objectUtilities.waitforElement(10,lstEmp);
        lstEmp.click();
        Assert.assertTrue(objectUtilities.isElementvisible(10,employeeList));
    }

    public void saveNewEmployee(String firstName, String lastName) throws InterruptedException {
        objectUtilities.waitforElement(10, addEmp);
        addEmp.click();
        objectUtilities.waitforElement(10, idEmpFrstNam);
        idEmpFrstNam.sendKeys(firstName);
        objectUtilities.waitforElement(10, idEmpLastNam);
        idEmpLastNam.sendKeys(lastName);
        objectUtilities.waitFor(20);
        objectUtilities.waitforElement(10, idEmp);
        idEmp.clear();
    //    new Actions(driver).moveToElement(idEmpSaveBtn).doubleClick().perform(); //Issues with save button requires minimum two actions to click save.
        new Actions(driver).moveToElement(idEmpSaveBtn).click().perform();
        objectUtilities.waitforElement(30,empPerDet);
    }

    public void searchEmployee(String empName){
        navigateToEmpLaunchPage();
        objectUtilities.waitFor(20);
        objectUtilities.waitforElement(10, empIdSearch);
        empIdSearch.sendKeys(empName);
        objectUtilities.waitforElement(10, empSearchButton);
        empSearchButton.click();

    }

    public boolean isEmpRecordavailable(String empName){
        searchEmployee(empName);
        return objectUtilities.isElementvisible(10,empDelete);
    }

    public boolean isEmpRecordDeleted(String empName){
        searchEmployee(empName);
        objectUtilities.waitforElement(10, noRecs);
        return noRecs.isEnabled();
    }


    public void deleteEmp(){
        objectUtilities.waitforElement(10, empDelete);
        empDelete.click();
        objectUtilities.waitforElement(10,dialogDelete);
        dialogDelete.click();
    }

    public void editEmployee(String empName, String lastName){
        searchEmployee(empName);
        objectUtilities.waitforElement(10,editEmp);
        editEmp.click();
        objectUtilities.waitforElement(30,empPerDet);
        objectUtilities.waitforElement(10, editLastName);
        editLastName.clear();
        new Actions(driver).moveToElement(editLastName).doubleClick().perform();
        editLastName.sendKeys(lastName);
        objectUtilities.waitforElement(10, editSaveBtn);
        editSaveBtn.click();
//        new Actions(driver).moveToElement(editSaveBtn).click().perform();
        objectUtilities.waitFor(20);

    }
}
