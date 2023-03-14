package hr.java.vjezbe.sql;

public class ProfesorSQL {

    private Integer id_profesori;
    private String sifra;
    private String ime;
    private String prezime;
    private String titula;

    public ProfesorSQL(Integer id_profesori, String sifra, String ime, String prezime, String titula ) {
        this.id_profesori = id_profesori;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.titula = titula;
    }

    public Integer getId_profesori() {
        return id_profesori;
    }

    public void setId_profesori(Integer id_profesori) {
        this.id_profesori = id_profesori;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }
}
