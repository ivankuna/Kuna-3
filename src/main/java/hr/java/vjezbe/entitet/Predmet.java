package hr.java.vjezbe.entitet;

public class Predmet {
    private String sifra;
    private String naziv;
    private Integer brojEctsBodova;
    private Profesor nositelj;
    private Student[] studenti;

    public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj, Student[] studenti) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.brojEctsBodova = brojEctsBodova;
        this.nositelj = nositelj;
        this.studenti = studenti;
    }

    public String getSifra() {
        return this.sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getBrojEctsBodova() {
        return this.brojEctsBodova;
    }

    public void setBrojEctsBodova(Integer brojEctsBodova) {
        this.brojEctsBodova = brojEctsBodova;
    }

    public Profesor getNositelj() {
        return this.nositelj;
    }

    public void setNositelj(Profesor nositelj) {
        this.nositelj = nositelj;
    }

    public Student[] getStudenti() {
        return this.studenti;
    }

    public void setStudenti(Student[] studenti) {
        this.studenti = studenti;
    }
}