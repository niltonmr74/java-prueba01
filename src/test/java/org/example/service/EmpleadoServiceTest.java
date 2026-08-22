package org.example.service;

import org.example.model.Empleado;
import org.example.repository.InMemoryEmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void debeBuscarEmpleadoPorIdConOptional() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));

        Optional<Empleado> resultado = empleadoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getNombre());
    }

    @Test
    void debeRetornarEmptyCuandoNoExisteEmpleado() {
        Optional<Empleado> resultado = empleadoService.buscarPorId(99L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void debeBuscarEmpleadoPorNombre() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));
        empleadoService.registrarEmpleado(new Empleado(2L, "Ana", "Gomez", 28, 2500.0));

        List<Empleado> resultado = empleadoService.buscarPorNombre("juan");

        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    void debeListarEmpleadosOrdenadosPorApellido() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));
        empleadoService.registrarEmpleado(new Empleado(2L, "Ana", "Gomez", 28, 2500.0));

        List<Empleado> resultado = empleadoService.listarEmpleadosOrdenadosPorApellido();

        assertEquals("Gomez", resultado.get(0).getApellido());
        assertEquals("Perez", resultado.get(1).getApellido());
    }

    @Test
    void debeFiltrarEmpleadosConSalarioMayor() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));
        empleadoService.registrarEmpleado(new Empleado(2L, "Ana", "Gomez", 28, 2500.0));
        empleadoService.registrarEmpleado(new Empleado(3L, "Luis", "Diaz", 35, 3000.0));

        List<Empleado> resultado = empleadoService.listarEmpleadosConSalarioMayor(2500.0);

        assertEquals(1, resultado.size());
        assertEquals("Luis", resultado.get(0).getNombre());
    }

    @Test
    void debeCalcularTotalSalarios() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));
        empleadoService.registrarEmpleado(new Empleado(2L, "Ana", "Gomez", 28, 2500.0));

        assertEquals(4500.0, empleadoService.totalSalarios());
    }

    @Test
    void noDebeRegistrarEmpleadoConIdDuplicado() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> empleadoService.registrarEmpleado(new Empleado(1L, "Ana", "Gomez", 28, 2500.0))
        );

        assertEquals("Ya existe un empleado con id 1", exception.getMessage());
    }

    @Test
    void debeFallarConNombreVacio() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> empleadoService.registrarEmpleado(new Empleado(1L, "", "Perez", 30, 2000.0))
        );

        assertEquals("El nombre no puede estar vacio", exception.getMessage());
    }

    @Test
    void debeFallarConSalarioNegativo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, -50.0))
        );

        assertEquals("El salario no puede ser negativo", exception.getMessage());
    }
}
