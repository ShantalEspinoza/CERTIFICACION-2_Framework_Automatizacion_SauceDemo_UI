package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;

public class LoginSteps {
    private LoginPage loginPage = new LoginPage(Hooks.getDriver());

    @Given("el usuario se encuentra en la página de login de SauceDemo")
    public void elUsuarioSeEncuentraEnLaPaginaDeLoginDeSauceDemo() {
        Hooks.getDriver().get("https://www.saucedemo.com/");
    }

    @When("el usuario ingresa las credenciales {string} y {string}")
    public void elUsuarioIngresaLasCredencialesY(String username, String password) {
        loginPage.setUserNameTextBox(username);
        loginPage.setPasswordTextBox(password);
        loginPage.clickOnLoginButton();
    }

    @Then("el usuario es redirigido a la página principal")
    public void elUsuarioEsRedirigidoALaPaginaPrincipal() {
        Assertions.assertTrue(Hooks.getDriver().getCurrentUrl().contains("inventory.html"),
                "El login falló o no redirigió correctamente.");
    }
}
