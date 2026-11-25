package EquipoMaraton;

import java.util.ArrayList;

public class EquipoMaratonProgramacion {
    private String nombreEquipo;
    private String universidad;
    private String lenguajeProgramacion;
    private ArrayList<Programador> programadores;

    public EquipoMaratonProgramacion(String nombreEquipo, String universidad, String lenguajeProgramacion) {
        this.nombreEquipo = nombreEquipo;
        this.universidad = universidad;
        this.lenguajeProgramacion = lenguajeProgramacion;
        this.programadores = new ArrayList<>();
    }

    public boolean estaLleno() {
        return programadores.size() >= 3;
    }

    public void anadirProgramador(Programador p) throws Exception {
        if (estaLleno()) {
            throw new Exception("El equipo ya está lleno (máximo 3 integrantes).");
        }
        programadores.add(p);
    }

    public static void validarCampo(String campo) throws Exception {
        if (campo == null || campo.trim().isEmpty()) {
            throw new Exception("El campo no puede estar vacío.");
        }
        for (int j = 0; j < campo.length(); j++) {
            char c = campo.charAt(j);
            if (Character.isDigit(c)) {
                throw new Exception("El nombre o apellido no puede contener dígitos numéricos.");
            }
        }
        if (campo.length() > 20) {
            throw new Exception("La longitud del campo no debe ser superior a 20 caracteres.");
        }
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public String getUniversidad() {
        return universidad;
    }

    public String getLenguajeProgramacion() {
        return lenguajeProgramacion;
    }

    public ArrayList<Programador> getProgramadores() {
        return programadores;
    }
}
