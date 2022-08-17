package Personaldaten.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Personaldaten.model.Mitarbeitende;
import Personaldaten.util.DateUtil;

/**
 * Dialog zur editieren von Mitarbeitenden.
 * 
 * Autor Reto Caviezel
 */
public class MitarbeitendeEditDialogController {

    @FXML
    private TextField vornameField;
    @FXML
    private TextField nachnameField;
    @FXML
    private TextField geburtsdatumField;
    @FXML
    private TextField strasseField;
    @FXML
    private TextField nummerField;
    @FXML
    private TextField ortField;
    @FXML
    private TextField postleitzahlField;
    @FXML
    private TextField telefonnummerField;
    @FXML
    private TextField einsatzjahrField;
    @FXML
    private TextField einsatzortField;
    @FXML
    private TextField samstagField;
    @FXML
    private TextField sonntagField;
    @FXML
    private TextField montagField;
 


    private Stage dialogStage;
    private Mitarbeitende mitarbeitende;
    private boolean okClicked = false;
    

    /**
     * Initialisiert die controller klasse. Diese Methode ist automatisch aufgerufen nachdem das fxml file geladen wurde.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Setzt eine stage in diesem dialog.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Setzt Mitarbeidende welche in diesen dialog editiert werden.
     */
    public void setMitarbeitende(Mitarbeitende mitarbeitende) {
        this.mitarbeitende = mitarbeitende;

        vornameField.setText(mitarbeitende.getVorname());
        nachnameField.setText(mitarbeitende.getNachname());
        strasseField.setText(mitarbeitende.getStrasse());
        postleitzahlField.setText(Integer.toString(mitarbeitende.getPostleitzahl()));
        nummerField.setText(Integer.toString(mitarbeitende.getNummer()));
        ortField.setText(mitarbeitende.getOrt());
        geburtsdatumField.setText(DateUtil.format(mitarbeitende.getGeburtsdatum()));
        geburtsdatumField.setPromptText("dd.mm.yyyy");
        telefonnummerField.setText(mitarbeitende.getTelefonnummer());
        einsatzortField.setText(mitarbeitende.getEinsatzort());
        samstagField.setText(mitarbeitende.getSamstag());
        sonntagField.setText(mitarbeitende.getSonntag());
        montagField.setText(mitarbeitende.getMontag());
        einsatzjahrField.setText(Integer.toString(mitarbeitende.getEinsatzjahr()));
    }

    /**
     * Rerturniert true wenn der benutzer OK klickt, ansonsten false.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * ruft wenn der benutzer ok klickt.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	mitarbeitende.setVorname(vornameField.getText());
        	mitarbeitende.setNachname(nachnameField.getText());
        	mitarbeitende.setStrasse(strasseField.getText());
        	mitarbeitende.setPostleitzahl(Integer.parseInt(postleitzahlField.getText()));
        	mitarbeitende.setNummer(Integer.parseInt(nummerField.getText()));
        	mitarbeitende.setOrt(ortField.getText());
        	mitarbeitende.setGeburtsdatum(DateUtil.parse(geburtsdatumField.getText()));
        	mitarbeitende.setTelefonnummer(telefonnummerField.getText());
        	mitarbeitende.setEinsatzort(einsatzortField.getText());
        	mitarbeitende.setSamstag(samstagField.getText());
        	mitarbeitende.setSonntag(sonntagField.getText());
        	mitarbeitende.setMontag(montagField.getText());
        	mitarbeitende.setEinsatzjahr(Integer.parseInt(einsatzjahrField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Aufrufe wenn der Benzutzer close klickt.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Prüft den Input des Benutzers.
     * 
     * returniert true wenn der Input korrekt ist
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (vornameField.getText() == null || vornameField.getText().length() == 0) {
            errorMessage += "Kein gültiger Vorname!\n"; 
        }
        if (nachnameField.getText() == null || nachnameField.getText().length() == 0) {
            errorMessage += "Kein gültiger Nachname!\n"; 
        }
        if (strasseField.getText() == null || strasseField.getText().length() == 0) {
            errorMessage += "Keine gültige Strasse!\n"; 
        }

        if (postleitzahlField.getText() == null || postleitzahlField.getText().length() == 0) {
            errorMessage += "Keine gültige Postleitzahl!\n"; 
        } else {
            // analysieren der Postleitzahl.
            try {
                Integer.parseInt(postleitzahlField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Keine gültige Postleitzahl (muss eine Zahl sein)!\n"; 
            }
        }

        if (ortField.getText() == null || ortField.getText().length() == 0) {
            errorMessage += "Kein gültiger Ort!\n"; 
        }

        if (geburtsdatumField.getText() == null || geburtsdatumField.getText().length() == 0) {
            errorMessage += "Kein gültiges Geburtsdatum!\n";
        } else {
            if (!DateUtil.validDate(geburtsdatumField.getText())) {
                errorMessage += "Kein gültiges Geburtsdatum. Bitte folgendes Format nutzen dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Zeigt die Fehlermeldungen.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ungültige Felder");
            alert.setHeaderText("Bitte ungültige Felder korrigieren");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}