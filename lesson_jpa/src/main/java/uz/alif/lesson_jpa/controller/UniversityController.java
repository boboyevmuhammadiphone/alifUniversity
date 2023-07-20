package uz.alif.lesson_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.alif.lesson_jpa.entity.Address;
import uz.alif.lesson_jpa.entity.Student;
import uz.alif.lesson_jpa.entity.University;
import uz.alif.lesson_jpa.payload.UniversityDto;
import uz.alif.lesson_jpa.repositoru.AddressRepository;
import uz.alif.lesson_jpa.repositoru.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    @RequestMapping(value = "/getAllUnversity", method = RequestMethod.GET)
    public List<University> getAllUniversitu(){
        return universityRepository.findAll();
    }

    @RequestMapping(value = "/addUnversity", method = RequestMethod.POST)
    public String addUniversitu(@RequestBody UniversityDto universityDto){
        Address address = new Address(universityDto.getRegion(), universityDto.getDistrict(), universityDto.getStreet());
        Address savedAddress = addressRepository.save(address);
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);
        return "university saved";
    }

    @RequestMapping(value = "/editUnversity/{id}", method = RequestMethod.PUT)
    public String editUniversitu(@PathVariable int id, @RequestBody UniversityDto universityDto){

        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isEmpty())
            return "university not found";
        University university = optionalUniversity.get();
        university.setName(universityDto.getName());

        Address address = university.getAddress();
        address.setRegion(universityDto.getRegion());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        addressRepository.save(address);
        universityRepository.save(university);
        return "university updated";
    }

    @RequestMapping(value = "/editUnversity/{id}", method = RequestMethod.DELETE)
    public String deleteUniversitYById(@PathVariable int id){
        Optional<University>optionalUniversity = universityRepository.findById(id);
        if (!optionalUniversity.isPresent())
            return "university not found";

        addressRepository.deleteById(optionalUniversity.get().getAddress().getId());

        universityRepository.deleteById(id);
        return "university deleted";
    }



}
