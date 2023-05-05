package blokus;

public class Controller {
    private Modele m;
    Controller (Modele m) {
        this.m = m;
    }
    public void ActionPerformed(int x, int y) {
        m.changeColor(x, y);
    }
}
