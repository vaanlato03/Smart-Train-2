package cl.unab.ptec102.smarttrain.backend;

import cl.unab.ptec102.smarttrain.frontend.Estilos;

import java.util.ArrayList;

public class Rutina {

    // Atributos privados
    private String cliente;
    private int nivelIntensidad;
    private ArrayList<Ejercicio> ejercicios;

    // Constructor
    public Rutina(String cliente, int nivelIntensidad) {
        this.cliente = cliente;
        this.nivelIntensidad = nivelIntensidad;
        this.ejercicios = new ArrayList<>();
    }

    // Getters
    public String getCliente() { return this.cliente; }
    public int getNivelIntensidad() { return this.nivelIntensidad; }
    public ArrayList<Ejercicio> getEjercicios() { return this.ejercicios; }
    public String getNivelString() {
        return switch (this.nivelIntensidad) {
            case 1 -> "Basico";
            case 2 -> "Intermedio";
            case 3 -> "Avanzado";
            case 4 -> "Alto Rendimiento";
            default -> "Desconocido";
        };
    }

    public void agregarEjercicio(Ejercicio e) {
        this.ejercicios.add(e);
    }

    public void eliminarEjercicio(int indice) {
        this.ejercicios.remove(indice);
    }

    public int calcularTiempoTotal() {
        int total = 0;
        for (Ejercicio e : this.ejercicios) {
            total += e.getTiempoMinutos();
        }
        return total;
    }

    public int contarPorTipo(int tipo) {
        int count = 0;
        for (Ejercicio e : this.ejercicios) {
            if (e.getTipo() == tipo) count++;
        }
        return count;
    }

    public int contarPorIntensidad(int intensidad) {
        int count = 0;
        for (Ejercicio e : this.ejercicios) {
            if (e.getIntensidad() == intensidad) count++;
        }
        return count;
    }

    public String mostrarRutina() {
        StringBuilder sb = new StringBuilder();
        sb.append(Estilos.lineaDecorativa);
        sb.append("          RUTINA DE ENTRENAMIENTO\n");
        sb.append(Estilos.lineaDecorativa);
        sb.append("Cliente: ").append(this.cliente).append("\n");
        sb.append("Intensidad: ").append(this.getNivelString()).append("\n");
        sb.append("Tiempo: ").append(this.calcularTiempoTotal()).append(" minutos").append("\n");
        sb.append("Ejercicios: ").append(this.ejercicios.size()).append("\n");
        sb.append(Estilos.lineaDecorativa);
        sb.append("\n");

        int i = 1;
        for (Ejercicio e : this.ejercicios) {
            sb.append(i++).append(". ").append(e.getNombre()).append("\n");
            sb.append(e.mostrarInfo()).append("\n");
        }
        sb.append(Estilos.lineaDecorativa);
        sb.append("          GENERADO POR SMARTTRAIN\n");
        sb.append(Estilos.lineaDecorativa);

        return sb.toString();
    }
}