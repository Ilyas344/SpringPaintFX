package com.spring.paint.controller;

import com.jfoenix.controls.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component

public class PaintUIController2 implements Initializable {



    @FXML
    public Label label;
    @FXML
    public Label label2;

    @FXML
    public Button button;
    @FXML
    public Button button2;
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
    private JFXSlider sldEffectBlur;

    @FXML
    private JFXSlider sldEffectShadow;

    @FXML
    private JFXTextField tfTextString;

    @FXML
    private JFXTextField tfFontSize;

    @FXML
    private JFXColorPicker cpTextColor;

    @FXML
    private JFXComboBox cbbFontStyle;

    @FXML
    private ScrollPane spMain = new ScrollPane();

    @FXML
    private JFXTextField tfCanvaW;

    @FXML
    private JFXTextField tfCanvaH;

  //  @FXML
  //  private JFXButton btnResize =  new JFXButton();

 //   @FXML
 //   private JFXButton btnClear =  new JFXButton();

 //   @FXML
 //   private Pane mainPane = new Pane();

  //  @FXML
  //  private JFXComboBox cbLayer = new JFXComboBox();

  //  @FXML
  //  private JFXButton btnShapeRemove;

    @FXML
    private JFXTextField tfShapeRotation;

   // @FXML
   // private JFXButton btnShapeUpdate = new JFXButton();

   // @FXML
  //  private JFXButton btnAddShape = new JFXButton();

  //  @FXML
  //  private JFXButton btnSwapUp = new JFXButton();

    // Var
    public GraphicsContext gc;
    private double positionX = 0;
    private double positionY = 0;
    private String item;
    static interface MouseContext {
        public void config(MouseEvent m, GraphicsContext ctx);
    }

    @FXML
    public void initialize() {
        newProject();
        saveFile();
        onExit();
        aboutWindow();

    }
    public   void  newProject(){
        System.out.println("saveFile");
    }
    public void saveFile(){
        System.out.println("saveFile");
    }
    public void onExit(){
        System.out.println("onExit");
    }
    public void aboutWindow(){
        System.out.println("aboutWindow");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

