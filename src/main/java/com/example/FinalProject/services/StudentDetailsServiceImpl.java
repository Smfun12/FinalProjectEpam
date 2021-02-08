package com.example.FinalProject.services;

import com.example.FinalProject.entities.Student;
import com.example.FinalProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

import java.util.Optional;

public class StudentDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<Student> student = studentRepository.findByLogin(username);

        if (student.isPresent() && !student.get().isEnabled()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new StudentDetailsImpl(student.get());
    }

}