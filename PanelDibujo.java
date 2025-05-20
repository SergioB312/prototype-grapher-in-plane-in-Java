// Importamos las bibliotecas necesarias para crear la interfaz gráfica y trabajar con gráficos.
import javax.swing.*;
import java.awt.*;

// Definimos una clase llamada PanelDibujo que hereda de JPanel.
// JPanel es un componente que se puede usar para dibujar y organizar otros componentes en una ventana.
public class PanelDibujo extends JPanel {
    // Declaramos cuatro variables para almacenar las coordenadas de los puntos.
    double x1, y1, x2, y2;

    // Este es el constructor de la clase PanelDibujo.
    // Recibe cuatro parámetros: x1, y1, x2, y2, que son las coordenadas de los puntos a dibujar.
public PanelDibujo(double x1, double y1, double x2, double y2) {
    // Asignamos los valores de los parámetros a las variables de la clase.
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
}

    // Este método se llama automáticamente cuando se necesita dibujar el panel.
    // Aquí es donde se realiza todo el dibujo.
    protected void paintComponent(Graphics g) {
        // Llamamos al método de la clase padre para asegurarnos de que el panel se dibuje correctamente.
        super.paintComponent(g);

        // Convertimos g a Graphics2D para poder dibujar flechas
        Graphics2D g2 = (Graphics2D) g;
        
        // Establecemos el color de fondo a blanco.
        g.setColor(Color.WHITE);
        // Dibujamos un rectángulo blanco que cubre todo el panel.
        g.fillRect(0, 0, getWidth(), getHeight());

        // Calculamos el centro del panel.
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;

        // Establecemos el color a gris claro para dibujar los ejes.
        g.setColor(Color.LIGHT_GRAY);
        // Dibujamos la línea del eje X (horizontal) en el centro del panel.
        g.drawLine(0, centroY, getWidth(), centroY);
        // Dibujamos la línea del eje Y (vertical) en el centro del panel.
        g.drawLine(centroX, 0, centroX, getHeight());

        // Definimos una escala para convertir las coordenadas a píxeles.
        int escala = 40;

        // Dibujar ejes 
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));

        // Dibujar números en ejes
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.setColor(Color.BLACK);
        for (int i = -10; i <= 10; i++) {
        if (i == 0) continue;
        g.drawString(Integer.toString(i), centroX + i * escala - 5, centroY + 15); // eje X
        g.drawString(Integer.toString(-i), centroX - 15, centroY + i * escala + 5); // eje Y
        }

        // Etiquetas
        g.drawString("x", getWidth() - 15, centroY - 5);
        g.drawString("y", centroX + 5, 15);

        // Convertimos las coordenadas de los puntos a píxeles.
        int px1 = centroX + (int)(x1 * escala); // Coordenada X del primer punto
        int py1 = centroY - (int)(y1 * escala); // Coordenada Y del primer punto
        int px2 = centroX + (int)(x2 * escala); // Coordenada X del segundo punto
        int py2 = centroY - (int)(y2 * escala); // Coordenada Y del segundo punto

        // Establecemos el color a azul para dibujar la línea entre los dos puntos.
        g.setColor(Color.BLUE);
        // Dibujamos la línea que conecta los dos puntos.
        g.drawLine(px1, py1, px2, py2);

       // Dibujar flechas en ambos extremos
       drawArrowHead(g2, px2, py2, px1, py1); // hacia px1
       drawArrowHead(g2, px1, py1, px2, py2); // hacia px2
       
   }

   // Método para dibujar flecha
   private void drawArrowHead(Graphics2D g2, int tipX, int tipY, int tailX, int tailY) {
    double phi = Math.toRadians(30);
    int barb = 12;
    g2.setColor(Color.RED);
    g2.setColor(Color.RED);
    double dy = tipY - tailY;
    double dx = tipX - tailX;
    double theta = Math.atan2(dy, dx);

    double x1 = tipX - barb * Math.cos(theta + phi);
    double y1 = tipY - barb * Math.sin(theta + phi);
    double x2 = tipX - barb * Math.cos(theta - phi);
    double y2 = tipY - barb * Math.sin(theta - phi);

    g2.drawLine(tipX, tipY, (int)x1, (int)y1);
    g2.drawLine(tipX, tipY, (int)x2, (int)y2);
        
    
    }
}
