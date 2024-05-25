package pages;

import library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;

public class PayGradePage extends PageBase {

    public PayGradePage(WebDriver driver){
        super(driver);

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
    public String payGradeCancel = "//div[@class='oxd-form-actions'] /button[1]";
    public String editSaveBtn     = "button[class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']";


    public  void payGradePageNav(){
        click(By.xpath(adminBtn));
        click(By.xpath(jobBtn));
        click(By.xpath(payGradeBtn));
    }

    public  void addNewPayGrade(String newPayGradeName){
        click(By.xpath(payGradeAddBtn));
        setText(By.cssSelector(payGradeName),newPayGradeName);
        click(By.xpath(payGradeSaveBtn));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(payGradeTable)));
    }

    public void editPayGrade(){
        click(By.xpath(payGradeEditBtn));
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(txtNameField)));
        WebElement textField = driver.findElement(By.xpath(txtNameField));
        textField.click();
        textField.sendKeys("-sample");
        click(By.cssSelector(editSaveBtn));
       /* WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(payGradeTable)));
*/
    }

    public void delPayGrade(){
        click(By.xpath(payGradeDelBtn));
        click(By.xpath(confDelBtn));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(payGradeTable)));

    }


}
