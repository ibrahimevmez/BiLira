package stepdefinitions;

import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.Select;
import pages.SwapPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import static org.junit.Assert.*;

public class SwapStepDefs {
   SwapPage swapPage = new SwapPage();

    @Given("User logs in his/her account")
    public void user_logs_in() {
        Driver.getDriver().get("https://crypto-exchange.example.com/login");
        ReusableMethods.waitFor(1);
        swapPage.loginButton.click();
        ReusableMethods.waitFor(1);
        swapPage.username.click();
        swapPage.username.sendKeys(ConfigReader.getProperty("username"), Keys.TAB,
                ConfigReader.getProperty("testtest"),Keys.ENTER);
    }

    @And("User has {int} USDT balance")
    public void user_has_balance(int balance) {
        WebElement balanceField = swapPage.usdtbalance;
        balanceField.clear();
        balanceField.sendKeys(String.valueOf(balance));
    }

    @When("User navigates to the Swap page")
    public void navigate_to_swap_page() {
        ReusableMethods.clickWithJS(swapPage.swap);
    }

    @When("User selects {string} as the source currency")
    public void select_source_currency(String sourceCurrency) {
        Select dropdown = new Select(swapPage.sourceDropdown);
        dropdown.selectByVisibleText(sourceCurrency);
    }

    @When("User selects {string} as the target currency")
    public void select_target_currency(String targetCurrency) {
        Select dropdown = new Select(swapPage.targetDropdown);
        dropdown.selectByVisibleText(targetCurrency);
    }

    @When("User enters {string} as the amount")
    public void enter_amount(String amount) {
        WebElement amountField = swapPage.amount;
        amountField.clear();
        amountField.sendKeys(amount);
    }

    @When("User confirms the swap")
    public void confirm_swap() {
        ReusableMethods.waitFor(2);
       ReusableMethods.clickWithJS(swapPage.confirmSwapButton);
    }

    @Then("The swap is executed successfully")
    public void verify_swap_execution() {
        ReusableMethods.waitFor(2);
        WebElement successMessage = swapPage.successMessage;
        assertTrue(successMessage.getText().contains("Swap executed successfully"));
    }

    @Then("User sees that his/her BTC balance is updated")
    public void verify_btc_balance() {
        ReusableMethods.waitFor(2);
        WebElement btcBalance = swapPage.btcBalance;
        assertNotNull(btcBalance.getText());
    }

    @Then("User sees that his/her USDT balance is deducted")
    public void verify_usdt_balance_deduction() {
        ReusableMethods.waitFor(2);
        WebElement usdtBalance = swapPage.usdtbalance;
        assertNotEquals("500", usdtBalance.getText());
    }

    @Then("The transaction is recorded in the Swap history")
    public void verify_transaction_history() {
        ReusableMethods.waitFor(2);
        ReusableMethods.clickWithJS(swapPage.history);
        WebElement transactionRecord = swapPage.history;
        assertTrue(transactionRecord.isDisplayed());
    }

    @When("User attempts to swap {string} to {string}")
    public void attempt_swap(String amount, String targetCurrency) {
        ReusableMethods.waitFor(2);
        WebElement amountField = swapPage.amount;
        amountField.clear();
        amountField.sendKeys(amount);

        WebElement targetDropdown = Driver.getDriver().findElement(By.id("targetCurrency"));
        targetDropdown.click();
        Driver.getDriver().findElement(By.xpath("//option[text()='" + targetCurrency + "']")).click();

        Driver.getDriver().findElement(By.id("confirmSwapButton")).click();
    }

    @Then("User gets an error message stating {string}")
    public void verify_error_message(String errorMessage) {
        WebElement errorElement = swapPage.errorMessage;
        assertTrue(errorElement.getText().contains(errorMessage));
    }

    @Then("User sees no changes are made to his/her balances")
    public void verify_no_balance_changes() {
        WebElement usdtBalance = swapPage.usdtbalance;
        assertEquals("10", usdtBalance.getText());
    }

    @Then("User sees that the displayed exchange rate is updated every {int} seconds")
    public void verify_exchange_rate_update(int interval) throws InterruptedException {
        WebElement rateElement = swapPage.exchangeRate;
        String initialRate = rateElement.getText();
        ReusableMethods.waitFor(2);
        String updatedRate = rateElement.getText();
        assertNotEquals(initialRate, updatedRate);
    }

    @Then("User gets an error message stating {string}")
    public void verify_error_minimum_or_maximum(String errorMessage) {
        ReusableMethods.waitFor(2);
        WebElement errorElement = swapPage.errorMessage;
        assertTrue(errorElement.getText().contains(errorMessage));
    }

    @Then("User sees that no changes are made to his/her balances")
    public void verify_no_balance_change_min_max() {
        WebElement usdtBalance = swapPage.usdtbalance;
        assertEquals("500", usdtBalance.getText());
    }

    @After
    public void tearDown() {
        Driver.getDriver().quit();
    }
}
