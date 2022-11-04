package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForJamesDemo {

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

            // create more courses
            Course tempCourse1 = new Course("Rubik's Cube - How To Speed Cube Faster");
            Course tempCourse2 = new Course("Atari 2600 - Game Development Tutorial");

            // add student to courses
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);


            // save the courses
            System.out.println("\nSaving the courses ...");

            session.save(tempCourse1);
            session.save(tempCourse2);

            System.out.println("\nSave the courses: " + tempStudent.getCourseList());
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
