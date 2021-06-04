package com.webapplication.demo.services;

import com.webapplication.demo.services.exceptions.FileException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author CarlosMacaneta
 */
@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
        String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());

        if (!"png".equals(ext) && !"jpg".equals(ext)) {
            throw new FileException("Somente imagens PNG ou JPG são permitidas.");
        }
        try {
            BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
            if (!"png".equals(ext)) {
                img = pngTOJPG(img);
            }
            return img;
        } catch (IOException ex) {
            throw new FileException("Erro ao ler arquivo.");
        }
    }

    public BufferedImage pngTOJPG(BufferedImage img) {
        BufferedImage jpgImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImg.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);

        return jpgImg;
    }

    public InputStream getInputStream(BufferedImage img, String extension) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo");
        }
    }
}
