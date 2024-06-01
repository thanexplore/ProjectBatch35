package pages;

import library.PageBase;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class Employment_Status extends PageBase {

    WebDriver driver;

    private String ESAdd = "//button[text()=' Add ']";
    private String ESAddStatus = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//child::input";
    private String ESSaveData = "//button[text()=' Save ']";
    private String ESCancelData = "//button[text()=' Cancel ']";
    private String ESAlredyExisting = "//div[@class='oxd-form-row']/div";
    private String ESTable = "//div[@class='oxd-table-body']";
    private String ESEditStatus1 = "(//input[contains(@class,'oxd-input oxd-input')])[2]";
    private String ESDeleteYesDelete = "//button[text()=' Yes, Delete ']";
    private String ESDeleteNoCancel = "//button[text()=' No, Cancel ']";
    private String ESMultiDelete1 = "(//span[@class='oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input'])[2]";
    private String ESMultiDelete2 = "(//span[@class='oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input'])[3]";
    private String ESDeleteSelected = "//button[text()=' Delete Selected ']";
    private String ESRecord = "//span[@class='oxd-text oxd-text--span']";
    private String ESSave = "//p[text()='Success']";
    private final String DeleteButton = "//i[@class='oxd-icon bi-trash']";
    private final String EditButton= "//i[contains(@class,'oxd-icon bi-pencil-fill')]";
    private final String ES = "//div[@style='flex-basis: 80%;' and @role='cell']";
    private final String employmentStatus = "//div[@class='oxd-table-body']";
    @FindBy(xpath = employmentStatus)
    private List<WebElement> ESlist;
    @FindBy(xpath = ES )
    private List<WebElement> statusList;
    @FindBy(xpath= EditButton)
    private List<WebElement> editbutton;
    @FindBy(xpath = DeleteButton)
    private List<WebElement> deletebutton;
   /* @FindBy(xpath = Rows)
    private List<WebElement> ESRows;
    @FindBy(xpath = Columns)
    private List<WebElement> ESColumns;

    */
   public boolean match;

    public Employment_Status(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean saveNewES(String eStatus) {
        click(By.xpath(ESAdd));
        setText(By.xpath(ESAddStatus), eStatus);
        if (getText(By.xpath(ESAlredyExisting)).contains("Already exists")) {
            click(By.xpath(ESCancelData));
        } else {
            click(By.xpath(ESSaveData));

            isElementVisible(By.xpath(ES));
            match = statusList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(eStatus));

        }
        return match;
    }

    public String success() {

        return getText(By.xpath(ESSave));
    }

    public boolean EditEmpStatus(String editStatus, String updatedStatus) {
        sleep(3000);
        isElementVisible(By.xpath(ES));
        for (int i = 0; i < statusList.size(); i++) {
            if (statusList.get(i).getText().equalsIgnoreCase(editStatus)){
                editbutton.get(i).click();
                    sleep(5000);
                    WebElement clear = driver.findElement(By.xpath(ESEditStatus1));
                    clear.sendKeys(Keys.CONTROL + "a");
                    clear.sendKeys(Keys.BACK_SPACE);
                    sleep(5000);
                    isElementClickable(By.xpath(ESEditStatus1));
                    setText(By.xpath(ESEditStatus1), updatedStatus);
                    sleep(5000);
                    click(By.xpath(ESSaveData));
                    break;

                }
            }
        isElementVisible(By.xpath(ES));
        match = statusList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(updatedStatus));
        return match;
        }


    public boolean deleteEmpStatus(String deleteStatus) {
        sleep(2000);
        isElementVisible(By.xpath(ES));
            for (int i = 0; i < statusList.size(); i++) {
                if (statusList.get(i).getText().equalsIgnoreCase(deleteStatus)) {
                        deletebutton.get(i).click();
                        sleep(5000);
                        click(By.xpath(ESDeleteYesDelete));
                        break;
                    }
                }

            match = statusList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(deleteStatus));
            return match;
    }

    public void deleteRandomMultipleEmpStatus() {
        sleep(3000);
        click(By.xpath(ESMultiDelete1));
        click(By.xpath(ESMultiDelete2));
        click(By.xpath(ESDeleteSelected));
        click(By.xpath(ESDeleteYesDelete));
        isElementVisible(By.cssSelector(ESTable));
        System.out.println(ESTable);

    }


    public String countofRecord() {
        sleep(3000);
        return getText(By.xpath(ESRecord));

    }
}