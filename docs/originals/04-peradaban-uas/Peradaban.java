package Peradaban;

public class Peradaban {

    private String teknologi;
    private String ilmu;
    private String peninggalan;

    public String getTeknologi(){
        return teknologi;
    }

    public void setTeknologi(String teknologi){
        this.teknologi = teknologi;
    }

    public String getIlmu(){
        return ilmu;
    }

    public void setIlmu(String ilmu){
        this.ilmu = ilmu;
    }

    public String getPeninggalan(){
        return peninggalan;
    }

    public void setPeninggalan(String peninggalan){
        this.peninggalan = peninggalan;
    }

    public void tampilkandata() {
        System.out.println("Penemuan Teknologi pada masa ini adalah "+ getTeknologi());
        System.out.println("Perkembangan Ilmu pengetahuan pada masa ini adalah "+ getIlmu());
        System.out.println("Peninggalan dari masa ini adalah "+ getPeninggalan());
    }

    public void inputdata(String t, String i, String p){
        setTeknologi(t);
        setIlmu(i);
        setPeninggalan(p);
    }
}
