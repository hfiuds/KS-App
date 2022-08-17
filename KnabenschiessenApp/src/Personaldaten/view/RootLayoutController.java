package Personaldaten.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import Personaldaten.MainApp;

/**
 * Controller für root layout. Das Root layout bildet die Basis für das Applikations layout. Dies beinhaltet eine menu bar via welcher weitere
 * JavaFX elemente platziert werden können.
 * 
 * Autor Reto Caviezel
 */
public class RootLayoutController {

    // Referenziert zur Mail App
    private MainApp mainApp;

    /**
     * Wir aufgefrufen durch die main app und referenziert zurück zu sich selber.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * generiert eine leere Mitarbeitendendatenbank.
     */
    @FXML
    private void handleNew() {
        mainApp.getMitarbeitendeData().clear();
        mainApp.setMitarbeitendeFilePath(null);
    }

    /**
     * öffnet einen FileChooser und lässt den Benutzer eine Mitarbeitende Person wählen um diesen zu laden.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        //setzt den einen Filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // zeigt die gewünschten Daten
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadMitarbeitendeDataFromFile(file);
        }
    }

    /**
     * Sicher das File zu dem aktuell geöffneteten. Wenn kein File offen ist wird die Funktion save as angezeigt.
     */
    @FXML
    private void handleSave() {
        File mitarbeitendeFile = mainApp.getMitarbeitendeFilePath();
        if (mitarbeitendeFile != null) {
            mainApp.saveMitarbeitendeDataToFile(mitarbeitendeFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Öffnet einen FileChooser damit der Benutzer das selktierte File speichern kann.
     */
    @FXML
    private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Setzt den filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Zeigt das gespeicherte File
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Stellt sicher, dass der Pfad korrekt ist
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.saveMitarbeitendeDataToFile(file);
		}
	}

   
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("KnabenschiessenApp");
    	alert.setHeaderText("About");
    	alert.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");

    	alert.showAndWait();
    }
  
    
    /**
     * Öffnet die Filter Funktion.
     */
    @FXML
    private void handleSortFilter() {
      mainApp.showSortFilter();
    }
   

    /**
     * schliesst die App.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}