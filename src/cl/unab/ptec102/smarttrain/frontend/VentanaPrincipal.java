package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.GestorRutinas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal {

    private GestorRutinas gestor;
    private JFrame ventanaPrincipal;
    private JLabel lbLogo, lbFondo, lbSaludo, lbCantDisp, lbTiempoDisp, lbCantFuerza, lbCantCardio;
    private JLabel lbCantBasico, lbCantInter, lbCantAvanz, lbCantAltoRend;
    private JButton btGenRutina;

    public VentanaPrincipal(GestorRutinas gestor) {
        this.gestor = gestor;
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

        lbTiempoDisp = new JLabel("Tiempo total disponible: " + gestor.getTiempoTotal() + " minutos");
        lbTiempoDisp.setFont(Estilos.fuente.deriveFont(20f));
        lbTiempoDisp.setForeground(Estilos.colorClaro);

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

    public void componerLayout() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        lbFondo.add(lbLogo, gbc);

        gbc.gridy = 1;
        lbFondo.add(lbSaludo, gbc);

        gbc.gridy = 2;
        lbFondo.add(lbCantDisp, gbc);

        gbc.gridy = 3;
        lbFondo.add(lbTiempoDisp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lbFondo.add(lbCantFuerza, gbc);
        gbc.gridx = 2;
        lbFondo.add(lbCantCardio, gbc);

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

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 5, 5);
        lbFondo.add(btGenRutina, gbc);
    }

    public void setActionListeners() {
        btGenRutina.addActionListener(e -> {
            VentanaGenerarRutina ventanaGenerar = new VentanaGenerarRutina(gestor, this);
        });
    }

    public JFrame getFrame() { return ventanaPrincipal; }

    public void cerrarVentana() {
        ventanaPrincipal.dispose();
    }
}
