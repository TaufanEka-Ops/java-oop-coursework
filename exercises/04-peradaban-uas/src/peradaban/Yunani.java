package peradaban;

/**
 * Ancient Greece, expressed as a {@link Peradaban}.
 *
 * Like {@link Islam}, the original version instantiated a separate
 * {@code Peradaban} instead of using its own inherited state; that is fixed
 * here. The missing space in the original {@code "... dari" + masa}
 * concatenation (which printed {@code "daridelapan SM"}) was also corrected
 * (see docs/evaluation.md).
 */
public class Yunani extends Peradaban {

    public void tampilkan() {
        String masa = "delapan SM sampai empat SM";

        inputdata("Jam Air", "Filsafat", "Istana Emas Nero");
        tampilkandata();

        System.out.println("Masa peradaban Yunani dari " + masa);
    }
}
