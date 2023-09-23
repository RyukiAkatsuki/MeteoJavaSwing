package vues;
import modele.Meteo;
import modele.Modele;

public interface Vue {
    public void notifierChangement();
    public void setModele(Meteo meteo);

}
