package cl.unab.ptec102.smarttrain.backend;

public class Fuerza extends Ejercicio {

    // Constructor
    public Fuerza(int codigo, String nombre, String descripcion,
                  int intensidad, int ultimaSemana, int tiempoMinutos) {
        super(codigo, nombre, descripcion, intensidad, ultimaSemana, tiempoMinutos);
    }

    // Métodos sobreescritos de Ejercicio
    @Override
    public int getTipo() { return 1; }

    @Override
    public String getTipoString() { return "Fuerza"; }
}
