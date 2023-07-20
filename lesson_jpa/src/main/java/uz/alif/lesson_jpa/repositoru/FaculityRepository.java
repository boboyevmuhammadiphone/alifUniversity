package uz.alif.lesson_jpa.repositoru;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.alif.lesson_jpa.entity.Address;
import uz.alif.lesson_jpa.entity.Faculity;

import java.util.List;

@Repository
public interface FaculityRepository extends JpaRepository<Faculity, Integer> {

    // method query
    boolean existsByNameAndUniversityId(String name, Integer university_id);

    // method query
    List<Faculity> findAllByUniversityId(Integer university_id);

    // jpa query
    @Query(value = "select f from Faculity f where f.university.id=:university_id")
    List<Faculity> hammasiniOlishJpaQuery(int university_id);

    // native query
    @Query(value = "select fac.* from faculity fac join university un on fac.university_id = un.id where un.id=:universityId", nativeQuery = true)
    List<Faculity> hammasiniOlishNativeQuery(int universityId);





}
