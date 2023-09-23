package vues;


import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modele.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modele.*;

public class TableMeteo extends JScrollPane implements Vue {

    private Meteo modele;
    private JTable tableau;
    private final String[] entete = {"Jour","Temp駻atures","Pr馗ipitations"};

    public TableMeteo() {
        tableau = new JTable(recupererDonnees(),entete);
        setViewportView(tableau);
    }



    public Object[][] recupererDonnees(){

        Object [][] valeurs = new Object[modele.nbJours()][];

        for(int i = 0; i < modele.nbJours(); i++) {

            LocalDate jour = modele.getDate(i);
            int t = modele.getTemp(i);
            int p = modele.getPrec(i);
            valeurs[i] = new Object[] {jour,t,p};
        }

        return valeurs;
    }



    public DefaultTableModel getContent() {
        Object [][] data = this.recupererDonnees();
        DefaultTableModel dtm = new DefaultTableModel(data, this.entete);
        return dtm;
    }

    public void afficherModele() {
        tableau.setModel(this.getContent());
    }


    public void setModele(Modele m) {
        this.modele = (Meteo) m;
        this.afficherModele();
        System.out.println(modele);
    }


    public void notifierChangement() {
        this.afficherModele();
    }

}