package hr.java.vjezbe.glavna;
//import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.entitet.VeleucilisteJave;
import hr.java.vjezbe.entitet.FakultetRacunarstva;
import hr.java.vjezbe.entitet.Dvorana;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Map;

public class GlavnaTest {

    public static final Integer BROJ_PROFESORA = 2;
    public static final Integer BROJ_PREDMETA = 2;
    public static final Integer BROJ_ISPITA = 2;
    static Integer BROJ_STUDENATA = 2;
    public static Map<Integer, String> ocjene = Map.of(1, "nedovoljan", 2, "dovoljan", 3, "dobar", 4, "vrlo dobar", 5, "odličan");

    public static void main(String[] args) throws NemoguceOdreditiProsjekStudentaException {


        Scanner scanner = new Scanner(System.in);
        Profesor[] profesori = new Profesor[BROJ_PROFESORA];
        Predmet[] predmeti = new Predmet[BROJ_PREDMETA];
        Student[] studenti = new Student[BROJ_STUDENATA];
        Ispit[] ispiti = new Ispit[BROJ_ISPITA];

////        ORIGINALNI UNOS PROFESORA PREDEMTA I STUDENATA
////        Unos profesora:
//        for (int i = 0; i < BROJ_PROFESORA; i++) {
//            Profesor profesor = unosProfesora(scanner, i); // originalna linija
//            profesori[i] = profesor;
//        }
////        Unos predmeta:
//        for (int i = 0; i < BROJ_PREDMETA; i++) {
//            Predmet predmet = unosPredmeta(profesori, scanner, i);
//            predmeti[i] = predmet;
//        }
//
////        Unos studenata:
//        for (int i = 0; i < BROJ_STUDENATA; i++) {
//            Student student = unosStudenata(scanner, i);
//            studenti[i] = student;
//        }
////        Unos ispitnih rokova:
//        for (int i = 0; i < BROJ_ISPITA; i++) {
//            Ispit ispit = unosIspitnogRoka(predmeti, studenti, scanner);
//            ispiti[i] = ispit;
//        }

        System.out.print("Unesite broj ustanova: ");
        int brojUstanova = 2;

        for (int j = 0; j < brojUstanova; j++) {
            if (j == 0) {
                System.out.println("Unesite podatke za " + (j + 1) + ". obrazovnu ustanovu: ");

                // unos profesora za test:
                Scanner scannerProfesori = new Scanner("P1234\nPetar\nKovačević\nviši predavač\n");
                Profesor profesor = unosProfesora(scannerProfesori, 0);
                profesori[0] = profesor;
                scannerProfesori = new Scanner("P4454\nIvan\nBabić\nprofesor visoke škole\n");
                profesor = unosProfesora(scannerProfesori, 1);
                profesori[1] = profesor;

                // unos predmeta za test:
                Scanner scannerPredmet = new Scanner("PR443\nProgramiranje u jeziku java\n6\n1\n2\n");
                Predmet predmet = unosPredmeta(profesori, scannerPredmet, 0);
                predmeti[0] = predmet;
                scannerPredmet = new Scanner("PR667\nWeb aplikacije u javi\n7\n2\n2\n");
                predmet = unosPredmeta(profesori, scannerPredmet, 1);
                predmeti[1] = predmet;


                // unos studenata za test:
                Scanner scannerStudent = new Scanner("Marko\nCurić\n12.12.1999.\n024680654\n");
                Student student = unosStudenata(scannerStudent, 0);
                studenti[0] = student;
                scannerStudent = new Scanner("Ivana\nBelić\n11.11.1998.\n0036389977\n");
                student = unosStudenata(scannerStudent, 1);
                studenti[1] = student;

                // unos ispitnih rokova za test:
                //  parametri:
                //  1. predmet
                //  2. student
                //  3. oscjena

                Scanner scannerIspiti = new Scanner("1\nVelika dvorana\nVelika zgrada\n1\n4\n11.10.2022. 18:00\n");
                Ispit ispit = unosIspitnogRoka(predmeti, studenti, scannerIspiti, j);
                ispiti[0] = ispit;
                scannerIspiti = new Scanner("2\nVelika dvorana\nVelika zgrada\n2\n5\n12.10.2022. 17:30\n");
                ispit = unosIspitnogRoka(predmeti, studenti, scannerIspiti, j);
                ispiti[1] = ispit;

                System.out.println("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 - Veleučilište Jave, 2 - Fakultet računarstva):");
                int odabir = 2;
                System.out.print("Unesite naziv obrazovne ustanove: ");
                String nazivUstanove = "fer";

                VeleucilisteJave veleucilisteJave = new VeleucilisteJave(nazivUstanove, predmeti, profesori, studenti, ispiti);
                FakultetRacunarstva fakultetRacunarstva = new FakultetRacunarstva(nazivUstanove, predmeti, profesori, studenti, ispiti);

                switch (odabir) {
                    case 1:
                        //veleuciliste java
                        VeleucilisteJave java = new VeleucilisteJave(nazivUstanove, predmeti, profesori, studenti, ispiti);
                        break;
                    case 2:
                        //fakultet racunarstva
                        FakultetRacunarstva fer = new FakultetRacunarstva(nazivUstanove, predmeti, profesori, studenti, ispiti);
                        break;
                    default:
                        break;
                }
                for (Student studentTemp : studenti) {
                    System.out.print("Unesite ocjenu završnog rada za studenta " + studentTemp.getPrezime() + " " + studentTemp.getIme() + ": ");
                    intException(scanner);
                    Scanner ocjenaPismenogDijela = new Scanner("4");
                    System.out.print("Unesite ocjenu obrane završnog rada za studenta " + studentTemp.getPrezime() + " " + studentTemp.getIme() + ": ");
                    intException(scanner);
                    int ocjenaObrane = scanner.nextInt();
                    scanner.nextLine();

//                    if (odabir == 1) {
//                        System.out.println("Konačna ocjena studija studenta " + studentTemp.getPrezime() + " " + studentTemp.getIme() + " je: " + veleucilisteJave.izracunajKonacnuOcjenuStudijaZaStudenta(ispiti, ocjenaPismenogDijela, ocjenaObrane));
//                    } else if (odabir == 2) {
//                        System.out.println("Konačna ocjena studija studenta " + studentTemp.getPrezime() + " " + studentTemp.getIme() + " je: " + fakultetRacunarstva.izracunajKonacnuOcjenuStudijaZaStudenta(ispiti, ocjenaPismenogDijela, ocjenaObrane));
//                    }
                }
                switch (odabir) {
                    case 1 -> {
                        Student najuspjesnijiStudent = veleucilisteJave.odrediNajuspjesnijegStudentaNaGodini(2022);
                        System.out.println("Najbolji student 2022. godine je: " + najuspjesnijiStudent.getPrezime() + " " +  najuspjesnijiStudent.getIme() + " JMBAG: " + najuspjesnijiStudent.getJmbag());
                    }
                    case 2 -> {
                        Student najuspjesnijiStudent = fakultetRacunarstva.odrediNajuspjesnijegStudentaNaGodini(2022);
                        System.out.println("Najbolji student 2022. godine je: " + najuspjesnijiStudent.getPrezime() + " " +  najuspjesnijiStudent.getIme() + " JMBAG: " + najuspjesnijiStudent.getJmbag());
                        Student rektor = fakultetRacunarstva.odrediStudentaZaRektorovuNagradu();
                        System.out.println("Student koji je osvojio rektorovu nagradu je: " + rektor.getPrezime() + " " +  rektor.getIme() + " JMBAG: " + rektor.getJmbag());
                    }
                    default -> {
                    }
                }


            } else if (j == 1) {
                System.out.println("Unesite podatke za " + (j + 1) + ". obrazovnu ustanovu: ");

                // unos profesora za test:
                Scanner scannerProfesori = new Scanner("P1234\nPetar\nKovačević\nviši predavač\n");
                Profesor profesor = unosProfesora(scannerProfesori, 0);
                profesori[0] = profesor;
                scannerProfesori = new Scanner("P4454\nIvan\nBabić\nprofesor visoke škole\n");
                profesor = unosProfesora(scannerProfesori, 1);
                profesori[1] = profesor;

                // unos predmeta za test:
                Scanner scannerPredmet = new Scanner("PR443\nProgramiranje u jeziku java\n6\n1\n2\n");
                Predmet predmet = unosPredmeta(profesori, scannerPredmet, 0);
                predmeti[0] = predmet;
                scannerPredmet = new Scanner("PR667\nWeb aplikacije u javi\n7\n2\n2\n");
                predmet = unosPredmeta(profesori, scannerPredmet, 1);
                predmeti[1] = predmet;


                // unos studenata za test:
                Scanner scannerStudent = new Scanner("Marko\nCurić\n12.12.1999.\n024680654\n");
                Student student = unosStudenata(scannerStudent, 0);
                studenti[0] = student;
                scannerStudent = new Scanner("Ivana\nBelić\n11.11.1998.\n0036389977\n");
                student = unosStudenata(scannerStudent, 1);
                studenti[1] = student;

                // unos ispitnih rokova za test:
                //  parametri:
                //  1. predmet
                //  dvorana
                //  2. student
                //  3. oscjena

                Scanner scannerIspiti = new Scanner("1\nVelika dvorana\nVelika zgrada\n1\n4\n11.10.2022. 18:00\n");
                Ispit ispit = unosIspitnogRoka(predmeti, studenti, scannerIspiti, j);
                ispiti[0] = ispit;
                scannerIspiti = new Scanner("2\nVelika dvorana\nVelika zgrada\n2\n5\n12.10.2022. 17:30\n");
                ispit = unosIspitnogRoka(predmeti, studenti, scannerIspiti, j);
                ispiti[1] = ispit;

                System.out.println("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 - Veleučilište Jave, 2 - Fakultet računarstva):");
                int odabir = 2;
                System.out.print("Unesite naziv obrazovne ustanove: ");
                String nazivUstanove = "ovo je java";

                VeleucilisteJave veleucilisteJave = new VeleucilisteJave(nazivUstanove, predmeti, profesori, studenti, ispiti);
                FakultetRacunarstva fakultetRacunarstva = new FakultetRacunarstva(nazivUstanove, predmeti, profesori, studenti, ispiti);

                switch (odabir) {
                    case 1:
                        //veleuciliste java
                        VeleucilisteJave java = new VeleucilisteJave(nazivUstanove, predmeti, profesori, studenti, ispiti);
                        break;
                    case 2:
                        //fakultet racunarstva
                        FakultetRacunarstva fer = new FakultetRacunarstva(nazivUstanove, predmeti, profesori, studenti, ispiti);
                        break;
                    default:
                        break;
                }
                for (Student studentTemp : studenti) {
                    System.out.print("Unesite ocjenu završnog rada za studenta " + studentTemp.getPrezime() + " " + studentTemp.getIme() + ": ");
                    intException(scanner);
                    int ocjenaPismenogDijela = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Unesite ocjenu obrane završnog rada za studenta " + studentTemp.getPrezime() + " " + studentTemp.getIme() + ": ");
                    intException(scanner);
                    int ocjenaObrane = scanner.nextInt();
                    scanner.nextLine();

                    if (odabir == 1) {
                        System.out.println("Konačna ocjena studija studenta " + studentTemp.getPrezime() + " " + studentTemp.getIme() + " je: " + veleucilisteJave.izracunajKonacnuOcjenuStudijaZaStudenta(ispiti, ocjenaPismenogDijela, ocjenaObrane));
                    } else if (odabir == 2) {
                        System.out.println("Konačna ocjena studija studenta " + studentTemp.getPrezime() + " " + studentTemp.getIme() + " je: " + fakultetRacunarstva.izracunajKonacnuOcjenuStudijaZaStudenta(ispiti, ocjenaPismenogDijela, ocjenaObrane));
                    }
                }
                switch (odabir) {
                    case 1 -> {
                        Student najuspjesnijiStudent = veleucilisteJave.odrediNajuspjesnijegStudentaNaGodini(2022);
                        System.out.println("Najbolji student 2022. godine je: " + najuspjesnijiStudent.getPrezime() + " " +  najuspjesnijiStudent.getIme() + " JMBAG: " + najuspjesnijiStudent.getJmbag());
                    }
                    case 2 -> {
                        Student najuspjesnijiStudent = fakultetRacunarstva.odrediNajuspjesnijegStudentaNaGodini(2022);
                        System.out.println("Najbolji student 2022. godine je: " + najuspjesnijiStudent.getPrezime() + " " +  najuspjesnijiStudent.getIme() + " JMBAG: " + najuspjesnijiStudent.getJmbag());
                        Student rektor = fakultetRacunarstva.odrediStudentaZaRektorovuNagradu();
                        System.out.println("Student koji je osvojio rektorovu nagradu je: " + rektor.getPrezime() + " " +  rektor.getIme() + " JMBAG: " + rektor.getJmbag());
                    }
                    default -> {
                    }
                }
            }
        }

//        System.out.println();
//        ispisiProfesore(profesori);
//        System.out.println();
//        ispisiStudente(studenti);
//        System.out.println();
//        ispisNajstudenta(predmeti, profesori, studenti, ispiti);
//        System.out.println();
//        ispisiKonacnuTempOcjenu(predmeti, profesori, studenti, ispiti);
//        System.out.println();
//        ispisiNajvisePetica(predmeti, profesori, studenti, ispiti);
        System.out.println();
        ispisiStudentaZaRektorovuNagradu(predmeti, profesori, studenti, ispiti);

    }

