package uz.alif.lesson_jpa.repositoru;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.alif.lesson_jpa.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
