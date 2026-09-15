package stepdefinitions;

import com.google.common.collect.Ordering;
import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;

import java.util.List;

public class HomeSteps {
    private HomePage homePage = new HomePage(Hooks.getDriver());

    @Given("el usuario agrega el producto {string} al carrito")
    public void elUsuarioAgregaElProductoAlCarrito(String product) {
        homePage.addProductToCart(product);
    }

    @Given("el usuario agrega los siguientes productos al carrito:")
    public void elUsuarioAgregaLosSiguientesProductosAlCarrito(DataTable dataTable) {
        List<String> products = dataTable.asList();
        for (String product : products) {
            homePage.addProductToCart(product);
        }
    }

    @Then("el contador del carrito muestra {string}")
    public void elContadorDelCarritoMuestra(String quantity) {
        Assertions.assertEquals(quantity, homePage.getShoppingCartBadgeText());
    }

    @Then("el contador del carrito debe estar vacío")
    public void elContadorDelCarritoDebeEstarVacio() {
        Assertions.assertEquals("", homePage.getShoppingCartBadgeText(), "El contador no se limpió.");
    }

    @When("el usuario reinicia el estado de la aplicación y actualiza la página")
    public void elUsuarioReiniciaElEstadoDeLaAplicacionYActualizaLaPagina() {
        homePage.resetAppState();
        Hooks.getDriver().navigate().refresh();
    }

    @When("el usuario hace clic en el producto {string}")
    public void elUsuarioHaceClicEnElProducto(String product) {
        homePage.clickProductImageOrName(product);
    }

    @Then("el botón del producto {string} cambia a {string}")
    public void elBotonDelProductoCambiaA(String product, String expectedText) {
        Assertions.assertTrue(homePage.getProductButtonText(product).equalsIgnoreCase(expectedText));
    }

    @When("el usuario selecciona la opción {string} del combo de ordenamiento")
    public void elUsuarioSeleccionaLaOpcionDelComboDeOrdenamiento(String option) {
        homePage.selectSortComboBox(option);
    }

    @Then("la lista de precios debe estar ordenada de {string}")
    public void laListaDePreciosDebeEstarOrdenadaDe(String sortType) {
        List<Double> actualPrices = homePage.getProductPrices();
        boolean isSorted = Ordering.natural().reverse().isOrdered(actualPrices);
        Assertions.assertTrue(isSorted, "La lista no se ordenó de mayor a menor.");
    }
}
