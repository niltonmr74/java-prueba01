package org.example.service;

import org.example.model.Empleado;
import org.example.repository.InMemoryEmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmpleadoServiceTest {

    private EmpleadoService empleadoService;

    @BeforeEach
    void setUp() {
        empleadoService = new EmpleadoService(new InMemoryEmpleadoRepository());
    }

    @Test
    void debeRegistrarEmpleadoValido() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));

        assertEquals(1, empleadoService.listarEmpleados().size());
    }

    @Test
    void noDebeRegistrarEmpleadoConIdDuplicado() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> empleadoService.registrarEmpleado(new Empleado(1L, "Ana", "Gomez", 28, 2500.0)));

        assertEquals("Ya existe un empleado con id 1", exception.getMessage());
    }

    @Test
    void debeBuscarEmpleadoPorIdExistente() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));

        Empleado empleado = empleadoService.buscarPorId(1L);

        assertNotNull(empleado);
        assertEquals("Juan", empleado.getNombre());
    }

    @Test
    void debeFallarSiBuscaIdNoExistente() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> empleadoService.buscarPorId(99L));

        assertEquals("No existe un empleado con id 99", exception.getMessage());
    }

    @Test
    void debeActualizarEmpleadoExistente() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));

        empleadoService.actualizarEmpleado(new Empleado(1L, "Juan", "Lopez", 30, 2500.0));

        Empleado actualizado = empleadoService.buscarPorId(1L);
        assertEquals("Lopez", actualizado.getApellido());
        assertEquals(2500.0, actualizado.getSalario());
    }

    @Test
    void debeEliminarEmpleadoExistente() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));

        empleadoService.eliminarEmpleado(1L);

        assertEquals(0, empleadoService.listarEmpleados().size());
    }

    @Test
    void debeFallarConNombreVacio() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> empleadoService.registrarEmpleado(new Empleado(1L, "", "Perez", 30, 2000.0)));

        assertEquals("El nombre no puede estar vacio", exception.getMessage());
    }

    @Test
    void debeFallarConEdadNegativa() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", -1, 2000.0)));

        assertEquals("La edad no puede ser negativa", exception.getMessage());
    }

    @Test
    void debeFallarConSalarioNegativo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, -10.0)));

        assertEquals("El salario no puede ser negativo", exception.getMessage());
    }
}
