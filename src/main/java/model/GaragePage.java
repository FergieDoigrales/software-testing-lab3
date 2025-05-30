package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GaragePage extends BasePage {
    private final By garageHeader = By.xpath("//div[@id='garageFloatHead']/span");
    private final By addNewVehicleButton = By.xpath("//a[contains(text(),'Добавить ТС')]");
    private final By vinInput = By.xpath("//input[contains(@id,'f_38')]");
    private final By continueButton = By.xpath("//input[@id='btnContinue']");
    private final By selectSpecificVehicle = By.xpath("//a[contains(@id,'slbCheckVinSelect')]/dl/dt[2]");
    private final By saveButton = By.xpath("//input[contains(@id,'bnSave')]");

    private final By firstCar = By.xpath("//div[@id='gvCars']/div/div[2]/a");
    private final By notebook = By.xpath("//a[@id='ctl00_ctl00_b_b_hlFavorites']");
    private final By addNoteButton = By.xpath("//a[@onclick=\"this.parentNode.classList.add('user-notepad__header--editing')\"]");
    private final By artInput = By.xpath("//input[@id='ctl00_ctl00_b_b_ctrlAddPart_txtPartNo']");
    private final By nextButton = By.xpath("//a[@id='ctl00_ctl00_b_b_ctrlAddPart_btnNext']");
    private final By brandInput = By.xpath("//ul[@id='ctl00_ctl00_b_b_ctrlAddPart_itemsList']/li[2]");
    private final By CountInput = By.xpath("//input[@id='ctl00_ctl00_b_b_ctrlAddPart_txtPartCount']");
    private final By noteSave = By.xpath("//input[@id='ctl00_ctl00_b_b_ctrlAddPart_btnSave']");


    public GaragePage(WebDriver driver) {
        super(driver);
    }

    public void hoverOverGarageHeader() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(garageHeader));
        new Actions(driver).moveToElement(element).perform();
    }

    public void clickAddNewVehicle() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewVehicleButton)).click();
    }

    public void enterVinNumber(String vin) {
        WebElement vinField = wait.until(ExpectedConditions.elementToBeClickable(vinInput));
        vinField.click();
        vinField.clear();
        vinField.sendKeys(vin);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void selectSpecificVehicle() {
        wait.until(ExpectedConditions.elementToBeClickable(selectSpecificVehicle)).click();
    }

    public void saveVehicle() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public boolean isVehicleAdded(String vin) {
        try {
            return wait.until(d -> d.getPageSource().contains(vin));
        } catch (Exception e) {
            return false;
        }
    }

    public void pickFistCar(){
        wait.until(ExpectedConditions.elementToBeClickable(firstCar)).click();
        wait.until(ExpectedConditions.elementToBeClickable(notebook)).click();
    }

    public void addNote(String article) {
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton)).click();
        WebElement artField = wait.until(ExpectedConditions.elementToBeClickable(artInput));
        artField.click();
        artField.clear();
        artField.sendKeys(article);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(brandInput)).click();
        WebElement countField = wait.until(ExpectedConditions.elementToBeClickable(CountInput));
        countField.click();
        countField.clear();
        countField.sendKeys("1");
        wait.until(ExpectedConditions.elementToBeClickable(noteSave)).click();

    }




}