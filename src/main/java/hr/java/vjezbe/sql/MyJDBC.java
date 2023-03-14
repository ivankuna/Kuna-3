package hr.java.vjezbe.sql;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import hr.java.vjezbe.entitet.Student;

public class MyJDBC {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/školski_informacijski_sustav", "root", "plazmax123");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Metoda za brisanje svog sadržaja svih tablica baze podataka 'školski_informacijski_sustav':
    public static void deleteAllRows(Connection connection) {
        try {
            PreparedStatement statement1 = connection.prepareStatement("DELETE FROM ispitni_rokovi");
            PreparedStatement statement2 = connection.prepareStatement("DELETE FROM obrazovna_ustanova");
            PreparedStatement statement3 = connection.prepareStatement("DELETE FROM predmeti");
            PreparedStatement statement4 = connection.prepareStatement("DELETE FROM profesori");
            PreparedStatement statement5 = connection.prepareStatement("DELETE FROM studenti");

            statement1.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();
            statement4.executeUpdate();
            statement5.executeUpdate();

            statement1.close();
            statement2.close();
            statement3.close();
            statement4.close();
            statement5.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Metoda za unos podataka u tablicu 'profesori':
    public static void insertProfesor(Connection connection, String sifra, String ime, String prezime, String titula, Integer temp_obrazovna_ustanova) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO profesori( sifra, ime, prezime,titula, temp_obrazovna_ustanova ) VALUES (?, ?, ?, ?, ?)");

            statement.setString(1, sifra);
            statement.setString(2, ime);
            statement.setString(3, prezime);
            statement.setString(4, titula);
            statement.setInt(5, temp_obrazovna_ustanova);


            int rowsInserted = statement.executeUpdate();

            if (rowsInserted <= 0) {
                System.out.println("An error has occurred!");
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Metoda za kreiranje polja objekata tipa ProfesorSQL:
    public static ArrayList<ProfesorSQL> createProfessorList(Connection connection , Integer temp_obrazovna_ustanova) throws SQLException {

        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM profesori  WHERE  id_obrazovna_ustanova =  " + id_obrazovna_ustanova.toString() );
        String query = "SELECT * FROM profesori  WHERE  temp_obrazovna_ustanova =  " + temp_obrazovna_ustanova.toString() ;
        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<ProfesorSQL> profesoriList = new ArrayList<>();

        while (resultSet.next()) {
            int id_profesori = resultSet.getInt("id_profesori");
            String sifra = resultSet.getString("sifra");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String titula = resultSet.getString("titula");

            ProfesorSQL professor = new ProfesorSQL(id_profesori, sifra, ime, prezime, titula);
            profesoriList.add(professor);
        }
        resultSet.close();
        statement.close();
        return profesoriList;
    }

//    Metoda za unos podataka u tablicu 'predmeti':
    public static void insertPredmet(Connection connection, String sifra, String naziv, Integer ects, Integer id_profesori, Integer broj_studenata, Integer temp_obrazovna_ustanova) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO predmeti( sifra, naziv, ects, id_profesori, broj_studenata, temp_obrazovna_ustanova ) VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, sifra);
            statement.setString(2, naziv);
            statement.setInt(3, ects);
            statement.setInt(4, id_profesori);
            statement.setInt(5, broj_studenata);
            statement.setInt(6, temp_obrazovna_ustanova);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted <= 0) {
                System.out.println("An error has occurred!");
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    Metoda za kreiranje polja objekata tipa PredmetSQL:
    public static ArrayList<PredmetSQL> createClassList(Connection connection, Integer temp_obrazovna_ustanova) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM predmeti WHERE  temp_obrazovna_ustanova =  " + temp_obrazovna_ustanova.toString());
        ArrayList<PredmetSQL> classList = new ArrayList<>();

        while (resultSet.next()) {
            int id_predmeta = resultSet.getInt("id_predmeta");
            String sifra = resultSet.getString("sifra");
            String naziv = resultSet.getString("naziv");
            int ects = resultSet.getInt("ects");
            int id_profesori = resultSet.getInt("id_profesori");
            int broj_studenata = resultSet.getInt("broj_studenata");

            PredmetSQL predmet = new PredmetSQL(id_predmeta, sifra, naziv, ects, id_profesori, broj_studenata);
            classList.add(predmet);
        }
        resultSet.close();
        statement.close();
        return classList;
    }

    //    Metoda za unos podataka u tablicu 'studenti':
    public static void insertStudent(Connection connection, String ime, String prezime, LocalDate datum_rodjenja, String jmbag, Integer temp_obrazovna_ustanova) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO studenti( ime, prezime,datum_rodjenja, jmbag, temp_obrazovna_ustanova ) VALUES (?, ?, ?, ?, ?)");

            statement.setString(1, ime);
            statement.setString(2, prezime);
            statement.setDate(3, java.sql.Date.valueOf(datum_rodjenja.toString()));
            statement.setString(4, jmbag);
            statement.setInt(5, temp_obrazovna_ustanova);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted <= 0) {
                System.out.println("An error has occurred!");
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    Metoda za kreiranje polja objekata tipa StudentSQL:
    public static ArrayList<StudentSQL> createStudentList(Connection connection, Integer temp_obrazovna_ustanova) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM studenti  WHERE  temp_obrazovna_ustanova =  " + temp_obrazovna_ustanova.toString());
        ArrayList<StudentSQL> studentList = new ArrayList<>();

        while (resultSet.next()) {
            int id_studenti = resultSet.getInt("id_studenti");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String jmbag = resultSet.getString("jmbag");
            Date datum_rodjenja = resultSet.getDate("datum_rodjenja");

            StudentSQL student = new StudentSQL(id_studenti, ime, prezime, jmbag, datum_rodjenja);
            studentList.add(student);
        }
        resultSet.close();
        statement.close();
        return studentList;
    }

