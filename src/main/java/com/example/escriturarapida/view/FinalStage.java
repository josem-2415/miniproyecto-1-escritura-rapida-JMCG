package com.example.escriturarapida.view;

import com.example.escriturarapida.controller.FinalController;
import com.example.escriturarapida.model.GameModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents the final game view.
 */
public class FinalStage {
    public FinalStage(Stage stage, GameModel gameState){
        try{
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/escriturarapida/FXML/final-view.fxml")
            );
            Parent root = loader.load();
            FinalController controller = loader.getController();
            controller.setResults(gameState);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

