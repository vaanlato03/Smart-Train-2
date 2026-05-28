package cl.unab.ptec102.smarttrain.backend;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GestorRutinas {

    // Atributos
    private ArrayList<Ejercicio> ejercicios;
    private Rutina rutinaActual;
    private String cliente;

    // Constructor
    public GestorRutinas(String cliente) {
        this.cliente = cliente;
        this.ejercicios = new ArrayList<>();
        this.rutinaActual = null;
    }

    // Getters
    public Rutina getRutinaActual() { return this.rutinaActual; }
    public String getCliente() { return this.cliente; }
    public int getNumEjercicios() { return this.ejercicios.size(); }

    // Otros métodos
    /// Calcula la suma, en minutos, de la duración de todos los ejercicios cargados.
    public int calcularTiempoTotal() {
        int tiempoTotal = 0;
        for (Ejercicio e : this.ejercicios) {
            tiempoTotal += e.getTiempoMinutos();
        }
        return tiempoTotal;
    }

    /// Cuenta la cantidad de ejercicios, del tipo especificado, que hay cargados en el gestor.
    /// @param tipo 1: Fuerza, 2: Cardio.
    public int contarPorTipo(int tipo) {
        int contador = 0;
        for (Ejercicio e : ejercicios) {
            if (e.getTipo() == tipo) {
                contador++;
            }
        }
        return contador;
    }

    /// Cuenta la cantidad de ejercicios, de la intensidad especificada, que hay cardados en el gestor.
    /// @param inten 1: Básico, 2: Intermedio, 3: Avanzado, 4: Alto Rendimiento.
    public int contarPorInten(int inten) {
        int contador = 0;
        for (Ejercicio e : ejercicios) {
            if (e.getIntensidad() == inten) {
                contador++;
            }
        }
        return contador;
    }

    /// Cuenta la cantidad de ejercicios que coincidan con el tipo y la intensidad especificados,
    /// que no hayan sido utilizados la semana pasada.
    /// @param tipo 1: Fuerza, 2: Cardio.
    /// @param intensidad 1: Básico, 2: Intermedio, 3: Avanzado, 4: Alto Rendimiento.
    public int cantTipoPorIntenRestringido(int intensidad, int tipo) {
        int contador = 0;
        for (Ejercicio e : ejercicios) {
            if (e.getIntensidad() == intensidad && e.getTipo() == tipo && e.getUltimaSemana() == 2) {
                contador++;
            }
        }
        return contador;
    }

    /// Agrega un ejercicio a la lista del gestor, dependiendo de su tipo.
    public void agregarEjercicio(int cod, String nom, int tipo, int inten, int semana, int tiempo, String desc) {
        Ejercicio nuevo;
        if (tipo == 1) {
            nuevo = new Fuerza(cod, nom, desc, inten, semana, tiempo);
        } else {
            nuevo = new Cardio(cod, nom, desc, inten, semana, tiempo);
        }
        this.ejercicios.add(nuevo);
    }

    /// Carga los ejercicios desde un archivo csv con el formato:
    /// código,nombre,tipo,intensidad,ultimaSemana,tiempoMinutos,descripción
    /// @return 0: carga exitosa, 1: archivo no encontrado, 2: dato numérico incorrecto, 3: otra excepción.
    public int cargarEjercicios() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Ejercicios400.csv"));

            String linea = reader.readLine();
            boolean esEncabezado = true;

            while (linea != null) {
                // Se salta el encabezado si es que hay.
                if(esEncabezado) {
                    String[] primerosDatos = linea.split(",");
                    if(primerosDatos.length > 0 && !primerosDatos[0].matches("\\d+")) {
                        esEncabezado = false;
                        linea = reader.readLine();
                        continue;
                    }
                    esEncabezado = false;
                }

                String[] datos = linea.split(",");
                this.agregarEjercicio(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        Integer.parseInt(datos[2]),
                        Integer.parseInt(datos[3]),
                        Integer.parseInt(datos[4]),
                        Integer.parseInt(datos[5]),
                        datos[6]);
                linea = reader.readLine();
            }
            reader.close();
            return 0;
        } catch (FileNotFoundException e) {
            return 1;
        } catch (NumberFormatException e) {
            return 2;
        } catch (Exception e) {
            return 3;
        }
    }

    /// Genera una rutina en función de la intensidad y la cantidad de cada tipo de ejercicio que se entregue.
    /// @return true: generación exitosa, false: no se pudo generar.
    public boolean generarRutina(int cantFuerza, int cantCardio, int intensidad) {
        try {
            ArrayList<Ejercicio> candidatosFuerza = new ArrayList<>();
            ArrayList<Ejercicio> candidatosCardio = new ArrayList<>();

            // Obtiene los ejercicios que coincidan con la intensidad y no se hayan realizado la semana pasada.
            for (Ejercicio e : this.ejercicios) {
                if (e.getIntensidad() == intensidad && e.getUltimaSemana() != 1) {
                    if (e.getTipo() == 1) {
                        candidatosFuerza.add(e);
                    } else {
                        candidatosCardio.add(e);
                    }
                }
            }

            // Lanza excepciones si no hay suficientes ejercicios de algún tipo.
            if (candidatosFuerza.size() < cantFuerza) {
                throw new Exception(
                        "No hay suficientes ejercicios de Fuerza disponibles.\n"
                                + "Disponibles: " + candidatosFuerza.size()
                                + "  |  Solicitados: " + cantFuerza);
            }
            if (candidatosCardio.size() < cantCardio) {
                throw new Exception(
                        "No hay suficientes ejercicios Cardiovasculares disponibles.\n"
                                + "Disponibles: " + candidatosCardio.size()
                                + "  |  Solicitados: " + cantCardio);
            }

            Rutina nuevaRutina = new Rutina(this.cliente, intensidad);
            Random rnd = new Random();

            // Selección aleatoria sin repetir desde los candidatos para Fuerza
            for (int i = 0; i < cantFuerza; i++) {
                int index = rnd.nextInt(candidatosFuerza.size());
                nuevaRutina.agregarEjercicio(candidatosFuerza.get(index));
                candidatosFuerza.remove(index);
            }

            // Selección aleatoria sin repetir desde los candidatos para Cardio
            for (int i = 0; i < cantCardio; i++) {
                int index = rnd.nextInt(candidatosCardio.size());
                nuevaRutina.agregarEjercicio(candidatosCardio.get(index));
                candidatosCardio.remove(index);
            }

            // Asigna la rutina creada al atributo rutinaActual
            this.rutinaActual = nuevaRutina;
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /// Escribe la rutina en un archivo .txt (nombrado y guardado por el usuario).
    /// @return true: escritura exitosa, false: no se escribió correctamente o el usuario canceló.
    public boolean descargarRutina(Rutina rutina, JFrame ventana) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("rutina_" + rutina.getCliente() + ".txt"));

        int resultado = fileChooser.showSaveDialog(ventana);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(fileChooser.getSelectedFile()));

                writer.write(rutina.mostrarRutina());

                writer.close();
                return true;

            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
