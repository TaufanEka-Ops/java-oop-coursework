package peradaban;

/**
 * Entry point for the final-exam exercise: prints two civilisations in turn.
 */
public class RunPeradaban {
    public static void main(String[] args) {

        System.out.println("Yunani");

        Yunani y = new Yunani();
        y.tampilkan();

        System.out.println("\nIslam");

        Islam m = new Islam();
        m.tampilkan();
    }
}
