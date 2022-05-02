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

public class TestTopDown {

    public static Service service;

    @BeforeAll
    public static void setup(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        TestTopDown.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }


    @Test
    public void addStudent_topdown_ValidData_CreatedSuccessfully(){
        /// ...

        String nrTema = "999";
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
    public void addTema_topdown_ValidData_CreatedSuccessfully(){
        /// addStudent + addTema
        this.addStudent_topdown_ValidData_CreatedSuccessfully();

        String nrTema = "999";
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
    public void addGrade_topdown_ValidData_CreatedSuccessfully(){
        /// addStudent + addTema + addNota

        this.addStudent_topdown_ValidData_CreatedSuccessfully();
        this.addTema_topdown_ValidData_CreatedSuccessfully();


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

}
