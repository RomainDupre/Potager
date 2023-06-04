package blokus.RecolteLegume;

import blokus.LegumeCase;
import blokus.LegumeModele.Legumes;

import javax.swing.*;

public class LegumesRecolte extends LegumeCase {

    private JLabel nbrLegumeRecolte;
    public LegumesRecolte(Legumes legume)
    {
        super(legume);
        nbrLegumeRecolte = new JLabel("x"+0);
        add(nbrLegumeRecolte);
        nbrLegumeRecolte.setVisible(true);

    }

    public void setNbrLegumeRecolte(int nbrLegumeRecolte)
    {
        this.nbrLegumeRecolte.setText("x"+Integer.toString(nbrLegumeRecolte));
    }
    public JLabel getNbrLegumeRecolte()
    {
        return nbrLegumeRecolte;
    }
}
