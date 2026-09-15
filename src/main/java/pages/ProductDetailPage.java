package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BasePage {
    @FindBy(xpath = "//button[text()='Add to cart']") private WebElement addToCartButton;
    @FindBy(id = "back-to-products") private WebElement backButton;

    public ProductDetailPage(WebDriver driver) { super(driver); }

    public void clickAddToCart() { addToCartButton.click(); }
    public void clickBackButton() { backButton.click(); }
}
