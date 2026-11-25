package Vendedor;

/**
 * Clase Vendedor que aplica la sentencia throw para lanzar 
 * una excepción IllegalArgumentException si la edad ingresada no es válida.
 */
public class Vendedor {
    private String nombre;
    private String apellidos;
    private int edad;

    public Vendedor(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public void verificarEdad(int edad) {
        if (edad < 18) {
            throw new IllegalArgumentException("El vendedor debe ser mayor de 18 años.");
        }
        if (edad >= 18 && edad <= 120) {
            this.edad = edad;
        } else {
            throw new IllegalArgumentException("La edad no puede ser negativa ni mayor a 120 años.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }
}
