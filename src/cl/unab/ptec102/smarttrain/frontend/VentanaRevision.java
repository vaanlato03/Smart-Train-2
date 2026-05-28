package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.Ejercicio;
import cl.unab.ptec102.smarttrain.backend.GestorRutinas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/// Ventana de revisión de la rutina. Pasa los ejercicios uno a uno con
/// botones de navegación, y al final lleva al resumen de la rutina.
public class VentanaRevision {

    // Atributo para recibir el gestor creado anteriormente.
    private GestorRutinas gestor;

    // Elementos de la ventana
    private JFrame ventanaRevision;
    private JPanel panelRevision;
    private JLabel lbRevision, lbEjercicioActual, lbNombre, lbTipo, lbIntensidad, lbTiempo, lbDescripcion;
    private JTextArea taDescripcion;
    private JButton btAnterior, btSiguiente;

    // Otros atributos
    private ArrayList<Ejercicio> ejercicios;
    private int indiceActual;

    public VentanaRevision(GestorRutinas gestor) {
        // Recibe el gestor y lo almacena en su atributo gestor.
        this.gestor = gestor;
        // Utiliza el gestor para llegar a la rutina y obtener su lista de ejercicios.
        ejercicios = this.gestor.getRutinaActual().getEjercicios();
        indiceActual = 0;

        // Inicializa y configura el JFrame.
        ventanaRevision = new JFrame();
        ventanaRevision.setTitle("Revision de la rutina");
        ventanaRevision.setSize(600, 600);
        ventanaRevision.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        crearComponentes();
        componerLayout();
        setActionListeners();

        ventanaRevision.setContentPane(panelRevision);
        ventanaRevision.setIconImage(Estilos.icono.getImage());
        ventanaRevision.setResizable(false);
        ventanaRevision.setLocationRelativeTo(null);

        // Empieza mostrando el primer ejercicio.
        mostrarEjercicio(indiceActual);

        ventanaRevision.setVisible(true);
    }

