package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski  {
    public FakultetRacunarstva(String nazivUstanove, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaPismenogDijela, int ocjenaObrane) {
        int ukupnaOcjena = 0;
        int brojPolozenihIspita = 0;

        for (int i = 0; i < ispiti.length; i++) {
            if (ispiti[i].getOcjena() > 1) {
                ukupnaOcjena += ispiti[i].getOcjena();
                brojPolozenihIspita += 1;
            }
        }
        BigDecimal prosjekOcjena = new BigDecimal(ukupnaOcjena).divide(new BigDecimal(brojPolozenihIspita), 2, RoundingMode.HALF_UP);

        BigDecimal konacnaOcjena = prosjekOcjena.multiply(new BigDecimal("3")).add(new BigDecimal(ocjenaPismenogDijela)).add(new BigDecimal(ocjenaObrane)).divide(new BigDecimal("5"), 0,
                RoundingMode.HALF_UP);
        return konacnaOcjena;
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
        Ispit[] ispiti = getIspiti();
        Student[] studenti = getStudenti();
        Student najuspjesnijiStudent = null;

        int peticeTemp = 0;
        for (Student student : studenti) {
            int petice = 0;
            Ispit[] ispitiPoStudentu = filtrirajIspitePoStudentu(ispiti, student);
            for (Ispit ispit : ispitiPoStudentu) {
                if (ispit.getOcjena() == 5) {
                    petice += 1;
                }
            }
            if (petice > peticeTemp) {
                peticeTemp = petice;
                najuspjesnijiStudent = student;
            }
        }
        return najuspjesnijiStudent;
    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() throws NemoguceOdreditiProsjekStudentaException {
        Student[] studenti = getStudenti();
        Ispit[] ispiti = getIspiti();
        Student najuspjesnijiStudent = null;
        BigDecimal najveciProsjek = BigDecimal.ZERO;
        LocalDate tempDate = LocalDate.now();

        for (Student student : studenti) {
            Ispit[] ispitiPoStudentu = filtrirajIspitePoStudentu(ispiti, student);
            if (ispitiPoStudentu.length != 0) {
                BigDecimal tempProsjek = odrediProsjekOcjenaNaIspitima(ispitiPoStudentu);
                if (tempProsjek.compareTo(najveciProsjek) >= 0 && student.getDatumRodjenja().isBefore(tempDate)) {
                    tempDate = student.getDatumRodjenja();
                    najveciProsjek = tempProsjek;
                    najuspjesnijiStudent = student;
                }
            }
        }
        return najuspjesnijiStudent;
    }
}
