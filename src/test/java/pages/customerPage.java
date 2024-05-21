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
    private final String tableCustomers = ".oxd-table-body";
    private final String customers = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String successfullySvd = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']";
    private final String dltSingleCustomer = ".//i[@class='oxd-icon bi-trash']";
    private final String yesDelete = "//button[text()=' Yes, Delete ']";
    private final String successfullyDltd = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']";

    private String xpathYesDeleteButton = "//div[@class=\"orangehrm-modal-footer\"]//button[2]";
    private String xpathSuccessMessage = "//div[@class=\"oxd-toast oxd-toast--success oxd-toast-container--toast\"]";
    @FindBy(xpath = "//i[@class=\"oxd-icon bi-trash\"]")
    List<WebElement> delete;
    @FindBy(xpath = "//div[@class=\"oxd-table-body\"] //div[@class=\"oxd-table-cell oxd-padding-cell\"][2]")
    List<WebElement> listOfRecordNames;

    public customerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean deleteCustomer(String name) {
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
}

/* List<WebElement> table_RowElement = driver.findElements(By.xpath(tableRow));
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
                }*/
