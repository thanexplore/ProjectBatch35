package pages;

import library.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CandidatePage extends PageBase {
    WebDriver driver;
    private final String dropDown = "//label[text()='%s']//following::div[3]";
    private final String textField = "//label[text()='%s']//following::input[1]";
    private final String nameTextField = "//div[@class = 'oxd-autocomplete-option']//descendant::span[text() = '%s']";
    private final String searchButton = "//button[text() = ' Search ']";
    private final String resetButton = "//button[text() = ' Reset ']";
    private final String addButton = "//button[text() = ' Add ']";
    private final String firstFieldName = "firstName";
    private final String lastFieldname = "lastName";
    private final String addDropDown = "//label[text()='Vacancy']//following::div[5]";
    private final String addTextField = "//label[text()='%s']//following::input";
    private final String browseFile = ".oxd-file-input";
    private final String saveButton = "//button[text() = ' Save ']";
    private final String checkBoxButton = "div[class='oxd-checkbox-wrapper'] span[class*='oxd-checkbox-input']";
    private final String shortlistButton = ".oxd-button--success";
    private final String rejectButton = "//button[text() = ' Reject ']";
    private final String textBox = "//*[@placeholder=\"Type here\"]";
    private final String scheduleButton = "//button[text() = ' Schedule Interview ']";
    private final String interviewTextBox = "//label[text() = '%s']//following::input[1]";
    private final String interviewerFailedButton = "//button[text() = ' Mark Interview Failed ']";
    private final String editActionButton = "//div[text() = '%s']//following::div[9]//descendant::button[1]";
    private final String editClick = "span[class = 'oxd-switch-input oxd-switch-input--active --label-left']";
    private final String deleteButton = "//div[text() = '%s']//following::div[9]//descendant::button[2]";
    private final String confirmDelete = "//button[text() = ' Yes, Delete ']";
    private final String successToast = "//*[text() = 'Successfully Saved']";
    private final String searchResult = ".orangehrm-horizontal-padding";
    private final String editToast = "//*[text() ='Successfully Updated']";
    private final String deleteToast = "//*[text() ='It has been removed successfully']";
    public CandidatePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean search(String vacancy, String hiringManager, String status, String keyword, String selection, String date){
        boolean search = false;
        clickSelectButton(By.xpath(dropDown.replace("%s","Vacancy")), 1);
        selectOptionButton("Vacancy", vacancy,1);
        clickSelectButton(By.xpath(dropDown.replace("%s","Hiring Manager")), 1);
        selectOptionButton("Hiring Manager", hiringManager,1);
        clickSelectButton(By.xpath(dropDown.replace("%s","Status")), 1);
        selectOptionButton("Status", status,1);
        setText(By.xpath(textField.replace("%s","Candidate Name")),keyword,1);
        clickSelectButton(By.xpath(nameTextField.replace("%s",selection)),1);
        setText(By.xpath(textField.replace("%s", "Date of Application")),date);
        click(By.xpath(searchButton));
        String resultContainer = getText(By.cssSelector(searchResult));
        if(resultContainer.contains("Record Found") || resultContainer.contains("Records Found")) {
            search = true;
        }
        sleep(2000);
        click(By.xpath(resetButton));
        return search;
    }

    public boolean add(String fName, String lName, String vacancy,String Email, String contact, String title, String keyword, String selection, String date){
        boolean added = false;
        click(By.xpath(addButton));
        setText(By.name(firstFieldName),fName);
        setText(By.name(lastFieldname),lName);
        clickSelectButton(By.xpath(addDropDown.replace("%s","Vacancy")), 1);
        selectOptionButton("Vacancy", vacancy, 1);
        setText(By.xpath(addTextField.replace("%s","Email")),Email, 1);
        setText(By.xpath(addTextField.replace("%s","Contact Number")),contact, 1);
        fileUpload(By.cssSelector(browseFile),"D:/downloads/Get_Started_With_Smallpdf.pdf",1);
        click(By.cssSelector(checkBoxButton));
        click(By.xpath(saveButton));
        if(isElementVisible(By.xpath(successToast))) {
            added = true;
        }
        click(By.cssSelector(shortlistButton));
        setText(By.xpath(textBox),"Candidate is shortlisted");
        click(By.xpath(saveButton));
        click(By.xpath(scheduleButton));
        setText(By.xpath(interviewTextBox.replace("%s","Interview Title")),title,1);
        setText(By.xpath(textField.replace("%s","Interviewer")),keyword,1);
        clickSelectButton(By.xpath(nameTextField.replace("%s",selection)),1);
        setText(By.xpath(textField.replace("%s", "Date")),date);
        click(By.xpath(saveButton));
        click(By.xpath(interviewerFailedButton));
        setText(By.xpath(textBox),"Interview did not pass");
        click(By.xpath(saveButton));
        click(By.xpath(rejectButton));
        setText(By.xpath(textBox),"Candidate rejected");
        click(By.xpath(saveButton));
        return added;
    }

    public boolean edit(){
        boolean edit = false;
        click(By.xpath(editActionButton.replace("%s","Senior QA Lead")));
        click(By.cssSelector(editClick));
        sleep(2000);
        String editText = "xiang@icloud.com";
        setText(By.xpath(addTextField.replace("%s","Email")),editText,1);
        click(By.xpath(saveButton));
        if(isElementVisible(By.xpath(editToast))) {
            edit = true;
        }
        return edit;
    }

    public boolean delete(){
        boolean deleted = false;
        click(By.xpath(deleteButton.replace("%s","Java Developer")));
        click(By.xpath(confirmDelete));
        if(isElementVisible(By.xpath(deleteToast))) {
            deleted = true;
        }
        return deleted;
    }
}