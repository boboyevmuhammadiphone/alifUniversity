package uz.alif.lesson_jpa.repositoru;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.alif.lesson_jpa.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
}
