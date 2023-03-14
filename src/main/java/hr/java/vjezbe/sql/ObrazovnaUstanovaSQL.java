package hr.java.vjezbe.sql;

public class ObrazovnaUstanovaSQL {
    private Integer id_obrazovna_ustanova;
    private String naziv_obrazovne_ustanove;
    private String tip_obrazovne_ustanove;

    public ObrazovnaUstanovaSQL(Integer id_obrazovna_ustanova, String naziv_obrazovne_ustanove, String tip_obrazovne_ustanove) {
        this.id_obrazovna_ustanova = id_obrazovna_ustanova;
        this.naziv_obrazovne_ustanove = naziv_obrazovne_ustanove;
        this.tip_obrazovne_ustanove = tip_obrazovne_ustanove;
    }
    public Integer getId_obrazovna_ustanova() {
        return id_obrazovna_ustanova;
    }

    public void setId_obrazovna_ustanova(Integer id_obrazovna_ustanova) {
        this.id_obrazovna_ustanova = id_obrazovna_ustanova;
    }

    public String getNaziv_obrazovne_ustanove() {
        return naziv_obrazovne_ustanove;
    }

    public void setNaziv_obrazovne_ustanove(String naziv_obrazovne_ustanove) {
        this.naziv_obrazovne_ustanove = naziv_obrazovne_ustanove;
    }

    public String getTip_obrazovne_ustanove() {
        return tip_obrazovne_ustanove;
    }

    public void setTip_obrazovne_ustanove(String tip_obrazovne_ustanove) {
        this.tip_obrazovne_ustanove = tip_obrazovne_ustanove;
    }
}
