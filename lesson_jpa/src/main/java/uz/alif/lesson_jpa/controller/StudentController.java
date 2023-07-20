package uz.alif.lesson_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.alif.lesson_jpa.entity.Student;
import uz.alif.lesson_jpa.repositoru.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    // vazirlik
    // universitet
    // facultet
    // group

    @Autowired
    StudentRepository studentRepository;

    // vazirlik uchun
    public Page<Student> getAll(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAll(pageable);
    }

    // universitet uchun
    public Page<Student> getAll(@RequestParam int page, @PathVariable int id){
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_Faculity_UniversityId(id, pageable);
    }



}