    //    Metoda za unos podataka u tablicu 'ispitni_rokovi':
    public static void insertExam(Connection connection, Integer id_predmeta, Integer id_studenti, Integer ocjena, LocalDateTime datum_i_vrijeme, Integer temp_obrazovna_ustanova) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ispitni_rokovi( id_predmeta, id_studenti,ocjena, datum_i_vrijeme, temp_obrazovna_ustanova ) VALUES (?, ?, ?, ?, ?)");

            statement.setInt(1, id_predmeta);
            statement.setInt(2, id_studenti);
            statement.setInt(3, ocjena);
            statement.setTimestamp(4, Timestamp.valueOf(datum_i_vrijeme));
            statement.setInt(5, temp_obrazovna_ustanova);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted <= 0) {
                System.out.println("An error has occurred!");
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    Metoda za unos podataka u tablicu 'obrazovna_ustanova':
    public static void insertEducationalInstitution(Connection connection, String naziv_obrazovne_ustanove, String tip_obrazovne_ustanove) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO obrazovna_ustanova( naziv_obrazovne_ustanove, tip_obrazovne_ustanove ) VALUES (?, ?)");

            statement.setString(1, naziv_obrazovne_ustanove);
            statement.setString(2, tip_obrazovne_ustanove);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted <= 0) {
                System.out.println("An error has occurred!");
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    Metoda za kreiranje polja objekata tipa ObrazovnaUstanovaSQL:
    public static ArrayList<ObrazovnaUstanovaSQL> createEducationalInstitutionList(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM obrazovna_ustanova");
        ArrayList<ObrazovnaUstanovaSQL> educationalInstitutionList = new ArrayList<>();

        while (resultSet.next()) {
            int id_obrazovna_ustanova = resultSet.getInt("id_obrazovna_ustanova");
            String naziv_obrazovne_ustanove = resultSet.getString("naziv_obrazovne_ustanove");
            String tip_obrazovne_ustanove = resultSet.getString("tip_obrazovne_ustanove");

            ObrazovnaUstanovaSQL obrazovnaUstanova = new ObrazovnaUstanovaSQL(id_obrazovna_ustanova, naziv_obrazovne_ustanove, tip_obrazovne_ustanove);
            educationalInstitutionList.add(obrazovnaUstanova);
        }
        resultSet.close();
        statement.close();
        return educationalInstitutionList;
    }


}
