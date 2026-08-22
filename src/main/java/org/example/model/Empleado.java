package org.example.model;

public class Empleado {
// crear un emppleado con id tipo Long, nombre, apellido, edad y salario
// il id debe ser long y estar en el constructor, el resto de los atributos deben ser privados y tener sus getters y setters
    private String nombre;
    private String apellido;
    private int edad;
    private double salario;
    private Long id;

    public Empleado(Long id, String nombre, String apellido, int edad, double salario ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.salario = salario;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    // crear un metodo que devuelva el salario anual del empleado
    public double salarioAnual() {
        return salario * 12;
    }
}