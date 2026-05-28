package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.GestorRutinas;
import cl.unab.ptec102.smarttrain.backend.Rutina;

import javax.swing.*;
import java.awt.*;

/// Ventana de resumen de la rutina. Muestra las estadísticas de la rutina y da la opción de
/// descargarla como txt, volver al menú principal o salir del sistema.
public class VentanaResumen {

    // Atributo para recibir el gestor creado anteriormente.
    private GestorRutinas gestor;

    // Atributo para guardar la rutina y utilizarla más cómodamente.
    private Rutina rutina;

    // Elementos de la ventana
    private JFrame ventanaResumen;
    private JLabel lbFondo, lbCliente, lbResumen, lbCantEjercicios, lbTiempoTotal, lbCantFuerza, lbCantCardio;
    private JLabel lbCantBasico, lbCantInter, lbCantAvanz, lbCantAltoRend;
    private JButton btVolverMenu, btSalir, btGuardar;

    public VentanaResumen(GestorRutinas gestor) {
        // Recibe el gestor y lo almacena en su atributo gestor.
        this.gestor = gestor;
        // Utiliza el gestor para obtener la rutina y la guarda en su atributo rutina.
        rutina = this.gestor.getRutinaActual();
        // Inicializa y configura el JFrame.
        ventanaResumen = new JFrame();
        ventanaResumen.setSize(750,500);
        ventanaResumen.setTitle("Resumen de la rutina");
        ventanaResumen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crearComponentes();
        componerLayout();
        setActionListeners();

        ventanaResumen.setContentPane(lbFondo);
        ventanaResumen.setIconImage(Estilos.icono.getImage());
        ventanaResumen.setVisible(true);
        ventanaResumen.setResizable(false);
        ventanaResumen.setLocationRelativeTo(null);
    }

