import javax.swing.JButton;
import javax.swing.JFrame;

public class FenAjoutBouton extends JFrame {
    public FenAjoutBouton(String titre, int w, int h) {
        super(titre);
        this.initialise();
        this.setSize(w,h);
        this.setVisible(true);
    }

    private void initialise() {
        JButton butOK = new JButton("OK");
        this.add(butOK);
    }

    public static void main(String[] args) {
        new FenAjoutBouton("Ajout d'un bouton", 300, 100);
    }
}