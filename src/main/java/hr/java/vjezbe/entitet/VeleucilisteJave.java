package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {

    public VeleucilisteJave(String nazivUstanove, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
        }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaPismenogDijela, int ocjenaObrane) throws NemoguceOdreditiProsjekStudentaException {

        BigDecimal prosjekOcjena = Visokoskolska.super.odrediProsjekOcjenaNaIspitima(ispiti);

        // ako iz bilo kojeg ispita ima 1 ili ocjena pismenog je 1 ili ocjena obrane  je 1 nije položio
        


        return prosjekOcjena.multiply(new BigDecimal("2")).add(new BigDecimal(ocjenaPismenogDijela)).add(new BigDecimal(ocjenaObrane)).divide(new BigDecimal("4"), 0,
                RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti) throws NemoguceOdreditiProsjekStudentaException {
        return Visokoskolska.super.odrediProsjekOcjenaNaIspitima(ispiti);
    }

    @Override
    public Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti) {
        return Visokoskolska.super.filtrirajPolozeneIspite(ispiti);
    }

    @Override
    public Ispit[] filtrirajIspitePoStudentu (Ispit[] ispiti, Student student) {
        return Visokoskolska.super.filtrirajIspitePoStudentu(ispiti, student);
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(int godina) throws NemoguceOdreditiProsjekStudentaException {
        Ispit[] ispiti = getIspiti();
        Student[] studenti = getStudenti();
        Student najuspjesnijiStudent = null;
        BigDecimal najveciProsjek = BigDecimal.ZERO;

        for (Student student : studenti) {
            Ispit[] ispitiPoStudentu = filtrirajIspitePoStudentu(ispiti, student);
            if (ispitiPoStudentu.length != 0) {
                BigDecimal tempProsjek = odrediProsjekOcjenaNaIspitima(ispitiPoStudentu);
                if (tempProsjek.compareTo(najveciProsjek) >= 0) {
                    najveciProsjek = tempProsjek;
                    najuspjesnijiStudent = student;
                }
            }
        }
        return najuspjesnijiStudent;
    }
}
