package hr.java.vjezbe.entitet;

import java.time.LocalDate;
import java.util.Date;

public class Student extends Osoba  {
    private String jmbag;
    private LocalDate datumRodjenja;

    public Student(String ime, String prezime, String jmbag, LocalDate datumRodjenja) {
        super(ime, prezime);
        this.jmbag = jmbag;
        this.datumRodjenja = datumRodjenja;
    }

    public String getJmbag() {
        return this.jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDatumRodjenja() {
        return this.datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
