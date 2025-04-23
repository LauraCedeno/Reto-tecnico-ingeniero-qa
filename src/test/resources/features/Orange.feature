Feature: Agregar nuevo empleado

  Scenario: Crear nuevo empleado exitosamente
    Given el usuario accede al login de OrangeHRM
    When ingresa credenciales válidas con usuario "Admin" y contraseña "admin123"
    When navega a la sección PIM y agrega un nuevo empleado con nombre "Laura", apellido "Andrade", email "pablo.doe@example.com"

  Scenario: Buscar un empleado por nombre
    When busca al empleado por nombre "Laura Andrade"
    Then los resultados deben mostrar al empleado con nombre "Laura" y apellido "Andrade"

  Scenario: Editar un empleado existente
    Given edita el empleado con nombre "Laura" cambiando el apellido a "Castro"
    When busca al empleado por nombre "Laura Castro"
    Then los resultados deben mostrar al empleado con nombre "Laura" y apellido "Castro"

  Scenario: Eliminar un empleado y validar que ya no aparece en la lista
    When elimina al empleado con nombre "Laura Castro"


 
   
