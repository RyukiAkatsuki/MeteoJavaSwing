package controle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import modele.Meteo;
import vues.GraphiqueMeteo;
import modele.Meteo;
import vues.TableMeteo;
import vues.TexteMeteo;

@SuppressWarnings("serial")
public class FenMeteo extends JFrame {

    static final String MOIS[] = { "Janvier", "F騅rier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Ao皦", "Septembre",
            "Octobre", "Novembre", "D馗embre" };
    static final Integer ANNEES[] = { new Integer(2019), new Integer(2020), new Integer(2021), new Integer(2022) };

    private Meteo modele;

    private int j = 1;
    private int m = 1;

    private JButton btnSauver;
    private JButton btnCharger;

    private JTextArea txtListe;
    private JButton[] btnJours;
    private JButton[] btnMois;
    private JComboBox<Integer> cbbAnnees;
    private JButton btnAjouter;
    private JTextField txtTemp;
    private JTextField txtPrec;
    private JLabel lblDate;
    private GraphiqueMeteo jpnGraph;

    static final int ZONE_JOURS = 1;
    static final int ZONE_MOIS = 2;
    static final int ZONE_AUTRE = 3;
    static final int CODE_AJOUTER = 1;
    static final int CODE_CHARGER = 2;
    static final int CODE_SAUVER = 3;

    public FenMeteo(String str) {
        super("M騁駮 M2105");
        modele = new Meteo(str);
        this.initComposants();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.centrer(0.4);
        this.setVisible(true);
        this.initEcouteurs();
    }

    private void initComposants() {
        JPanel panPrincipal = new JPanel();

        this.add(panPrincipal);

        panPrincipal.setLayout(new BorderLayout());
        panPrincipal.add(buildPanelFichiers(),BorderLayout.SOUTH);
        panPrincipal.add(buildPanelAjout(),BorderLayout.NORTH);
        panPrincipal.add(buildPanelMeteo(),BorderLayout.CENTER);
        panPrincipal.add(buildPanelJours(),BorderLayout.WEST);
        panPrincipal.add(buildPanelMois(),BorderLayout.EAST);
    }


    public JPanel buildPanelFichiers() {
        JPanel pan = new JPanel();

        btnCharger = new JButton("Charger");
        pan.add(btnCharger);

        btnSauver = new JButton("Sauver");
        pan.add(btnSauver);

        pan.setBorder(BorderFactory.createEtchedBorder());


        return pan;
    }


    public JPanel buildPanelAjout() {
        JPanel pan = new JPanel();

        lblDate = new JLabel("_/_/");
        pan.add(lblDate);

        cbbAnnees = new JComboBox<Integer>(ANNEES);
        pan.add(cbbAnnees);

        JLabel lblTemp = new JLabel("Temp駻ature (ｰC)");
        pan.add(lblTemp);

        txtTemp = new JTextField("                   ");
        pan.add(txtTemp);

        JLabel lblPrec = new JLabel("Pr馗ipitations (mm)");
        pan.add(lblPrec);

        txtPrec = new JTextField("                   ");
        pan.add(txtPrec);

        btnAjouter = new JButton("Ajouter");
        pan.add(btnAjouter);

        pan.setBorder(BorderFactory.createEtchedBorder());

        return pan;
    }

    public JPanel buildPanelMeteo() {

        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(-1, 1));
        txtListe = new JTextArea(15,40);
        txtListe.append(modele.toString());
        Meteo meteo = new Meteo("meteo.txt");
        txtListe = new JTextArea(meteo.toString());
        pan.add(txtListe);
        JScrollPane ascListe = new JScrollPane(txtListe);

        jpnGraph = new GraphiqueMeteo(modele);
        jpnGraph.setBorder(BorderFactory.createEtchedBorder());

        JTabbedPane onglet = new JTabbedPane();
        onglet.addTab("Liste", ascListe);
        onglet.addTab("Graphique", jpnGraph);

