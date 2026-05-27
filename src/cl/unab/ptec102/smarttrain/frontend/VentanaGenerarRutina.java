package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.GestorRutinas;

import javax.swing.*;
import java.awt.*;

public class VentanaGenerarRutina {

    private GestorRutinas gestor;
    private VentanaPrincipal padre;
    private JDialog ventanaGenerarRutina;
    private JPanel panelGenerarRutina;
    private JLabel lbGenera, lbIngresar, lbIntensidad, lbFuerza, lbFuerzaDisp, lbCardio, lbCardioDisp;
    private JComboBox<String> cbIntensidades;
    private JSpinner cantFuerza, cantCardio;
    private JButton btGenerar, btCancelar;

    public VentanaGenerarRutina(GestorRutinas gestor, VentanaPrincipal padre) {
        this.gestor = gestor;
        this.padre = padre;
        ventanaGenerarRutina = new JDialog(padre.getFrame(), true);
        ventanaGenerarRutina.setSize(500, 400);
        ventanaGenerarRutina.setTitle("Generar Rutina");
        ventanaGenerarRutina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        crearComponentes();
        componerLayout();
        setActionListeners();

        ventanaGenerarRutina.setContentPane(panelGenerarRutina);
        ventanaGenerarRutina.setIconImage(Estilos.icono.getImage());
        ventanaGenerarRutina.setResizable(false);
        ventanaGenerarRutina.setLocationRelativeTo(null);
        ventanaGenerarRutina.setVisible(true);
    }

    public void crearComponentes() {
        panelGenerarRutina = new JPanel(new GridBagLayout());
        panelGenerarRutina.setFont(Estilos.fuente);
        panelGenerarRutina.setBackground(Estilos.colorOscuroFondo);

        lbGenera = new JLabel("Generar tu rutina!");
        lbGenera.setFont(Estilos.fuente.deriveFont(26f));
        lbGenera.setForeground(Estilos.colorMedio);

        lbIngresar = new JLabel("Ingresar los datos para generar la rutina.");
        lbIngresar.setFont(Estilos.fuente.deriveFont(20f));
        lbIngresar.setForeground(Estilos.colorClaro);

        lbIntensidad = new JLabel("Nivel de intensidad:");
        lbIntensidad.setFont(Estilos.fuente);
        lbIntensidad.setForeground(Color.WHITE);

        cbIntensidades = new JComboBox<>();
        cbIntensidades.addItem("Seleccionar...");
        cbIntensidades.addItem("Básico");
        cbIntensidades.addItem("Intermedio");
        cbIntensidades.addItem("Avanzado");
        cbIntensidades.addItem("Alto Rendimiento");
        cbIntensidades.setSelectedIndex(0);
        cbIntensidades.setFont(Estilos.fuente);
        cbIntensidades.setForeground(Estilos.colorOscuro);

        lbFuerza = new JLabel("N° ejercicios de Fuerza:");
        lbFuerza.setFont(Estilos.fuente);
        lbFuerza.setForeground(Color.WHITE);

        cantFuerza = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        cantFuerza.setFont(Estilos.fuente);
        cantFuerza.setForeground(Estilos.colorOscuro);
        cantFuerza.setEnabled(false);

        lbFuerzaDisp = new JLabel("(!) Disponibles para ti: ---");
        lbFuerzaDisp.setForeground(Estilos.colorClaro);
        lbFuerzaDisp.setFont(Estilos.fuente.deriveFont(Font.PLAIN, 14));
        lbFuerzaDisp.setToolTipText("Solo están disponibles los ejercicios que no hayas realizado la semana pasada.");

        lbCardio = new JLabel("N° ejercicios de Cardio:");
        lbCardio.setFont(Estilos.fuente);
        lbCardio.setForeground(Color.WHITE);

        cantCardio = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        cantCardio.setFont(Estilos.fuente);
        cantCardio.setForeground(Estilos.colorOscuro);
        cantCardio.setEnabled(false);

        lbCardioDisp = new JLabel("(!) Disponibles para ti: ---");
        lbCardioDisp.setForeground(Estilos.colorClaro);
        lbCardioDisp.setFont(Estilos.fuente.deriveFont(Font.PLAIN, 14));
        lbCardioDisp.setToolTipText("Solo están disponibles los ejercicios que no hayas realizado la semana pasada.");

        btGenerar = new JButton("Generar");
        btGenerar.setFont(Estilos.fuente);
        btGenerar.setForeground(Estilos.colorOscuro);
        btGenerar.setBackground(Color.GRAY);
        btGenerar.setMargin(new Insets(5, 30, 5, 30));
        btGenerar.setFocusPainted(false);
        btGenerar.setEnabled(false);

        btCancelar = new JButton("Cancelar");
        btCancelar.setFont(Estilos.fuente);
        btCancelar.setForeground(Color.WHITE);
        btCancelar.setBackground(Estilos.colorRojo);
        btCancelar.setMargin(new Insets(5, 30, 5, 30));
        btCancelar.setFocusPainted(false);
    }

