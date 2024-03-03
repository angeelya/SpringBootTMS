package com.example.SpringBootTMS.work47;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class FileService {
    private final Path path = Paths.get("files");
    private File directory;
    private final String EX_SAVE = "Couldn't save the file. ",
            EX_LOAD = "Couldn't load the file. ";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public FileService() {
        directory = new File(path.toString());
        if (!directory.exists()) {
            directory.mkdir();
            logger.info("Created" + directory);
        }
    }

    public void save(MultipartFile file) {
        try {
            FileOutputStream fileInputStream = new FileOutputStream(path + "/" + file.getOriginalFilename());
            fileInputStream.write(file.getInputStream().readAllBytes());
        } catch (IOException e) {
            logger.error(EX_SAVE + e.getMessage());
            new RuntimeException(EX_SAVE + e.getMessage());
        }
    }

    public Resource load(String fileName) {
        try {
            Resource resource = new UrlResource(path.resolve(fileName).toUri());
            if (resource.exists() || resource.isFile() || resource.isReadable())
                return resource;
            else throw new RuntimeException(EX_LOAD);
        } catch (MalformedURLException e) {
            throw new RuntimeException(EX_LOAD + e.getMessage());
        }
    }

    public Stream<File> getFilesList() {
        return Arrays.stream(directory.listFiles());
    }

}
