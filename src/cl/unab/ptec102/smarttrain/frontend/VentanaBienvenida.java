package cl.unab.ptec102.smarttrain.frontend;

import cl.unab.ptec102.smarttrain.backend.GestorRutinas;

import javax.swing.*;
import java.awt.*;

/// Ventana de bienvenida. Se abre al iniciar el programa y solicita el nombre del cliente.
public class VentanaBienvenida {

    // Elementos de la ventana
    private JFrame ventanaBienvenida;
    private JLabel lbFondo, lbLogo, lbBienvenido, lbIngresar, lbCliente, lbMensaje;
    private JTextField tfCliente;
    private JButton btCargar;

    // Constructor
    public VentanaBienvenida() {
        // Inicializa y configura el JFrame.
        ventanaBienvenida = new JFrame();
        ventanaBienvenida.setSize(600,400);
        ventanaBienvenida.setTitle("Bienvenido");
        ventanaBienvenida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crearComponentes();
        componerLayout();
        setActionListeners();

        ventanaBienvenida.setContentPane(lbFondo);
        ventanaBienvenida.setIconImage(Estilos.icono.getImage());
        ventanaBienvenida.setVisible(true);
        ventanaBienvenida.setResizable(false);
        ventanaBienvenida.setLocationRelativeTo(null);
    }

    /// Inicializa los componentes que tendrá la ventana y los estiliza.
    public void crearComponentes() {
        lbFondo = new JLabel(Estilos.fondoMancuernas);
        lbFondo.setLayout(new GridBagLayout());
        lbFondo.setFont(Estilos.fuente);

        lbLogo = new JLabel(Estilos.logo);
        lbLogo.setPreferredSize(new Dimension(300, 108));
        lbLogo.setMinimumSize(new Dimension(300, 108));
        lbLogo.setMaximumSize(new Dimension(300, 108));

        lbBienvenido = new JLabel("¡BIENVENIDO A SMART TRAIN!");
        lbBienvenido.setFont(Estilos.fuente.deriveFont(26f));
        lbBienvenido.setForeground(Estilos.colorMedio);

        lbIngresar = new JLabel("Ingrese su nombre para cargar los ejercicios disponibles.");
        lbIngresar.setFont(Estilos.fuente.deriveFont(18f));
        lbIngresar.setForeground(Estilos.colorClaro);

        lbMensaje = new JLabel(" ");
        lbMensaje.setFont(Estilos.fuente.deriveFont(Font.PLAIN, 14));
        lbMensaje.setForeground(Estilos.colorRojo);

        lbCliente = new JLabel("Nombre del cliente: ");
        lbCliente.setFont(Estilos.fuente);
        lbCliente.setForeground(Color.WHITE);

        tfCliente = new JTextField();
        tfCliente.setFont(Estilos.fuente);
        tfCliente.setForeground(Estilos.colorOscuro);

        btCargar = new JButton("Cargar ejercicios");
        btCargar.setBackground(Estilos.colorMedio);
        btCargar.setForeground(Estilos.colorOscuro);
        btCargar.setFont(Estilos.fuente);
        btCargar.setMargin(new Insets(5,30,5,30));
        btCargar.setFocusPainted(false);
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
        lbFondo.add(lbLogo, gbc);

        // Fila 1
        gbc.gridy = 1;
        lbFondo.add(lbBienvenido, gbc);

        // Fila 2
        gbc.gridy = 2;
        lbFondo.add(lbIngresar, gbc);

        // Fila 3
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0,10,0,10);
        gbc.anchor = GridBagConstraints.WEST;
        lbFondo.add(lbMensaje, gbc);

        // Fila 4
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.CENTER;
        lbFondo.add(lbCliente, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lbFondo.add(tfCliente, gbc);

        // Fila 5
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        lbFondo.add(btCargar, gbc);
    }

    /// Configura los Listeners de los elementos de la ventana.
    public void setActionListeners() {
        // Si el nombre del cliente es válido crea la siguiente ventana y cierra la ventana actual.
        btCargar.addActionListener(e -> {
            String cliente = tfCliente.getText().trim();
            if (!cliente.isBlank() && cliente.length()<30) {
                VentanaCarga carga = new VentanaCarga(new GestorRutinas(cliente)); // Aquí se crea el gestor.
                ventanaBienvenida.dispose();
            } else {
                lbMensaje.setText("El campo no puede estar vacío ni contener más de 30 caracteres.");
            }
        });

        // Como evento adicional, al presionar Enter desde el TextField, simula un clic en el botón Cargar.
        tfCliente.addActionListener(e -> {
            btCargar.doClick();
        });
    }
}
