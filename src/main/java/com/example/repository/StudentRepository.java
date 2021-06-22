package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstname);

    Student findByLastNameAndFirstName (String lastName, String firstName);

    List<Student> findByFirstNameOrLastName (String firstName, String lastName);

    List<Student> findByFirstNameIn (List<String> firstNames);
}
