import domain.Tema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

public class TestWBTestClass {

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
        TestWBTestClass.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    // Assignment
    //    private String nrTema;
    //    private String descriere;
    //    private int deadline;
    //    private int primire;

    @Test
    public void addTema_ValidData_CreatedSuccessfully() {

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
    public void addTema_Invalid_nrTema_ThrowsError() {

        String nrTema = null;
        String descriere = "test";
        int deadline = 12;
        int primire = 2;

        Tema tema = new Tema(nrTema, descriere, deadline, primire );

        try{
            service.addTema(tema);
            assert(false);

        }catch (ValidationException ve){
            System.out.println("Validation Exception: " + ve.getMessage());
            assert(true);

        }

    }

    @Test
    public void addTema_Invalid_descriere_ThrowsError() {

        String nrTema = "101";
        String descriere = "";
        int deadline = 12;
        int primire = 2;

        Tema tema = new Tema(nrTema, descriere, deadline, primire );

        try{
            service.addTema(tema);
            assert(false);

        }catch (ValidationException ve){
            System.out.println("Validation Exception: " + ve.getMessage());
            assert(true);

        }

    }

    @Test
    public void addTema_Invalid_deadline_ThrowsError() {

        String nrTema = "102";
        String descriere = "test";
        int deadline = 0;
        int primire = 11;

        Tema tema = new Tema(nrTema, descriere, deadline, primire );

        try{
            service.addTema(tema);
            assert(false);

        }catch (ValidationException ve){
            System.out.println("Validation Exception: " + ve.getMessage());
            assert(true);

        }

    }

    @Test
    public void addTema_Invalid_primire_ThrowsError() {

        String nrTema = "103";
        String descriere = "test";
        int deadline = 12;
        int primire = 15;

        Tema tema = new Tema(nrTema, descriere, deadline, primire );

        try{
            service.addTema(tema);
            assert(false);

        }catch (ValidationException ve){
            System.out.println("Validation Exception: " + ve.getMessage());
            assert(true);

        }

    }








}
