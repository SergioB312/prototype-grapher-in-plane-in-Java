import javax.swing.*;
import java.awt.*;

public class PanelMultiple extends JPanel {
    double[] x1s, y1s, x2s, y2s;

    public PanelMultiple(double[] x1s, double[] y1s, double[] x2s, double[] y2s) {
        this.x1s = x1s;
        this.y1s = y1s;
        this.x2s = x2s;
        this.y2s = y2s;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, centroY, getWidth(), centroY); // eje X
        g.drawLine(centroX, 0, centroX, getHeight()); // eje Y

        int escala = 40;

        // Dibujar ejes con flechas
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));

        // Dibujar números en ejes
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.setColor(Color.BLACK);
        for (int i = -10; i <= 10; i++) {
        if (i == 0) continue;
        g.drawString(Integer.toString(i), centroX + i * escala - 5, centroY + 15); // eje X
        g.drawString(Integer.toString(-i), centroX - 15, centroY + i * escala + 5); // eje Y
        }

            // Etiquetas
        g.drawString("x", getWidth() - 15, centroY - 5);
        g.drawString("y", centroX + 5, 15);

        Color[] colores = {Color.BLUE, Color.RED, Color.BLACK, Color.GREEN, Color.ORANGE, Color.CYAN };

        for (int i = 0; i < x1s.length; i++) {
            int px1 = centroX + (int)(x1s[i] * escala);
            int py1 = centroY - (int)(y1s[i] * escala);
            int px2 = centroX + (int)(x2s[i] * escala);
            int py2 = centroY - (int)(y2s[i] * escala);
            Color color = colores[i % colores.length]; 

            g.setColor(colores[i % colores.length]);
            g.drawLine(px1, py1, px2, py2);

            g2.setColor(color.darker()); // La flehca sale un poco mas oscura
            drawArrowHead(g2, px2, py2, px1, py1);
            drawArrowHead(g2, px1, py1, px2, py2);
        }

    }
     private void drawArrowHead(Graphics2D g2, int tipX, int tipY, int tailX, int tailY) {
        double phi = Math.toRadians(30);
        int barb = 12;

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