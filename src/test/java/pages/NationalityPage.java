package pages;

import library.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.naming.event.NamingListener;
import java.time.Duration;
import java.util.List;

public class NationalityPage extends PageBase {

    WebDriver driver;
    private final String addNationBtn=".oxd-button--secondary";
    private final String nationTextBox=".oxd-input.oxd-input--active";
    private final String textNation="//div[@class='oxd-input-group oxd-input-field-bottom-space']//div[2]//input";
    private final String alreadyExistsMsg="//div[@class='oxd-form-row'] /div";
    private final String cancelButton="oxd-button oxd-button--medium oxd-button--ghost";
    private final String nationSaveBtn="//button[@type='submit']";
    private final String nationListTable=".oxd-table-body";
    private final String nationEditBtn="//button[@class='oxd-icon-button oxd-table-cell-action-space']//following-sibling::button";
    private final String nationDeleteBtn="//button[@class='oxd-icon-button oxd-table-cell-action-space'][1]";
    private final String deleteNationBtn="//div/div[3]/div/button[1]";
    private final String nationalities = "//div[@class='oxd-table-body'] /div[@class='oxd-table-card']";
    private final String deletePopupBtn="//div[@class='orangehrm-modal-footer']/button[2]";
    private final String nationCheckBox="//div[@class='oxd-table-card-cell-checkbox']//i";
    private final String deleteSelctedBtn="//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button";
    private final String nationName="//div[@class='oxd-table-row oxd-table-row--with-border']/div[@class='oxd-table-cell oxd-padding-cell'][2]/div";

