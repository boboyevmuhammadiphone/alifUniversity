package uz.alif.lesson_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.alif.lesson_jpa.entity.Subject;
import uz.alif.lesson_jpa.repositoru.SubjectRepository;

import java.util.List;


@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }

    @PostMapping
    public String add(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "this subject is already exists";
        subjectRepository.save(new Subject(subject.getName()));
        return "subject added";
    }

}
