package pages;

import library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class CustomersPage extends PageBase {

    WebDriver driver;
    private final String idCustAddBtn = ".oxd-button--secondary";
    private final String idCustTxtLevel ="div[class='oxd-form-row'] input[class*='oxd-input']";
    private final String idCustSaveBtn="//button[@type='submit']";
    private final String lblAlrdyExistsMessage = "//div[@class='oxd-form-row'] /div";
    private final String getIdCustCancelBtn= "//div[@class='oxd-form-actions'] /button[1]";
    private final String tableCustomers = ".oxd-table-body";
    private final String customers = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String successfullySvd = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']";
    private final String dltSingleCustomer = "//div[@class='oxd-table']//child::div[@class='oxd-table-body']//descendant::div[@class='oxd-table-card'][2]//child::i[@class='oxd-icon bi-trash']";
    private final String yesDelete = "//button[text()=' Yes, Delete ']";
    private final String successfullyDltd = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']";
    private final String editCust = "//div[@class='oxd-table']//child::div[@class='oxd-table-body']//descendant::div[@class='oxd-table-card'][2]//child::i[@class='oxd-icon bi-pencil-fill']";
    private final String descripAddCust = "div[class='oxd-form-row'] textarea[class*='oxd-textarea']";
    private final String slctMultiCust1 = "//div[@class='oxd-table-body']//div[@class='oxd-table-card'][2]//label[1]//span";
    private final String slctMultiCust2 = "//div[@class='oxd-table-body']//div[@class='oxd-table-card'][8]//label[1]//span";
    private final String deleteSlctdBtn = "//button[text()=' Delete Selected ']";
    public  Boolean match;
    public String dlt;
    public String updt;

    @FindBy(xpath = customers)
    private List<WebElement> listCustomers;
    public CustomersPage(WebDriver driver, Boolean match) {
        super(driver);
        this.driver=driver;
        this.match = match;
        PageFactory.initElements(driver,this);
    }

    public void saveNewCustomer(String customerName){
        sleep(3000);
        click(By.cssSelector(idCustAddBtn));
        setText(By.cssSelector(idCustTxtLevel), customerName);
        sleep(4000);
        if (getText(By.xpath(lblAlrdyExistsMessage)).contains("Already exists")) {
            click(By.xpath(getIdCustCancelBtn));
            System.out.println(customerName +" already exists");
        } else {
            click(By.xpath(idCustSaveBtn));
        }
        isElementVisible(By.cssSelector(tableCustomers));
        isElementVisible(By.xpath(successfullySvd));

        for (WebElement Education : listCustomers) {
            String txtEducation = Education.getText();
            System.out.println(txtEducation);

        }
        match = listCustomers.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(customerName));

    }

    public void deleteCustomer(){
        sleep(5000);
        click(By.xpath(dltSingleCustomer));
        click(By.xpath(yesDelete));

        //isElementVisible(By.cssSelector(tableCustomers));
        dlt = getText(By.xpath(successfullyDltd));


    }
    public void editCustomer(String descriptionText){

        click(By.xpath(editCust));
        sleep(3000);
        setText(By.cssSelector(descripAddCust), descriptionText);
        click(By.xpath(idCustSaveBtn));

        //isElementVisible(By.cssSelector(tableCustomers));
        updt = getText(By.xpath(successfullyDltd));


    }

    public void deleteMultipleCustomers(){
        sleep(4000);
        click(By.xpath(slctMultiCust1));
        click(By.xpath(slctMultiCust2));
        click(By.xpath(deleteSlctdBtn));
        click(By.xpath(yesDelete));

        //isElementVisible(By.cssSelector(tableCustomers));
        dlt = getText(By.xpath(successfullyDltd));

        isElementVisible(By.cssSelector(tableCustomers));
        System.out.println("Customer List:\n");
        for (WebElement Education : listCustomers) {
            String txtEducation = Education.getText();
            System.out.println(txtEducation);

        }


    }
}
