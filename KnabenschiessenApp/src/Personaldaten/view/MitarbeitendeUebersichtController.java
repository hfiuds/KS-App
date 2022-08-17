package Personaldaten.view;		
		
import java.net.URL;
import java.util.ResourceBundle;

import Personaldaten.MainApp;		
import Personaldaten.model.Mitarbeitende;		
import Personaldaten.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;		
import javafx.scene.control.Alert;		
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;		
import javafx.scene.control.TableView;		
	

public class MitarbeitendeUebersichtController {
	
	
    @FXML		
    private TableView<Mitarbeitende> mitarbeitendeTable;		
    @FXML		
    private TableColumn<Mitarbeitende, String> vornameColumn;		
    @FXML		
    private TableColumn<Mitarbeitende, String> nachnameColumn;		
		
    @FXML		
    private Label vornameLabel;		
    @FXML		
    private Label nachnameLabel;
    @FXML		
    private Label geburtsdatumLabel;
    @FXML		
    private Label strasseLabel;		
    @FXML			
    private Label nummerLabel;
    @FXML
    private Label ortLabel;		
    @FXML		
    private Label postleitzahlLabel;		
    @FXML		
    private Label telefonnummerLabel;	
    @FXML	
    private Label einsatzjahrLabel;	
    @FXML	
    private Label einsatzortLabel;	
    @FXML	
    private Label samstagLabel;	
    @FXML	
    private Label sonntagLabel;	
    @FXML	
    private Label montagLabel;	
	
    	
   		

		
    // referenziert zur main app.		
    private MainApp mainApp;		
		
    /**		
     * der Konstruktor.		
     * der Konstruktor wird vor der intialisierungsmethode aufgerufen.		
     */		
    public MitarbeitendeUebersichtController() {		
    }		
		
    /**		
     * Initialisiert die controller Klasse. Diese Methode ist wird automatisch aufgefrufen nachdem das fxml geladen wurde.		
     */		
    @FXML		
    private void initialize() {		
        // Initialisiert die Mitarbeitende table mit den zwei Spalten.		
        vornameColumn.setCellValueFactory(		
                cellData -> cellData.getValue().vornameProperty());		
        nachnameColumn.setCellValueFactory(		
                cellData -> cellData.getValue().nachnameProperty());		
		
        // l�scht Mitarbitende daten.		
       showMitarbeitendeDetails(null);		
		
        // Pr�ft �nderungen und zeigt die ge�nderten Mitarbeitendendaten.		
       mitarbeitendeTable.getSelectionModel().selectedItemProperty().addListener(		
               (observable, oldValue, newValue) -> showMitarbeitendeDetails(newValue));		
    }		
		
    /**		
     * Wird aufgerufen vom main app und gibt eine R�ckmeldung zu sich selber.			
     */		
    public void setMainApp(MainApp mainApp) {		
        this.mainApp = mainApp;		
		
        // F�gt die observable list zum table		
        mitarbeitendeTable.setItems(mainApp.getMitarbeitendeData());		
    }
    
    /**						
     * F�llt die Textfelder und zeigt die Mitarbeitendendetails.						
     * Wenn ein Mitarbeitende null ist, werden die Textfelder gel�scht.											
     */						
    private void showMitarbeitendeDetails(Mitarbeitende mitarbeitende) {						
        if (mitarbeitende != null) {						
            // die labels werden analog der Mitarbeitenden Objekte erstellt.						
            vornameLabel.setText(mitarbeitende.getVorname());						
            nachnameLabel.setText(mitarbeitende.getNachname());						
            strasseLabel.setText(mitarbeitende.getStrasse());	
            nummerLabel.setText(Integer.toString(mitarbeitende.getNummer()));
            postleitzahlLabel.setText(Integer.toString(mitarbeitende.getPostleitzahl()));						
            ortLabel.setText(mitarbeitende.getOrt());	
            telefonnummerLabel.setText(mitarbeitende.getTelefonnummer());
            
            
												
            geburtsdatumLabel.setText(DateUtil.format(mitarbeitende.getGeburtsdatum()));	
            einsatzjahrLabel.setText(Integer.toString(mitarbeitende.getEinsatzjahr()));	
            einsatzortLabel.setText(mitarbeitende.getEinsatzort());	
            samstagLabel.setText(mitarbeitende.getSamstag());	
            sonntagLabel.setText(mitarbeitende.getSonntag());	
            montagLabel.setText(mitarbeitende.getMontag());	
         
        } else {						
           				
            vornameLabel.setText("");						
            nachnameLabel.setText("");						
            strasseLabel.setText("");
            nummerLabel.setText("");
            postleitzahlLabel.setText("");						
            ortLabel.setText("");						
            geburtsdatumLabel.setText("");
            telefonnummerLabel.setText("");
            einsatzjahrLabel.setText("");
            einsatzortLabel.setText("");
            samstagLabel.setText("");
            sonntagLabel.setText("");
            montagLabel.setText("");
           
        }						
    }						
    /**		
     * Wird aufgerufen wenn der Benutzer den delete knopf dr�ckt.		
     */		
    @FXML		
    private void handleDeleteMitarbeitende() {		
        int selectedIndex = mitarbeitendeTable.getSelectionModel().getSelectedIndex();		
        if (selectedIndex >= 0) {            		
        		
        	mitarbeitendeTable.getItems().remove(selectedIndex);	
            		
            //Wenn eine eingabe im table gel�scht wird, ist auch die Mitarbeitende Liste um den entsprechenden Eintrag zu l�schen 	
        	//In MitarbeitendeData wird der entsprechende Eintrag gel�scht.	           		
            		
        } else {		
            // nichts ausgew�hlt.		
            Alert alert = new Alert(AlertType.WARNING);		
            alert.initOwner(mainApp.getPrimaryStage());		
            alert.setTitle("Keine Auswahl");		
            alert.setHeaderText("Keine Person gew�hlt");		
            alert.setContentText("Bitte eine Person ausw�hlen.");		
		
            alert.showAndWait();		
        }		
    }		
    /**
     * Wird aufgerufen wenn der Benutzer auf den New Knopf dr�ckt. �ffnet ein dailog zum editieren von details eines Mitarbeitenden
     */
    @FXML
    private void handleNewMitarbeitende() {
    	Mitarbeitende tempMitarbeitende = new Mitarbeitende();
        boolean okClicked = mainApp.showMitarbeitendeEditDialog(tempMitarbeitende);
        if (okClicked) {
            mainApp.getMitarbeitendeData().add(tempMitarbeitende);
        }
    }

    /**
    * Wird aufgerufen wenn der Benutzer auf den Edit Knopf dr�ckt. �ffnet ein dailog zum editieren von details eines Mitarbeitenden
     */
    @FXML
    private void handleEditMitarbeitende() {
        Mitarbeitende selectedMitarbeitende = mitarbeitendeTable.getSelectionModel().getSelectedItem();
        if (selectedMitarbeitende != null) {
            boolean okClicked = mainApp.showMitarbeitendeEditDialog(selectedMitarbeitende);
            if (okClicked) {
                showMitarbeitendeDetails(selectedMitarbeitende);
            }

        } else {
            // NIchts ausgew�hlt.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}		
    		
    