//package nomads;
//
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//
//import java.io.FileNotFoundException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//
//public class SDestController {
//    @FXML
//    private ButtonBar ButtonBar;
//    @FXML
//    private Button SearchDestButton;
//
//    @FXML
//    private AnchorPane anchorPane;
//
//    @FXML
//    private Button favButton;
//
//    @FXML
//    private ListView<?> listView;
//
//    @FXML
//    private Button quitButton;
//
//    @FXML
//    private Button updateUserButton;
//
//    @FXML
//    void onFavButtonClicked(ActionEvent event) {
//
//    }
//
//    @FXML
//    void onQuitButtonClicked(ActionEvent event) {
//        Stage stage = (Stage) quitButton.getScene().getWindow();
//        stage.close();
//    }
//
//    @FXML
//    void onSearchDestClicked(ActionEvent event) {
//        ArrayList<Country> countries;
//        ListView mylistview = new ListView<>();
//
//
//        AnchorPane anchorPane;
//        DataProcessor datap;
//        User user;
//        VBox vBox;
//        final String[] target = {"United States", "Bangladesh", "Australia", "Japan", "Brazil", "Spain", "Mexico", "Thailand", "Sweden", "Morocco", "China"};
//
//            datap = new DataProcessor();
//            HashMap hashMap = datap.generateVisaInfo(user);
//            datap.generateCountries(hashMap);
//
//
//
//
//        protected void generatelistview (String[]for (Country s : countries) {
//            mylistview.getItems().add(s);
//
//        }
//
//
//    }
//
//
//    protected void displayCountries(Stage stage) {
//        Scene scenedis = new Scene(new Group());
//        stage.setTitle("SearchDestCountriesfromList");
//        stage.setHeight(400);
//        stage.setWidth(600);
//
//        Label countrylistlabel = new Label("List of Destinations");
//        countrylistlabel.setFont(new Font("Times", 20));
//
//        generatelistview(target);
//        handleItemClicks();
//        vBox.setSpacing(5);
//        vBox.setPadding(new Insets(10, 0, 0, 10));
//        vBox.getChildren().addAll(countrylistlabel, mylistview);
//        vBox.setAlignment(Pos.CENTER);
//        Group group = ((Group) scenedis.getRoot());
//        group.getChildren().addAll(vBox);
//        group.setLayoutX(100);
//
//        stage.setScene(scenedis);
//        stage.show();
//
//
//    }
//
//    private void handleItemClicks() {
//        mylistview.setOnMouseClicked(mouseEvent -> {
//            String selectedItem = mylistview.getSelectionModel().getSelectedItem().toString();
//            Dialog d = new Alert(Alert.AlertType.INFORMATION, selectedItem);
//            d.show();
//        });
//    }
//
//
//    @FXML
//    void onUpdateUserClicked(ActionEvent event) {
//
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
///*
//    void displayCountries(ArrayList<Country> countries){
//        for (int i = 0; i < countries.size(); i++) {
//            Button nation = new Button(Country.name.toUpperCase());
//            gridPane.add(nation,i,i,i,i);
//
//
//        }
//
// */
//
//