    /// Inicializa los componentes que tendrá la ventana y los estiliza.
    public void crearComponentes() {
        panelRevision = new JPanel(new GridBagLayout());
        panelRevision.setBackground(Estilos.colorOscuroFondo);
        panelRevision.setFont(Estilos.fuente);

        lbRevision = new JLabel("Revision de la rutina");
        lbRevision.setForeground(Estilos.colorMedio);
        lbRevision.setFont(Estilos.fuente.deriveFont(22f));

        lbEjercicioActual = new JLabel("Ejercicio x de x");
        lbEjercicioActual.setForeground(Estilos.colorClaro);
        lbEjercicioActual.setFont(Estilos.fuente.deriveFont(Font.PLAIN));

        lbNombre = new JLabel("Nombre del ejercicio");
        lbNombre.setForeground(Color.WHITE);
        lbNombre.setFont(Estilos.fuente.deriveFont(28f));

        lbTipo = new JLabel("Tipo del ejercicio");
        lbTipo.setPreferredSize(new Dimension(250, 45));
        lbTipo.setMinimumSize(new Dimension(250, 45));
        lbTipo.setMaximumSize(new Dimension(250, 45));

        lbIntensidad = new JLabel("Intensidad: Alto Rendimiento");
        lbIntensidad.setPreferredSize(new Dimension(250, 45));
        lbIntensidad.setMinimumSize(new Dimension(250, 45));
        lbIntensidad.setMaximumSize(new Dimension(250, 45));

        lbTiempo = new JLabel("Tiempo del ejercicio");

        // Bucle para configurar las JLabel del mismo estilo y evitar repetir.
        for (JLabel lb : new JLabel[]{lbTipo, lbIntensidad, lbTiempo}) {
            lb.setForeground(Color.WHITE);
            lb.setFont(Estilos.fuente.deriveFont(Font.PLAIN));
            lb.setOpaque(true);
            lb.setBackground(Estilos.colorClaroOpacidad);
            lb.setHorizontalAlignment(SwingConstants.CENTER);
            lb.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Estilos.colorClaro, 2),
                    BorderFactory.createEmptyBorder(10, 8, 10, 8)));
        }

        lbDescripcion = new JLabel("Descripcion del ejercicio:");
        lbDescripcion.setForeground(Estilos.colorClaro);
        lbDescripcion.setFont(Estilos.fuente);
        lbDescripcion.setHorizontalAlignment(SwingConstants.LEFT);

        taDescripcion = new JTextArea("Descripcion del ejercicio");
        taDescripcion.setFont(Estilos.fuente.deriveFont(Font.PLAIN));
        taDescripcion.setBackground(Estilos.colorOscuroFondo);
        taDescripcion.setForeground(Color.WHITE);
        taDescripcion.setEditable(false);
        taDescripcion.setLineWrap(true);
        taDescripcion.setRows(5);
        taDescripcion.setFocusable(false);
        taDescripcion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Estilos.colorClaro, 2),
                BorderFactory.createEmptyBorder(10, 8, 10, 8)
        ));

        btAnterior = new JButton("Anterior");
        btAnterior.setBackground(Color.GRAY);
        btAnterior.setForeground(Estilos.colorOscuro);
        btAnterior.setFont(Estilos.fuente);
        btAnterior.setFocusPainted(false);
        btAnterior.setEnabled(false);
        btAnterior.setMargin(new Insets(5, 30, 5, 30));

        btSiguiente = new JButton("Siguiente");
        btSiguiente.setBackground(Estilos.colorMedio);
        btSiguiente.setForeground(Estilos.colorOscuro);
        btSiguiente.setFont(Estilos.fuente);
        btSiguiente.setFocusPainted(false);
        btSiguiente.setMargin(new Insets(5, 30, 5, 30));
    }

    /// Utiliza GridBagConstraints para componer el GridBagLayout principal.
    public void componerLayout() {
        // Se reutiliza gbc para acomodar todos los elementos.
        GridBagConstraints gbc = new GridBagConstraints();

        // Fila 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelRevision.add(lbRevision, gbc);

        // Fila 1
        gbc.gridy = 1;
        panelRevision.add(lbEjercicioActual, gbc);

        // Fila 2
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 5, 20, 5);
        panelRevision.add(lbNombre, gbc);

        // Fila 3
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelRevision.add(lbTipo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelRevision.add(lbIntensidad, gbc);

        // Fila 4
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelRevision.add(lbTiempo, gbc);

        // Fila 5
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelRevision.add(lbDescripcion, gbc);

        // Fila 6
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelRevision.add(taDescripcion, gbc);

        // Fila 7
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panelRevision.add(btAnterior, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        panelRevision.add(btSiguiente, gbc);
    }

    /// Configura los Listeners de los elementos de la ventana.
    public void setActionListeners() {
        // Muestra el ejercicio anterior siempre y cuando no esté en el primero.
        btAnterior.addActionListener(e -> {
           if (indiceActual > 0) {
               indiceActual--;
               mostrarEjercicio(indiceActual);
           }
        });

        // Muestra el ejercicio siguiente; si está en el último, crea la siguiente ventana y cierra la actual.
        btSiguiente.addActionListener(e -> {
            if (indiceActual < ejercicios.size() - 1) {
                indiceActual++;
                mostrarEjercicio(indiceActual);
            } else {
                VentanaResumen resumen = new VentanaResumen(gestor);
                ventanaRevision.dispose();
            }
        });
    }

    /// Utiliza el ArrayList ejercicios para navegar entre ellos, actualizando los elementos de la ventana.
    public void mostrarEjercicio(int indice) {
        Ejercicio ejercicio = ejercicios.get(indice);
        lbEjercicioActual.setText("Ejercicio " + (indice + 1) + " de " + ejercicios.size());
        lbNombre.setText(ejercicio.getNombre());
        lbTipo.setText("Tipo: " + ejercicio.getTipoString());
        lbIntensidad.setText("Intensidad: " + ejercicio.getIntensidadString());
        lbTiempo.setText("Tiempo estimado: " + ejercicio.getTiempoMinutos() + " minutos");
        taDescripcion.setText(ejercicio.getDescripcion());

        // Desactiva el botón Anterior si está en el primer ejercicio.
        if (indiceActual == 0) {
            btAnterior.setEnabled(false);
            btAnterior.setBackground(Color.GRAY);
        } else {
            btAnterior.setEnabled(true);
            btAnterior.setBackground(Estilos.colorMedio);
        }

        // Cambia el estilo del botón Siguiente si está en el último ejercicio.
        if (indiceActual == ejercicios.size() - 1) {
            btSiguiente.setText("Resumen de la rutina");
            btSiguiente.setBackground(Estilos.colorClaro);
            btSiguiente.setForeground(Estilos.colorOscuro);
        } else {
            btSiguiente.setText("Siguiente");
            btSiguiente.setBackground(Estilos.colorMedio);
            btSiguiente.setForeground(Estilos.colorOscuro);
        }
    }
}
