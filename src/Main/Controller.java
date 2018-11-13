package Main;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;


public class Controller {
    @FXML
    private TextField left;
    @FXML
    private TextField right;
    @FXML
    private TextField result;
    @FXML
    private Button confirm;
    @FXML
    private TextArea vertical;
    @FXML
    private TextArea number;
    @FXML
    private ChoiceBox<String> operator;
    @FXML
    private ChoiceBox<String> method;
    @FXML
    private ListView<String> example;
    @FXML
    private TableView<Result> history;
    @FXML
    private TableColumn<Result,String> historyMethod;
    @FXML
    private TableColumn<Result,Float>historyTime;

    private Equation equation;
    private Main main;
    @FXML
    public void initialize(){
        operator.setItems(FXCollections.observableArrayList("+","-","*","÷"));
        operator.setValue("+");
        method.setItems(FXCollections.observableArrayList("deep first search","breadth first search","genetic"));
        method.setValue("deep first search");
        ObservableList<String> examples=FXCollections.observableArrayList();
        examples.add("FIVE + FOUR = NINE");
        examples.add("SIX * TWO = TWELVE");
        example.setItems(examples);

    }
    @FXML
    private void onExampleSelect(MouseEvent event){
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount()==2){
                MultipleSelectionModel<String> selectionModel=example.getSelectionModel();
                String selected=selectionModel.getSelectedItem();
                String[] parts=selected.split(" ");
                left.setText(parts[0]);
                right.setText(parts[2]);
                operator.setValue(parts[1]);
                result.setText(parts[4]);
            }
        }

    }
    @FXML
    private void onEquationConfirmed(Event event){
        if(!left.getText().isEmpty() & !right.getText().isEmpty() & !operator.getValue().isEmpty() & !result.getText().isEmpty()){
            equation=new Equation(left.getText().toUpperCase(),right.getText().toUpperCase(),operator.getValue(),result.getText().toUpperCase());
            if(!equation.isLegal()){
                Alert alert=new Alert(Alert.AlertType.ERROR,"表达式中字母个数多于10");
                alert.showAndWait();
            }
            else{
                String string=equation.toString();
                this.vertical.setText(string);
            }
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"未输入完整的表达式");
            alert.showAndWait();
        }
    }

    @FXML
    private void onCalculationStarted(Event event){
        if(vertical.getText().isEmpty()){
            Alert alertForSearch=new Alert(Alert.AlertType.ERROR,"未确认等式");
            alertForSearch.showAndWait();
        }
        else{
            number.setText("Processing...");
            switch (method.getValue()){
                case "deep first search":
                    equation.deepFirstSolver();break;
                case "breadth first search":
                    equation.breadthFirstSolver();break;
                case "genetic":
                    equation.geneticSolver();break;

                    default:
                        new Alert(Alert.AlertType.ERROR,"未选定方法");

            }
            number.setText(equation.outputResult());
        }
    }
    @FXML
    private void onAboutSelected(Event event){
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"竖式猜数字\n代港凡-2015012502");
        alert.setTitle("关于");
        alert.setHeaderText("关于");
        alert.show();

    }

    public void setMain(Main main){
        this.main=main;
    }

}
