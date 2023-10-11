package com.spring.paint.service.Impl;

import com.spring.paint.service.FileService;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Override
    public void saveFile(Pane pane) {
        try {
            FileChooser fc = fileChooser();
            File selectedFile = fc.showSaveDialog(new Stage());
            Image newImage = pane.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(newImage, null), "png", selectedFile);
        } catch (NullPointerException e) {
            log.warn("No image to save");
        } catch (IOException e) {
            log.error("Error saving file", e);
        } catch (Exception e) {
            log.info("No file", e);
        }

    }


    @Override
    public Image loadFile() {
        File file;
        FileChooser fc = fileChooser();
        Image image = null;
        try {
            file = fc.showOpenDialog(new Stage());
            image = new Image(file.toURI().toString());
        } catch (Exception e) {
            log.warn("No file to load", e);
        }
        return image;
    }

    private FileChooser fileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG File", "*.png"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG File", "*.jpg"));
        return fileChooser;
    }

}
