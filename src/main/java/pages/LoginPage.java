package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "user-name") private WebElement userNameTextBox;
    @FindBy(id = "password") private WebElement passwordTextBox;
    @FindBy(id = "login-button") private WebElement loginButton;

    public LoginPage(WebDriver driver) { super(driver); }

    public void setUserNameTextBox(String userName) { userNameTextBox.sendKeys(userName); }
    public void setPasswordTextBox(String password) { passwordTextBox.sendKeys(password); }
    public void clickOnLoginButton() { loginButton.click(); }
}
