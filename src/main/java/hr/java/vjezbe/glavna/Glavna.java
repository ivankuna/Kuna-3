package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.sql.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

public class Glavna {

    public static final Integer BROJ_PROFESORA = 2;
    public static final Integer BROJ_PREDMETA = 2;
    public static final Integer BROJ_ISPITA = 2;
    static Integer BROJ_STUDENATA = 2;
    public static Map<Integer, String> ocjene = Map.of(1, "nedovoljan", 2, "dovoljan", 3, "dobar", 4, "vrlo dobar", 5, "odličan");


    public static void main(String[] args) throws NemoguceOdreditiProsjekStudentaException, SQLException {

        Connection connection;
        {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/školski_informacijski_sustav", "root", "plazmax123");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

//        Ovdje brišem sav sadržaj svih tablica baze podataka 'školski_informacijski_sustav':
        MyJDBC.deleteAllRows(connection);

        Scanner scanner = new Scanner(System.in);

//        Ovdje punim scanner object sa .txt fajlom za debagiranje:
        try {
            File file = new File("dat/podaci.txt");
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Fajl nije pronađen.");
        }


        Profesor[] profesori = new Profesor[BROJ_PROFESORA];
        Predmet[] predmeti = new Predmet[BROJ_PREDMETA];
        Student[] studenti = new Student[BROJ_STUDENATA];
        Ispit[] ispiti = new Ispit[BROJ_ISPITA];

        System.out.print("Unesite broj ustanova: ");
        intException(scanner);
        int brojUstanova = scanner.nextInt();
        scanner.nextLine();

        for (int j = 0; j < brojUstanova; j++) {
            System.out.println("Unesite podatke za " + (j + 1) + ". obrazovnu ustanovu: ");

            //        Unos profesora:
            for (int i = 0; i < BROJ_PROFESORA; i++) {
                Profesor profesor = unosProfesora(scanner, i);
                profesori[i] = profesor;
                MyJDBC.insertProfesor(connection, profesor.getSifra(), profesor.getIme(), profesor.getPrezime(), profesor.getTitula(), j);
            }

            //        Unos predmeta:
            for (int i = 0; i < BROJ_PREDMETA; i++) {
                Predmet predmet = unosPredmeta(connection, profesori, scanner, i, j);
                predmeti[i] = predmet;
            }

            //        Unos studenata:
            for (int i = 0; i < BROJ_STUDENATA; i++) {
                Student student = unosStudenata(scanner, i);
                studenti[i] = student;
                MyJDBC.insertStudent(connection, student.getIme(), student.getPrezime(), student.getDatumRodjenja(), student.getJmbag(), j);
            }

            //        Unos ispitnih rokova:
            for (int i = 0; i < BROJ_ISPITA; i++) {
                Ispit ispit = unosIspitnogRoka(connection, predmeti, studenti, scanner, i, j);
                ispiti[i] = ispit;
            }

            ispisOdlikasa(ispiti);

            System.out.print("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti \n(1 - Veleučilište Jave, 2 - Fakultet računarstva): ");

            intException(scanner);
            int odabir = scanner.nextInt();
            scanner.nextLine();
            odabir = vratiBrojOdDo(scanner, odabir, 1,brojUstanova);

            System.out.print("Unesite naziv obrazovne ustanove: ");
            String nazivUstanove = scanner.nextLine();

            switch (odabir) {
                case 1 -> {
                    MyJDBC.insertEducationalInstitution(connection, nazivUstanove, "Veleučilište Jave");
                }
                case 2 -> {
                    MyJDBC.insertEducationalInstitution(connection, nazivUstanove, "Fakultet računarstva");
                }
                default -> {
                }
            }

            VeleucilisteJave veleucilisteJave = new VeleucilisteJave(nazivUstanove, predmeti, profesori, studenti, ispiti);
            FakultetRacunarstva fakultetRacunarstva = new FakultetRacunarstva(nazivUstanove, predmeti, profesori, studenti, ispiti);

            switch (odabir) {
                case 1 -> {
                    VeleucilisteJave java = new VeleucilisteJave(nazivUstanove, predmeti, profesori, studenti, ispiti);
                }
                case 2 -> {
                    FakultetRacunarstva fer = new FakultetRacunarstva(nazivUstanove, predmeti, profesori, studenti, ispiti);
                }
                default -> {
                }
            }
            for (Student student : studenti) {
                System.out.print("Unesite ocjenu završnog rada za studenta " + student.getPrezime() + " " + student.getIme() + ": ");
                intException(scanner);
                int ocjenaPismenogDijela = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Unesite ocjenu obrane završnog rada za studenta " + student.getPrezime() + " " + student.getIme() + ": ");
                intException(scanner);
                int ocjenaObrane = scanner.nextInt();
                scanner.nextLine();

                if (odabir == 1) {
                    System.out.println("Konačna ocjena studija studenta " + student.getPrezime() + " " + student.getIme() + " je: " + veleucilisteJave.izracunajKonacnuOcjenuStudijaZaStudenta(ispiti, ocjenaPismenogDijela, ocjenaObrane));
                } else if (odabir == 2) {
                    System.out.println("Konačna ocjena studija studenta " + student.getPrezime() + " " + student.getIme() + " je: " + fakultetRacunarstva.izracunajKonacnuOcjenuStudijaZaStudenta(ispiti, ocjenaPismenogDijela, ocjenaObrane));
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
        return new Profesor.Builder()
                .ime(ime)
                .prezime(prezime)
                .sifra(sifra)
                .titula(titula)
                .build();
    }

    //    Metoda za unos predmeta:
    private static Predmet unosPredmeta (Connection connection, Profesor[] profesori, Scanner scanner, int i, int j) throws SQLException {
        System.out.println("Unesite " + (i + 1) + ". predmet:");
        System.out.print("Unesite šifru predmeta: ");
        String sifra = scanner.nextLine();
        System.out.print("Unesite naziv predmeta: ");
        String nazivPredmeta = scanner.nextLine();
        System.out.print("Unesite broj ECTS bodova za predmet '" + nazivPredmeta + "': ");
        intException(scanner);
        int ects = scanner.nextInt();
        scanner.nextLine();
        ects = vratiBrojOd(scanner, ects, 1);

        ArrayList<ProfesorSQL> profesoriListTemp =  MyJDBC.createProfessorList(connection ,j);
        System.out.println("Odaberite profesora:");
        for (int k = 0; k < profesoriListTemp.size(); k++) {
            System.out.println((k +1) + ". " + profesoriListTemp.get(k).getIme() + " " + profesoriListTemp.get(k).getPrezime());
        }
        System.out.print("Odabir >> ");
        intException(scanner);
        int odabir = scanner.nextInt();
        scanner.nextLine();
        odabir = vratiBrojOdDo(scanner, odabir, 1, profesoriListTemp.size());

        System.out.print("Unesite broj studenata za predmet '" + nazivPredmeta + "': ");
        intException(scanner);
        int tempBrojStudenata = scanner.nextInt();
        scanner.nextLine();
        Student[] tempStudenti = new Student[tempBrojStudenata];

        MyJDBC.insertPredmet(connection, sifra, nazivPredmeta, ects, profesoriListTemp.get(odabir - 1).getId_profesori(), tempBrojStudenata, j);
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

        LocalDate datumRodjenja = LocalDate.MIN;
        while (true) {
            try {
                String unosDatuma = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                datumRodjenja = LocalDate.parse(unosDatuma, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Neispravan unos.");
                System.out.print("Upišite datum u formatu 'dd.MM.yyyy.': ");
            }
        }

        System.out.print("Unesite JMBAG studenta " + prezime + " " + ime + ": ");
        String jmbag = scanner.nextLine();
        return new Student(ime, prezime, jmbag, datumRodjenja);
    }

    //    Metoda za unos ispitnog roka:
    private static Ispit unosIspitnogRoka (Connection connection, Predmet[] predmeti, Student[] studenti, Scanner scanner, int i, int j) throws SQLException {
        System.out.println("Unesite " + (i + 1)+". ispitni rok:");

        ArrayList<PredmetSQL> classListTemp =  MyJDBC.createClassList(connection,j);
        System.out.println("Odaberite predmet:");
        for (int l = 0; l < classListTemp.size(); l++) {
            System.out.println((l +1) + ". " + classListTemp.get(l).getNaziv());
        }
        System.out.print("Odabir >> ");
        intException(scanner);
        int odabirPredmeta = scanner.nextInt();
        scanner.nextLine();
        odabirPredmeta = vratiBrojOdDo(scanner, odabirPredmeta, 1, classListTemp.size());

        System.out.print("Unesite naziv dvorane: ");
        String nazivDvorane = scanner.nextLine();
        System.out.print("Unesite zgradu dvorane: ");
        String zgradaDvorane = scanner.nextLine();
        Dvorana dvorana = new Dvorana(nazivDvorane,zgradaDvorane);

        ArrayList<StudentSQL> studentListTemp =  MyJDBC.createStudentList(connection,j);
        System.out.println("Odaberite studenta:");
        for (int k = 0; k < studentListTemp.size(); k++) {
            System.out.println((k +1) + ". " + studentListTemp.get(k).getIme() + " " + studentListTemp.get(k).getPrezime());
        }
        System.out.print("Odabir >> ");
        intException(scanner);
        int odabirStudenta = scanner.nextInt();
        scanner.nextLine();
        odabirStudenta = vratiBrojOdDo(scanner, odabirStudenta, 1, studentListTemp.size());

        System.out.print("Unesite ocjenu na ispitu (1-5): ");
        intException(scanner);
        int broj = scanner.nextInt();
        scanner.nextLine();
        broj = vratiBrojOdDo(scanner, broj, 1, 5);

        System.out.print("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm): ");

        LocalDateTime datumIspita = LocalDateTime.MIN;
        while (true) {
            try {
                String unosDatuma = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
                datumIspita = LocalDateTime.parse(unosDatuma, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Neispravan unos.");
                System.out.print("Upišite datum u formatu 'dd.MM.yyyy.THH:mm': ");
            }
        }

        MyJDBC.insertExam(connection, classListTemp.get(odabirPredmeta - 1).getId_predmeta(), studentListTemp.get(odabirStudenta - 1).getId_studenti(), broj, datumIspita, j);
        return new Ispit(predmeti[odabirPredmeta - 1], studenti[odabirStudenta - 1], broj, datumIspita, dvorana);
    }

    //    Metoda za ispis odlikaša:
    private static void ispisOdlikasa (Ispit[] ispiti) {
        for (Ispit ispit : ispiti) {
            if (ispit.getOcjena() == 5)
                System.out.println("Student " + ispit.getStudent().getIme() + " " + ispit.getStudent().getPrezime() +
                        " je ostvario ocjenu '" + ocjene.get(ispit.getOcjena()) + "' na predmetu '" + ispit.getPredmet().getNaziv() + "'");
        }
    }
    // Metoda za exception error pri unošenju integera:
    private static void intException (Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("To nije cijeli broj!");
            System.out.print("Unesite cijeli broj: ");
            scanner.nextLine();
        }
    }

//    Metoda za pokrivanje exception-a (1. varijanta):
    private static int vratiBrojOdDo(Scanner scanner, int broj, int odBroja, int doBroja) {
        while (broj < odBroja || broj > doBroja) {
            System.out.println("Upisana vrijednost mora biti veća ili jednaka "+ odBroja + " i manja ili jednaka " + doBroja);
            System.out.print("Ponovite upis: ");
            intException(scanner);
            broj = scanner.nextInt();
            scanner.nextLine();
        }
        return broj;
    }

//    Metoda za pokrivanje exception-a (2. varijanta):
    private static int vratiBrojOd(Scanner scanner, int broj, int odBroja) {
        while (broj < odBroja ) {
            System.out.println("Upisana vrijednost mora biti veća ili jednaka "+ odBroja );
            System.out.print("Ponovite upis: ");
            intException(scanner);
            broj = scanner.nextInt();
            scanner.nextLine();
        }
        return broj;
    }
}


