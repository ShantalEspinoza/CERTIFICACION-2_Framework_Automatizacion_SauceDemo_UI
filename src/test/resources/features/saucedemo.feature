Feature: Validación de flujos principales y E2E en SauceDemo

  Background: Inicio de sesión exitoso
    Given el usuario se encuentra en la página de login de SauceDemo
    When el usuario ingresa las credenciales "standard_user" y "secret_sauce"
    Then el usuario es redirigido a la página principal

  Scenario Outline: Validar que la falta de código postal marca los campos con error
    Given el usuario agrega el producto "Sauce Labs Backpack" al carrito
    And el usuario navega a la página de checkout
    When el usuario llena el formulario con nombre "<firstName>", apellido "<lastName>" y código postal "<postalCode>"
    And hace clic en el botón continuar
    Then se muestra el mensaje de error "<expectedError>"
    And todos los campos se marcan con la clase de error

    Examples:
      | firstName | lastName | postalCode | expectedError                  |
      | Shantal   | Espinoza |            | Error: Postal Code is required |

  Scenario: Validar que el reinicio del estado de la aplicación limpia el carrito
    Given el usuario agrega el producto "Sauce Labs Backpack" al carrito
    And el contador del carrito muestra "1"
    When el usuario reinicia el estado de la aplicación y actualiza la página
    Then el contador del carrito debe estar vacío

  Scenario: Validar que el artículo permanece en el carrito al regresar de la página de detalles
    When el usuario hace clic en el producto "Sauce Labs Fleece Jacket"
    And hace clic en agregar al carrito en la página de detalles
    And hace clic en volver a los productos
    Then el contador del carrito muestra "1"
    And el botón del producto "Sauce Labs Fleece Jacket" cambia a "Remove"

  Scenario Outline: Validar que los productos pueden ser ordenados
    When el usuario selecciona la opción "<sortOption>" del combo de ordenamiento
    Then la lista de precios debe estar ordenada de "<sortType>"

    Examples:
      | sortOption          | sortType     |
      | Price (high to low) | mayor a menor|

  Scenario: Validar que el precio total calculado es correcto en el checkout
    Given el usuario agrega los siguientes productos al carrito:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    When el usuario navega a la página de checkout
    And el usuario llena el formulario con nombre "Juan", apellido "Perez" y código postal "0000"
    And hace clic en el botón continuar
    Then el precio total mostrado debe ser la suma exacta del subtotal y los impuestos