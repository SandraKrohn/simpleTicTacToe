package MyApp;
import MyApp.Controller.MainViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

// erbt immer von Application
public class MyApp extends Application {
    // singleton: Klasse ist von ueberall erreichbar. Es wird sichergestellt, dass nur eine Instanz existiert
    // noch mal zur Erinnerung: static = objektunabhaengig

    public static MyApp instance;

    BorderPane rootLayout;
    Stage primaryStage;

    // dies ist quasi die Main
    public static void run(String[] args) {
        // diese Methode ist in Application enthalten
        launch(args);
    }

    // 1. Schritt - Stage erstellen
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        instance = this;

        primaryStage.setTitle("Hier koennte Ihre Werbung stehen!");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        // verhindert Aenderungen in der Groesse (auch Maximieren)
        primaryStage.setResizable(false);

        setIcon();
        initRootLayout();

        // .show() macht die Stage sichtbar
        primaryStage.show();
    }

    // 2. Schritt - Icon setzen
    void setIcon() {
        InputStream iconStream = getClass().getResourceAsStream("/images/pokeball.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MyApp/View/MainView.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // laedt andere Views in das Center vom BorderPane (RootLayout)
    public void loadView(String newView) throws IOException {
        String viewPath = "/MyApp/View/" + newView + ".fxml";

        // loescht bestehendes Center
        rootLayout.getChildren().remove(rootLayout.getCenter());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewPath));

        Pane pane = loader.load();
        rootLayout.setCenter(pane);
    }

    /* 3. Scene mit Nodes erstellen - aber nicht so...
    void createScene() {
        Label helloWorldLabel = new Label("Hello World!");
        // Pos. = static Klasse; BASELINE_CENTER = Konstante
        helloWorldLabel.setAlignment(Pos.BASELINE_CENTER);

        // neue Scene erstellen, dann in der Scene die primayScene setzen
        Scene primaryScene = new Scene(helloWorldLabel);
        primaryStage.setScene(primaryScene);
    } */

    /* 4. ...sondern lieber so
    void loadScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        // "welche Datei soll geladen werden?" -> im Ordner MyApp anfangen
        loader.setLocation(getClass().getResource("/MyApp/View/MainView.fxml"));
        // Controller der Datei von eben laden
        MainViewController controller = loader.getController();

        BorderPane pane = loader.load();

        Scene primaryScene = new Scene(pane);
        primaryStage.setScene(primaryScene);
    } */

    public void shutdown() {
        Platform.exit();
        System.exit(0);
    }
}
