package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class customerPage {
    @FindBy(xpath = customers)
    private List<WebElement> listCustomers;

    public CustomersPage(WebDriver driver, Boolean match) {
        super(driver);
        this.driver = driver;
        this.match = match;
        PageFactory.initElements(driver, this);
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

}