    //    Metoda za unos profesora:
    private static Profesor unosProfesora (Scanner scanner, int i) {
        System.out.println("Unesite " + (i + 1) + ". profesora:");
        System.out.print("Unesite sifru profesora: ");
        String sifra = scanner.nextLine();
        System.out.print("Unesite ime profesora: ");
        String ime = scanner.nextLine();
        System.out.print("Unesite prezime profesora: ");
        String prezime = scanner.nextLine();
        System.out.print("Unesite titulu profesora: ");
        String titula = scanner.nextLine();
//        Profesor profesor = new Profesor(sifra, ime, prezime, titula);
        Profesor profesor = new Profesor.Builder()
                .ime(ime)
                .prezime(prezime)
                .sifra(sifra)
                .titula(titula)
                .build();
        return profesor;
    }

    //    Metoda za unos predmeta:
    private static Predmet unosPredmeta (Profesor[] profesori, Scanner scanner, int i) {
        System.out.println("Unesite " + (i + 1) + ". predmet:");
        System.out.print("Unesite šifru predmeta: ");
        String sifra = scanner.nextLine();
        System.out.print("Unesite naziv predmeta: ");
        String nazivPredmeta = scanner.nextLine();
        System.out.print("Unesite broj ECTS bodova za predmet '" + nazivPredmeta + "': ");
        intException(scanner);
        int ects = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Odaberite profesora:");
        for (int j = 0; j < profesori.length; j++) {
            System.out.println((j +1) + ". " + profesori[j].getIme() + " " + profesori[j].getPrezime());
        }
        System.out.print("Odabir >> ");
        intException(scanner);
        int odabir = scanner.nextInt();
        while (odabir < profesori.length || odabir > profesori.length) {
            System.out.println("Unesite važeću brojku!");
            System.out.print("Odabir >> ");
            odabir = scanner.nextInt();
            scanner.nextLine();
        }
//        scanner.nextLine();
        System.out.print("Unesite broj studenata za predmet '" + nazivPredmeta + "': ");
        intException(scanner);
        int tempBrojStudenata = scanner.nextInt();
        scanner.nextLine();
        Student[] tempStudenti = new Student[tempBrojStudenata];
        return new Predmet(sifra, nazivPredmeta, ects, profesori[odabir -1], tempStudenti);
    }

