import modele.Meteo;

public class TestModeleMeteo {
    public static void main (String[] args) {
        Meteo met = new Meteo("./meteo.txt");
        System.out.println(met.versFichier());
        System.out.println(met.toString());
    }

}