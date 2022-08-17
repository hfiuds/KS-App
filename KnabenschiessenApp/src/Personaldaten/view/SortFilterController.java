package Personaldaten.view;

import Personaldaten.model.Mitarbeitende;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * View-Controller für Filterfunktion
 *
 * Autor Reto Caviezel
 */
public class SortFilterController {

  @FXML
  private TextField filterField;
  @FXML
  private TableView<Mitarbeitende> mitarbeitendeTable;
  @FXML
  private TableColumn<Mitarbeitende, String> vornameColumn;
  @FXML
  private TableColumn<Mitarbeitende, String> nachnameColumn;
  @FXML
  private TableColumn<Mitarbeitende, String> einsatzortColumn;
  @FXML
  private TableColumn<Mitarbeitende, String> samstagColumn;
  @FXML
  private TableColumn<Mitarbeitende, String> sonntagColumn;
  @FXML
  private TableColumn<Mitarbeitende, String> montagColumn;
  
  private ObservableList<Mitarbeitende> mitarbeitendeData = FXCollections.observableArrayList();

  public void setMitarbeitendeData(ObservableList<Mitarbeitende> mitarbeitendeData) {
    this.mitarbeitendeData = mitarbeitendeData;
    initTable();
  }

  /**
   * Initalisiert die Controller Klasse. Diese Methode wird aufgerufen nachdem das fxml File geladen werden konnte.
   * Initialisiert die Colums und setzt die Filterfunktion.
   */
  @FXML
  private void initialize() {
    
    vornameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
    nachnameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
    einsatzortColumn.setCellValueFactory(cellData -> cellData.getValue().einsatzortProperty());
    samstagColumn.setCellValueFactory(cellData -> cellData.getValue().samstagProperty());
    sonntagColumn.setCellValueFactory(cellData -> cellData.getValue().sonntagProperty());
    montagColumn.setCellValueFactory(cellData -> cellData.getValue().montagProperty());
      }

  private void initTable() {
    
    FilteredList<Mitarbeitende> filteredData = new FilteredList<>(mitarbeitendeData, p -> true);

    //passt den Filter jederzeit an.
    filterField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredData.setPredicate(mitarbeitende -> {
        // Wenn der Filter nicht genutzt wird, werden alle Mitarbeitendendaten angezeigt.
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        // Vergleicht die Eingabe im Filter im dem Inhalt in Einsatzort.
        String lowerCaseFilter = newValue.toLowerCase();

        if (mitarbeitende.getEinsatzort().toLowerCase().contains(lowerCaseFilter)) {
          return true; // Filter matched Einsatzort.
   
        }
        return false; // wenn kein matching vorhanden ist
      });
    });

    // Zeigt gefilterte Daten in der Tabelle.
    mitarbeitendeTable.setItems(filteredData);
  }
}