    /// Inicializa los componentes que tendrá la ventana y los estiliza.
    public void crearComponentes() {
        lbFondo = new JLabel(Estilos.fondoGimnasio);
        lbFondo.setLayout(new GridBagLayout());
        lbFondo.setFont(Estilos.fuente);

        lbResumen = new JLabel("Resumen de la rutina");
        lbResumen.setFont(Estilos.fuente.deriveFont(28f));
        lbResumen.setForeground(Estilos.colorMedio);

        lbCliente = new JLabel("Cliente: " + rutina.getCliente());
        lbCliente.setFont(Estilos.fuente.deriveFont(24f));
        lbCliente.setForeground(Color.WHITE);

        lbCantEjercicios = new JLabel("N° de ejercicios: " + rutina.getEjercicios().size());
        lbCantEjercicios.setFont(Estilos.fuente.deriveFont(20f));
        lbCantEjercicios.setForeground(Color.WHITE);

        lbTiempoTotal = new JLabel("Tiempo total: " + rutina.calcularTiempoTotal() + " minutos");
        lbTiempoTotal.setFont(Estilos.fuente.deriveFont(20f));
        lbTiempoTotal.setForeground(Color.WHITE);

        // Utiliza HTML para agregar salto de línea y centrado al texto.
        lbCantFuerza = new JLabel("<html><center>Ejercicios de fuerza:<br>" +
                rutina.contarPorTipo(1) + "</center></html>");
        lbCantCardio = new JLabel("<html><center>Ejercicios de cardio:<br>" +
                rutina.contarPorTipo(1) + "</center></html>");
        lbCantBasico = new JLabel("<html><center>Básicos:<br>" +
                rutina.contarPorIntensidad(1) + "</center></html>");
        lbCantInter = new JLabel("<html><center>Intermedios:<br>" +
                rutina.contarPorIntensidad(2) + "</center></html>");
        lbCantAvanz = new JLabel("<html><center>Avanzados:<br>" +
                rutina.contarPorIntensidad(3) + "</center></html>");
        lbCantAltoRend = new JLabel("<html><center>Alto rendimiento:<br>" +
                rutina.contarPorIntensidad(4) + "</center></html>");

        // Bucle para configurar las JLabel del mismo estilo y evitar repetir.
        for (JLabel lb : new JLabel[]{
                lbCantFuerza, lbCantCardio, lbCantBasico,
                lbCantInter, lbCantAvanz, lbCantAltoRend }) {
            lb.setPreferredSize(new Dimension(160, 50));
            lb.setMinimumSize(new Dimension(160, 50));
            lb.setMaximumSize(new Dimension(160, 50));
            lb.setForeground(Color.WHITE);
            lb.setFont(Estilos.fuente.deriveFont(Font.PLAIN));
            lb.setOpaque(true);
            lb.setBackground(Estilos.colorClaroOpacidad);
            lb.setHorizontalAlignment(SwingConstants.CENTER);
            lb.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Estilos.colorClaro, 2),
                    BorderFactory.createEmptyBorder(10, 8, 10, 8)));
        }

        btGuardar = new JButton("Descargar rutina (.txt)");
        btGuardar.setFont(Estilos.fuente);
        btGuardar.setBackground(Estilos.colorClaro);
        btGuardar.setForeground(Estilos.colorOscuro);
        btGuardar.setMargin(new Insets(5, 30, 5, 30));
        btGuardar.setFocusPainted(false);

        btVolverMenu = new JButton("Volver al menu");
        btVolverMenu.setFont(Estilos.fuente);
        btVolverMenu.setForeground(Estilos.colorOscuro);
        btVolverMenu.setBackground(Estilos.colorMedio);
        btVolverMenu.setMargin(new Insets(5, 30, 5, 30));
        btVolverMenu.setFocusPainted(false);

        btSalir = new JButton("Salir del sistema");
        btSalir.setFont(Estilos.fuente);
        btSalir.setForeground(Color.WHITE);
        btSalir.setBackground(Estilos.colorRojo);
        btSalir.setMargin(new Insets(5, 30, 5, 30));
        btSalir.setFocusPainted(false);
    }

    /// Utiliza GridBagConstraints para componer el GridBagLayout principal.
    public void componerLayout() {
        // Se reutiliza gbc para acomodar todos los elementos.
        GridBagConstraints gbc = new GridBagConstraints();

        // Fila 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        lbFondo.add(lbResumen, gbc);

        // Fila 1
        gbc.gridy = 1;
        lbFondo.add(lbCliente, gbc);

        // Fila 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 20, 5);
        lbFondo.add(lbCantEjercicios, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        lbFondo.add(lbTiempoTotal, gbc);

        // Fila 3
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        lbFondo.add(lbCantFuerza, gbc);

        gbc.gridx = 2;
        lbFondo.add(lbCantCardio, gbc);

        // Fila 4
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        lbFondo.add(lbCantBasico, gbc);

        gbc.gridx = 1;
        lbFondo.add(lbCantInter, gbc);

        gbc.gridx = 2;
        lbFondo.add(lbCantAvanz, gbc);

        gbc.gridx = 3;
        lbFondo.add(lbCantAltoRend, gbc);

        // Fila 5
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 5, 5, 5);
        lbFondo.add(btGuardar, gbc);

        // Fila 6
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        lbFondo.add(btVolverMenu, gbc);

        gbc.gridx = 2;
        lbFondo.add(btSalir, gbc);
    }

    /// Configura los Listeners de los elementos de la ventana.
    public void setActionListeners() {
        // Vuelve a crear la ventana principal y cierra la ventana actual.
        btVolverMenu.addActionListener(e -> {
            VentanaPrincipal principal = new VentanaPrincipal(gestor);
            ventanaResumen.dispose();
        });

        // Acciona la descarga de la rutina mediante el gestor y muestra un mensaje acorde al resultado.
        btGuardar.addActionListener(e -> {
            boolean exito = gestor.descargarRutina(rutina, ventanaResumen);
            if (exito) {
                JOptionPane.showMessageDialog(null,
                        "Se ha descargado correctamente la rutina.",
                        "Descarga exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se ha podido descargar correctamente la rutina.",
                        "Error en la descarga",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Cierra completamente el programa
        btSalir.addActionListener(e -> {
           System.exit(0);
        });
    }
}
