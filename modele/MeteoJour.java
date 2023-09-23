package modele;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Scanner;

public class MeteoJour {
    private int temp;
    private int prec;
    private LocalDate date;

    public MeteoJour() {
        temp = 15;
        prec = 0;
        date = LocalDate.now();
    }

    public MeteoJour(int j, int m, int a, int t, int p) {
        temp = t;
        prec = p;
        this.setDate(j, m, a);
    }

    public int getTemp() {
        return temp;
    }

    public int getPrec() {
        return prec;
    }

    public long getTemps() {
        LocalDate zero = LocalDate.of(1900, Month.JANUARY, 1);
        return ChronoUnit.DAYS.between(zero, date);
    }

    public long getDate() {
        LocalDate zero = LocalDate.of(1900, Month.JANUARY, 1);
        return ChronoUnit.DAYS.between(zero, date);
    }

    public void setDate(int j, int m, int a) {
        date = LocalDate.of(a, m, j);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.toString());
        stringBuilder.append(" : (temp=");
        stringBuilder.append(temp);
        stringBuilder.append(",prec=");
        stringBuilder.append(prec);
        stringBuilder.append(")");
        return (stringBuilder.toString());
    }

    public String versFichier() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.getDayOfMonth());
        stringBuilder.append(" ");
        stringBuilder.append(date.getMonthValue());
        stringBuilder.append(" ");
        stringBuilder.append(date.getYear());
        stringBuilder.append(" ");
        stringBuilder.append(temp);
        stringBuilder.append(" ");
        stringBuilder.append(prec);
        return (stringBuilder.toString());
    }

    public static MeteoJour parse(String str) {
        MeteoJour mj = new MeteoJour();
        Scanner scan = new Scanner(str);
        scan.useDelimiter(" ");
        mj.setDate(scan.nextInt(), scan.nextInt(), scan.nextInt());
        mj.temp = scan.nextInt();
        mj.prec = scan.nextInt();
        scan.close();
        return mj;
    }

    static final Comparator<MeteoJour> ordreTemp = new Comparator<MeteoJour>() {
        public int compare(MeteoJour mj1, MeteoJour mj2) {
            return (new Integer(mj1.temp)).compareTo(new Integer(mj2.temp));
        }
    };

    static final Comparator<MeteoJour> ordrePrec = new Comparator<MeteoJour>() {
        public int compare(MeteoJour mj1, MeteoJour mj2) {
            return (new Integer(mj1.prec)).compareTo(new Integer(mj2.prec));
        }
    };

    static final Comparator<MeteoJour> ordreTemps = new Comparator<MeteoJour>() {
        public int compare(MeteoJour mj1, MeteoJour mj2) {
            return mj1.date.compareTo(mj2.date);
        }
    };

    public static void main(String[] args) {
        MeteoJour mj = new MeteoJour(2, 1, 2020, 0, 0);
        System.out.println("temps : " + mj.getDate() + " " + mj.date);
        mj = new MeteoJour(3, 1, 2020, 0, 0);
        System.out.println("temps : " + mj.getDate() + " " + mj.date);
    }


}