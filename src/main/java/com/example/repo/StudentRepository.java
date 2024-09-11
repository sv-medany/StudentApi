package com.example.repo;

import com.example.models.Student;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
List<Student> findAll();
}

