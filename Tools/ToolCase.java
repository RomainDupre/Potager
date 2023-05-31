package blokus.Tools;

import blokus.LegumeModele.Legumes;
import blokus.Tools.Tools;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ToolCase extends JLabel implements Observer {
    private Tools tool;
    private String label;

    public ToolCase(Tools tool) {
        this.tool = tool;
        this.label = tool.getLabel();
        add(new JLabel(label));
    }

    public String getLabel() {
        return label;
    }

    public Tools getTool() {
        return tool;
    }
    @Override
    public void update(Observable o, Object arg) {

    }
}
