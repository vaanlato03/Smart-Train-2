package cl.unab.ptec102.smarttrain.frontend;

import javax.swing.*;
import java.awt.*;

/// Clase de utilidad con elementos del diseño del frontend del programa como colores,
/// la fuente principal, las imágenes a utilizar, entre otros.
public class Estilos {
    // Fuente principal
    public static final Font fuente = new Font("Arial", Font.BOLD, 16);

    // Colores
    public static final Color colorMedio = new Color(25, 157, 227);
    public static final Color colorOscuro = new Color(2,7,23);
    public static final Color colorOscuroFondo = new Color(22, 26, 46);
    public static final Color colorClaroOpacidad = new Color(172,232,252, 30);
    public static final Color colorClaro = new Color(172, 232, 252);
    public static final Color colorRojo = new Color(196, 33, 33);

    // Imágenes
    public static final ImageIcon icono;
    public static final ImageIcon logo;
    public static final ImageIcon fondoMancuernas;
    public static final ImageIcon fondoGimnasio;

    // String decorativo
    public static final String lineaDecorativa = "=".repeat(50) + "\n";

    // Cargar las imágenes una vez al inicio del programa
    static {
        icono = cargarImagen("recursos/IconoSmartTrain.png");
        logo = cargarImagen("recursos/LogoSmartTrain.png");
        fondoMancuernas = cargarImagen("recursos/FondoMancuernas.jpg");
        fondoGimnasio = cargarImagen("recursos/FondoGimnasio.jpg");
    }

    /// Carga las imágenes en un ImageIcon desde la ruta especificada.
    /// @param ruta ruta relativa desde la clase a la imagen.
    private static ImageIcon cargarImagen(String ruta) {
        try {
            return new ImageIcon(Estilos.class.getResource(ruta));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Hay un error con los recursos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return null;
        }
    }
}
