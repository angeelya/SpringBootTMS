package com.example.SpringBootTMS.work47;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Message> upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            fileService.save(multipartFile);
            return new ResponseEntity<>(new Message("File uploaded"), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public List<FileInf> getFilesList() {
        List<FileInf> filesInf = fileService.getFilesList().map(file -> {
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileController.class, "download", file.getName())
                    .build().toUriString();
            return new FileInf(file.getName(), url);
        }).collect(Collectors.toList());
        return filesInf;
    }

    @GetMapping("download")
    public ResponseEntity download(@RequestParam("fileName") String fileName) {
        try {
            Resource file = fileService.load(fileName);
            return new ResponseEntity(file, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