    //    Metoda za unos studenta:
    private static Student unosStudenata (Scanner scanner, int i) {
        System.out.println("Unesite " + (i + 1) + ". studenta:");
        System.out.print("Unesite ime studenta: ");
        String ime = scanner.nextLine();
        System.out.print("Unesite prezime studenta: ");
        String prezime = scanner.nextLine();
        System.out.print("Unesite datum rođenja studenta " + ime + " " + prezime + " u formatu (dd.MM.yyyy.): ");
        String unosDatuma = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate datumRodjenja = LocalDate.parse(unosDatuma, formatter);
        System.out.print("Unesite JMBAG studenta " + prezime + " " + ime + ": ");
        String jmbag = scanner.nextLine();
        return new Student(ime, prezime, jmbag, datumRodjenja);
    }

    //    Metoda za unos ispitnog roka:
    private static Ispit unosIspitnogRoka (Predmet[] predmeti, Student[] studenti, Scanner scanner, int j) {
        System.out.println("Unesite " + (j + 1)+". ispitni rok:");
        System.out.println("Odaberite predmet:");
        for (int l = 0; l < BROJ_PREDMETA; l++) {
            System.out.println((l + 1) + ". " + predmeti[l].getNaziv());
        }
        System.out.print("Odabir >> ");
        intException(scanner);
        int odabirPredmeta = scanner.nextInt();
        while (odabirPredmeta < predmeti.length || odabirPredmeta > predmeti.length) {
            System.out.println("Unesite važeću brojku!");
            System.out.print("Odabir >> ");
            odabirPredmeta = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Unesite naziv dvorane: ");
        String nazivDvorane = scanner.nextLine();
        System.out.print("Unesite zgradu dvorane: ");
        String zgradaDvorane = scanner.nextLine();
        Dvorana dvorana = new Dvorana(nazivDvorane,zgradaDvorane);

        System.out.println("Odaberite studenta:");
        for (int k = 0; k < studenti.length; k++) {
            System.out.println((k + 1) + ". " + studenti[k].getIme() + " " + studenti[k].getPrezime());
        }
        System.out.print("Odabir >> ");
        intException(scanner);
        int odabirStudenta = scanner.nextInt();
        while (odabirStudenta < studenti.length || odabirStudenta > studenti.length) {
            System.out.println("Unesite važeću brojku!");
            System.out.print("Odabir >> ");
            odabirStudenta = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.print("Unesite ocjenu na ispitu (1-5): ");
        intException(scanner);
        int broj = scanner.nextInt();
        while (broj < 1 || broj > 5) {
            System.out.println("Unesite važeću brojku!");
            System.out.print("Unesite ocjenu na ispitu (1-5): ");
            broj = scanner.nextInt();
            scanner.nextLine();
        }
        scanner.nextLine();
        System.out.print("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy. HH:mm): ");
        String unosDatuma = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
        LocalDateTime datumIspita = LocalDateTime.parse(unosDatuma, formatter);
        return new Ispit(predmeti[odabirPredmeta - 1], studenti[odabirStudenta - 1], broj, datumIspita, dvorana);
    }

    //      Metoda za exception error pri unošenju integera:
    private static void intException (Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("To nije cijeli broj!");
            System.out.print("Unesite cijeli broj: ");
            scanner.next();
        }
    }

    //    Metoda za ispis odlikaša:
    private static void ispisOdlikasa (Ispit[] ispiti) {
        for (Ispit ispit : ispiti) {
            if (ispit.getOcjena() == 5)
                System.out.println("Student " + ispit.getStudent().getIme() + " " + ispit.getStudent().getPrezime() +
                        " je ostvario ocjenu '" + ocjene.get(ispit.getOcjena()) + "' na predmetu '" + ispit.getPredmet().getNaziv() + "'");
        }
    }

    private static void ispisiProfesore (Profesor[] profesori) {
        System.out.println("Profesori:");
        for (Profesor profesor : profesori) {
            //System.out.println();
            System.out.println(profesor.getSifra() + " " + profesor.getIme() + " " + profesor.getPrezime() + " " + profesor.getTitula());
        }
    }

    private static void ispisiStudente (Student[] studenti) {
        System.out.println("Studenti:");
        for (Student student : studenti) {
            //System.out.println();
            System.out.println(student.getIme() + " " + student.getPrezime());
        }
    }

    private static void ispisNajstudenta(Predmet[] predmeti, Profesor[] profesori, Student[] studenti,Ispit[] ispiti) throws NemoguceOdreditiProsjekStudentaException {
        VeleucilisteJave test_naj_studenta = new  VeleucilisteJave("testiranje",  predmeti, profesori, studenti, ispiti) ;
        Student najStudent = test_naj_studenta.odrediNajuspjesnijegStudentaNaGodini(2023);
        System.out.println("Najbolji student na 2023. godini je:");
        System.out.println(najStudent.getIme() +" " + najStudent.getPrezime());
    }

    private static void ispisiKonacnuTempOcjenu(Predmet[] predmeti, Profesor[] profesori, Student[] studenti,Ispit[] ispiti){
        FakultetRacunarstva test = new FakultetRacunarstva("testiranje",  predmeti, profesori, studenti, ispiti) ;
        BigDecimal konacna_ocjena = test.izracunajKonacnuOcjenuStudijaZaStudenta(ispiti,4,5);
        System.out.println("Konačna ocjena na studiju:");
        System.out.println(konacna_ocjena.toString());
    }

    private static void ispisiNajvisePetica(Predmet[] predmeti, Profesor[] profesori, Student[] studenti,Ispit[] ispiti){
        FakultetRacunarstva test = new FakultetRacunarstva("testiranje",  predmeti, profesori, studenti, ispiti) ;
        Student najStudent = test.odrediNajuspjesnijegStudentaNaGodini(2023);
        System.out.println("Najuspješniji student:");
        System.out.println(najStudent.getIme() +" " + najStudent.getPrezime());
    }
    private static void ispisiStudentaZaRektorovuNagradu(Predmet[] predmeti, Profesor[] profesori, Student[] studenti,Ispit[] ispiti) throws NemoguceOdreditiProsjekStudentaException {
        FakultetRacunarstva test = new FakultetRacunarstva("testiranje",  predmeti, profesori, studenti, ispiti) ;
        Student najStudent = test.odrediStudentaZaRektorovuNagradu();
        System.out.println("Student nagradjen rektorovom nagradom:");
        System.out.println(najStudent.getIme() +" " + najStudent.getPrezime());
    }
}


