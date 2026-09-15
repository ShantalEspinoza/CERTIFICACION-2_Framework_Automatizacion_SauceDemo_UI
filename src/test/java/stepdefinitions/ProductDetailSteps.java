package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.When;
import pages.ProductDetailPage;

public class ProductDetailSteps {
    private ProductDetailPage detailPage = new ProductDetailPage(Hooks.getDriver());

    @When("hace clic en agregar al carrito en la página de detalles")
    public void haceClicEnAgregarAlCarritoEnLaPaginaDeDetalles() {
        detailPage.clickAddToCart();
    }

    @When("hace clic en volver a los productos")
    public void haceClicEnVolverALosProductos() {
        detailPage.clickBackButton();
    }
}
