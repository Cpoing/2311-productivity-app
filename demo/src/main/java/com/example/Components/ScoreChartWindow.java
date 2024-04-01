package com.example.Components;

import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;


public class ScoreChartWindow extends Stage {
    private ScoreCounter scores;

    public ScoreChartWindow(ScoreCounter scores) {
        this.scores = scores;
        init();
    }

    private void init() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time/s");
        xAxis.setAnimated(false);
        yAxis.setLabel("Value");
        yAxis.setAnimated(false);

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Scores over Time");
        lineChart.setAnimated(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        lineChart.getData().add(series);

        Scene scene = new Scene(lineChart, 800, 600);
        setScene(scene);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");

        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Integer random = scores.getCounter();

            Platform.runLater(() -> {
                Date now = new Date();
                series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random));
            });
        }, 0, 1, TimeUnit.SECONDS);
    
}
}