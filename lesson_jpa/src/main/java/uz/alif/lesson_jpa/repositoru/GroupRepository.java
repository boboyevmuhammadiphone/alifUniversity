package uz.alif.lesson_jpa.repositoru;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.alif.lesson_jpa.entity.Address;
import uz.alif.lesson_jpa.entity.Faculity;
import uz.alif.lesson_jpa.entity.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAllByFaculity_UniversityId(Integer faculity_university_id);

    @Query(value = "select g from groups g where g.faculity.university.id=:id")
    List<Group> jpafindAllById(int id);

    @Query(value = "select g.* from groups g join faculity f on f.id=g.faculity_id join university u on f.university_id=u.id where u.id=:id", nativeQuery = true)
    List<Group> nativfindAllById(int id);


}
