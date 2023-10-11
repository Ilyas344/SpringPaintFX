package com.spring.paint.service;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public interface FileService {
    void saveFile(Pane pane);
    Image loadFile();
}
