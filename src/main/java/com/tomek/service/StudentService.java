package com.tomek.service;

import com.tomek.repository.StudentRepository;
import com.tomek.student.Student;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {

        boolean exist = studentRepository.existsById(id.longValue());
        if (!exist) {
            throw new IllegalStateException("student with id-" + id + " - does not exist");
        }
        studentRepository.deleteById(id.longValue());
    }

    @Transactional
    public void updateStudent(Integer id, String name, String email) {
        Student student = studentRepository.findById(id.longValue())
                .orElseThrow(() -> new IllegalStateException("student with id-" + id + " - does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)
        ) {
            student.setName(name);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)
        ) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
