package com.spring.paint.service.Impl;


import com.spring.paint.service.FileService;
import com.spring.paint.service.ShapeService;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
@RequiredArgsConstructor
public class ShapeServiceImpl implements ShapeService {
    private final FileService fileService;

    @Override
    public Rectangle square(Color fillColor, Color strokeColor, double posX, double posY, double x,
                            double y, double lineSize, double opacity) {
        Rectangle rectangle;
        rectangle = new Rectangle(posX, posY, x, y);
        rectangle.setStrokeWidth(lineSize);
        rectangle.setFill(fillColor);
        rectangle.setStroke(strokeColor);
        rectangle.setOpacity(opacity / 100);
        return rectangle;
    }

    @Override
    public Rectangle image(Color fillColor, Color strokeColor, double posX, double posY, double x, double y,
                           double lineSize, double opacity) {
        Rectangle rectangle;
        rectangle = new Rectangle(posX, posY, x, y);
        rectangle.setStrokeWidth(lineSize);
        rectangle.setFill(new ImagePattern(fileService.loadFile()));
        rectangle.setStroke(strokeColor);
        rectangle.setOpacity(opacity / 100);
        return rectangle;
    }

    @Override
    public Rectangle ellipse(Color fillColor, Color strokeColor, double posX, double posY, double x,
                             double lineSize, double opacity) {
        Rectangle circle = new Rectangle(posX, posY, x, x);
        circle.setStrokeWidth(lineSize);
        circle.setFill(fillColor);
        circle.setStroke(strokeColor);
        circle.setArcHeight(100000000);
        circle.setArcWidth(100000000);
        circle.setOpacity(opacity / 100);

        return circle;
    }

    @Override
    public Line line(double lineSize, Color strokeColor, double posX, double posY, double p2x, double p2y,
                     double opacity) {
        Line line = new Line(posX, posY, p2x, p2y);
        line.setStrokeWidth(lineSize);
        line.setStroke(strokeColor);
        line.setOpacity(opacity / 100);
        return line;
    }

    @Override
    public Text text(String string, String textSize, Color textColor, String fontName, double posX,
                     double posY, double opacity) {
        Text text = new Text(posX, posY, string);
        double fs = Double.parseDouble(textSize);
        text.setFont(new Font(fontName, fs));
        text.setFill(textColor);
        text.setOpacity(opacity / 100);
        return text;
    }

    @Override
    public Shape triangle(Color fillColor, Color strokeColor, double posX, double posY, double pos2X, double pos2Y, double pos3X, double pos3Y, double lineSize, double opacity) {

        Polygon triangle = new Polygon();
        triangle.setStrokeWidth(lineSize);
        triangle.setFill(fillColor);
        triangle.setStroke(strokeColor);
        triangle.setOpacity(opacity);
        triangle.getPoints().addAll(posX, posY,
                pos2X, pos2Y,
                pos3X, pos3Y);
        return triangle;
    }

    @Override
    public Shape star(Color fillColor, Color strokeColor, double posX, double posY, double pos2X,
                      double pos2Y, double lineSize, double opacity) {
        double angle = 2 * Math.PI / 5;
        double radius = Math.sqrt(Math.pow(pos2X - posX, 2) + Math.pow(pos2Y - posY, 2));
        double[] starPoints = new double[10];
        for (int i = 0, j = 1; i < 5; i++) {
            double currentAngle = angle * i;
            double x = posX + radius * Math.sin(currentAngle);
            double y = posY - radius * Math.cos(currentAngle);
            if (i < 3) {
                starPoints[4 * i] = x;
                starPoints[4 * i + 1] = y;
            } else {
                starPoints[2 * j] = x;
                starPoints[2 * j + 1] = y;
                j += 2;
            }
        }
        Polygon star = new Polygon(starPoints);
        star.setStrokeWidth(lineSize);
        star.setFill(fillColor);
        star.setStroke(strokeColor);
        star.setOpacity(opacity);
        return star;
    }

}



