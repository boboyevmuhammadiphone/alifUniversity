package uz.alif.lesson_jpa.repositoru;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.alif.lesson_jpa.entity.AttachmentContent;

import java.util.Optional;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
//    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
}
