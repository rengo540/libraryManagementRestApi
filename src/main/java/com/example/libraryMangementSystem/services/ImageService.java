package com.example.libraryMangementSystem.services;



import com.example.libraryMangementSystem.config.config;
import com.example.libraryMangementSystem.exceptions.ResourceNotFoundException;
import com.example.libraryMangementSystem.models.Book;
import com.example.libraryMangementSystem.models.CoverImage;
import com.example.libraryMangementSystem.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;


    @Autowired
    private BookService bookService;


    public CoverImage getImageById(Long id) {
        return imageRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("this image not found id="+id));
    }


    public void deleteImageById(Long id) throws IOException {
         CoverImage image = getImageById(id);
         Path filePath = Paths.get(config.UPLOAD_DIR +image.getId().toString()+image.getFileName());
         Files.delete(filePath);
         imageRepo.deleteById(id);
    }

    public void addImages(List<MultipartFile> files, Long bookId) throws IOException {
        if (files.isEmpty()) {
            throw new IOException("there is no file");
        }

        for(MultipartFile file :files){
            String originalFilename = file.getOriginalFilename();
            CoverImage image= saveImage(originalFilename,file.getContentType(),bookId);
            Path filePath = Paths.get(config.UPLOAD_DIR +image.getId().toString()+originalFilename);
            image.setImagePath(filePath.toString());
            imageRepo.save(image);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public Resource viewImage(String fileName) throws MalformedURLException {
        CoverImage image = imageRepo.findByFileName(fileName);
        Path filePath =Paths.get(image.getImagePath());
        return new UrlResource(filePath.toUri());
    }

    private CoverImage saveImage(String filename,String filetype,Long bookId) {
        CoverImage image = new CoverImage();
        image.setFileName(filename);
        image.setFileType(filetype);
        Book book= bookService.getBookById(bookId);
        image.setBook(book);
        return  imageRepo.save(image);
    }




}
