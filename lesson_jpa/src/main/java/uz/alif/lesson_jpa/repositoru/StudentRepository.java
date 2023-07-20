package uz.alif.lesson_jpa.repositoru;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.alif.lesson_jpa.entity.Address;
import uz.alif.lesson_jpa.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findAllByGroup_Faculity_UniversityId(Integer group_faculity_university_id, Pageable pageable);
}
