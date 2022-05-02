import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.AfterAll;
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

    @AfterAll
    public static void teardown(){
        service.deleteStudent("999");
        service.deleteTema("999");
        service.deleteNota("999");
    }


    @Test
    public void addStudent_topdown_ValidData_CreatedSuccessfully(){
        Student student = new Student("999", "john", 934, "john_doe@yahoo.com");

        try{
            service.addStudent(student);
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
        Tema tema = new Tema("999", "test", 5, 1 );

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
        String[] words =  "2023,11,1".split(",");
        LocalDate date = LocalDate.of(Integer.parseInt(words[0]),Integer.parseInt(words[1]),Integer.parseInt(words[2]) );
        Nota notaObj = new Nota("999", "999", "999", 5, date);

        try{
            service.addNota(notaObj, "feedback");
            assert(true);
        }catch (ValidationException e){
            e.printStackTrace();
            assert(false);
        }
    }



}
