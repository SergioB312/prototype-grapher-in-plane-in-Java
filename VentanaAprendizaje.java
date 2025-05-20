import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAprendizaje extends JFrame {
    private JTextField inputResultado;
    private JLabel lblPregunta;
    private JLabel lblFeedback;

    private int x1, y1, x2, y2, pendiente;

    public VentanaAprendizaje() {
        setTitle("Modo Aprendizaje");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar el diseño de la ventana
        setLayout(new GridLayout(6, 1, 10, 10));

        // Generar valores aleatorios para la fórmula
        generarValores();

        // Crear componentes
        lblPregunta = new JLabel("Calcula la pendiente (m) entre los puntos (" + x1 + ", " + y1 + ") y (" + x2 + ", " + y2 + "):");
        lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);

        inputResultado = new JTextField();
        JButton btnVerificar = new JButton("Verificar");
        lblFeedback = new JLabel("", SwingConstants.CENTER);
        lblFeedback.setForeground(Color.BLUE);

        // Acción del botón "Verificar"
        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarResultado();
            }
        });

        // Agregar componentes a la ventana
        add(lblPregunta);
        add(inputResultado);
        add(btnVerificar);
        add(lblFeedback);

        // Botón para cerrar la ventana
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);
    }

    private void generarValores() {
        // Generar valores enteros aleatorios para los puntos
        x1 = (int) (Math.random() * 11 - 5); // Entre -5 y 5
        y1 = (int) (Math.random() * 11 - 5);
        x2 = (int) (Math.random() * 11 - 5);
        y2 = (int) (Math.random() * 11 - 5);

        // Asegurarse de que x1 != x2 para evitar división por cero
        while (x1 == x2) {
            x2 = (int) (Math.random() * 11 - 5);
        }

        // Calcular la pendiente como un número entero
        pendiente = (y2 - y1) / (x2 - x1);

        // Asegurarse de que la pendiente sea un número entero exacto
        while ((y2 - y1) % (x2 - x1) != 0) {
            x2 = (int) (Math.random() * 11 - 5);
            y2 = (int) (Math.random() * 11 - 5);
            pendiente = (y2 - y1) / (x2 - x1);
        }
    }

    private void verificarResultado() {
        try {
            // Obtener el resultado ingresado por el usuario
            int resultadoUsuario = Integer.parseInt(inputResultado.getText());

            // Verificar si el resultado es correcto
            if (resultadoUsuario == pendiente) {
                lblFeedback.setText("¡Correcto! La pendiente es " + pendiente);
                lblFeedback.setForeground(Color.GREEN);

                // Preguntar si desea ver la gráfica
                int opcion = JOptionPane.showConfirmDialog(this, "¿Quieres ver la gráfica?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    mostrarGrafica();
                }
            } else {
                mostrarExplicacion();
            }
        } catch (NumberFormatException e) {
            lblFeedback.setText("Por favor, introduce un número válido.");
            lblFeedback.setForeground(Color.RED);
        }
    }

    private void mostrarExplicacion() {
        // Mostrar una explicación paso a paso
        String explicacion = "<html><body style='text-align: center;'>"
                + "Incorrecto. Aquí tienes cómo calcular la pendiente:<br><br>"
                + "Fórmula: m = (y2 - y1) / (x2 - x1)<br>"
                + "Sustituyendo:<br>"
                + "m = (" + y2 + " - " + y1 + ") / (" + x2 + " - " + x1 + ")<br>"
                + "m = " + (y2 - y1) + " / " + (x2 - x1) + "<br>"
                + "m = " + pendiente + "<br><br>"
                + "Intenta de nuevo."
                + "</body></html>";

        lblFeedback.setText(explicacion);
        lblFeedback.setForeground(Color.RED);
    }

    private void mostrarGrafica() {
        // Abrir una ventana gráfica para mostrar la recta
        SwingUtilities.invokeLater(() -> {
            VentanaGrafica ventana = new VentanaGrafica(x1, y1, x2, y2);
            ventana.setVisible(true);
        });
    }
}