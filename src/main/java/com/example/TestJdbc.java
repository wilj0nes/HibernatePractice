package com.example;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.time.LocalTime;
import java.util.stream.Stream;

public class TestJdbc {
    public static void main(String args) {

        int d;
        System.out.println(LocalTime.now());


//        System.out.println(Stream.of("green", "yellow", "blue").max(String::compareTo).filter(s -> s.endsWith("n")).orElse("yellow"));

        //        String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=FALSE ";
//        String user = "hbstudent";
//        String pass = "hbstudent";

        // create session factory
        SessionFactory factory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // maven will be a bitch and break this unless the cfg file is in src/main/resources

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a student object
            System.out.println("Creating a new student object");
            Student tempStudent = new Student("will", "jones", "some@email.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            session.save(tempStudent);
            System.out.println("Saving the student... ");
            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            factory.close();
        }
    }
}