    private final String nList="//div[@class='oxd-table-row oxd-table-row--with-border']";
    @FindBy(xpath = nList)
    private List<WebElement> listN;
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
            if (getText(By.xpath(alreadyExistsMsg)).contains("Already exists")) {
                click(By.xpath(cancelButton));
            } else {
                click(By.xpath(nationSaveBtn));
            }
            System.out.println("save pressed");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
            wait.until(ExpectedConditions.visibilityOfAllElements(nationList));
            System.out.println(nationList);
            isElementVisible(By.cssSelector(nationListTable));
            return nationList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(nationalityName));
        } catch (Exception e) {
            System.out.println("exception occuered :"+e.getMessage());
        }
        return false;
    }



    public Boolean addExistingNationality(String nationalityName) {
        try {
            click(By.cssSelector(addNationBtn));
            setText(By.cssSelector(nationTextBox), nationalityName);
            sleep(3000);
            System.out.println("msgstart:"+getText(By.xpath(alreadyExistsMsg))+":msgend");
            return (getText(By.xpath(alreadyExistsMsg))).trim().equalsIgnoreCase("Name\nAlready exists");
        } catch (Exception e) {
            System.out.println("Exception occured:"+e.getMessage());
        }
        return false;
    }


    ////////////


    public Boolean editNationality(String nationalityName) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
            wait.until(ExpectedConditions.visibilityOfAllElements(nationList));
            System.out.println("nationList:" + nationList.size());
            isElementVisible(By.cssSelector(nationListTable));

            for (WebElement nation : nationList) {

                if (nationalityName.equalsIgnoreCase(nation.getText())) {
                    System.out.println("name matched");
                    List<WebElement> nationElements = nation.findElements(By.xpath(nationEditBtn));
                    System.out.println("nationElements count"+nationElements.size());

                    for (WebElement editBtn : nationElements) {
                        isElementVisible(By.xpath(nationEditBtn));
                        if (isElementClickable(By.xpath(nationEditBtn))){
                            editBtn.click();
                            isElementVisible(By.xpath(textNation));
                            WebElement we =driver.findElement(By.xpath(textNation));
                            System.out.println("Before clearing: " + we.getAttribute("value"));
                            while(!we.getAttribute("value").equals("")){
                                System.out.println("inside while");
                                we.sendKeys(Keys.BACK_SPACE);
                            }
                            System.out.println("After clearing:"+we.getAttribute("value"));
                            we.sendKeys("A1");
                            System.out.println("after update: " + we.getAttribute("value"));
                            if (getText(By.xpath(alreadyExistsMsg)).contains("Already exists")) {
                                click(By.xpath(cancelButton));
                            } else {
                                click(By.xpath(nationSaveBtn));
                            }

                            isElementVisible(By.xpath(nationalities));
                            nationList=driver.findElements(By.xpath(nationalities));
                            WebDriverWait nationWait = new WebDriverWait(driver, Duration.ofSeconds(10));
                            nationWait.until(ExpectedConditions.visibilityOfAllElements(nationList));
                            System.out.println(nationList.size());
                            Boolean match = nationList.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase("A1"));
                            return match;

                        }
                    }
                }


            }
        }catch(StaleElementReferenceException e){
            System.out.println("exeception occured");
           // e.printStackTrace();
        }
        return false;
    }

    //Issue- Always the first record gets deleted.
    public Boolean deleteNationality(String nationalityName) {

        try {

            if(saveNewNationality("A") && saveNewNationality("AA")){

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
                wait.until(ExpectedConditions.visibilityOfAllElements(nationList));
                System.out.println("nationList:" + nationList.size());
                isElementVisible(By.cssSelector(nationListTable));

                for (WebElement nation : nationList) {
                    WebElement nameNation = nation.findElement(By.xpath(nationName));
                    isElementVisible(By.xpath(nationName));
                    System.out.println("nation WE:" + nation.getText());
                    System.out.println("nationName from Xpath:" + nameNation.getText());
                    if (nationalityName.equalsIgnoreCase(nation.getText())) {
                        System.out.println(nation.getText());
                        System.out.println("name matched");
                        WebElement deleteBtn = nation.findElement(By.xpath(deleteNationBtn));
                        //System.out.println("nationElements :"+nationElements.size());
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
            System.out.println("exeception occured :"+e.getMessage());
            // e.printStackTrace();
        }
        return true;
    }

    public Boolean deleteMultipleNationality(String nationalityName1,String nationalityName2) {

        try {
            if(saveNewNationality("A")&&saveNewNationality("AA")) {
                System.out.println("inside if");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
                wait.until(ExpectedConditions.visibilityOfAllElements(listN));
                System.out.println("nationList:" + listN.size());
                isElementVisible(By.cssSelector(nationListTable));


                for (WebElement Nation : listN) {
                    System.out.println("Nation size"+Nation.getSize());
                    System.out.println(Nation.getText());
                    if (nationalityName1.equalsIgnoreCase(Nation.getText()) || nationalityName2.equalsIgnoreCase(Nation.getText())) {
                        System.out.println("name matched" + Nation.getText());
                        List<WebElement> nationElements = Nation.findElements(By.xpath(nationCheckBox));
                        System.out.println("nationCBElements " + nationElements.size());
                        for (WebElement checkBox : nationElements) {
                            System.out.println("inside for:");
                            isElementVisible(By.xpath(nationCheckBox));
                            System.out.println(checkBox.getText());
                            System.out.println(checkBox.getTagName());
                            System.out.println(checkBox.getAttribute("class"));
                            isElementClickable(By.xpath(nationCheckBox));
                            checkBox.click();
                            System.out.println("checkbox selected:breaking");
                            break;

                        }
                    }
                }
                if (isElementVisible(By.xpath(deleteSelctedBtn))) {
                    System.out.println("inside popup");
                    isElementClickable(By.xpath(deleteSelctedBtn));
                    click(By.xpath(deleteSelctedBtn));
                    sleep(2000);
                    isElementVisible(By.xpath(deletePopupBtn));
                    isElementClickable(By.xpath(deletePopupBtn));
//                    click(By.xpath(deletePopupBtn));
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
                System.out.println("exeception occured");
                // e.printStackTrace();
        }
        return false;
    }

}
