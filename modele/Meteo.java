package modele;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import vues.Vue;

public class Meteo {
    private ArrayList<MeteoJour> liste;
    private ArrayList<Vue> vues;

    public Meteo() {
        liste = new ArrayList<MeteoJour>();
        vues = new ArrayList<Vue>();
    }

    public Meteo(String fichier) {
        this();
        this.charger(fichier);
    }

    public void ajouter(int j, int m, int a, int t, int p) {
        liste.add(new MeteoJour(j, m, a, t, p));
    }

    public void charger(String nomFichier) {
        try {
            System.out.println(nomFichier);
            liste = new ArrayList<MeteoJour>();

            BufferedReader br = Files.newBufferedReader(Paths.get(nomFichier));
            String ligne;

            ligne = br.readLine();
            while (ligne != null) {
                liste.add(MeteoJour.parse(ligne));
                ligne = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void sauver(String nomFichier) {
        try {
            System.out.println(nomFichier);
            BufferedWriter bw=Files.newBufferedWriter(Paths.get(nomFichier),
                    StandardCharsets.UTF_8, StandardOpenOption.CREATE);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            JFrame parentFrame = new JFrame();

            int userSelection = fileChooser.showSaveDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                System.out.println("Save as file: " +
                        fileToSave.getAbsolutePath());
            }

            bw.write(this.versFichier());
            bw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public String toString() {
        String str = "";
        for (MeteoJour mj : liste) {
            str += mj.toString();
            str += "\n";
        }
        return str;
    }

    public String versFichier() {
        String str = "";
        for (MeteoJour mj : liste) {
            str += mj.versFichier();
            str += "\n";
        }
        return str;
    }

    public long minJours1900() {
        try {
            return Collections.min(liste, MeteoJour.ordreTemps).getTemps();
        } catch (java.util.NoSuchElementException e) {
            return Integer.MIN_VALUE;
        }
    }

    public long maxJours1900() {
        try {
            return Collections.max(liste, MeteoJour.ordreTemps).getTemps();
        } catch (java.util.NoSuchElementException e) {
            return Integer.MAX_VALUE;
        }
    }

    public int minTemp() {
        try {
            return Collections.min(liste, MeteoJour.ordreTemp).getTemp();
        } catch (java.util.NoSuchElementException e) {
            return Integer.MIN_VALUE;
        }
    }

    public int maxTemp() {
        try {
            return Collections.max(liste, MeteoJour.ordreTemp).getTemp();
        } catch (java.util.NoSuchElementException e) {
            return Integer.MAX_VALUE;
        }
    }

    public int minPrec() {
        try {
            return Collections.min(liste, MeteoJour.ordrePrec).getPrec();
        } catch (java.util.NoSuchElementException e) {
            return Integer.MIN_VALUE;
        }
    }

    public int maxPrec() {
        try {
            return Collections.max(liste, MeteoJour.ordrePrec).getPrec();
        } catch (java.util.NoSuchElementException e) {
            return Integer.MAX_VALUE;
        }
    }

    public long minTemps() {
        try {
            return Collections.min(liste, MeteoJour.ordreTemps).getTemps();
        } catch (java.util.NoSuchElementException e) {
            return Integer.MIN_VALUE;
        }
    }

    public long maxTemps() {
        try {
            return Collections.max(liste, MeteoJour.ordreTemps).getTemps();
        } catch (java.util.NoSuchElementException e) {
            return Integer.MAX_VALUE;
        }
    }
    public void triDate() {
        Collections.sort(liste, MeteoJour.ordreTemps);
    }

    public int nbJours() {
        return liste.size();
    }

    public int getTemp(int i) {
        return liste.get(i).getTemp();
    }

    public int getPrec(int i) {
        return liste.get(i).getPrec();
    }

    public long getTemps(int i) {
        return liste.get(i).getTemps();
    }

    public long getDate(int i) {
        return liste.get(i).getDate();
    }

    public void enregistrer(Vue v) {
        vues.add(v);
        v.setModele(this);
    }

    private void notifierToutesVues() {
        System.out.println("Nb Vues notifi馥s: "+ vues.size());
        for (Vue v : vues)
            v.notifierChangement();
    }
}