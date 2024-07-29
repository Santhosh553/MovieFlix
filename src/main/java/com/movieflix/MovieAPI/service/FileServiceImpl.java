package com.movieflix.MovieAPI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        //get file details
        String fileName = file.getOriginalFilename();
        String filePath = path + File.separator + fileName; // "" + "" - path needs to be appended.

        //create file object
        File f = new File(path);
        if(!f.exists())
            f.mkdir();


        // upload file to path
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            // Replace Exisiting - it will overwrite the uploaded filename is already exist.

        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {

        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
}