    public void componerLayout() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelGenerarRutina.add(lbGenera, gbc);

        gbc.gridy = 1;
        panelGenerarRutina.add(lbIngresar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelGenerarRutina.add(lbIntensidad, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelGenerarRutina.add(cbIntensidades, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        panelGenerarRutina.add(lbFuerza, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelGenerarRutina.add(cantFuerza, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 10, 10, 10);
        panelGenerarRutina.add(lbFuerzaDisp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelGenerarRutina.add(lbCardio, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelGenerarRutina.add(cantCardio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 10, 10, 10);
        panelGenerarRutina.add(lbCardioDisp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelGenerarRutina.add(btCancelar, gbc);

        gbc.gridx = 1;
        panelGenerarRutina.add(btGenerar, gbc);
    }

    public void setActionListeners() {
        btCancelar.addActionListener(e -> ventanaGenerarRutina.dispose());

        cbIntensidades.addActionListener(e -> {
           int intensidad = cbIntensidades.getSelectedIndex();
           if (intensidad != 0) {
               habilitarSpinners(intensidad);
               habilitarBtGenerar();
           } else {
               deshabilitarSpinners();
               habilitarBtGenerar();
           }
        });

        cantFuerza.addChangeListener(e -> habilitarBtGenerar());

        cantCardio.addChangeListener(e -> habilitarBtGenerar());

        btGenerar.addActionListener(e -> generarRutina());
    }

    public void habilitarSpinners(int intensidad) {
        lbFuerzaDisp.setText("(!) Disponibles para ti: " + gestor.cantTipoPorIntenRestringido(intensidad, 1));
        cantFuerza.setEnabled(true);
        cantFuerza.setModel(new SpinnerNumberModel(0, 0,
                gestor.cantTipoPorIntenRestringido(intensidad, 1), 1));

        lbCardioDisp.setText("(!) Disponibles para ti: " + gestor.cantTipoPorIntenRestringido(intensidad, 2));
        cantCardio.setEnabled(true);
        cantCardio.setModel(new SpinnerNumberModel(0, 0,
                gestor.cantTipoPorIntenRestringido(intensidad, 2), 1));
    }

    public void deshabilitarSpinners() {
        cantFuerza.setEnabled(false);
        cantCardio.setEnabled(false);
        lbFuerzaDisp.setText("(!) Disponibles para ti: ---");
        lbCardioDisp.setText("(!) Disponibles para ti: ---");
    }

    public void habilitarBtGenerar() {
        int valorFuerza = (int)cantFuerza.getValue();
        int valorCardio = (int)cantCardio.getValue();
        if (valorFuerza > 0 || valorCardio > 0) {
            btGenerar.setBackground(Estilos.colorMedio);
            btGenerar.setEnabled(true);
        } else {
            btGenerar.setBackground(Color.GRAY);
            btGenerar.setEnabled(false);
        }
    }

    public void generarRutina() {
        int valorFuerza = (int)cantFuerza.getValue();
        int valorCardio = (int)cantCardio.getValue();
        int intensidad = cbIntensidades.getSelectedIndex();
        boolean exito = gestor.generarRutina(valorFuerza, valorCardio, intensidad);
        if (exito) {
            VentanaRevision revision = new VentanaRevision(gestor);
            this.padre.cerrarVentana();
            ventanaGenerarRutina.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error al generar la rutina.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
