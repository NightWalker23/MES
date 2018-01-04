public class Main {

    public static void main ( String args[] ) {
        Calka_podwojna calka = new Calka_podwojna();

        double wynik1 = calka.oblicz_2p();
        double wynik2 = calka.oblicz_3p();
        double wynik5 = calka.oblicz_5p();

        //System.out.println( "Wynik 2p = " + wynik1 );
        //System.out.println( "Wynik 3p = " + wynik2 );
        System.out.println( "Wynik 5p = " + wynik5 );
    }
}
