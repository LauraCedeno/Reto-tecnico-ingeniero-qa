Feature: Agregar nuevo empleado

  Scenario: Crear nuevo empleado exitosamente
    Given el usuario accede al login de OrangeHRM
    When ingresa credenciales válidas con usuario "Admin" y contraseña "admin123"
    When navega a la sección PIM y agrega un nuevo empleado con nombre "John", apellido "Doe", email "john.doe@example.com" y departamento "Engineering"
    Then debería ver el nuevo empleado listado correctamente
   
