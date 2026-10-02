package peradaban;

/**
 * Encapsulated base class for the final-exam exercise: a "civilisation" with
 * three pieces of state and a method that prints them.
 *
 * This class was already properly encapsulated in the original submission
 * (private fields, public accessors) — the only change is the package name,
 * which was {@code Peradaban} and is now lowercase {@code peradaban}
 * (see docs/evaluation.md).
 */
public class Peradaban {

    private String teknologi;
    private String ilmu;
    private String peninggalan;

    public String getTeknologi() {
        return teknologi;
    }

    public void setTeknologi(String teknologi) {
        this.teknologi = teknologi;
    }

    public String getIlmu() {
        return ilmu;
    }

    public void setIlmu(String ilmu) {
        this.ilmu = ilmu;
    }

    public String getPeninggalan() {
        return peninggalan;
    }

    public void setPeninggalan(String peninggalan) {
        this.peninggalan = peninggalan;
    }

    public void tampilkandata() {
        System.out.println("Penemuan Teknologi pada masa ini adalah " + getTeknologi());
        System.out.println("Perkembangan Ilmu pengetahuan pada masa ini adalah " + getIlmu());
        System.out.println("Peninggalan dari masa ini adalah " + getPeninggalan());
    }

    public void inputdata(String t, String i, String p) {
        setTeknologi(t);
        setIlmu(i);
        setPeninggalan(p);
    }
}
