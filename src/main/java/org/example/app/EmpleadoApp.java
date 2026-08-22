package org.example.app;

import org.example.model.Empleado;
import org.example.service.EmpleadoService;

public class EmpleadoApp {
    private final EmpleadoService empleadoService;

    public EmpleadoApp(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public void ejecutar() {
        empleadoService.registrarEmpleado(new Empleado(1L, "Juan", "Perez", 30, 2000.0));
        empleadoService.registrarEmpleado(new Empleado(2L, "Ana", "Gomez", 28, 2500.0));

        System.out.println("=== Lista inicial ===");
        imprimirEmpleados();

        Empleado encontrado = empleadoService.buscarPorId(1L);
        System.out.println("Buscado por id=1: " + encontrado.getNombre() + " " + encontrado.getApellido());

        Empleado empleadoActualizado = new Empleado(2L, "Ana", "Martinez", 28, 2800.0);
        empleadoService.actualizarEmpleado(empleadoActualizado);

        empleadoService.eliminarEmpleado(1L);

        System.out.println("=== Lista final ===");
        imprimirEmpleados();
    }

    private void imprimirEmpleados() {
        for (Empleado empleado : empleadoService.listarEmpleados()) {
            System.out.println("Id: " + empleado.getId()
                    + ", Nombre: " + empleado.getNombre() + " " + empleado.getApellido()
                    + ", Edad: " + empleado.getEdad()
                    + ", Salario mensual: " + empleado.getSalario()
                    + ", Salario anual: " + empleado.salarioAnual());
        }
    }
}
