import java.util.Scanner;

public class DN02_63250270   {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int type = sc.nextInt();
        int d = sc.nextInt();
        int n = sc.nextInt();
        int from, to = sc.nextInt();

        long result = 0;

        /*
         * iteriramo za n - 1 iteracij, vsakič posebej preberemo naslednjo številko
         * z Scanner.nextInt(), pred tem pa prejšnjo vrednost nastavimo na variable from.
         * naslednji korak je identifikacija metoda, primerne za računanje razdalje odvisne
         * od vrste strukture tipkovnice. To dosežemo s preprostim if-statementom, v katerega
         * glavo postavimo spremenljivko type:
         *  - če je type 1, se to linija
         *  - če je type 2, je to kvadratnica
         *  - če je type 3, je to piramidica
         *  - če je type 4, je pa to še zadnja možnost, spiralica!
         * Glede na vrednost typa (ki jo preberemo kot prvi Scanner.nextInt() programa) torej
         * spremenljivki result (long, saj je int glede na vnosne intervale premajhna vrednost)
         * prištejemo vrnitveno vrednost meto, povezane z vrednostjo typa.
         * 
         * Ta postopek ponavljamo za vse iteracije, končni rezultat pa izpišemo s
         * System.out.println(), metodo, ki v terminalu v novi vrstici izpiše podano
         * vrednost.
         * 
         * Na koncu ne smemo pozabiti še najpomembnejšega ukaza; Scanner.close()
         * S tem, da objektu Scanner sporočimo, da je njegovo delo opravljeno,
         * se navadimo dobrih navad, tako temeljitosti kot vljudnosit (navsezadnje,
         * bi bili tudi vi jezni, če vas nihče nebi obvestil o zaključitvi dela (delo, kot
         * glagol dejanja, pri katerem oseba opravlja razne dejavnosti, in v zameno od 
         * delodajalca dobi denar.)) Bonton je na prvem mestu!
         */
        for (int i = 0; i < n - 1; i++) {
            from = to;
            to = sc.nextInt();
            switch (type) {
                case 1:
                    result += distanceLine(from, to);
                    break;
                case 2:
                    result += distanceSquare(from, to, d);
                    break;
                case 3:
                    result += distancePyramid(from, to);
                    break;
                case 4:
                    result += distanceSpyral(from, to, d);
                    break;
                default:
            }
        }

        System.out.println(result);
        /*
         * Počivaj zdaj, otročič moj. blesteče si opravil svoje delo.
         * Vse kar lakho zdaj zate storim, kot bitje z avtoriteto, ki te
         * je priklicalo v tvoj mali svet javanskega programa, mnogo dimenzij
         * izven sveta mojega obstoja...
         * Spi mirno, dete.
         */
        sc.close();
    }

    //simple
    public static int distanceLine(int from, int to) {
        return Math.abs(to - from);
    }

    //tud simple sam ne tok kt line
    public static int distanceSquare(int from, int to, int d) {
        return Math.abs(to / d - from / d) + Math.abs(to % d - from % d);
    }

    //blabla vrstica k je neki s koreni pa so manjši pa to blabal
    public static int distancePyramid(int from, int to) {
        int fromRow = (int)Math.sqrt(from);
        int toRow = (int)Math.sqrt(to);
        return Math.abs(toRow - fromRow) + Math.abs(to - (from + toRow*(toRow + 1) - fromRow*(fromRow + 1)));
    }

    //pretrovr v 0-(d*d-1), koordinate navadnga kvadrata
    public static int distanceSpyral(int from, int to, int d) {
        int translatedFrom = (from != 0) ? translateSpyralSquare(from, d) : d / 2 * d + d / 2;
        int translatedTo = (to != 0) ? translateSpyralSquare(to, d) : d*d / 2 + d / 2;
        return distanceSquare(translatedFrom, translatedTo, d);
    }

    //spet neki intervali s koreni sam da mau bl zoprn, pol pa gledas stranice lowkey sm ze pozabu mau sam dela
    public static int translateSpyralSquare(int n, int d) {
        int someSquareBSToGetTHerINGorsomething = (int)Math.sqrt(n) - ((int)Math.sqrt(n) + 1)%2;
        int ringNumber = someSquareBSToGetTHerINGorsomething / 2;
        int ringLength = 8 * (ringNumber + 1);
        int nIndex = n - someSquareBSToGetTHerINGorsomething*someSquareBSToGetTHerINGorsomething;
        int ringCorner = d * (d / 2 - ringNumber - 1) + d / 2 - ringNumber - 1;

        if (nIndex < ringLength / 4) {
            return ringCorner + nIndex;
        } else if (nIndex < ringLength / 2) {
            return ringCorner + (ringLength / 4) + d*(nIndex - ringLength / 4);
        } else if (nIndex < 3 * ringLength / 4) {
            return ringCorner + (ringLength / 4 - (nIndex - ringLength / 2)) + d*(ringLength / 4);
        } else {
            return ringCorner + d * (ringLength / 4 - nIndex + 3 * ringLength / 4);
        }
    }
}