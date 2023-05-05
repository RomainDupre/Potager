/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
/**
 *
 * @author frederic
 */
public class Case extends JPanel {
    int x;
    int y;
    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Get Random Color
     */
    public Color getRandomColor() {
        int r = (int) (Math.random() * 256); //red
        int g = (int) (Math.random() * 256); //green
        int b = (int) (Math.random() * 256); //blue
        return new Color(r, g, b);
    }
}
