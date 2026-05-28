package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.GestorRutinas;

import javax.swing.*;
import java.awt.*;

/// Ventana de carga. Muestra si se cargaron correctamente o no los ejercicios.
public class VentanaCarga {

    // Atributo para recibir el gestor creado anteriormente.
    private GestorRutinas gestor;

    // Elementos de la ventana
    private JFrame ventanaCarga;
    private JPanel panelCarga;
    private JLabel lbCargando, lbBuscando, lbEstado;
    private JButton btContinuar;

    // Constructor
    public VentanaCarga(GestorRutinas gestor) {
        // Recibe el gestor y lo almacena en su atributo gestor.
        this.gestor = gestor;
        // Inicializa y configura el JFrame.
        ventanaCarga = new JFrame();
        ventanaCarga.setSize(400,400);
        ventanaCarga.setTitle("Cargando los ejercicios...");
        ventanaCarga.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crearComponentes();
        componerLayout();
        setActionListeners();

        ventanaCarga.setContentPane(panelCarga);
        ventanaCarga.setIconImage(Estilos.icono.getImage());
        ventanaCarga.setVisible(true);
        ventanaCarga.setResizable(false);
        ventanaCarga.setLocationRelativeTo(null);

        // Inicia la carga de los ejercicios.
        cargar();
    }

    /// Inicializa los componentes que tendrá la ventana y los estiliza.
    public void crearComponentes() {
        panelCarga = new JPanel(new GridBagLayout());
        panelCarga.setBackground(Estilos.colorOscuroFondo);
        panelCarga.setFont(Estilos.fuente);

        lbCargando = new JLabel("Cargando...");
        lbCargando.setFont(Estilos.fuente.deriveFont(26f));
        lbCargando.setForeground(Estilos.colorMedio);

        lbBuscando = new JLabel("Buscando el archivo...");
        lbBuscando.setFont(Estilos.fuente);
        lbBuscando.setForeground(Estilos.colorClaro);

        lbEstado = new JLabel(" ");
        lbEstado.setFont(Estilos.fuente.deriveFont(Font.PLAIN));
        lbEstado.setForeground(Estilos.colorClaro);

        btContinuar = new JButton("Continuar");
        btContinuar.setBackground(Color.GRAY);
        btContinuar.setForeground(Estilos.colorOscuro);
        btContinuar.setFont(Estilos.fuente);
        btContinuar.setMargin(new Insets(5,30,5,30));
        btContinuar.setEnabled(false);
        btContinuar.setFocusPainted(false);
    }

    /// Utiliza GridBagConstraints para componer el GridBagLayout principal.
    public void componerLayout() {
        // Se reutiliza gbc para acomodar todos los elementos.
        GridBagConstraints gbc = new GridBagConstraints();

        // Fila 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;
        panelCarga.add(lbCargando, gbc);

        // Fila 1
        gbc.gridy = 1;
        gbc.insets = new Insets(5,10,5,10);
        panelCarga.add(lbBuscando, gbc);

        // Fila 2
        gbc.gridy = 2;
        panelCarga.add(lbEstado, gbc);

        // Fila 3
        gbc.gridy = 3;
        gbc.insets = new Insets(30,10,10,10);
        gbc.anchor = GridBagConstraints.CENTER;
        panelCarga.add(btContinuar, gbc);
    }

    /// Configura los Listeners de los elementos de la ventana.
    public void setActionListeners() {
        // Al presionar el botón crea la siguiente ventana y cierra la actual.
        btContinuar.addActionListener(e -> {
            VentanaPrincipal principal = new VentanaPrincipal(gestor); // Se pasa el gestor
            ventanaCarga.dispose();
        });
    }

    /// Se inicia la carga de los ejercicios con el gestor, y se actualizan los elementos en consecuencia.
    public void cargar() {
        int estado = gestor.cargarEjercicios();
        switch (estado) {
            case 0: { // Carga exitosa
                lbCargando.setText("Todo listo!");
                lbBuscando.setText("Archivo encontrado.");
                lbEstado.setText("Se han cargado " + gestor.getNumEjercicios() + " ejercicios.");
                btContinuar.setBackground(Estilos.colorMedio);
                btContinuar.setEnabled(true);
                break;
            }
            case 1: { // Archivo no encontrado
                lbCargando.setText("Ups...");
                lbBuscando.setText("No se ha podido encontrar el archivo.");
                lbEstado.setText("No se han podido cargar los ejercicios.");
                break;
            }
            case 2: { // Dato numérico incorrecto
                lbCargando.setText("Ups...");
                lbBuscando.setText("Un dato del archivo no coincide con el formato.");
                lbEstado.setText("No se han podido cargar los ejercicios.");
                break;
            }
            case 3: { // Otra excepción
                lbCargando.setText("Ups...");
                lbBuscando.setText("Ha ocurrido un error inesperado.");
                lbEstado.setText("No se han podido cargar los ejercicios.");
                break;
            }
        }
    }
}
