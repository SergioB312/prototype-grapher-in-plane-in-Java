// Importamos la biblioteca Swing, que pues sirve para crear la ventana gráfica.
import javax.swing.*;

// JFrame es una clase que representa una ventana en una aplicación gráfica.
public class VentanaGrafica extends JFrame {

    // se llama al constructor de la clase JFrame para crear la ventana.
    public VentanaGrafica(double x1, double y1, double x2, double y2) {
        
        setTitle("Recta punto-punto o recta punto-pendiente");
        
        setSize(1150, 1150);
        setResizable(false);
        
        // la ventana se cerrará pero la aplicación seguirá ejecutándose.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLocationRelativeTo(null);
        
        // Este panel se encargará de dibujar algo en la ventana utilizando las coordenadas que se pasaron.
        add(new PanelDibujo(x1, y1, x2, y2));
    }
}