        pan.add(onglet, BorderLayout.CENTER);
        pan.setBorder(BorderFactory.createEtchedBorder());
        pan.revalidate();

        return pan;
    }


    public JPanel buildPanelMois() {
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(-1, 1));
        btnMois = new JButton[MOIS.length];
        for(int i = 0; i < MOIS.length; i++) {
            btnMois[i] = new JButton(MOIS[i]);
            pan.add(btnMois[i]);
        }
        pan.setBorder(BorderFactory.createEtchedBorder());
        return pan;
    }

    public JPanel buildPanelJours() {
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(-1, 3));
        btnJours = new JButton[31];
        for(int i = 0; i < 31; i++) {
            btnJours[i] = new JButton(Integer.toString(i+1));
            pan.add(btnJours[i]);
        }
        pan.setBorder(BorderFactory.createEtchedBorder());
        return pan;
    }


    public void centrer(double d) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int largeur = (int) (d * dim.width);
        int longueur = (int) (d * dim.height);
        this.setBounds((dim.width - largeur) / 2, (dim.height - longueur) / 2, largeur, longueur);
    }


    class EcouteurBoutons implements ActionListener {

        private int zone;
        private int code;

        public EcouteurBoutons(int z, int c) {
            this.zone = z;
            this.code = c;
        }

        public void ajouterJ() {
            try {
                modele.ajouter(j,m,((Integer)cbbAnnees.getSelectedItem()).intValue(),
                        Integer.parseInt(txtTemp.getText().trim()),Integer.parseInt
                                (txtPrec.getText()
                                        .trim()));
                txtListe.setText(null);
                txtListe.append(modele.toString());
                jpnGraph.repaint();
            } catch (NumberFormatException e) {
                System.err.println(e);
            }
        }

        public void actionPerformed(ActionEvent e) {
            switch (zone) {
                case ZONE_JOURS: {
                    System.out.println("Bouton JOUR nｰ " + code);
                    j = code;
                    lblDate.setText("" + j + "/" + m + "/");
                    break;
                }
                case ZONE_MOIS: {
                    System.out.println("Bouton MOIS nｰ " + code);
                    m = code;
                    lblDate.setText("" + j + "/" + m + "/");
                    break;
                }
                case ZONE_AUTRE:
                    switch (code) {
                        case CODE_AJOUTER:
                            System.out.println("J'ajoute");
                            ajouterJ();
                            break;
                        case CODE_CHARGER:
                            System.out.println("Je charge");
                            FenMeteo.this.chargerMeteo();
                            break;
                        case CODE_SAUVER:
                            System.out.println("Je sauve");
                            FenMeteo.this.sauverMeteo();
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        }
    }

    public void initEcouteurs() {
        this.btnCharger.addActionListener(new EcouteurBoutons(FenMeteo.ZONE_AUTRE,
                FenMeteo.CODE_CHARGER));
        this.btnSauver.addActionListener(new EcouteurBoutons(FenMeteo.ZONE_AUTRE, FenMeteo.
                CODE_SAUVER));
        this.btnAjouter.addActionListener(new EcouteurBoutons(ZONE_AUTRE,CODE_AJOUTER));

        for (int i = 0; i < 31; i++) {
            btnJours[i].addActionListener(new EcouteurBoutons(ZONE_JOURS, i+1));
        }

        for (int i = 0; i < 12; i++) {
            btnMois[i].addActionListener(new EcouteurBoutons(ZONE_MOIS, i+1));
        }
    }



    public void chargerMeteo() {
        String fic = "meteo.txt";
        modele.charger(fic);
        txtListe.setText(null);
        txtListe.append(modele.toString());
        jpnGraph.repaint();
    }

    public void sauverMeteo() {
        String fic = "meteo.txt";
        modele.sauver(fic);
    }


    public static void main(String[] args) {
        new FenMeteo("meteo.txt");

    }

}