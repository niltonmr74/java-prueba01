package org.example.repository;

import org.example.model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEmpleadoRepository implements EmpleadoRepository {
    private final List<Empleado> empleados = new ArrayList<>();

    @Override
    public void guardar(Empleado empleado) {
        //agregar print para indicar que se está guardando un empleado en memoria
        System.out.println("Guardando empleado con id: " + empleado.getId());

        empleados.add(empleado);
    }

    @Override
    public Empleado buscarPorId(Long id) {
        //agregar print para indicar que se está buscando un empleado por id en memoria y el resultado
        System.out.println("Buscando empleado con id: " + id);

        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }
        return null;
    }
    //agregar print para indicar que se está listando todos los empleados en memoria
    @Override
    public List<Empleado> listarTodos() {
        System.out.println("Listando todos los empleados en memoria");
        return List.copyOf(empleados);
    }

    @Override
    public void actualizar(Empleado empleadoActualizado) {
        //agregar print para indicar que se está actualizando un empleado en memoria con el detalle del empleado actualizado
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
        //agregar print para indicar que se está eliminando un empleado por id en memoria
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
