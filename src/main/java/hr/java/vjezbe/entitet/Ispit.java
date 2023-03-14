package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

public non-sealed class Ispit implements Online {
    private Predmet predmet;
    private Student student;
    private int ocjena;
    private LocalDateTime datumIVrijeme;
    private Dvorana dvorana;
    private String softwareName;

    public Ispit(Predmet predmet, Student student, int ocjena, LocalDateTime datumIVrijeme, Dvorana dvorana) {
        this.predmet = predmet;
        this.student = student;
        this.ocjena = ocjena;
        this.datumIVrijeme = datumIVrijeme;
        this.dvorana = dvorana;

    }

    public Predmet getPredmet() {
        return this.predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getOcjena() {
        return this.ocjena;
    }

    public void setOcjena(int ocjena) {
        this.ocjena = ocjena;
    }

    public LocalDateTime getDatumIVrijeme() {
        return this.datumIVrijeme;
    }

    public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
        this.datumIVrijeme = datumIVrijeme;
    }

    public Dvorana getDvorana() {return dvorana;}

    public void setDvorana(Dvorana dvorana) {this.dvorana = dvorana;}

    @Override
    public void setSoftware(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getSoftwareName() {
        return softwareName;
    }


}