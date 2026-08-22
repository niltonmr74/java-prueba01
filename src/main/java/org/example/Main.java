package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // crear al empleado Juan con id 1, nombre Juan, apellido Perez, edad 30 y salario 2000.0
    public static void main(String[] args) {
        Empleado empleado = new Empleado(1L, "Juan", "Perez", 30, 2000.0);
        System.out.println("Empleado: " + empleado.getNombre() + " " + empleado.getApellido());
        System.out.println("Edad: " + empleado.getEdad());
        System.out.println("Salario mensual: " + empleado.getSalario());
        System.out.println("Salario anual: " + empleado.salarioAnual());
    }
}
