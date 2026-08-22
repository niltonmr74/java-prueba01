package org.example.service;

import org.example.model.Empleado;
import org.example.repository.EmpleadoRepository;

import java.util.List;

public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public void registrarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser null");
        }

        if (empleadoRepository.buscarPorId(empleado.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con id " + empleado.getId());
        }

        empleadoRepository.guardar(empleado);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.listarTodos();
    }
}
