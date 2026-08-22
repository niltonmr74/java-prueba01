Feature: Gestion de empleados

  Scenario: Registrar y listar empleados
    Given un servicio de empleados vacio
    When registro un empleado con id 1 nombre "Juan" apellido "Perez" edad 30 salario 2000.0
    Then la cantidad de empleados debe ser 1

  Scenario: Actualizar empleado existente
    Given un servicio de empleados vacio
    Given existe un empleado con id 2 nombre "Ana" apellido "Gomez" edad 28 salario 2500.0
    When actualizo el empleado con id 2 nombre "Ana" apellido "Martinez" edad 28 salario 2800.0
    Then el empleado con id 2 debe tener apellido "Martinez" y salario 2800.0

  Scenario: Eliminar empleado existente
    Given un servicio de empleados vacio
    Given existe un empleado con id 3 nombre "Luis" apellido "Diaz" edad 35 salario 3000.0
    When elimino el empleado con id 3
    Then la cantidad de empleados debe ser 0
