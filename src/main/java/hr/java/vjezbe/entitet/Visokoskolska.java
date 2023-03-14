package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public interface Visokoskolska {
    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, int ocjenaPismenogDijela, int ocjenaObrane) throws NemoguceOdreditiProsjekStudentaException;

//    default BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti) {
//        int ukupnaOcjena = 0;
//        int brojPolozenihIspita = 0;
//
//        for (int i = 0; i < ispiti.length; i++) {
//            if (ispiti[i].getOcjena() > 1) {
//                ukupnaOcjena += ispiti[i].getOcjena();
//                brojPolozenihIspita += 1;
//            }
//        }
//        BigDecimal prosjekOcjena = new BigDecimal(ukupnaOcjena).divide(new BigDecimal(brojPolozenihIspita), 2, RoundingMode.HALF_UP);
//        return prosjekOcjena;
//    }

    default BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti)  throws NemoguceOdreditiProsjekStudentaException  {
        int ukupnaOcjena = 0;
        int brojPolozenihIspita = 0;

        for (int i = 0; i < ispiti.length; i++) {
            if (ispiti[i].getOcjena() > 1) {
                ukupnaOcjena += ispiti[i].getOcjena();
                brojPolozenihIspita += 1;
            }
        }
        BigDecimal prosjekOcjena = BigDecimal.ZERO;
        try {
            prosjekOcjena = new BigDecimal(ukupnaOcjena).divide(new BigDecimal(brojPolozenihIspita), 2, RoundingMode.HALF_UP);
            return prosjekOcjena;
        } catch (ArithmeticException e) {
            throw new NemoguceOdreditiProsjekStudentaException("Nemoguće odrediti prosjek ocjena na ispitu.");
        } finally {
            return prosjekOcjena;

        }

    }


    default Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti) {

        List<Ispit> polozeniIspitiLista = new ArrayList<>();
        for (int i = 0; i < ispiti.length; i++) {
            if (ispiti[i].getOcjena() > 1)
                polozeniIspitiLista.add(ispiti[i]);
        }
        Ispit[] polozeniIspiti = new Ispit[polozeniIspitiLista.size()];
        for (int i = 0; i < polozeniIspitiLista.size(); i++) {
            polozeniIspiti[i] = polozeniIspitiLista.get(i);
        }
        return polozeniIspiti;
    }

    default Ispit[] filtrirajIspitePoStudentu (Ispit[] ispiti, Student student) {
        List<Ispit> ispitiPoStudentimaLista = new ArrayList<>();
        for (int i = 0; i < ispiti.length; i++) {
            if (ispiti[i].getStudent() == student)
                ispitiPoStudentimaLista.add(ispiti[i]);
        }
        Ispit[] ispitiPoStudentima = new Ispit[ispitiPoStudentimaLista.size()];
        for (int i = 0; i < ispitiPoStudentimaLista.size(); i++) {
            ispitiPoStudentima[i] = ispitiPoStudentimaLista.get(i);
        }
        return ispitiPoStudentima;
    }
}
