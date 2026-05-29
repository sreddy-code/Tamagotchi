import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class gameUI extends Application {

    Pet pet = new Pet("Mochi");
    NeedsQueue needsQueue = new NeedsQueue();
    ActionStack actionStack = new ActionStack();
    ActionHistory actionHistory = new ActionHistory();

    //creating instances of progress bars
    ProgressBar hungerBar = new ProgressBar();
    ProgressBar thirstBar = new ProgressBar();
    ProgressBar energyBar = new ProgressBar();
    ProgressBar happinessBar = new ProgressBar();

    Label statusLabel = new Label("Your cat is happy!");
    Label historyLabel = new Label("Action history will appear here.");

    public void start(Stage stage) {
        Image happyCat = new Image("images/cat_happy.png");
        ImageView catView = new ImageView(happyCat);
        catView.setFitWidth(200);
        catView.setFitHeight(200);

        Label hungerLabel = new Label("Hunger");
        Label thirstLabel = new Label("Thirst");
        Label happinessLabel = new Label("Happiness");
        Label energyLabel = new Label("Energy");

        hungerBar.setProgress(0.8);
        thirstBar.setProgress(0.8);
        energyBar.setProgress(0.8);
        happinessBar.setProgress(0.8);

        Button feedBtn = new Button("Feed");
        Button waterBtn = new Button("Give water");
        Button playBtn = new Button("Play");
        Button sleepBtn = new Button("Sleep");
        Button treatBtn = new Button("Treat sickness");
        Button undoBtn = new Button("Undo");

        //button actions below:

        feedBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.feed();
                actionStack.pushAction("feed");
                actionHistory.addAction("Fed the pet");
                updateUI();
            }
        });

        waterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.giveWater();
                actionStack.pushAction("giveWater");
                actionHistory.addAction("Gave water");
                updateUI();
            }
        });

        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.play();
                actionStack.pushAction("play");
                actionHistory.addAction("Played with pet");
                updateUI();
            }
        });

        sleepBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.sleep();
                actionStack.pushAction("sleep");
                actionHistory.addAction("Put pet to sleep");
                updateUI();
            }
        });

        treatBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.treatSickness();//add treat sickness method bc i dont have one
                actionStack.pushAction("treat");
                actionHistory.addAction("Treated sickness");
                updateUI();
            }
        });

    }
    private void updateUI(){
        hungerBar.setProgress(pet.getHunger() / 100.0);
        thirstBar.setProgress(pet.getThirst() / 100.0);
        happinessBar.setProgress(pet.getHappiness() / 100.0);
        energyBar.setProgress(pet.getEnergy() / 100.0);
        historyLabel.setText(actionHistory.getRecentActions(5));

        if (needsQueue.hasNeeds()) {
            statusLabel.setText("Your pet is " + needsQueue.getNextNeed() + "!");
        } else {
            statusLabel.setText("Your pet is happy!");
        }
    }

}








