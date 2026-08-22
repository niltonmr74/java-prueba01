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
        validarEmpleado(empleado);
        if (empleadoRepository.buscarPorId(empleado.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con id " + empleado.getId());
        }

        empleadoRepository.guardar(empleado);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.listarTodos();
    }

    public Empleado buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser null");
        }

        Empleado empleado = empleadoRepository.buscarPorId(id);
        if (empleado == null) {
            throw new IllegalArgumentException("No existe un empleado con id " + id);
        }

        return empleado;
    }

    public void actualizarEmpleado(Empleado empleado) {
        validarEmpleado(empleado);
        buscarPorId(empleado.getId());
        empleadoRepository.actualizar(empleado);
    }

    public void eliminarEmpleado(Long id) {
        buscarPorId(id);
        empleadoRepository.eliminarPorId(id);
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser null");
        }
        if (empleado.getId() == null) {
            throw new IllegalArgumentException("El id no puede ser null");
        }
        if (empleado.getNombre() == null || empleado.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (empleado.getApellido() == null || empleado.getApellido().isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
        if (empleado.getEdad() < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        if (empleado.getSalario() < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo");
        }
    }
}
