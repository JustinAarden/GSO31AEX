/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso31aex;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.GroupBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ScrollPaneBuilder;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Justin
 */
public class Gso31aex extends Application {
    
     @Override
  public void start(Stage stage) {
    
    String message = 
      "(-.(-.-(-.-)-.-).-) They see me scrolling, they Hating (-.(-.-(-.-)-.-).-)";

    // Reference to the Text
    Text textRef = TextBuilder.create()
      .layoutY(100)
      .textOrigin(VPos.TOP)
      .textAlignment(TextAlignment.JUSTIFY)
      .wrappingWidth(8000)
      .text(message)
      .fill(Color.rgb(255, 0, 0))
      .font(Font.font("SansSerif", FontWeight.BOLD, 24))
      .build();

     // Provides the animated scrolling behavior for the text
    TranslateTransition transTransition = TranslateTransitionBuilder.create()
      .duration(new Duration(7000))
            .node(textRef)
      .toX(-820)
      .interpolator(Interpolator.LINEAR)
      .cycleCount(Timeline.INDEFINITE)
      .build();

    
    Scene scene  = SceneBuilder.create()
      .width(516)
      .height(60)
      .root(
        GroupBuilder.create()
          .children(
            ImageViewBuilder.create()
              
              .build(),
            ScrollPaneBuilder.create()
              .layoutX(0)
              .layoutY(10)
              .prefWidth(516)
              .prefHeight(50)
              .hbarPolicy(ScrollBarPolicy.NEVER)
              .vbarPolicy(ScrollBarPolicy.NEVER)
              .pannable(true)
              .content(textRef)
              .style("-fx-background-color: white;")
              .build()
          )
          .build()
      )
      .build();

    stage.setScene(scene);
    stage.setTitle("AEX - RYAN - JUSTIN");
    stage.show();
    
    // Start the text animation
    transTransition.play();
  }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gso31aex.launch();

    }

    
}
