import javax.swing.*;

public class VentanaMultiple extends JFrame {
    
    public VentanaMultiple(double[] x1s, double[] y1s, double[] x2s, double[] y2s) {
        setTitle("Varias Rectas");

        setSize(1150, 1150);
        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        add(new PanelMultiple(x1s, y1s, x2s, y2s));
    }
}