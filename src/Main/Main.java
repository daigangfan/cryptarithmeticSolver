package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent root =loader.load();
        Controller controller=loader.getController();

        controller.setMain(this);
        primaryStage.setTitle("竖式猜数字");
        primaryStage.setScene(new Scene(root, 800,600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
