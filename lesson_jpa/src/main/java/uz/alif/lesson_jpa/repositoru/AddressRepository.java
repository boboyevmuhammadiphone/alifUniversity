package uz.alif.lesson_jpa.repositoru;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.alif.lesson_jpa.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
