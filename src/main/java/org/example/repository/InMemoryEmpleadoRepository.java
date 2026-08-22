package org.example.repository;

import org.example.model.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryEmpleadoRepository implements EmpleadoRepository {
    private final List<Empleado> empleados = new ArrayList<>();

    @Override
    public void guardar(Empleado empleado) {
        System.out.println("Guardando empleado con id: " + empleado.getId());
        empleados.add(empleado);
    }

    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        System.out.println("Buscando empleado con id: " + id);

        return empleados.stream()
                .filter(empleado -> empleado.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Empleado> listarTodos() {
        System.out.println("Listando todos los empleados en memoria");
        return List.copyOf(empleados);
    }

    @Override
    public void actualizar(Empleado empleadoActualizado) {
        System.out.println("Actualizando empleado con id: " + empleadoActualizado.getId());

        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            if (empleado.getId().equals(empleadoActualizado.getId())) {
                empleados.set(i, empleadoActualizado);
                return;
            }
        }

        throw new IllegalArgumentException("No existe un empleado con id " + empleadoActualizado.getId());
    }

    @Override
    public void eliminarPorId(Long id) {
        System.out.println("Eliminando empleado con id: " + id);

        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            if (empleado.getId().equals(id)) {
                empleados.remove(i);
                return;
            }
        }

        throw new IllegalArgumentException("No existe un empleado con id " + id);
    }
}
