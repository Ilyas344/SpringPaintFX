package com.spring.paint.service;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public interface ShapeService {
    Rectangle square(Color fillColor, Color strokeColor, double posX, double posY, double x, double y,
                     double lineSize, double opacity);

    Rectangle image(Color fillColor, Color strokeColor, double posX, double posY, double x, double y,
                    double lineSize, double opacity);

    Rectangle ellipse(Color fillColor, Color strokeColor, double posX, double posY, double x, double lineSize,
                      double opacity);

    Line line(double lineSize, Color strokeColor, double posX, double posY, double p2x, double p2y,
              double opacity);

    Text text(String string, String textSize, Color textColor, String fontName, double posX,
              double posY, double opacity);

    Shape triangle(Color fillColor, Color strokeColor, double posX, double posY, double pos2X, double pos2Y,
                   double pos3X, double pos3Y, double lineSize, double opacity);

    Shape star(Color fillColor, Color strokeColor, double posX, double posY, double pos2X, double pos2Y,
               double lineSize, double opacity);


}
