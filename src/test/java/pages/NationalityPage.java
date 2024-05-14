package pages;

import library.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class NationalityPage extends PageBase {

    WebDriver driver;
    private final String addNationBtn=".oxd-button--secondary";
    private final String nationTextBox=".oxd-input.oxd-input--active";
    private final String textNation="//div[@class='oxd-input-group oxd-input-field-bottom-space']//div[2]//input";
    private final String alreadyExistsMsg="//div[@class='oxd-form-row'] /div";
    private final String cancelButton="//button[@class='oxd-button oxd-button--medium oxd-button--ghost']";
    private final String nationSaveBtn="//button[@type='submit']";
    private final String nationListTable=".oxd-table-body";
    private final String nationEditBtn=".//div/div[3]/div/button[2]";
    private final String deleteNationBtn=".//div/div[3]/div/button[1]";
    private final String nationalities = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String deletePopupBtn="//div[@class='orangehrm-modal-footer']/button[2]";
    private final String nationCheckBox=".//div[@class='oxd-table-card-cell-checkbox']//i";
    private final String deleteSelectedBtn="//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button";
    private final String nationName=".//div[@class='oxd-table-row oxd-table-row--with-border']/div[@class='oxd-table-cell oxd-padding-cell'][2]/div";

    @FindBy(xpath = nationalities)
    private List<WebElement> nationList;

    public NationalityPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public Boolean saveNewNationality(String nationalityName) {

        try {
            click(By.cssSelector(addNationBtn));
            setText(By.cssSelector(nationTextBox), nationalityName);
            sleep(2000);
            if (getText(By.xpath(alreadyExistsMsg)).contains("Already exists")) {
                sleep(2000);
                click(By.xpath(cancelButton));
                System.out.println("cancel pressed");
            } else {
                click(By.xpath(nationSaveBtn));
                System.out.println("save pressed");
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
            wait.until(ExpectedConditions.visibilityOfAllElements(nationList));
            System.out.println(nationList);
            isElementVisible(By.cssSelector(nationListTable));
            return nationList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(nationalityName));
        } catch (Exception e) {
            System.out.println("exception occurred :"+e.getMessage());
        }
        return false;
    }



    public Boolean addExistingNationality(String nationalityName) {
        try {
            click(By.cssSelector(addNationBtn));
            setText(By.cssSelector(nationTextBox), nationalityName);
            sleep(3000);
            System.out.println("msg:"+getText(By.xpath(alreadyExistsMsg)));
            return (getText(By.xpath(alreadyExistsMsg))).trim().equalsIgnoreCase("Name\nAlready exists");
        } catch (Exception e) {
            System.out.println("Exception occurred:"+e.getMessage());
        }
        return false;
    }


    ////////////


    public Boolean editNationality(String nationalityNameOld,String nationalityNameNew) {

        try {
            if(saveNewNationality("A")){

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
                wait.until(ExpectedConditions.visibilityOfAllElements(nationList));
                System.out.println("nationList:" + nationList.size());
                isElementVisible(By.cssSelector(nationListTable));

                for (WebElement nation : nationList) {

                    WebElement nameNation = nation.findElement(By.xpath(nationName));
                    isElementVisible(By.xpath(nationName));

                    if (nationalityNameOld.equalsIgnoreCase(nameNation.getText())) {
                        System.out.println("name matched");
                        WebElement editBtn = nation.findElement(By.xpath(nationEditBtn));

                        isElementVisible(By.xpath(nationEditBtn));
                        if (isElementClickable(By.xpath(nationEditBtn))) {
                            editBtn.click();
                            isElementVisible(By.xpath(textNation));
                            sleep(2000);
                            WebElement textboxWe = driver.findElement(By.xpath(textNation));
                            System.out.println("Before clearing:" + textboxWe.getAttribute("value"));
                            while (!textboxWe.getAttribute("value").equals("")) {
                                textboxWe.clear();
                                textboxWe.sendKeys(Keys.BACK_SPACE);
                            }
                            System.out.println("After clearing:" + textboxWe.getAttribute("value"));
                            textboxWe.sendKeys(nationalityNameNew);
                            System.out.println("after update: " + textboxWe.getAttribute("value"));
                            if (getText(By.xpath(alreadyExistsMsg)).contains("Already exists")) {
                                click(By.xpath(cancelButton));
                            } else {
                                click(By.xpath(nationSaveBtn));
                            }

                            isElementVisible(By.xpath(nationalities));
                            nationList = driver.findElements(By.xpath(nationalities));
                            WebDriverWait nationWait = new WebDriverWait(driver, Duration.ofSeconds(10));
                            nationWait.until(ExpectedConditions.visibilityOfAllElements(nationList));
                            System.out.println(nationList.size());
                            return nationList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(nationalityNameNew));


                        }
                    }


                }

            }
        }catch(StaleElementReferenceException e){
            System.out.println("exception occurred");
           // e.printStackTrace();
        }
        return false;
    }


    public Boolean deleteNationality(String nationalityName) {

        try {

            if(saveNewNationality("A") && saveNewNationality("AA")){

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
                wait.until(ExpectedConditions.visibilityOfAllElements(nationList));
                isElementVisible(By.cssSelector(nationListTable));

                for (WebElement nation : nationList) {
                    WebElement nameNation = nation.findElement(By.xpath(nationName));
                    isElementVisible(By.xpath(nationName));
                    if (nationalityName.equalsIgnoreCase(nameNation.getText())) {
                        System.out.println(nation.getText());
                        System.out.println("name matched"+nameNation.getText());
                        WebElement deleteBtn = nation.findElement(By.xpath(deleteNationBtn));
                        isElementVisible(By.xpath(deleteNationBtn));
                        isElementClickable(By.xpath(deleteNationBtn));
                        deleteBtn.click();

                        sleep(2000);
                        isElementVisible(By.xpath(deletePopupBtn));
                        isElementClickable(By.xpath(deletePopupBtn));
                        click(By.xpath(deletePopupBtn));
                        isElementVisible(By.xpath(nationalities));
                        nationList = driver.findElements(By.xpath(nationalities));
                        WebDriverWait nationWait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
                        nationWait.until(ExpectedConditions.visibilityOfAllElements(nationList));
                        System.out.println("Assert check");
                        System.out.println(nationList.size());
                        return nationList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase("AA"));
                    }
                }

            }
        }catch(Exception e){
            System.out.println("exception occurred :"+e.getMessage());
            // e.printStackTrace();
        }
        return true;
    }

    public Boolean deleteMultipleNationality(String nationalityName1,String nationalityName2) {

        try {
            if(saveNewNationality("A")&&saveNewNationality("AA")) {
                System.out.println("inside if");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
                wait.until(ExpectedConditions.visibilityOfAllElements(nationList));
                System.out.println("nationList:" + nationList.size());
                isElementVisible(By.cssSelector(nationListTable));


                for (WebElement nation : nationList) {

                    if (nationalityName1.equalsIgnoreCase(nation.getText()) || nationalityName2.equalsIgnoreCase(nation.getText())) {
                        System.out.println("name matched" + nation.getText());
                        WebElement checkBox = nation.findElement(By.xpath(nationCheckBox));
                        isElementVisible(By.xpath(nationCheckBox));
                        isElementClickable(By.xpath(nationCheckBox));
                        checkBox.click();
                    }
                }
                if (isElementVisible(By.xpath(deleteSelectedBtn))) {
                    System.out.println("inside popup");
                    isElementClickable(By.xpath(deleteSelectedBtn));
                    click(By.xpath(deleteSelectedBtn));
                    sleep(2000);
                    isElementVisible(By.xpath(deletePopupBtn));
                    isElementClickable(By.xpath(deletePopupBtn));
                    click(By.xpath(deletePopupBtn));
                    isElementVisible(By.xpath(nationalities));
                    nationList = driver.findElements(By.xpath(nationalities));
                    WebDriverWait nationWait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
                    nationWait.until(ExpectedConditions.visibilityOfAllElements(nationList));
                    System.out.println("Assert check");
                    System.out.println(nationList.size());
                    Boolean match1 = nationList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase("A"));
                    Boolean match2 = nationList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase("AA"));
                    return !match1 && !match2;
                }
            }else return false;
        }catch(Exception e){
                System.out.println("exception occurred");
                // e.printStackTrace();
        }
        return false;
    }

}
