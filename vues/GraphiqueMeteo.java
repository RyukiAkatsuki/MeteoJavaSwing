package vues;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import modele.Meteo;

public class GraphiqueMeteo extends JPanel {

    private Meteo modele;

    public GraphiqueMeteo(Meteo m) {
        super();
        this.modele = m;
    }

    public int[] absJours1900() {
        int[] tab = new int[modele.nbJours()];

        long tempsMin = modele.minJours1900();
        long tempsMax = modele.maxJours1900();

        for (int i = 0; i < tab.length; i++) {
            long temps = modele.getJours1900(i);
            tab[i] = (int) (this.getWidth() * (temps - tempsMin) / (tempsMax - tempsMin));
        }
        return tab;
    }

    public int[] ordTemp() {
        int[] tab = new int[modele.nbJours()];

        int tempMin = modele.minTemp();
        int tempMax = modele.maxTemp();

        for (int i = 0; i < tab.length; i++) {
            int temp = modele.getTemp(i);
            tab[i] = (int) (this.getHeight() - this.getHeight() * (temp - tempMin) / (tempMax - tempMin));
        }
        return tab;
    }

    public int[] ordPrec() {
        int[] tab = new int[modele.nbJours()];

        int precMin = modele.minPrec();
        int precMax = modele.maxPrec();

        for (int i = 0; i < tab.length; i++) {
            int prec = modele.getPrec(i);
            tab[i] = (int) (this.getHeight() - this.getHeight() * (prec - precMin) / (precMax - precMin));
        }
        return tab;
    }

    public int tracerCourbe(int[] abs, int[] ord, Color couleur, Graphics g) {
        g.setColor(couleur);

        int x1, x2, y1, y2;
        if (abs.length == 0)
            return 1;
        else {
            x1 = abs[0];
            y1 = ord[0];
            for (int i = 1; i < abs.length; i++) {
                x2 = abs[i];
                y2 = ord[i];
                g.drawLine(x1, y1, x2, y2);
                x1 = x2;
                y1 = y2;
            }
        }
        return 0;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        modele.triDate();

        int[] absJours = this.absJours1900();
        int[] ordTemp = this.ordTemp();
        int[] ordPrec = this.ordPrec();

        tracerCourbe(absJours, ordTemp, Color.RED, g);
        tracerCourbe(absJours, ordPrec, Color.BLUE, g);
    }
}