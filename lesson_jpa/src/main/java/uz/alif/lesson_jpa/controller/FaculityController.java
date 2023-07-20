package uz.alif.lesson_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.alif.lesson_jpa.entity.Faculity;
import uz.alif.lesson_jpa.entity.University;
import uz.alif.lesson_jpa.payload.FaculityDto;
import uz.alif.lesson_jpa.repositoru.FaculityRepository;
import uz.alif.lesson_jpa.repositoru.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculity")
public class FaculityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    FaculityRepository faculityRepository;

//    @RequestMapping( method = RequestMethod.POST)
    @PostMapping()
    public String add (@RequestBody FaculityDto faculityDto){

        boolean existsByNameAndUniversityId = faculityRepository.existsByNameAndUniversityId(faculityDto.getName(), faculityDto.getUniversityId());
        if (existsByNameAndUniversityId)
            return "this university has this faculity name";

        Faculity faculity = new Faculity();
        faculity.setName(faculityDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(faculityDto.getUniversityId());
        if (optionalUniversity.isEmpty())
            return "this university is not found";
        faculity.setUniversity(optionalUniversity.get());
        faculityRepository.save(faculity);
        return "faculity saved";

    }

    // Vazirlik uchun
    @GetMapping
    public List<Faculity> getAll(){
        return faculityRepository.findAll();
    }

    // universitet moderator uchun
    @GetMapping("/{id}")
    public List<Faculity> getAll(@PathVariable int id){
        return faculityRepository.hammasiniOlishNativeQuery(id);
    }





}
