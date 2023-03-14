package hr.java.vjezbe.sql;

public class PredmetSQL {
    private Integer id_predmeta;
    private String sifra;
    private String naziv;
    private Integer brojEctsBodova;
    private Integer id_profesori;
    private Integer brojStudenata;

    public PredmetSQL(Integer id_predmeta, String sifra, String naziv, Integer brojEctsBodova, Integer id_profesori, Integer brojStudenata) {
        this.id_predmeta = id_predmeta;
        this.sifra = sifra;
        this.naziv = naziv;
        this.brojEctsBodova = brojEctsBodova;
        this.id_profesori = id_profesori;
        this.brojStudenata = brojStudenata;
    }

    public Integer getId_predmeta() {
        return id_predmeta;
    }

    public void setId_predmeta(Integer id_predmeta) {
        this.id_predmeta = id_predmeta;
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

    public Integer getId_profesori() {
        return id_profesori;
    }

    public void setId_profesori(Integer id_profesori) {
        this.id_profesori = id_profesori;
    }

    public Integer getBrojStudenata() {
        return brojStudenata;
    }

    public void setBrojStudenata(Integer brojStudenata) {
        this.brojStudenata = brojStudenata;
    }
}
