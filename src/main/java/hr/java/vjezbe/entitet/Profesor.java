package hr.java.vjezbe.entitet;

public class Profesor extends Osoba {
    private String sifra;
    private String titula;

    private Profesor(Builder builder) {
        super(builder.ime, builder.prezime);
        this.sifra = builder.sifra;
        this.titula = builder.titula;
    }

    public String getSifra() {
        return this.sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getTitula() {
        return this.titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    public static class Builder {
        private String sifra;
        private String ime;
        private String prezime;
        private String titula;

        public Builder sifra(String sifra) {
            this.sifra = sifra;
            return this;
        }

        public Builder ime(String ime) {
            this.ime = ime;
            return this;
        }

        public Builder prezime(String prezime) {
            this.prezime = prezime;
            return this;
        }

        public Builder titula(String titula) {
            this.titula = titula;
            return this;
        }

        public Profesor build() {
            return new Profesor(this);
        }
    }
}
