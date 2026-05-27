package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.GestorRutinas;

import javax.swing.*;
import java.awt.*;

public class VentanaCarga {
    private GestorRutinas gestor;
    private JFrame ventanaCarga;
    private JPanel panelCarga;
    private JLabel lbCargando, lbBuscando, lbEstado;
    private JButton btContinuar;

    public VentanaCarga(GestorRutinas gestor) {
        this.gestor = gestor;
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

        cargar();
    }

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

    public void componerLayout() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelCarga.add(lbCargando, gbc);

        gbc.insets = new Insets(5,10,5,10);
        gbc.gridy = 1;
        panelCarga.add(lbBuscando, gbc);

        gbc.gridy = 2;
        panelCarga.add(lbEstado, gbc);

        gbc.insets = new Insets(30,10,10,10);
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCarga.add(btContinuar, gbc);
    }

    public void setActionListeners() {
        btContinuar.addActionListener(e -> {
            VentanaPrincipal principal = new VentanaPrincipal(gestor);
            ventanaCarga.dispose();
        });
    }

    public void cargar() {
        int estado = gestor.cargarEjercicios();
        switch (estado) {
            case 0: {
                lbCargando.setText("Todo listo!");
                lbBuscando.setText("Archivo encontrado.");
                lbEstado.setText("Se han cargado " + gestor.getNumEjercicios() + " ejercicios.");
                btContinuar.setBackground(Estilos.colorMedio);
                btContinuar.setEnabled(true);
                break;
            }
            case 1: {
                lbCargando.setText("Ups...");
                lbBuscando.setText("No se ha podido encontrar el archivo.");
                lbEstado.setText("No se han podido cargar los ejercicios.");
                break;
            }
            case 2: {
                lbCargando.setText("Ups...");
                lbBuscando.setText("Un dato del archivo no coincide con el formato.");
                lbEstado.setText("No se han podido cargar los ejercicios.");
                break;
            }
            case 3: {
                lbCargando.setText("Ups...");
                lbBuscando.setText("Ha ocurrido un error inesperado.");
                lbEstado.setText("No se han podido cargar los ejercicios.");
                break;
            }
        }
    }
}
