package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Dakar;
import sample.model.DakarDAO;
import sample.utils.Validation;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField team_name;
    @FXML
    private TextField name_surname;
    @FXML
    private ComboBox<String> members;
    @FXML
    private CheckBox saudi_A_C;
    @FXML
    private CheckBox france_TV;
    @FXML
    private CheckBox aqua;
    @FXML
    private RadioButton toyota;
    @FXML
    private RadioButton mini;
    @FXML
    private RadioButton peugeot;
    @FXML
    private TableColumn id_colmn;
    @FXML
    private TableColumn team_colmn;
    @FXML
    private TableColumn name_colmn;
    @FXML
    private TableColumn sponsors_colmn;
    @FXML
    private TableColumn cars_colmn;
    @FXML
    private TableColumn members_colmn;
    @FXML
    private TableView dakar_table;
    @FXML
    private Label error_label;


    ObservableList<Dakar> list = FXCollections.observableArrayList();
    ObservableList<String> memberList = FXCollections.observableArrayList("1", "2", "3", "4", "5");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        members.setItems(memberList);
    }

    public void onCreateButtonClick() {

        error_label.setVisible(true);
        String teamName = team_name.getText();
        String nameSurname = name_surname.getText();
        String sponsors = "";
        String cars = "";
        // jeigu paliekamas tuscias langas
        if (teamName.equals("")) {
            error_label.setText("Privalomas Komandos Pavadinimas");
            //jeigu komandos pavadinimas yra jau uzregistruotas
        } else if (DakarDAO.teamName(teamName)) {
            error_label.setText("Komanda su tokiu pavadinimu jau yra");
            //jeigu paliekas tuscias nameSurname langas
        } else if (nameSurname.equals("")) {
            error_label.setText("Privalomas Vardas ir pavarde");
        } else {

            if (saudi_A_C.isSelected()) sponsors = sponsors + saudi_A_C.getText() + ",";
            if (france_TV.isSelected()) sponsors = sponsors + france_TV.getText() + ",";
            if (aqua.isSelected()) sponsors = sponsors + aqua.getText();

            if (toyota.isSelected()) cars = toyota.getText();
            else if (mini.isSelected()) cars = mini.getText();
            else if (peugeot.isSelected()) cars = peugeot.getText();

            //jeigu nepasirinko masinos
            if (cars.equals("")) {
                error_label.setText("Butina pasirinkti transporta");
            } else {

                Dakar dakar = new Dakar(teamName, nameSurname, sponsors, cars, Integer.parseInt(members.getValue()));
                DakarDAO.insert(dakar);
            }
        }
    }

    public void onSearchButtonClick() {

        // tam kad issivalytume anksciau buvusi sarasa
        list.clear();

        //TODO: Gauti sarasa is duomenu bazes
        List<Dakar> dakarList = new ArrayList<>();
        dakarList = DakarDAO.viewAll();

        for (Dakar dakar : dakarList) {
            //Is duomenu bazes saraso elementus sudedame i ObservableList (kad juos galetume matyti GUI)
            list.add(new Dakar(dakar.getId(), dakar.getTeamName(), dakar.getNameSurname(), dakar.getSponsor(), dakar.getRacingCars(), dakar.getMembers()));

            //Priskirsime lenteles stulpelems reiksmes is duomenu bazes
            id_colmn.setCellValueFactory(new PropertyValueFactory<>("id"));
            team_colmn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
            name_colmn.setCellValueFactory(new PropertyValueFactory<>("nameSurname"));
            sponsors_colmn.setCellValueFactory(new PropertyValueFactory<>("sponsor"));
            cars_colmn.setCellValueFactory(new PropertyValueFactory<>("racingCars"));
            members_colmn.setCellValueFactory(new PropertyValueFactory<>("members"));
        }
        //Dakaro TableView lentele surisime su ObservableList lentele
        dakar_table.setItems(list);

    }

    public void onUpdateButtonClick() {
        error_label.setVisible(true);
        int dakar_id = Integer.parseInt(id.getText());
        String teamName = team_name.getText();
        String nameSurname = name_surname.getText();
        String sponsors = "";
        String cars = "";

        if (!Validation.isValidId(String.valueOf(dakar_id))) {
            error_label.setText("Netinkamas ID formatas");
            // jeigu paliekamas tuscias langas
        } else if (teamName.equals("")) {
            error_label.setText("Privalomas Komandos Pavadinimas");
            //jeigu komandos pavadinimas yra jau uzregistruotas
        } else if (DakarDAO.isSameTeamName(teamName) != dakar_id) {
            error_label.setText("Yra kita komanda su tokiu pavadinimu");
            //jeigu paliekas tuscias nameSurname langas
        } else if (nameSurname.equals("")) {
            error_label.setText("Privalomas Vardas ir pavarde");
        } else {

            if (saudi_A_C.isSelected()) sponsors = sponsors + saudi_A_C.getText() + ",";
            if (france_TV.isSelected()) sponsors = sponsors + france_TV.getText() + ",";
            if (aqua.isSelected()) sponsors = sponsors + aqua.getText();

            if (toyota.isSelected()) cars = toyota.getText();
            else if (mini.isSelected()) cars = mini.getText();
            else if (peugeot.isSelected()) cars = peugeot.getText();

            //jeigu nepasirinko masinos
            if (cars.equals("")) {
                error_label.setText("Butina pasirinkti transporta");
            } else {

                Dakar dakar = new Dakar(Integer.parseInt(id.getText()), teamName, nameSurname, sponsors, cars, Integer.parseInt(members.getValue()));
                DakarDAO.update(dakar);
                error_label.setText("Pavyko atnaujinti duomenis");
            }
        }
    }

    public void onDeleteButtonClick() {

        error_label.setVisible(true);
        int dakar_id = Integer.parseInt(id.getText());
        if (!Validation.isValidId(String.valueOf(dakar_id))) {
            error_label.setText("Netinkamas ID formatas");
        } else {

            DakarDAO.deleteByID(dakar_id);
            error_label.setText("Pasalinimas Pavyko");
        }

    }


}
