package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPages extends BasePage {
    @FindBy(id = "checkout") private WebElement checkoutButton;
    @FindBy(id = "first-name") private WebElement firstNameInput;
    @FindBy(id = "last-name") private WebElement lastNameInput;
    @FindBy(id = "postal-code") private WebElement postalCodeInput;
    @FindBy(id = "continue") private WebElement continueButton;
    @FindBy(css = "h3[data-test='error']") private WebElement errorMessage;
    @FindBy(className = "summary_subtotal_label") private WebElement subtotalLabel;
    @FindBy(className = "summary_tax_label") private WebElement taxLabel;
    @FindBy(className = "summary_total_label") private WebElement totalLabel;
    @FindBy(css = ".input_error.error") private List<WebElement> errorFields;

    public CheckoutPages(WebDriver driver) { super(driver); }

    public void clickCheckout() { checkoutButton.click(); }

    public void fillCheckoutInformation(String fName, String lName, String zip) {
        firstNameInput.sendKeys(fName);
        lastNameInput.sendKeys(lName);
        postalCodeInput.sendKeys(zip);
    }

    public void clickContinue() { continueButton.click(); }
    public String getErrorMessage() { return errorMessage.getText(); }
    public boolean doAllFieldsHaveErrorClass() { return errorFields.size() == 3; }

    public double getSubtotal() {
        return Double.parseDouble(subtotalLabel.getText().replace("Item total: $", ""));
    }
    public double getTax() {
        return Double.parseDouble(taxLabel.getText().replace("Tax: $", ""));
    }
    public double getTotal() {
        return Double.parseDouble(totalLabel.getText().replace("Total: $", ""));
    }
}
