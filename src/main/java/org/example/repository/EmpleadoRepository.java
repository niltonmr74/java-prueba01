package org.example.repository;

import org.example.model.Empleado;

import java.util.List;

public interface EmpleadoRepository {
    void guardar(Empleado empleado);
    Empleado buscarPorId(Long id);
    List<Empleado> listarTodos();
}
