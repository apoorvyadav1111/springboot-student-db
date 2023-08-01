package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> student1 = studentRepository.findStudentByEmail(student.getEmail());
        if (student1.isPresent()) {
            throw new IllegalStateException("email already taken");
        } else {
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        } else {
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));

        if (name != null && name.length() > 0 && !name.equals(student.getName())) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !email.equals(student.getEmail())){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email already taken");
            }else{
                student.setEmail(email);
            }
        }
        studentRepository.save(student);
    }
}
