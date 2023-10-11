package com.spring.paint.config;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.net.URL;


@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final String applicationTitle;

    private final Resource fxml;
    private final ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            @Value("${java.fx.fxml}") Resource fxml,
                            ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.fxml = fxml;
        this.applicationContext = applicationContext;
    }

    public Scene scene(Parent root, int v, int v1) {
        return new Scene(root, v, v1);
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            Stage stage = event.getStage();
            URL url = this.fxml.getURL();
            stage.setMaximized(true);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent mainFXML = fxmlLoader.load();
            Scene scene = scene(mainFXML, 640, 480);
            scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Roboto");

            stage.setScene(scene);
            stage.setTitle(this.applicationTitle);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
