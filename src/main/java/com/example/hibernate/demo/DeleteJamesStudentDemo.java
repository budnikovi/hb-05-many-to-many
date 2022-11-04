package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteJamesStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // get the student James from database
            int studentId = 2;
            Student tempStudent  = session.get(Student.class, studentId);

            System.out.println("\nLoaded student: " + tempStudent);
            System.out.println("Course: " + tempStudent.getCourseList());

            // delete student
            System.out.println("\nDeleting student: " + tempStudent);
            session.delete(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
