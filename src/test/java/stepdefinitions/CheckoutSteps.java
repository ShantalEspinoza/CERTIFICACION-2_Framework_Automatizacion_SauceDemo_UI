package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.CheckoutPages;
import pages.HomePage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckoutSteps {
    private CheckoutPages checkout = new CheckoutPages(Hooks.getDriver());
    private HomePage homePage = new HomePage(Hooks.getDriver());

    @Given("el usuario navega a la página de checkout")
    public void elUsuarioNavegaALaPaginaDeCheckout() {
        homePage.clickShoppingCart();
        checkout.clickCheckout();
    }

    @When("el usuario llena el formulario con nombre {string}, apellido {string} y código postal {string}")
    public void elUsuarioLlenaElFormularioConNombreApellidoYCodigoPostal(String fName, String lName, String zip) {
        checkout.fillCheckoutInformation(fName, lName, zip);
    }

    @When("hace clic en el botón continuar")
    public void haceClicEnElBotonContinuar() {
        checkout.clickContinue();
    }

    @Then("se muestra el mensaje de error {string}")
    public void seMuestraElMensajeDeError(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, checkout.getErrorMessage());
    }

    @Then("todos los campos se marcan con la clase de error")
    public void todosLosCamposSeMarcanConLaClaseDeError() {
        Assertions.assertTrue(checkout.doAllFieldsHaveErrorClass(), "Los campos no marcaron la clase CSS roja.");
    }

    @Then("el precio total mostrado debe ser la suma exacta del subtotal y los impuestos")
    public void elPrecioTotalMostradoDebeSerLaSumaExactaDelSubtotalYLosImpuestos() {
        BigDecimal subtotal = BigDecimal.valueOf(checkout.getSubtotal());
        BigDecimal tax = BigDecimal.valueOf(checkout.getTax());
        BigDecimal expectedTotal = subtotal.add(tax).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualTotalDisplayed = BigDecimal.valueOf(checkout.getTotal());

        Assertions.assertEquals(expectedTotal, actualTotalDisplayed, "Discrepancia en el cálculo del total.");
    }
}
