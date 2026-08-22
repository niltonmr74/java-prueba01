package org.example.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.model.Empleado;
import org.example.repository.InMemoryEmpleadoRepository;
import org.example.service.EmpleadoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmpleadoSteps {
    private EmpleadoService empleadoService;
    private IllegalArgumentException ultimaExcepcion;
    private boolean resultadoBusquedaVacio;

    @Given("un servicio de empleados vacio")
    public void unServicioDeEmpleadosVacio() {
        empleadoService = new EmpleadoService(new InMemoryEmpleadoRepository());
    }

    @When("registro un empleado con id {long} nombre {string} apellido {string} edad {int} salario {double}")
    public void registroUnEmpleadoConIdNombreApellidoEdadSalario(Long id, String nombre, String apellido, Integer edad, Double salario) {
        empleadoService.registrarEmpleado(new Empleado(id, nombre, apellido, edad, salario));
    }

    @Given("existe un empleado con id {long} nombre {string} apellido {string} edad {int} salario {double}")
    public void existeUnEmpleadoConIdNombreApellidoEdadSalario(Long id, String nombre, String apellido, Integer edad, Double salario) {
        empleadoService.registrarEmpleado(new Empleado(id, nombre, apellido, edad, salario));
    }

    @When("actualizo el empleado con id {long} nombre {string} apellido {string} edad {int} salario {double}")
    public void actualizoElEmpleadoConIdNombreApellidoEdadSalario(Long id, String nombre, String apellido, Integer edad, Double salario) {
        empleadoService.actualizarEmpleado(new Empleado(id, nombre, apellido, edad, salario));
    }

    @When("elimino el empleado con id {long}")
    public void eliminoElEmpleadoConId(Long id) {
        empleadoService.eliminarEmpleado(id);
    }

    @When("intento registrar un empleado con id {long} nombre {string} apellido {string} edad {int} salario {double}")
    public void intentoRegistrarUnEmpleadoConIdNombreApellidoEdadSalario(Long id, String nombre, String apellido, Integer edad, Double salario) {
        try {
            empleadoService.registrarEmpleado(new Empleado(id, nombre, apellido, edad, salario));
            ultimaExcepcion = null;
        } catch (IllegalArgumentException exception) {
            ultimaExcepcion = exception;
        }
    }

    @When("intento buscar empleado con id {long}")
    public void intentoBuscarEmpleadoConId(Long id) {
        resultadoBusquedaVacio = empleadoService.buscarPorId(id).isEmpty();
        ultimaExcepcion = null;
    }

    @When("intento eliminar empleado con id {long}")
    public void intentoEliminarEmpleadoConId(Long id) {
        try {
            empleadoService.eliminarEmpleado(id);
            ultimaExcepcion = null;
        } catch (IllegalArgumentException exception) {
            ultimaExcepcion = exception;
        }
    }

    @Then("la cantidad de empleados debe ser {int}")
    public void laCantidadDeEmpleadosDebeSer(Integer cantidadEsperada) {
        assertEquals(cantidadEsperada, empleadoService.listarEmpleados().size());
    }

    @Then("el empleado con id {long} debe tener apellido {string} y salario {double}")
    public void elEmpleadoConIdDebeTenerApellidoYSalario(Long id, String apellidoEsperado, Double salarioEsperado) {
        Empleado empleado = empleadoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un empleado con id " + id));
        assertEquals(apellidoEsperado, empleado.getApellido());
        assertEquals(salarioEsperado, empleado.getSalario());
    }

    @Then("debe mostrarse el error {string}")
    public void debeMostrarseElError(String mensajeEsperado) {
        assertNotNull(ultimaExcepcion);
        assertEquals(mensajeEsperado, ultimaExcepcion.getMessage());
    }

    @Then("el resultado de la busqueda debe estar vacio")
    public void elResultadoDeLaBusquedaDebeEstarVacio() {
        assertEquals(true, resultadoBusquedaVacio);
    }
}
