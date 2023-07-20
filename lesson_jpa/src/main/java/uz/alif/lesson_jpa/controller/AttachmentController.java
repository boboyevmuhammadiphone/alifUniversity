package uz.alif.lesson_jpa.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.alif.lesson_jpa.entity.Attachment;
import uz.alif.lesson_jpa.entity.AttachmentContent;
import uz.alif.lesson_jpa.repositoru.AttachmentContentRepository;
import uz.alif.lesson_jpa.repositoru.AttachmentRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    private static final String uploadDirectory = "saqlanganlar";

//    @PostMapping
//    public String add(MultipartHttpServletRequest request) throws IOException {
//
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//
//        String name = file.getOriginalFilename();
//        String contentType = file.getContentType();
//        long size = file.getSize();
//
//        Attachment attachment = new Attachment();
//        attachment.setOriginalFileName(name);
//        attachment.setContentType(contentType);
//        attachment.setSize(size);
//        Attachment save = attachmentRepository.save(attachment);
//
//        AttachmentContent attachmentContent = new AttachmentContent();
//        attachmentContent.setAttachment(save);
//        attachmentContent.setContent(file.getBytes());
//        attachmentContentRepository.save(attachmentContent);
//        return save.getOriginalFileName() + " file is savid";
//    }

//    @GetMapping("/downloaddb/{id}")
//    public void downloaddb(@PathVariable Integer id, HttpServletResponse response) throws IOException {
//
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()) {
//            Attachment attachment = optionalAttachment.get();
//            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
//            if (optionalAttachmentContent.isPresent()) {
//                AttachmentContent attachmentContent = optionalAttachmentContent.get();
//                response.setHeader("Content-Disposition", "attachment: filename=\"" + attachment.getOriginalFileName() + "\"");
//                response.setContentType(attachment.getContentType());
//                FileCopyUtils.copy(attachmentContent.getContent(), response.getOutputStream());
//            }
//        }
//
//    }


    @PostMapping
    public String saveToSystem(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        String name = file.getOriginalFilename(); // windows.rasmi.jpg
        String contentType = file.getContentType();
        long size = file.getSize();
        String[] split = name.split("\\.");
        String s = UUID.randomUUID().toString(); // jhasjd-asdkjdf-54454-lsklkfd454-565656454.jpg
        s = s + "." + split[(split.length - 1)];

        Attachment attachment = new Attachment();
        attachment.setOriginalFileName(name);
        attachment.setContentType(contentType);
        attachment.setSize(size);
        attachment.setName(s);
        Attachment save = attachmentRepository.save(attachment);

        Path path = Paths.get(uploadDirectory + "/" + s);
        Files.copy(file.getInputStream(), path);
        return "file saqlandi id si : " + save.getId();
    }

    @GetMapping("/downloadfilesystem/{id}")
    public void downloadFileSystem(@PathVariable int id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            response.setHeader("Content-Disposition", "attachment: filename=\"" + attachment.getOriginalFileName() + "\"");
            response.setContentType(attachment.getContentType());
            FileInputStream fileInputStream = new FileInputStream(uploadDirectory+"/"+attachment.getName());
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
        }
    }


//    @GetMapping("/download/{id}")
//    public void getById(@PathVariable Integer id, HttpServletResponse response) throws IOException {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()) {
//            Attachment attachment = optionalAttachment.get();
//            response.setHeader("Content-Disposition", "attachment: filename=\"" + attachment.getOriginalFileName() + "\"");
//            response.setContentType(attachment.getContentType());
//            FileInputStream fileInputStream = new FileInputStream(uploadDirectory + "/" + attachment.getName());
//            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
//        }
//    }


}
