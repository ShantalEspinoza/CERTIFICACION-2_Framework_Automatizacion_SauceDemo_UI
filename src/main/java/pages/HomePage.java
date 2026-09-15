package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {
    @FindBy(className = "shopping_cart_link") private WebElement shoppingCart;
    @FindBy(className = "shopping_cart_badge") private List<WebElement> cartBadge;
    @FindBy(id = "react-burger-menu-btn") private WebElement menuButton;
    @FindBy(id = "reset_sidebar_link") private WebElement resetLink;
    @FindBy(className = "product_sort_container") private WebElement sortComboBox;
    @FindBy(className = "inventory_item_price") private List<WebElement> itemPrices;

    public HomePage(WebDriver driver) { super(driver); }

    public void clickShoppingCart() { shoppingCart.click(); }

    public void addProductToCart(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id("add-to-cart-" + formattedName)).click();
    }

    public String getShoppingCartBadgeText() {
        return cartBadge.isEmpty() ? "" : cartBadge.get(0).getText();
    }

    public void resetAppState() {
        menuButton.click();
        wait.until(d -> resetLink.isDisplayed());
        resetLink.click();
    }

    public void clickProductImageOrName(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']")).click();
    }

    public String getProductButtonText(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        return driver.findElement(By.id("remove-" + formattedName)).getText();
    }

    public void selectSortComboBox(String option) {
        new Select(sortComboBox).selectByVisibleText(option);
    }

    public List<Double> getProductPrices() {
        return itemPrices.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }
}