package com.spring.paint.service;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;

public interface FileService {
    void saveFile(Pane pane);
    Image loadFile();
}
