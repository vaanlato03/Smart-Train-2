package cl.unab.ptec102.smarttrain.backend;

public class Ejercicio {
    // Atributos privados
    private int codigo;
    private String nombre;
    private String descripcion;
    private int intensidad; // 1: Basico, 2: Intermedio, 3: Avanzado, 4: AltoRendimiento
    private int ultimaSemana; // 1: Si fue realizado la semana pasada, 2: No se realizó
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
    public int getCodigo() { return this.codigo; } // Se podría utilizar si se implementa una búsqueda por código.
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
    // Se podrían utilizar en una versión futura donde se modifiquen los ejercicios.
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setIntensidad(int intensidad) { this.intensidad = intensidad; }
    public void setUltimaSemana(int ultimaSemana) { this.ultimaSemana = ultimaSemana; }
    public void setTiempoMinutos(int tiempo) { this.tiempoMinutos = tiempo; }

    // Otros métodos
    /// Devuelve un string con la información del ejercicio.
    public String mostrarInfo() {
        return "   Tipo        : " + this.getTipoString() + "\n" +
                "   Intensidad  : " + this.getIntensidadString() + "\n" +
                "   Tiempo      : " + this.tiempoMinutos + " min" + "\n" +
                "   Descripcion : " + this.descripcion + "\n";
    }

    // Métodos que serán sobreescritos por Fuerza y Cardio
    /// Devuelve el tipo del ejercicio.
    /// @return 1 para Fuerza, 2 para Cardio; 0 para indefinido.
    public int getTipo() {
        return 0;
    }

    /// Devuelve el tipo del ejercicio en un String.
    /// @return Fuerza o Cardiovascular; null si no está definido.
    public String getTipoString() {
        return null;
    }
}