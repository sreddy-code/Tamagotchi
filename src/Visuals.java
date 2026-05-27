import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private int hunger = 70;
    private int happiness = 70;
    private int energy = 70;

    private final Label petLabel = new Label();
    private final Label statusLabel = new Label();
    private final ProgressBar hungerBar = new ProgressBar();
    private final ProgressBar happinessBar = new ProgressBar();
    private final ProgressBar energyBar = new ProgressBar();

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("Tamagotchi");
        titleLabel.setFont(Font.font(28));

        petLabel.setFont(Font.font(72));

        Button feedButton = new Button("Feed");
        Button playButton = new Button("Play");
        Button sleepButton = new Button("Sleep");

        feedButton.setOnAction(event -> {
            hunger += 15;
            energy -= 5;
            updateScreen();
        });

        playButton.setOnAction(event -> {
            happiness += 15;
            hunger -= 8;
            energy -= 10;
            updateScreen();
        });

        sleepButton.setOnAction(event -> {
            energy += 20;
            hunger -= 5;
            updateScreen();
        });

        VBox statsBox = new VBox(
                8,
                createStatRow("Food", hungerBar),
                createStatRow("Happy", happinessBar),
                createStatRow("Energy", energyBar)
        );
        statsBox.setMaxWidth(280);

        HBox buttonsBox = new HBox(10, feedButton, playButton, sleepButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(18, titleLabel, petLabel, statusLabel, statsBox, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(24));
        root.setStyle("-fx-background-color: #f7f3e8;");

        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            hunger -= 4;
            happiness -= 3;
            energy -= 2;
            updateScreen();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        updateScreen();

        Scene scene = new Scene(root, 420, 460);
        stage.setTitle("Tamagotchi");
        stage.setScene(scene);
        stage.show();
    }

    private HBox createStatRow(String name, ProgressBar bar) {
        Label label = new Label(name);
        label.setMinWidth(60);
        bar.setPrefWidth(190);

        HBox row = new HBox(10, label, bar);
        row.setAlignment(Pos.CENTER);
        return row;
    }

    private void updateScreen() {
        hunger = clamp(hunger);
        happiness = clamp(happiness);
        energy = clamp(energy);

        hungerBar.setProgress(hunger / 100.0);
        happinessBar.setProgress(happiness / 100.0);
        energyBar.setProgress(energy / 100.0);

        if (hunger == 0 || happiness == 0 || energy == 0) {
            petLabel.setText(":(");
            statusLabel.setText("Your pet needs help!");
        } else if (hunger < 30) {
            petLabel.setText(":o");
            statusLabel.setText("Your pet is hungry.");
        } else if (energy < 30) {
            petLabel.setText("-_-");
            statusLabel.setText("Your pet is sleepy.");
        } else if (happiness < 30) {
            petLabel.setText(":|");
            statusLabel.setText("Your pet wants to play.");
        } else {
            petLabel.setText(":)");
            statusLabel.setText("Your pet is doing great!");
        }
    }

    private int clamp(int value) {
        if (value < 0) {
            return 0;
        }
        if (value > 100) {
            return 100;
        }
        return value;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
