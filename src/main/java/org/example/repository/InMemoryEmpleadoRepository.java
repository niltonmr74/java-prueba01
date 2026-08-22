package org.example.repository;

import org.example.model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEmpleadoRepository implements EmpleadoRepository {
    private final List<Empleado> empleados = new ArrayList<>();

    @Override
    public void guardar(Empleado empleado) {
        empleados.add(empleado);
    }

    @Override
    public Empleado buscarPorId(Long id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }
        return null;
    }

    @Override
    public List<Empleado> listarTodos() {
        return List.copyOf(empleados);
    }
}
