package org.example.service;

import org.example.model.Empleado;
import org.example.repository.EmpleadoRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public void registrarEmpleado(Empleado empleado) {
        validarEmpleado(empleado);

        if (empleadoRepository.buscarPorId(empleado.getId()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con id " + empleado.getId());
        }

        empleadoRepository.guardar(empleado);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.listarTodos();
    }

    public Optional<Empleado> buscarPorId(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        return empleadoRepository.listarTodos()
                .stream()
                .filter(empleado -> empleado.getId().equals(id))
                .findFirst();
    }

    public List<Empleado> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return List.of();
        }

        return empleadoRepository.listarTodos()
                .stream()
                .filter(empleado -> empleado.getNombre().equalsIgnoreCase(nombre))
                .toList();
    }

    public List<Empleado> listarEmpleadosOrdenadosPorApellido() {
        return empleadoRepository.listarTodos()
                .stream()
                .sorted(Comparator.comparing(Empleado::getApellido))
                .toList();
    }

    public List<Empleado> listarEmpleadosConSalarioMayor(double salarioMinimo) {
        return empleadoRepository.listarTodos()
                .stream()
                .filter(empleado -> empleado.getSalario() > salarioMinimo)
                .toList();
    }

    public double totalSalarios() {
        return empleadoRepository.listarTodos()
                .stream()
                .mapToDouble(Empleado::getSalario)
                .sum();
    }

    public void actualizarEmpleado(Empleado empleado) {
        validarEmpleado(empleado);
        buscarPorId(empleado.getId())
                .orElseThrow(() -> new IllegalArgumentException("No existe un empleado con id " + empleado.getId()));
        empleadoRepository.actualizar(empleado);
    }

    public void eliminarEmpleado(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser null");
        }

        Empleado empleado = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un empleado con id " + id));

        empleadoRepository.eliminarPorId(empleado.getId());
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
