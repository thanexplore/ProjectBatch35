package pages;

import com.google.common.collect.Table;
import library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import javax.swing.text.TableView;
import java.util.Arrays;
import java.util.List;

public class CustomersPage extends PageBase {

    WebDriver driver;
    private final String idCustAddBtn = ".oxd-button--secondary";
    private final String idCustTxtLevel = "div[class='oxd-form-row'] input[class*='oxd-input']";
    private final String idCustSaveBtn = "//button[@type='submit']";
    private final String lblAlrdyExistsMessage = "//div[@class='oxd-form-row'] /div";
    private final String getIdCustCancelBtn = "//div[@class='oxd-form-actions'] /button[1]";
    private final String tableCustomers = ".oxd-table-body";
    private final String customers = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String successfullySvd = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']";
    private final String dltSingleCustomer = ".//i[@class='oxd-icon bi-trash']";
    private final String yesDelete = "//button[text()=' Yes, Delete ']";
    private final String successfullyDltd = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']";
    private final String editCust = ".//i[@class='oxd-icon bi-pencil-fill']";
    private final String descripAddCust = "div[class='oxd-form-row'] textarea[class*='oxd-textarea']";
    private final String slctMultiCust = ".//span[@class='oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input']";
    private final String deleteSlctdBtn = "//button[text()=' Delete Selected ']";
    private final String tableRow = "//div[@class='oxd-table-card']";
    private final String tableColumn = ".//div[@class='oxd-table-cell oxd-padding-cell']";
    public Boolean match;
    public String dlt;
    public String updt;
    public String txtCustomer;
    public int matchCount;
    public Boolean dltSingleMatch;
    public Boolean editMatch;

    @FindBy(xpath = customers)
    private List<WebElement> listCustomers;

    public CustomersPage(WebDriver driver, Boolean match) {
        super(driver);
        this.driver = driver;
        this.match = match;
        PageFactory.initElements(driver, this);
    }

    public void saveNewCustomer(String customerName) {
        sleep(3000);
        click(By.cssSelector(idCustAddBtn));
        setText(By.cssSelector(idCustTxtLevel), customerName);
        sleep(4000);
        if (getText(By.xpath(lblAlrdyExistsMessage)).contains("Already exists")) {
            click(By.xpath(getIdCustCancelBtn));
            System.out.println(customerName + " already exists");
        } else if (getText(By.xpath(lblAlrdyExistsMessage)).contains("Required")) {
            click(By.xpath(getIdCustCancelBtn));
            System.out.println("Customer name is required");
        } else {
            click(By.xpath(idCustSaveBtn));
        }
        sleep(1000);
        isElementVisible(By.xpath(successfullySvd));
        isElementVisible(By.cssSelector(tableCustomers));

        for (WebElement Customer : listCustomers) {
            String txtCustomer = Customer.getText();
            System.out.println(txtCustomer);
        }
        match = listCustomers.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(customerName));

    }

    public void deleteCustomer(String dltSingleCustomerName) {
        sleep(3000);
        List<WebElement> table_RowElement = driver.findElements(By.xpath(tableRow));
        try {
            for (WebElement row : table_RowElement) {
                List<WebElement> table_ColElement = row.findElements(By.xpath(tableColumn));
                for (int i = 0; i < table_ColElement.size(); i++) {
                    WebElement cell = table_ColElement.get(i);
                    txtCustomer = cell.getText();
                    if (txtCustomer.equalsIgnoreCase(dltSingleCustomerName)) {
                        WebElement trashButtonCell = table_ColElement.get(i + 2);
                        WebElement trashButton = trashButtonCell.findElement(By.xpath(dltSingleCustomer));
                        trashButton.click();
                        sleep(2000);
                        click(By.xpath(yesDelete));
                        dlt = getText(By.xpath(successfullyDltd));
                        sleep(2000);
                        break;
                    }
                }
                if (txtCustomer.equalsIgnoreCase(dltSingleCustomerName)) {
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        dltSingleMatch = listCustomers.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(dltSingleCustomerName));
    }

    public void editCustomer(String editCustName, String descriptionText) {
        sleep(3000);
        List<WebElement> table_RowElement = driver.findElements(By.xpath(tableRow));
        try {
            for (WebElement row : table_RowElement) {
                List<WebElement> table_ColElement = row.findElements(By.xpath(tableColumn));
                for (int i = 0; i < table_ColElement.size(); i++) {
                    WebElement cell = table_ColElement.get(i);
                    txtCustomer = cell.getText();
                    if (txtCustomer.equalsIgnoreCase(editCustName)) {
                        WebElement editButtonCell = table_ColElement.get(i + 2);
                        WebElement editButton = editButtonCell.findElement(By.xpath(editCust));
                        editButton.click();
                        sleep(3000);
                        setText(By.cssSelector(descripAddCust), descriptionText);
                        click(By.xpath(idCustSaveBtn));
                        updt = getText(By.xpath(successfullyDltd));
                        sleep(2000);
                        break;
                    }
                }
                if (txtCustomer.equalsIgnoreCase(editCustName)) {
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        sleep(3000);
        for (WebElement Customer : listCustomers) {
            String txtCustomer = Customer.getText();
            if (txtCustomer.contains(descriptionText)){
                editMatch = true;
            }
            System.out.println(txtCustomer);
        }
    }

    public void deleteMultipleCustomers(String[] inputTexts) {
        sleep(3000);
        List<WebElement> table_RowElement = driver.findElements(By.xpath(tableRow));
        try {
            for (String inputText : inputTexts) {
                boolean textFound = false;

                for (WebElement row : table_RowElement) {
                    List<WebElement> table_ColElement = row.findElements(By.xpath(tableColumn));
                    for (int i = 0; i < table_ColElement.size(); i++) {
                        WebElement cell = table_ColElement.get(i);
                        txtCustomer = cell.getText();
                        if (txtCustomer.equalsIgnoreCase(inputText)) {
                            WebElement checkboxCell = table_ColElement.get(i - 1);
                            WebElement checkBox = checkboxCell.findElement(By.xpath(slctMultiCust));
                            checkBox.click();
                            textFound = true;
                            break;
                        }
                    }
                    if (textFound) {
                        break;
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        sleep(2000);
        click(By.xpath(deleteSlctdBtn));
        sleep(2000);
        click(By.xpath(yesDelete));
        dlt = getText(By.xpath(successfullyDltd));
        sleep(2000);
        isElementVisible(By.cssSelector(tableCustomers));
        for (String splitted : inputTexts) {
            Boolean dltMatch =false;
            dltMatch = listCustomers.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(splitted));
            if(dltMatch){
                matchCount++;
            }
        }
        System.out.println("Customer List:\n");
        for (WebElement Customer : listCustomers) {
            String txtCustomer = Customer.getText();
            System.out.println(txtCustomer);
            System.out.println("\n");
        }
    }
}

