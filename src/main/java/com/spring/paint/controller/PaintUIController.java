package com.spring.paint.controller;

import com.jfoenix.controls.*;
import com.spring.paint.service.DragResizeMod;
import com.spring.paint.service.FileService;
import com.spring.paint.service.ShapeService;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.labs.util.event.MouseControlUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class PaintUIController implements Initializable {

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXComboBox<String> cbShapes;

    @FXML
    private JFXSlider sldOpacity;

    @FXML
    private JFXColorPicker cpFillColor;

    @FXML
    private JFXSlider sldBorderSize;

    @FXML
    private JFXColorPicker cpBorderColor;

    @FXML
    private JFXTextField tfTextString;

    @FXML
    private JFXTextField tfFontSize;

    @FXML
    private JFXColorPicker cpTextColor;

    @FXML
    private JFXComboBox<String> cbbFontStyle;

    @FXML
    private ScrollPane spMain = new ScrollPane();
    @FXML
    private final JFXButton btnClear = new JFXButton();
    @FXML
    private final Pane mainPane = new Pane();

    @FXML
    private JFXComboBox<Integer> cbLayer = new JFXComboBox<>();

    @FXML
    private JFXButton btnAddShape = new JFXButton();

    private double positionX = 0;
    private double positionY = 0;
    private String item;
    private final ShapeService tools;
    private final FileService  fileService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newProject();
        cbShapesConfig();
        cbFontStyleConfig();
        draw();
        saveFile();
        mainPane.setPrefSize(1100, 600);
        spMain.setContent(mainPane);
        spMain.setPrefSize(1100, 600);
        btnClear.setOnAction(event -> newProject());
    }

    void cbShapesConfig() {
        cbShapes.getItems().addAll("Квадрат", "Треугольник", "Звезда", "Круг", "Линия", "Текст", "Рисунок", "Фоновый цвет");
        cbShapes.setValue("Квадрат");
    }

    void cbFontStyleConfig() {
        cbbFontStyle.getItems().addAll(Font.getFontNames());
        cbbFontStyle.setValue("Roboto");
    }

    public void draw() {
        cbShapesSelect();
    }

    void cbShapesSelect() {
        btnAddShape.setOnMouseClicked(event -> {
            item = cbShapes.getSelectionModel().getSelectedItem();
            Shape shape;
            double borderSize = sldBorderSize.getValue();
            double opacity = sldOpacity.getValue();
            switch (item) {
                case "Квадрат":
                    shape = tools.square(cpFillColor.getValue(), cpBorderColor.getValue(), 10., 10., 100.0, 100.0, borderSize, opacity);
                    DragResizeMod.makeResizable(shape);
                    mainPane.getChildren().addAll(shape);
                    break;
                case "Треугольник":
                    mainPane.setOnMouseClicked(event1 -> {
                        positionX = event1.getX();
                        positionY = event1.getY();
                        mainPane.setOnMouseClicked(event2 -> {
                            double p2x = event2.getX();
                            double p2y = event2.getY();
                            mainPane.setOnMouseClicked(event3 -> {
                                Shape shape1;
                                double p3x = event3.getX();
                                double p3y = event3.getY();
                                shape1 = tools.triangle(cpFillColor.getValue(), cpBorderColor.getValue(), positionX, positionY, p2x, p2y, p3x, p3y, borderSize, opacity);
                                MouseControlUtil.makeDraggable(shape1);
                                mainPane.getChildren().addAll(shape1);
                                mainPane.setOnMouseClicked(null);
                            });
                        });
                    });
                    break;
                case "Звезда":
                    mainPane.setOnMouseClicked(event1 -> {
                        positionX = event1.getX();
                        positionY = event1.getY();
                        mainPane.setOnMouseClicked(event2 -> {
                            Shape shape1;
                            double p2x = event2.getX();
                            double p2y = event2.getY();
                            shape1 = tools.star(cpFillColor.getValue(), cpBorderColor.getValue(), positionX, positionY, p2x, p2y, borderSize, opacity);
                            MouseControlUtil.makeDraggable(shape1);
                            mainPane.getChildren().addAll(shape1);
                            mainPane.setOnMouseClicked(null);
                        });
                    });
                    break;
                case "Круг":
                    shape = tools.ellipse(cpFillColor.getValue(), cpBorderColor.getValue(), 10., 10., 100.0, borderSize, opacity);
                    DragResizeMod.makeResizable(shape);
                    mainPane.getChildren().addAll(shape);
                    break;
                case "Линия":
                    mainPane.setOnMouseClicked(event1 -> {
                        double p1x = event1.getX();
                        double p1y = event1.getY();
                        mainPane.setOnMouseClicked(event2 -> {
                            Shape shape1;
                            double p2x = event2.getX();
                            double p2y = event2.getY();
                            shape1 = tools.line(sldBorderSize.getValue(), cpBorderColor.getValue(), p1x, p1y, p2x, p2y, sldOpacity.getValue());
                            mainPane.getChildren().addAll(shape1);
                            positionX = p2x;
                            positionY = p2y;
                            MouseControlUtil.makeDraggable(shape1);
                            mainPane.setOnMouseClicked(null);
                        });
                    });
                    break;
                case "Текст":
                    mainPane.setOnMouseClicked(event1 -> {
                        Shape shape1;
                        double p1x = event1.getX();
                        double p1y = event1.getY();
                        shape1 = tools.text(tfTextString.getText(), tfFontSize.getText(), cpTextColor.getValue(), cbbFontStyle.getValue(), p1x, p1y, opacity);
                        MouseControlUtil.makeDraggable(shape1);
                        mainPane.getChildren().addAll(shape1);
                        mainPane.setOnMouseClicked(null);
                    });
                    break;
                case "Рисунок":
                    shape = tools.image(cpFillColor.getValue(), cpBorderColor.getValue(), 0.0, 0.0, 100.0, 100.0, borderSize, opacity);
                    DragResizeMod.makeResizable(shape);
                    mainPane.getChildren().addAll(shape);
                    break;
                case "Фоновый цвет":
                    double r, g, b;
                    r = cpFillColor.getValue().getRed();
                    g = cpFillColor.getValue().getGreen();
                    b = cpFillColor.getValue().getBlue();
                    mainPane.setBackground(new Background(new BackgroundFill(Color.color(r, g, b), CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
            }
        });
    }


    public void onExit() {
        Platform.exit();
    }

    public void saveFile() {
        btnSave.setOnMouseClicked(event1 -> {
            fileService.saveFile(mainPane);
        });
    }

    public void newProject() {
        mainPane.setPrefSize(1100, 600);
        mainPane.getChildren().clear();
        mainPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        cbLayer.getItems().clear();

    }

}