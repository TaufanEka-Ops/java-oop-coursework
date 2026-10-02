package peradaban;

/**
 * The Islamic Golden Age, expressed as a {@link Peradaban}.
 *
 * The original {@code tampilkan()} created a brand-new {@code Peradaban}
 * object, populated that, and printed it — the inherited state of the
 * {@code Islam} instance itself was never used, which defeats the point of
 * inheritance. It now populates and prints its own inherited state
 * (see docs/evaluation.md).
 */
public class Islam extends Peradaban {

    public void tampilkan() {
        String masa = "750 M sampai 1258 M";

        inputdata("Konsep Robot", "Matematika", "Masjid");
        tampilkandata();

        System.out.println("Masa Keemasan peradaban Islam dari " + masa);
    }
}
