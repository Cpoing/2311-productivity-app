package com.example.Components;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;

public class MusicPlayer extends VBox {
    private MediaPlayer mediaPlayer;
    private Button chooseMusicButton;
    private Button playButton;
    private Button pauseButton;
    private Button stopButton;

    public MusicPlayer() {


        // Create buttons
        chooseMusicButton = new Button("Choose Music");
         playButton = new Button("Play");
        pauseButton = new Button("Pause");
        stopButton = new Button("Stop");

        // Set event handlers for buttons
        chooseMusicButton.setOnAction(event -> chooseMusic());
        playButton.setOnAction(event -> mediaPlayer.play());
        pauseButton.setOnAction(event -> mediaPlayer.pause());
        stopButton.setOnAction(event -> mediaPlayer.stop());


        // Initially disable playback buttons
        playButton.setDisable(true);
        pauseButton.setDisable(true);
        stopButton.setDisable(true);

        // Add buttons to the VBox
        getChildren().addAll(chooseMusicButton,playButton, pauseButton, stopButton);

        Image image = new Image(getClass().getResource("headphones-png-20177.jpg").toExternalForm());
        ImageView imageView = new ImageView(image);
        getChildren().add(imageView);
    }

    //allow user to choose a music file from their computer
    private void chooseMusic() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Music File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Media media = new Media(selectedFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            // Enable playback buttons
            playButton.setDisable(false);
            pauseButton.setDisable(false);
            stopButton.setDisable(false);
        }
    }
//stop the music when gui closed

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }


    public void onClose() {
        // Stop music playback
        stopMusic();
    }
}