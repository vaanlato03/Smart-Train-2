package cl.unab.ptec102.smarttrain.backend;

public class Ejercicio {
    // Atributos privados
    private int codigo;
    private String nombre;
    private String descripcion;
    private int tiempoMinutos;

    // Constructor
    public Ejercicio(int codigo, String nombre, String descripcion,
                     int intensidad, int ultimaSemana, int tiempoMinutos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.intensidad = intensidad;
        this.ultimaSemana = ultimaSemana;
        this.tiempoMinutos = tiempoMinutos;
    }

    // Getters
    public String getNombre() { return this.nombre; }
    public String getDescripcion() { return this.descripcion; }
    public int getIntensidad() { return this.intensidad; }
    public int getUltimaSemana() { return this.ultimaSemana; }
    public int getTiempoMinutos() { return this.tiempoMinutos; }
    public String getIntensidadString() {
        return switch (this.intensidad) {
            case 1 -> "Basico";
            case 2 -> "Intermedio";
            case 3 -> "Avanzado";
            case 4 -> "Alto Rendimiento";
            default -> "Desconocido";
        };
    }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setIntensidad(int intensidad) { this.intensidad = intensidad; }
    public void setUltimaSemana(int ultimaSemana) { this.ultimaSemana = ultimaSemana; }
    public void setTiempoMinutos(int tiempo) { this.tiempoMinutos = tiempo; }

    public String mostrarInfo() {
        return "   Tipo        : " + this.getTipoString() + "\n" +
                "   Intensidad  : " + this.getIntensidadString() + "\n" +
                "   Tiempo      : " + this.tiempoMinutos + " min" + "\n" +
                "   Descripcion : " + this.descripcion + "\n";
    }

    public int getTipo() {
        return 0;
    }

    public String getTipoString() {
        return null;
    }
}