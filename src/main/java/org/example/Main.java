package org.example;

import org.example.app.EmpleadoApp;
import org.example.repository.InMemoryEmpleadoRepository;
import org.example.service.EmpleadoService;

public class Main {
    public static void main(String[] args) {
        EmpleadoService empleadoService = new EmpleadoService(new InMemoryEmpleadoRepository());
        EmpleadoApp empleadoApp = new EmpleadoApp(empleadoService);
        empleadoApp.ejecutar();
    }
}
