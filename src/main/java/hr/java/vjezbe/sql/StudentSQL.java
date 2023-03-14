package hr.java.vjezbe.sql;

import java.sql.Date;

public class StudentSQL {
    private Integer id_studenti;
    private String ime;
    private String prezime;
    private String jmbag;
    private Date datum_rodjenja;

    public StudentSQL(Integer id_studenti, String ime, String prezime, String jmbag, Date datum_rodjenja) {
        this.id_studenti = id_studenti;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbag = jmbag;
        this.datum_rodjenja = datum_rodjenja;
    }

    public Integer getId_studenti() {
        return id_studenti;
    }

    public void setId_studenti(Integer id_studenti) {
        this.id_studenti = id_studenti;
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

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public Date getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(Date datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }
}
