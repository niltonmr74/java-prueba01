package org.example.repository;

import org.example.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository {
   void guardar(Empleado empleado);
   Optional<Empleado> buscarPorId(Long id);
   List<Empleado> listarTodos();
   void actualizar(Empleado empleado);
   void eliminarPorId(Long id);
}
