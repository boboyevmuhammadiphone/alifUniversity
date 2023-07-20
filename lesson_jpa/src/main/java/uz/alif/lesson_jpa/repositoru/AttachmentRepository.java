package uz.alif.lesson_jpa.repositoru;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.alif.lesson_jpa.entity.Address;
import uz.alif.lesson_jpa.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
