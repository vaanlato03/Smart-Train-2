package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.GestorRutinas;

import javax.swing.*;
import java.awt.*;

/// Ventana principal del programa. Muestra las estadísticas de los ejercicios cargados y permite generar una rutina.
public class VentanaPrincipal {

    // Atributo para recibir el gestor creado anteriormente.
    private GestorRutinas gestor;

    // Elementos de la ventana
    private JFrame ventanaPrincipal;
    private JLabel lbLogo, lbFondo, lbSaludo, lbCantDisp, lbTiempoDisp, lbCantFuerza, lbCantCardio;
    private JLabel lbCantBasico, lbCantInter, lbCantAvanz, lbCantAltoRend;
    private JButton btGenRutina;

    // Constructor
    public VentanaPrincipal(GestorRutinas gestor) {
        // Recibe el gestor y lo almacena en su atributo gestor.
        this.gestor = gestor;
        // Inicializa y configura el JFrame.
        ventanaPrincipal = new JFrame();
        ventanaPrincipal.setSize(800,500);
        ventanaPrincipal.setTitle("Panel Principal");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crearComponentes();
        componerLayout();
        setActionListeners();

        ventanaPrincipal.setContentPane(lbFondo);
        ventanaPrincipal.setIconImage(Estilos.icono.getImage());
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setLocationRelativeTo(null);
    }

    /// Inicializa los componentes que tendrá la ventana y los estiliza.
    public void crearComponentes() {
        lbFondo = new JLabel(Estilos.fondoGimnasio);
        lbFondo.setLayout(new GridBagLayout());
        lbFondo.setFont(Estilos.fuente);

        lbLogo = new JLabel(Estilos.logo);
        lbLogo.setPreferredSize(new Dimension(300, 108));
        lbLogo.setMinimumSize(new Dimension(300, 108));
        lbLogo.setMaximumSize(new Dimension(300, 108));

        lbSaludo = new JLabel("Hola " + gestor.getCliente() + "!");
        lbSaludo.setFont(Estilos.fuente.deriveFont(22f));
        lbSaludo.setForeground(Estilos.colorMedio);

        lbCantDisp = new JLabel();
        lbCantDisp.setText("Hay " + gestor.getNumEjercicios() + " ejercicios cargados en el sistema");
        lbCantDisp.setFont(Estilos.fuente.deriveFont(28f));
        lbCantDisp.setForeground(Estilos.colorMedio);

        lbTiempoDisp = new JLabel("Tiempo total disponible: " + gestor.calcularTiempoTotal() + " minutos");
        lbTiempoDisp.setFont(Estilos.fuente.deriveFont(20f));
        lbTiempoDisp.setForeground(Estilos.colorClaro);

        // Utiliza HTML para dar salto de línea y centrado dentro del texto.
        lbCantFuerza = new JLabel("<html><center>Ejercicios de fuerza:<br>" +
                gestor.contarPorTipo(1) + "</center></html>");
        lbCantCardio = new JLabel("<html><center>Ejercicios de cardio:<br>" +
                gestor.contarPorTipo(2) + "</center></html>");
        lbCantBasico = new JLabel("<html><center>Básicos:<br>" +
                gestor.contarPorInten(1) + "</center></html>");
        lbCantInter = new JLabel("<html><center>Intermedios:<br>" +
                gestor.contarPorInten(2) + "</center></html>");
        lbCantAvanz = new JLabel("<html><center>Avanzados:<br>" +
                gestor.contarPorInten(3) + "</center></html>");
        lbCantAltoRend = new JLabel("<html><center>Alto rendimiento:<br>" +
                gestor.contarPorInten(4) + "</center></html>");

        // Bucle para configurar las JLabel del mismo estilo y evitar repetir.
        for (JLabel lb : new JLabel[]{
                lbCantFuerza, lbCantCardio, lbCantBasico,
                lbCantInter, lbCantAvanz, lbCantAltoRend }) {
            lb.setPreferredSize(new Dimension(160, 50));
            lb.setMinimumSize(new Dimension(160, 50));
            lb.setMaximumSize(new Dimension(160, 50));
            lb.setForeground(Estilos.colorClaro);
            lb.setFont(Estilos.fuente);
            lb.setOpaque(true);
            lb.setBackground(Estilos.colorClaroOpacidad);
            lb.setHorizontalAlignment(SwingConstants.CENTER);
            lb.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Estilos.colorClaro, 2),
                    BorderFactory.createEmptyBorder(10, 8, 10, 8)));
        }

        btGenRutina = new JButton("Generar Rutina");
        btGenRutina.setFont(Estilos.fuente);
        btGenRutina.setForeground(Estilos.colorOscuro);
        btGenRutina.setBackground(Estilos.colorMedio);
        btGenRutina.setMargin(new Insets(5, 30, 5, 30));
        btGenRutina.setFocusPainted(false);
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
        gbc.anchor = GridBagConstraints.CENTER;
        lbFondo.add(lbLogo, gbc);

        // Fila 1
        gbc.gridy = 1;
        lbFondo.add(lbSaludo, gbc);

        // Fila 2
        gbc.gridy = 2;
        lbFondo.add(lbCantDisp, gbc);

        // Fila 3
        gbc.gridy = 3;
        lbFondo.add(lbTiempoDisp, gbc);

        // Fila 4
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lbFondo.add(lbCantFuerza, gbc);

        gbc.gridx = 2;
        lbFondo.add(lbCantCardio, gbc);

        // Fila 5
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        lbFondo.add(lbCantBasico, gbc);

        gbc.gridx = 1;
        lbFondo.add(lbCantInter, gbc);

        gbc.gridx = 2;
        lbFondo.add(lbCantAvanz, gbc);

        gbc.gridx = 3;
        lbFondo.add(lbCantAltoRend, gbc);

        // Fila 6
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 5, 5);
        lbFondo.add(btGenRutina, gbc);
    }

    /// Configura los Listeners de los elementos de la ventana.
    public void setActionListeners() {
        // Al presionar el botón crea una ventana modal.
        btGenRutina.addActionListener(e -> {
            // Pasa el gestor y a sí misma.
            VentanaGenerarRutina ventanaGenerar = new VentanaGenerarRutina(gestor, this);
        });
    }

    /// Getter del frame principal, para pasar como padre a la ventana modal.
    public JFrame getFrame() { return ventanaPrincipal; }

    /// Cierra la ventana, pudiendo ejecutarse desde fuera de esta ventana.
    public void cerrarVentana() {
        ventanaPrincipal.dispose();
    }
}
