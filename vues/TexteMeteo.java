import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.Meteo;
import modele.Modele;

public class TexteMeteo extends JScrollPane {

    private Meteo modele;
    private JTextArea txtMeteo;

    public TexteMeteo() {
        super();
        txtMeteo = new JTextArea(15, 40);
        txtMeteo.setEditable(false);
        this.setViewportView(txtMeteo);
    }

    public void afficherModele () {
        txtMeteo.setText(modele.toString());
    }

    public void notifierChangement() {
        this.afficherModele();
    }

    public void setModele(Modele m) {
        this.modele = (Meteo) m;
        this.afficherModele();
    }
}