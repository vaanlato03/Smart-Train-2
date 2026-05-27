package cl.unab.ptec102.smarttrain.backend;

public class Cardio extends Ejercicio{

    // Constructor
    public Cardio(int codigo, String nombre, String descripcion,
                  int intensidad, int ultimaSemana, int tiempoMinutos) {
        super(codigo, nombre, descripcion, intensidad, ultimaSemana, tiempoMinutos);
    }

    // Métodos sobreescritos de Ejercicio
    @Override
    public int getTipo() { return 2; }

    @Override
    public String getTipoString() { return "Cardiovascular"; }
}