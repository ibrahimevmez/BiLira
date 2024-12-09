package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;


public class SwapPage {
    public SwapPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//span[.='Login']")
    public WebElement loginButton;

    @FindBy(xpath = "//input[@id='username']")
    public WebElement username;

    @FindBy(xpath = "//button[@id='password']")
    public WebElement password;

    @FindBy(xpath = "(//button[@class='usdtbalance'])[2]")
    public WebElement usdtbalance;

    @FindBy(xpath = "//input[@id='swap']")
    public WebElement swap;

    @FindBy(xpath = "//button[@id='sourceDropdown']")
    public WebElement sourceDropdown ;

    @FindBy(xpath = "//button[@id='sourceDropdown']")
    public WebElement targetDropdown ;

    @FindBy(xpath = "//button[@id='amount']")
    public WebElement amount ;

    @FindBy(xpath = "//img[@class='confirmSwapButton']")
    public WebElement confirmSwapButton;

    @FindBy(xpath = "(//span[@class='successMessage'])")
    public  WebElement successMessage;

    @FindBy(xpath = "(//span[@class='btcBalance'])")
    public  WebElement btcBalance;

    @FindBy(xpath = "(//span[@class='history'])")
    public  WebElement history;


    @FindBy(xpath = "(//span[@class='errorMessage']")
    public  WebElement errorMessage;

    @FindBy(xpath = "(//span[@class='exchangeRate']")
    public  WebElement  exchangeRate;







}
