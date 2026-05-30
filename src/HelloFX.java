import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox; //vertical, horizontal box UI containers
import javafx.scene.layout.HBox;
import javafx.animation.Timeline; //updates game
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.text.Font;// so we can have nice font




public class HelloFX extends Application {

    Pet pet = new Pet("Mochi");
    NeedsQueue needsQueue = new NeedsQueue();
    ActionStack actionStack = new ActionStack();
    ActionHistory actionHistory = new ActionHistory();
    Shop shop = new Shop();
    Label tokenLabel = new Label("Tokens: 0");

    SoundManager soundManager = new SoundManager();

    //creating instances of progress bars
    ProgressBar hungerBar = new ProgressBar();
    ProgressBar thirstBar = new ProgressBar();
    ProgressBar energyBar = new ProgressBar();
    ProgressBar happinessBar = new ProgressBar();

    private Label styledLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Georgia", 14));
        return label; //changes font to georgia that's why the labels are listed as
        //"styled label"
    }

    Label statusLabel = new Label("Your cat is happy!");
    Label historyLabel =  new Label("Action history will appear here.");

    public void start(Stage stage) {
        Image happyCat = new Image(new java.io.File("images/cat_happy.png").toURI().toString());;
        //found this format online to import images
        ImageView catView = new ImageView(happyCat);
        catView.setFitWidth(200);
        catView.setFitHeight(200);

        soundManager.playMusic("sounds/cafe_music.mp3");

        Label hungerLabel = styledLabel("Hunger");
        Label thirstLabel = styledLabel("Thirst");
        Label happinessLabel = styledLabel("Happiness");
        Label energyLabel = styledLabel("Energy");

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

        Button muteBtn = new Button("Mute");

        //button actions below:

        feedBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.feed();
                actionStack.pushAction("feed");
                actionHistory.addAction("Fed the pet");
                needsQueue.resolveNeed();
                updateUI();
            }
        });

        waterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.giveWater();
                actionStack.pushAction("giveWater");
                actionHistory.addAction("Gave water");
                needsQueue.resolveNeed();
                updateUI();
            }
        });

        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.play();
                actionStack.pushAction("play");
                actionHistory.addAction("Played with pet");
                needsQueue.resolveNeed();
                updateUI();
            }
        });

        sleepBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.sleep();
                actionStack.pushAction("sleep");
                actionHistory.addAction("Put pet to sleep");
                needsQueue.resolveNeed();
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
        undoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String result = actionStack.undo(pet);
                actionHistory.addAction("UNDO: " + result);
                updateUI();
            }
        });

        muteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (soundManager.isMuted()) {
                    soundManager.unmute();
                    muteBtn.setText("Mute");
                } else {
                    soundManager.mute();
                    muteBtn.setText("Unmute");
                }
            }
        });

        VBox statBox = new VBox(5, hungerLabel, hungerBar, thirstLabel, thirstBar, happinessLabel,
                happinessBar, energyLabel, energyBar, tokenLabel); //label & bar container


        HBox buttonBox = new HBox(10, feedBtn, playBtn, sleepBtn, treatBtn, waterBtn, undoBtn);
        HBox muteBox = new HBox(muteBtn);
        muteBox.setStyle("-fx-alignment: center;");
// where the buttons will display


        VBox mainLayout = new VBox(20, catView, statusLabel, statBox, buttonBox, muteBox, historyLabel);
        mainLayout.setStyle("-fx-padding:20, -fx-alignment:center;");
        mainLayout.setStyle("-fx-background-color: #fff8f0; -fx-padding: 20; -fx-alignment: center;");
        hungerBar.setStyle("-fx-accent: #f4a7b9;");
        thirstBar.setStyle("-fx-accent: #c9a7f4;");
        energyBar.setStyle("-fx-accent: #f4c7a7;");
        happinessBar.setStyle("-fx-accent: #f4a7d4;"); //set to pastels & cream colors
        String btnStyle = "-fx-background-color: #f9c6d0; -fx-font-family: Georgia; -fx-font-size: 13; -fx-background-radius: 20; -fx-border-radius: 20; -fx-text-fill: #a0536a;";
        feedBtn.setStyle(btnStyle);
        waterBtn.setStyle(btnStyle);
        playBtn.setStyle(btnStyle);
        sleepBtn.setStyle(btnStyle);
        treatBtn.setStyle(btnStyle);
        undoBtn.setStyle(btnStyle); //changed font to georgia... it's prettier
        muteBtn.setStyle(btnStyle);
        statusLabel.setStyle("-fx-text-fill: #a0536a; -fx-font-family: Georgia; -fx-font-size: 18;");
        historyLabel.setStyle("-fx-text-fill: #b07080; -fx-font-family: Georgia; -fx-font-size: 12;");

        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.decayStats();
                needsQueue.checkNeeds(pet);
                shop.earnTokens(pet);
                updateUI();
            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        Scene scene = new Scene(mainLayout, 500, 600);
        stage.setTitle("tamagotchi :3");
        stage.setScene(scene);
        stage.show();



    }
    private void updateUI() {
        hungerBar.setProgress(pet.getHunger() / 100.0);
        thirstBar.setProgress(pet.getThirst() / 100.0);
        happinessBar.setProgress(pet.getHappiness() / 100.0);
        energyBar.setProgress(pet.getEnergy() / 100.0);
        historyLabel.setText(actionHistory.getRecentActions(5));
        tokenLabel.setText("Tokens: " + shop.getTokens());

        if (needsQueue.hasNeeds()) {
            statusLabel.setText("Your pet is " + needsQueue.getNextNeed() + "!");
        } else {
            statusLabel.setText("Your pet is happy!");
        }
    }




public static void main(String[] args) {
        launch(args);
    }
}
