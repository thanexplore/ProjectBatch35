package pages;

import library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;

public class PayGradePage extends PageBase {

    public PayGradePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public String adminBtn = "(//span[contains(@class,'oxd-text oxd-text--span oxd')])[1]";
    public String jobBtn   = "(//span[contains(@class,'oxd-topbar-body-nav-tab-item')])[2]";
    public String payGradeBtn = "//a[text()='Pay Grades']";

    public String payGradeAddBtn  = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']";
    public String payGradeName    = "div[class='oxd-form-row'] input[class*='oxd-input']";
    public String payGradeSaveBtn = "//button[@type='submit']";
    public String payGradeEditBtn = "(//i[contains(@class,'pencil')]) [1]";
    public String txtNameField    = "//div[@class='oxd-input-group__label-wrapper']//following::input[1]";
    public String payGradeDelBtn  ="(//i[contains(@class,' bi-trash')])[1]";
    public String payGradeTable   = "oxd-table-body";
    public String confDelBtn      = "//div[@class='orangehrm-modal-footer'] /button[2]";
    public String payGradeCancel  = "//div[@class='oxd-form-actions'] /button[1]";
    public String editSaveBtn     = "button[class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']";
    public String lblExistsMsg    = "//div[@class='oxd-form-row'] /div";
    private final String payGradeLst     = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
     @FindBy(xpath = payGradeLst)
     public List<WebElement> listPayGrades;

    public  void payGradePageNav(){

        click(By.xpath(adminBtn));
        click(By.xpath(jobBtn));
        click(By.xpath(payGradeBtn));
    }

    public  void addNewPayGrade(String newPayGradeName){

        click(By.xpath(payGradeAddBtn));
        setText(By.cssSelector(payGradeName),newPayGradeName);
        if (getText(By.xpath(lblExistsMsg)).contains("Already exists")){
            boolean is_Visible = getText(By.xpath(lblExistsMsg)).contains("Already exists");
            Assert.assertTrue(is_Visible);
            click(By.xpath(payGradeCancel));
        } else {
            click(By.xpath(payGradeSaveBtn));
            sleep(2000);
            click(By.xpath(payGradeCancel));
        }
        isElementVisible(By.cssSelector(payGradeTable));

        for (WebElement payGradeLst : listPayGrades) {
            String txtPayGrade = payGradeLst.getText();
            System.out.println(txtPayGrade);

        }
        Boolean match = listPayGrades.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(newPayGradeName));
        Assert.assertTrue(match);


    }

    public void editPayGrade(){
        click(By.xpath(payGradeEditBtn));
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(txtNameField)));
        WebElement textField = driver.findElement(By.xpath(txtNameField));
        textField.click();
        textField.sendKeys("-sample");
        click(By.cssSelector(editSaveBtn));
        sleep(1000);
        click(By.xpath(payGradeCancel));
        isElementVisible(By.cssSelector(payGradeTable));


    }

    public void delPayGrade(){
        click(By.xpath(payGradeDelBtn));
        click(By.xpath(confDelBtn));
        sleep(1000);
        isElementVisible(By.cssSelector(payGradeTable));


    }


}
