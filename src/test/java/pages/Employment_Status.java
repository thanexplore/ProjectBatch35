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
    private String ESTable = ".oxd-table-body";
    private String ESEdit = "(//button[@class='oxd-icon-button oxd-table-cell-action-space'])[2]";
    //private String ESEdit= "(//div[contains(text(),'Marketing')]//following::div[1]//child::button[2]";
    private String ESEditStatus1 = "(//input[contains(@class,'oxd-input oxd-input')])[2]";
    //private String ESEditStatus2 = "//input[@class='oxd-input oxd-input--active oxd-input--error']";
    private String ESDelete = "(//button[@class='oxd-icon-button oxd-table-cell-action-space'])[1]";
    //private String ESDelete = "(//div[contains(text(),'Marketing')]//following::div[1]//child::button[1]";
    private String ESDeleteYesDelete = "//button[text()=' Yes, Delete ']";
    private String ESDeleteNoCancel = "//button[text()=' No, Cancel ']";
    private String ESMultiDelete1 = "(//span[@class='oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input'])[2]";
    private String ESMultiDelete2 = "(//span[@class='oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input'])[3]";
    private String ESDeleteSelected = "//button[text()=' Delete Selected ']";
    private String ESRecord = "//span[@class='oxd-text oxd-text--span']";
    private String ESSave = "//p[text()='Success']";
    private final String TrashButton = "//i[contains(@class,'oxd-icon bi-trash')]";
    private final String EditButton= "//i[contains(@class,'oxd-icon bi-pencil-fill')]";
    private final String ES = "//div[contains(@style,'flex-basis: 80%;')]";
    private final String employmentStatus = "//div[@class='oxd-table-body']";
    @FindBy(xpath = employmentStatus)
    private List<WebElement> ESlist;
   /* @FindBy(xpath = Rows)
    private List<WebElement> ESRows;
    @FindBy(xpath = Columns)
    private List<WebElement> ESColumns;

    */

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

            isElementVisible(By.cssSelector(ESTable));

            for (WebElement EmpStatus : ESlist) {
                String txtEmpStatus = EmpStatus.getText();
                System.out.println(txtEmpStatus);

            }
            Boolean match = ESlist.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(eStatus));



        }
        return true;

    }

    public String success() {

        return getText(By.xpath(ESSave));
    }

    public boolean EditEmpStatus() {
        sleep(3000);
        click(By.xpath(ESEdit));
        /*List<WebElement> ESL = driver.findElements(By.xpath(ES));
        for(int i = 0; i< ESL.size();i++) {
            WebElement Edit = ESL.get(i);
            List<WebElement> EB = driver.findElements(By.xpath(EditButton));
            for (int j = 1; j < EB.size(); j++) {
                //WebElement xy = EB.get(j);
                String EH = Edit.getText();
                if (EH.equalsIgnoreCase(editStatus)) {
                    click(By.xpath(EditButton+"["+j+"]"));
                    sleep(3000);
                    */
        sleep(5000);
        WebElement clear = driver.findElement(By.xpath(ESEditStatus1));
        clear.sendKeys(Keys.CONTROL + "a");
        clear.sendKeys(Keys.BACK_SPACE);
        /*sleep(5000);
        isElementClickable(By.xpath(ESEditStatus1));
        setText(By.xpath(ESEditStatus1), updatedStatus);
        sleep(5000);
        click(By.xpath(ESSaveData));

         */

        //Boolean match = ESlist.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(editStatus));

        return true;

    }

    public boolean deleteEmpStatus(String deleteStatus) {
        sleep(2000);
        List<WebElement> ESL = driver.findElements(By.xpath(ES));
        for(int i = 0; i< ESL.size();i++){
            WebElement Trash = ESL.get(i);
            List<WebElement> TB = driver.findElements(By.xpath(TrashButton));
            for(int j=0; j<TB.size();j++) {
                //WebElement xy = TB.get(j);
                String EX = Trash.getText();
                if (EX.equalsIgnoreCase(deleteStatus)) {
                    click(By.xpath(TrashButton));
                    sleep(2000);
                    click(By.xpath(ESDeleteYesDelete));
                    break;
                }
            }


        }
        return true;
    }


    public String deleteRandomMultipleEmpStatus() {
        sleep(3000);
        click(By.xpath(ESMultiDelete1));
        click(By.xpath(ESMultiDelete2));
        click(By.xpath(ESDeleteSelected));
        click(By.xpath(ESDeleteYesDelete));
        isElementVisible(By.cssSelector(ESTable));
        System.out.println(ESTable);
        for (WebElement EmpStatus : ESlist) {
            String txtEmpStatus = EmpStatus.getText();
            System.out.println(txtEmpStatus);

        }
        return null;
    }


    public String countofRecord() {
        sleep(3000);
        return getText(By.xpath(ESRecord));

    }
}