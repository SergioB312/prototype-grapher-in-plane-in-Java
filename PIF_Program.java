import javax.swing.*;
import java.awt.*;

// Clase principal que inicia el programa
public class PIF_Program {
    public static void main(String[] args) {
        // Inicia la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true); // Muestra la ventana principal
        });
    }
}
 
// Clase que representa la ventana principal del programa
class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Menú Principal"); // Título de la ventana
        setSize(400, 300); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la app al cerrar la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Crear un panel para organizar los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 filas, 1 columna, espacio de 10px

        // Crear botones para cada opción del menú
        JButton btnPuntoPunto = new JButton("Fórmula punto-punto");
        JButton btnPuntoPendiente = new JButton("Fórmula punto-pendiente");
        JButton btnAprendizaje = new JButton("Modo Aprendizaje");
        JButton btnVariasGraficas = new JButton("Varias Gráficas");
        JButton btnSalir = new JButton("Salir");

        // Asignar acciones a cada botón usando expresiones lambda
        btnPuntoPunto.addActionListener(e -> abrirVentanaPuntoPunto());
        btnPuntoPendiente.addActionListener(e -> abrirVentanaPuntoPendiente());
        btnAprendizaje.addActionListener(e -> abrirModoAprendizaje());
        btnVariasGraficas.addActionListener(e -> abrirVariasGraficas());
        btnSalir.addActionListener(e -> System.exit(0)); // Sale del programa

        // Añadir los botones al panel
        panel.add(btnPuntoPunto);
        panel.add(btnPuntoPendiente);
        panel.add(btnAprendizaje);
        panel.add(btnVariasGraficas);
        panel.add(btnSalir);

        // Añadir el panel a la ventana principal
        add(panel);
    }

    // Método para abrir la ventana de la fórmula punto-punto
    private void abrirVentanaPuntoPunto() {
        // Solicita los valores de los puntos al usuario
        String x1 = JOptionPane.showInputDialog(this, "Introduce X1:");
        String y1 = JOptionPane.showInputDialog(this, "Introduce Y1:");
        String x2 = JOptionPane.showInputDialog(this, "Introduce X2:");
        String y2 = JOptionPane.showInputDialog(this, "Introduce Y2:");

        // Convierte los valores ingresados a tipo double
        double fx1 = Double.parseDouble(x1);
        double fy1 = Double.parseDouble(y1);
        double fx2 = Double.parseDouble(x2);
        double fy2 = Double.parseDouble(y2);

        // Crea y muestra la ventana gráfica con los puntos ingresados
        SwingUtilities.invokeLater(() -> {
            // VentanaGrafica es una ventana que muestra la recta entre dos puntos
            VentanaGrafica ventana = new VentanaGrafica(fx1, fy1, fx2, fy2);
            ventana.setVisible(true);
        });
    }

    // Método para abrir la ventana de la fórmula punto-pendiente
    private void abrirVentanaPuntoPendiente() {
        // Solicita el punto y la pendiente al usuario
        String x1 = JOptionPane.showInputDialog(this, "Introduce X1:");
        String y1 = JOptionPane.showInputDialog(this, "Introduce Y1:");
        String m = JOptionPane.showInputDialog(this, "Introduce la pendiente:");

        // Convierte los valores ingresados a tipo double
        double fx1 = Double.parseDouble(x1);
        double fy1 = Double.parseDouble(y1);
        double fm = Double.parseDouble(m);

        // Calcula el segundo punto usando la pendiente (x2 = x1 + 1, y2 = y1 + m)
        double fx2 = fx1 + 1;
        double fy2 = fy1 + fm;

        // Crea y muestra la ventana gráfica con el punto y la pendiente
        SwingUtilities.invokeLater(() -> {
            // VentanaGrafica es una ventana que muestra la recta usando punto y pendiente
            VentanaGrafica ventana = new VentanaGrafica(fx1, fy1, fx2, fy2);
            ventana.setVisible(true);
        });
    }

    // Método para abrir el modo aprendizaje
    private void abrirModoAprendizaje() {
        // Crea y muestra la ventana de aprendizaje
        SwingUtilities.invokeLater(() -> {
            // VentanaAprendizaje es una ventana especial para practicar y aprender
            VentanaAprendizaje ventana = new VentanaAprendizaje();
            ventana.setVisible(true);
        });
    }

    // Método para abrir la ventana de varias gráficas
    private void abrirVariasGraficas() {
        // Solicita al usuario cuántas rectas quiere graficar
        String nStr = JOptionPane.showInputDialog(this, "¿Cuántas rectas deseas graficar?");
        int n = Integer.parseInt(nStr);

        // Crea arreglos para almacenar los puntos de cada recta
        double[] x1s = new double[n];
        double[] y1s = new double[n];
        double[] x2s = new double[n];
        double[] y2s = new double[n];

        // Ciclo que se repite una vez por cada recta que el usuario quiere graficar
        for (int i = 0; i < n; i++) {
            // Para cada recta, pide los valores de los dos puntos (X1, Y1, X2, Y2)
            String x1 = JOptionPane.showInputDialog(this, "Recta " + (i + 1) + " - Introduce X1:");
            String y1 = JOptionPane.showInputDialog(this, "Recta " + (i + 1) + " - Introduce Y1:");
            String x2 = JOptionPane.showInputDialog(this, "Recta " + (i + 1) + " - Introduce X2:");
            String y2 = JOptionPane.showInputDialog(this, "Recta " + (i + 1) + " - Introduce Y2:");

            // Guarda los valores convertidos a double en los arreglos correspondientes
            x1s[i] = Double.parseDouble(x1);
            y1s[i] = Double.parseDouble(y1);
            x2s[i] = Double.parseDouble(x2);
            y2s[i] = Double.parseDouble(y2);
        }

        // Cuando termina el ciclo, crea una nueva ventana para mostrar todas las rectas juntas
        // VentanaMultiple es una ventana que recibe los arreglos de puntos y los grafica
        SwingUtilities.invokeLater(() -> {
            VentanaMultiple ventana = new VentanaMultiple(x1s, y1s, x2s, y2s);
            ventana.setVisible(true); // Muestra la ventana con todas las gráficas
        });
    }
}