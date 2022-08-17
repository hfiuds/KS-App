package Personaldaten;					
					
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Personaldaten.model.Mitarbeitende;
import Personaldaten.model.MitarbeitendeListWrapper;
import Personaldaten.view.MitarbeitendeUebersichtController;
import Personaldaten.view.RootLayoutController;
import Personaldaten.view.SortFilterController;
import Personaldaten.MainApp;
import Personaldaten.view.MitarbeitendeEditDialogController;
import javafx.application.Application;					
import javafx.collections.FXCollections;					
import javafx.collections.ObservableList;					
import javafx.fxml.FXMLLoader;					
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;					
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;					
					
public class MainApp extends Application {					
					
    private Stage primaryStage;					
    private BorderPane rootLayout;					
  					
    private ObservableList<Mitarbeitende> mitarbeitendeData = FXCollections.observableArrayList();					
					
	/**				
	 * konstruktoren				
	 */				
	public MainApp() {				
		// Beispieldaten		
		mitarbeitendeData.add(new Mitarbeitende("Reto", "Caviezel"));			
		mitarbeitendeData.add(new Mitarbeitende("Ester", "Caviezel"));			
		mitarbeitendeData.add(new Mitarbeitende("Rebecca", "Dünner"));			
		mitarbeitendeData.add(new Mitarbeitende("Corina", "Benso"));			
			
	}				
  					
	/**				
	 * observable Personenliste				
	 */				
	public ObservableList<Mitarbeitende>  getMitarbeitendeData() {				
		return  mitarbeitendeData;			
	}				
  					
					
    public void start(Stage primaryStage) {					
        this.primaryStage = primaryStage;					
        this.primaryStage.setTitle("KnabenschiessenApp");					
		
       //this.primaryStage.getIcons().add(new Image("file:Ressourcen/Incon/8664836_id_card_incon.png"));
        // Implementierung eines Icon ist mir leider nicht gelungen, deswegen bleibt diese Funktion ausgeblendet
        
        initRootLayout();					
					
        showMitarbeitendeUebersicht();					
    }					
    					
  					
    /**
     * Initializiert root layout und lädt die Mitarbeitenden Daten
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // zeigt scene im root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Gibt dem controller zugriff zum main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // lädt das letzte offene mitarbeitende file.
        File file = getMitarbeitendeFilePath();
        if (file != null) {
            loadMitarbeitendeDataFromFile(file);
        }
    }
					
    /**					
     * zeigt MitarbeitendeUebersicht im root layout.					
     */					
    public void showMitarbeitendeUebersicht() {					
        try {					
            // Lädt MiarbeitendeUebersicht.					
            FXMLLoader loader = new FXMLLoader();					
            loader.setLocation(MainApp.class.getResource("view/MitarbeitendeUebersicht.fxml"));					
            AnchorPane mitarbeitendeUebersicht = (AnchorPane) loader.load();					
            					
            // setzt MitarbeiterUebersicht ins zentrum des root layout.					
            rootLayout.setCenter(mitarbeitendeUebersicht);					
            	
            // Gibt dem controller Zugriff zum main app.
            MitarbeitendeUebersichtController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {					
            e.printStackTrace();					
        }					
    }					
    /**
     * öffnet ein Fenster zum editierden der details für einen Mitarbeitenden. Wenn der Benutzer OK klickt, 
     * die Daten sind gespeichert im Mitarbeitenden Objekt und sind true
     */
    public boolean showMitarbeitendeEditDialog(Mitarbeitende mitarbeitende) {
        try {
            // Lädt das fxml file und generiert eine neue stage für ein popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MitarbeitendeEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // generiert ein dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Mitarbeitende");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // setzt den Mitarbeitenden in den controller.
            MitarbeitendeEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMitarbeitende(mitarbeitende);

            // Zeigt dialog und wartet bis der Benutzer dieses schliesst
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Geht zurück zum Mitarbeitenden file preference, das letzt geöffnete File.
     * preference ist gelesen von OS specific register. Wenn keine
     * preference gefunden werden können, null wird zurück gegeben.
     * 
     */
    public File getMitarbeitendeFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * setzt den Pfad zum aktuell geladenen file. Der Pfad ist gespeichert in OS sepzifischem register.
     */
    public void setMitarbeitendeFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // aktualisiert den stage title.
            primaryStage.setTitle("KnabenschiessenApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // aktualisiert den  stage title.
            primaryStage.setTitle("KnabenschiessenApp");
        }
    }
    /**
     * Lädt Mitarbeitende daten aus dem spezifischen file. Die aktuellen Mitarbeitenden Daten werden ersetzt.
     */
    public void loadMitarbeitendeDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(MitarbeitendeListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // lesen XML vom file und aufrufen.
            MitarbeitendeListWrapper wrapper = (MitarbeitendeListWrapper) um.unmarshal(file);

            mitarbeitendeData.clear();
            mitarbeitendeData.addAll(wrapper.getPersonen());

            // Speichern des filepfades im register.
            setMitarbeitendeFilePath(file);

        } catch (Exception e) { // Abfagen von Ausnahmen
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Daten konnten nicht geladen werden");
            alert.setContentText("Date vom File konnten nicht geladen werden:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Sicher die Mitarbeitendendaten in einem spezifischen File.
     */
    public void saveMitarbeitendeDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(MitarbeitendeListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping die Miterbeitenden Daten.
            MitarbeitendeListWrapper wrapper = new MitarbeitendeListWrapper();
            wrapper.setPersonen(mitarbeitendeData);

            // Aufschaltung und sicherun XML im file.
            m.marshal(wrapper, file);

            // Sichern des Filepfades im register.
            setMitarbeitendeFilePath(file);
        } catch (Exception e) { // Fängt die Ausnahmen auf
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Daten konnten nicht gesichert werden");
            alert.setContentText("Daten konnten im File nicht gesichert werden:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    // Filterfunktion implementiert
    public void showSortFilter() {
        try {
            // Lädt das fxml file und generiert eine neue stage für ein popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SortFilter.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Sort Filter");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // setzt Mitarbeitende in den controller.
            SortFilterController controller = loader.getController();
            System.out.println("MitarbeitendeData:"+mitarbeitendeData);
            controller.setMitarbeitendeData(mitarbeitendeData);

            
            
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
	/**				
	 * zurück zur main stage.				
	 */				
	public Stage getPrimaryStage() {				
		return primaryStage;			
	}				
					
    public static void main(String[] args) {					
        launch(args);					
    }	
    
}					
					

