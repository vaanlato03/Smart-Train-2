package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.GestorRutinas;

import javax.swing.*;
import java.awt.*;

/// Ventana de generación de la rutina. Esta ventana utiliza un
/// JDialog para comportarse de manera modal con la Ventana Principal
public class VentanaGenerarRutina {

    // Atributo para recibir el gestor creado anteriormente.
    private GestorRutinas gestor;

    // Atributo para recibir la ventana principal.
    private VentanaPrincipal padre;

    // Elementos de la ventana
    private JDialog ventanaGenerarRutina;
    private JPanel panelGenerarRutina;
    private JLabel lbGenera, lbIngresar, lbIntensidad, lbFuerza, lbFuerzaDisp, lbCardio, lbCardioDisp;
    private JComboBox<String> cbIntensidades;
    private JSpinner cantFuerza, cantCardio;
    private JButton btGenerar, btCancelar;

    public VentanaGenerarRutina(GestorRutinas gestor, VentanaPrincipal padre) {
        // Recibe el gestor y la ventana principal y los almacena en sus atributos.
        this.gestor = gestor;
        this.padre = padre;

        // Inicializa y configura el JDialog, con el JFrame de la ventana principal como padre.
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

    /// Inicializa los componentes que tendrá la ventana y los estiliza.
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

    /// Utiliza GridBagConstraints para componer el GridBagLayout principal.
    public void componerLayout() {
        // Se reutiliza gbc para acomodar todos los elementos.
        GridBagConstraints gbc = new GridBagConstraints();

        // Fila 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelGenerarRutina.add(lbGenera, gbc);

        // Fila 1
        gbc.gridy = 1;
        panelGenerarRutina.add(lbIngresar, gbc);

        // Fila 2
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelGenerarRutina.add(lbIntensidad, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelGenerarRutina.add(cbIntensidades, gbc);

        // Fila 3
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        panelGenerarRutina.add(lbFuerza, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelGenerarRutina.add(cantFuerza, gbc);

        // Fila 4
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 10, 10, 10);
        panelGenerarRutina.add(lbFuerzaDisp, gbc);

        // Fila 5
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelGenerarRutina.add(lbCardio, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelGenerarRutina.add(cantCardio, gbc);

        // Fila 6
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 10, 10, 10);
        panelGenerarRutina.add(lbCardioDisp, gbc);

        // Fila 7
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        panelGenerarRutina.add(btCancelar, gbc);

        gbc.gridx = 1;
        panelGenerarRutina.add(btGenerar, gbc);
    }

    /// Configura los Listeners de los elementos de la ventana.
    public void setActionListeners() {
        // Botón Cancelar cierra la ventana actual (modal), volviendo a la ventana principal.
        btCancelar.addActionListener(e -> ventanaGenerarRutina.dispose());

        // Al seleccionar una intensidad válida se habilitan los spinners
        // y el botón Generar, en caso contrario se deshabilitan.
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

        // Al presionar el botón generar, acciona la generación de la rutina.
        btGenerar.addActionListener(e -> generarRutina());

        // (ChangeListener) Al cambiar de valor, verifican si se debe habilitar el botón Generar.
        cantFuerza.addChangeListener(e -> habilitarBtGenerar());
        cantCardio.addChangeListener(e -> habilitarBtGenerar());
    }

    /// Habilita los spinner cantFuerza y cantCardio, y adapta el modelo y el texto
    /// en función de los ejercicios disponibles dada la intensidad.
    /// @param intensidad intensidad de los ejercicios que estarán disponibles.
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

    /// Deshabilita los spinner cantFuerza y cantCardio, y restaura los textos.
    public void deshabilitarSpinners() {
        cantFuerza.setEnabled(false);
        cantCardio.setEnabled(false);
        lbFuerzaDisp.setText("(!) Disponibles para ti: ---");
        lbCardioDisp.setText("(!) Disponibles para ti: ---");
    }

    /// Verifica si se debe habilitar o no el botón Generar, dependiendo de
    /// si un spinner habilitado tiene un valor mayor a 0.
    public void habilitarBtGenerar() {
        int valorFuerza = (cantFuerza.isEnabled()) ? (int)cantFuerza.getValue() : 0;
        int valorCardio = (cantCardio.isEnabled()) ? (int)cantCardio.getValue() : 0;

        if (valorFuerza > 0 || valorCardio > 0) {
            btGenerar.setBackground(Estilos.colorMedio);
            btGenerar.setEnabled(true);
        } else {
            btGenerar.setBackground(Color.GRAY);
            btGenerar.setEnabled(false);
        }
    }

    /// Recolecta los valores y utiliza al gestor para generar la rutina, espera el resultado
    /// y, continua con la siguiente ventana, o muestra un mensaje de error.
    public void generarRutina() {
        int valorFuerza = (int)cantFuerza.getValue();
        int valorCardio = (int)cantCardio.getValue();
        int intensidad = cbIntensidades.getSelectedIndex();
        boolean exito = gestor.generarRutina(valorFuerza, valorCardio, intensidad);
        if (exito) {
            VentanaRevision revision = new VentanaRevision(gestor); // Crea la ventana y pasa el gestor.
            this.padre.cerrarVentana(); // Utiliza cerrarVentana() de VentanaPrincipal para cerrarla.
            ventanaGenerarRutina.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error al generar la rutina.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
