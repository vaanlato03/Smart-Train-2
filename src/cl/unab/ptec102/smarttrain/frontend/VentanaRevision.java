package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.Ejercicio;
import cl.unab.ptec102.smarttrain.backend.GestorRutinas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaRevision {

    private GestorRutinas gestor;
    private JFrame ventanaRevision;
    private JPanel panelRevision;
    private JLabel lbRevision, lbEjercicioActual, lbNombre, lbTipo, lbIntensidad, lbTiempo, lbDescripcion;
    private JTextArea taDescripcion;
    private JButton btAnterior, btSiguiente;
    private ArrayList<Ejercicio> ejercicios;
    private int indiceActual;

    public VentanaRevision(GestorRutinas gestor) {
        this.gestor = gestor;
        ejercicios = this.gestor.getRutinaActual().getEjercicios();
        indiceActual = 0;

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

        mostrarEjercicio(indiceActual);

        ventanaRevision.setVisible(true);
    }

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

    public void componerLayout() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelRevision.add(lbRevision, gbc);

        gbc.gridy = 1;
        panelRevision.add(lbEjercicioActual, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(20, 5, 20, 5);
        panelRevision.add(lbNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelRevision.add(lbTipo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelRevision.add(lbIntensidad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelRevision.add(lbTiempo, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelRevision.add(lbDescripcion, gbc);

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelRevision.add(taDescripcion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelRevision.add(btAnterior, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        panelRevision.add(btSiguiente, gbc);
    }

    public void setActionListeners() {
        btAnterior.addActionListener(e -> {
           if (indiceActual > 0) {
               indiceActual--;
               mostrarEjercicio(indiceActual);
           }
        });

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

    public void mostrarEjercicio(int indice) {
        Ejercicio ejercicio = ejercicios.get(indice);
        lbEjercicioActual.setText("Ejercicio " + (indice + 1) + " de " + ejercicios.size());
        lbNombre.setText(ejercicio.getNombre());
        lbTipo.setText("Tipo: " + ejercicio.getTipoString());
        lbIntensidad.setText("Intensidad: " + ejercicio.getIntensidadString());
        lbTiempo.setText("Tiempo estimado: " + ejercicio.getTiempoMinutos() + " minutos");
        taDescripcion.setText(ejercicio.getDescripcion());

        if (indiceActual == 0) {
            btAnterior.setEnabled(false);
            btAnterior.setBackground(Color.GRAY);
        } else {
            btAnterior.setEnabled(true);
            btAnterior.setBackground(Estilos.colorMedio);
        }

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
