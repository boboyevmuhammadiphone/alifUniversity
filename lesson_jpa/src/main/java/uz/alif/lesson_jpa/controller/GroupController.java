package uz.alif.lesson_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.alif.lesson_jpa.entity.Faculity;
import uz.alif.lesson_jpa.entity.Group;
import uz.alif.lesson_jpa.payload.GroupDto;
import uz.alif.lesson_jpa.repositoru.FaculityRepository;
import uz.alif.lesson_jpa.repositoru.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FaculityRepository faculityRepository;

    @PostMapping()
    public String add(@RequestBody GroupDto groupDto){
        Optional<Faculity> optionalFaculity = faculityRepository.findById(groupDto.getFaculityId());
        if (!optionalFaculity.isPresent())
            return "faculity not found";
        groupRepository.save(new Group(groupDto.getGroupName(), optionalFaculity.get()));
        return "added";
    }

    // vazirlikka
    @GetMapping
    public List<Group> get(){
        return groupRepository.findAll();
    }

    // universitet
    @GetMapping("/{id}")
    public List<Group> getById(@PathVariable int id){
        return groupRepository.nativfindAllById(id);
    }




}
