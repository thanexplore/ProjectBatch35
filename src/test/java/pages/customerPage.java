package pages;

import library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class customerPage extends PageBase {
    WebDriver driver;
    private final String idCustAddBtn = ".oxd-button--secondary";
    private final String idCustTxtLevel = "div[class='oxd-form-row'] input[class*='oxd-input']";
    private final String idCustSaveBtn = "//button[@type='submit']";
    private final String lblAlrdyExistsMessage = "//div[@class='oxd-form-row'] /div";
    private final String getIdCustCancelBtn = "//div[@class='oxd-form-actions'] /button[1]";
    private final String customers = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";

    private final String tableCustomers = ".oxd-table-body";
    private String xpathYesDeleteButton = "//div[@class=\"orangehrm-modal-footer\"]//button[2]";
    private String xpathSuccessMessage = "//div[@class=\"oxd-toast oxd-toast--success oxd-toast-container--toast\"]";
    private final String descripAddCust = "div[class='oxd-form-row'] textarea[class*='oxd-textarea']";
    private final String deleteSlctdBtn = "//button[text()=' Delete Selected ']";

    public Boolean editSuccess;
    @FindBy(xpath = "//i[@class=\"oxd-icon bi-trash\"]")
    List<WebElement> delete;
    @FindBy(xpath = "//i[@class=\"oxd-icon bi-pencil-fill\"]")
    List<WebElement> edit;
    @FindBy(xpath = "//div[@class=\"oxd-table-body\"]//span[@class=\"oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input\"]")
    List<WebElement> select;
    @FindBy(xpath = "//div[@class=\"oxd-table-body\"] //div[@class=\"oxd-table-cell oxd-padding-cell\"][2]")
    List<WebElement> listOfRecordNames;
    @FindBy(xpath = customers)
    private List<WebElement> listCustomers;


    public customerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean saveNewCustomer(String customerName) {
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
        isElementVisible(By.xpath(xpathSuccessMessage));
        isElementVisible(By.cssSelector(tableCustomers));

        for (WebElement Customer : listCustomers) {
            String txtCustomer = Customer.getText();
            System.out.println(txtCustomer);
        }
        Boolean match = listCustomers.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(customerName));
        return match;
    }

    public boolean deleteCustomer(String name) {
        sleep(3000);
        isElementVisible(By.xpath("//i[@class=\"oxd-icon bi-pencil-fill\"]"));
        for (int i = 0; i < listOfRecordNames.size(); i++) {
            if (listOfRecordNames.get(i).getText().equalsIgnoreCase(name)) {
                delete.get(i).click();
            }
        }

        click(By.xpath(xpathYesDeleteButton));
        return
                isElementVisible(By.xpath(xpathSuccessMessage));

    }

    public boolean editCustomer(String editCustName, String descriptionText) {
        sleep(3000);
        isElementVisible(By.xpath("//i[@class=\"oxd-icon bi-pencil-fill\"]"));
        for (int i = 0; i < listOfRecordNames.size(); i++) {
            if (listOfRecordNames.get(i).getText().equalsIgnoreCase(editCustName)) {
                edit.get(i).click();
                sleep(3000);
                setText(By.cssSelector(descripAddCust), descriptionText);
                sleep(1000);
                click(By.xpath(idCustSaveBtn));
                editSuccess = isElementVisible(By.xpath(xpathSuccessMessage));
            }
        }
        return
                editSuccess;
    }

    public boolean deleteMultipleCustomers(String[] inputTexts) {
        sleep(3000);
        isElementVisible(By.xpath("//i[@class=\"oxd-icon bi-pencil-fill\"]"));
        for (String inputText : inputTexts) {
            for (int i = 0; i < listOfRecordNames.size(); i++) {
                if (listOfRecordNames.get(i).getText().equalsIgnoreCase(inputText)) {
                    select.get(i).click();
                    break;
                }
            }
        }
        sleep(2000);
        click(By.xpath(deleteSlctdBtn));
        click(By.xpath(xpathYesDeleteButton));
        return
                isElementVisible(By.xpath(xpathSuccessMessage));

        /*List<WebElement> table_RowElement = driver.findElements(By.xpath(tableRow));
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
    }*/

    }
}


