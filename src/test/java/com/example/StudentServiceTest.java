package com.example;

import com.example.models.Student;
import com.example.repo.StudentRepository;
import com.example.services.StudentService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MicronautTest
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks // This injects the mock repository into the service
    private StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }
    @Test
    public void createStudent(){
        Student student = new Student();
        student.setName("elshoqer");
        student.setEmail("elbalbolllll@yahoo.com");
        student.setGrade(5);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        // When
        // Then
        assertEquals("elshoqer", studentService.createStudent(student).getName());
    }
    @Test public void findall(){
        // Given: a list of students
        Student student1 = new Student();
        student1.setName("John Doe");
        student1.setEmail("john@example.com");
        student1.setGrade(90);
        Student student2 = new Student();
        student2.setName("Jane Doe");
        student2.setEmail("jane@example.com");
        student2.setGrade(85);
        List<Student> studentList = new ArrayList<Student>() ;
        studentList.add(student2);
        studentList.add(student1);
        System.out.println(studentList.size());
        // Mock the repository behavior
        when(studentRepository.findAll()).thenReturn(studentList);
        // When: calling getAllStudents
        // Then: verify the list is as expected
        System.out.println(studentService.getAllStudents());
        assertEquals(2,studentService.getAllStudents().size());

    } @Test
    public void getStudentById() {
        // Given: a student object with a specific ID
        int id = 5;
        Student student = new Student();
        student.setId(id);
        student.setName("John Doe");
        student.setEmail("john@example.com");
        student.setGrade(90);
        // Mock the repository behavior to return the student with the given ID
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        // When: calling getStudent with the same ID
        Student result = studentService.getStudent(id);
        // Then: verify the returned student has the expected ID
        assertEquals(id, result.getId(), "The student ID should be 5");
        assertEquals("John Doe", result.getName(), "The student's name should be 'John Doe'");
    }
}
