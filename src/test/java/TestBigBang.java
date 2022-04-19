import curent.Curent;
import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestBigBang {

    public static Service service;

    @BeforeAll
    public static void setup() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        TestBigBang.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void addTema_bigbang_ValidData_CreatedSuccessfully() {

        String nrTema = "100";
        String descriere = "test";
        int deadline = 12;
        int primire = 1;

        Tema tema = new Tema(nrTema, descriere, deadline, primire );

        try{
            service.addTema(tema);
            assert(true);

        }catch (ValidationException ve){
            System.out.println("Validation Exception: " + ve.getMessage());
            assert(false);

        }
    }

    @Test
    public void addStudent_bigbang_ValidData_CreatedSuccessfully() {
        String idStudent = "test";
        String numeStudent = "john";
        int grupa = 934;
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            System.out.println(exception);
            assertFalse(true);
        }

        assert(service.findStudent(idStudent) != null);
    }

    @Test
    public void addNota_bigbang_ValidData_CreatedSuccessfully() {
//        private String id;
//        private String idStudent;
//        private String idTema;
//        private double nota;
//        private LocalDate data;

        String idStudent = "999";
        String numeStudentt = "john";
        int grupa = 934;
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudentt, grupa, email);

        String nrTema = "999";
        String descriere = "test";
        int deadline = 6;
        int primire = 1;

        Tema tema = new Tema(nrTema, descriere, deadline, primire );


        String id = "999";
        String idStudentt = "999";
        String idTema = "999";
        double nota = 5;

        String newnewDate = "2023,11,1";
        String[] words = newnewDate.split(",");

        LocalDate date = LocalDate.of(Integer.parseInt(words[0]),Integer.parseInt(words[1]),Integer.parseInt(words[2]) );

        Nota notaObj = new Nota(id, idStudentt, idTema, nota, date);

        try{

            service.addTema(tema);
            service.addStudent(student);
            service.addNota(notaObj, "feedback");
            assert(true);
        }catch (ValidationException e){
            e.printStackTrace();
            assert(false);
        }


    }

    @Test
    public void addNota_isolated_ValidData_CreatedSuccessfully() {


        String id = "999";
        String idStudentt = "999";
        String idTema = "999";
        double nota = 5;

        String newnewDate = "2023,11,1";
        String[] words = newnewDate.split(",");

        LocalDate date = LocalDate.of(Integer.parseInt(words[0]),Integer.parseInt(words[1]),Integer.parseInt(words[2]) );

        Nota notaObj = new Nota(id, idStudentt, idTema, nota, date);

        try{
            service.addNota(notaObj, "feedback");
            assert(true);
        }catch (ValidationException e){
            e.printStackTrace();
            assert(false);
        }


    }







}
