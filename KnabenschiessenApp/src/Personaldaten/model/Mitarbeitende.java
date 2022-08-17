package Personaldaten.model;					
					


import java.time.LocalDate;					
					
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import Personaldaten.util.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;					
import javafx.beans.property.ObjectProperty;					
import javafx.beans.property.SimpleIntegerProperty;					
import javafx.beans.property.SimpleObjectProperty;					
import javafx.beans.property.SimpleStringProperty;					
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;


					
					
/**					
 * Model klasse Mitarbeitende.					
 *					
 * Autor Reto Caviezel					
 */					
public class Mitarbeitende {					
					
    private final StringProperty vorname;					
    private final StringProperty nachname;	
    private final ObjectProperty<LocalDate> geburtsdatum;	
    private final StringProperty strasse;	
    private final IntegerProperty nummer;
    private final StringProperty ort;
    private final IntegerProperty postleitzahl;	
    private final StringProperty telefonnummer;
    private final IntegerProperty einsatzjahr;
    private final StringProperty einsatzort;
    private final StringProperty samstag;
    private final StringProperty sonntag;
    private final StringProperty montag;
	
    
    
   
    /**					
     * Standard constructor.					
     */					
    public Mitarbeitende() {					
        this(null, null);					
    }					
    					
    /**					
     * Constructor mit intialen daten.					
     * 										
     */					
    public Mitarbeitende(String vorname, String nachname) {					
        this.vorname = new SimpleStringProperty(vorname);					
        this.nachname = new SimpleStringProperty(nachname);					
        					
        // Einige initial dummy datan, zwecks testing.					
        this.strasse = new SimpleStringProperty("Musterstrasse");
        this.nummer = new SimpleIntegerProperty(1);
        this.postleitzahl = new SimpleIntegerProperty(1234);
        this.telefonnummer = new SimpleStringProperty("0790000000");
        this.ort = new SimpleStringProperty("Musterort");					
        this.geburtsdatum = new SimpleObjectProperty<LocalDate>(LocalDate.of(1900, 01, 01));
        this.einsatzjahr = new SimpleIntegerProperty (1900);
        this.einsatzort = new SimpleStringProperty("Wähle: Büro, Tombola, Restaurant");
        this.samstag = new SimpleStringProperty("Ja/Nein");
        this.sonntag = new SimpleStringProperty("Ja/Nein");
        this.montag = new SimpleStringProperty("Ja/Nein");
       
      
    }					
    					
    public String getVorname() {					
        return vorname.get();					
    }					
					
    public void setVorname(String vorname) {					
        this.vorname.set(vorname);					
    }					
    					
    public StringProperty vornameProperty() {					
        return vorname;					
    }					
					
    public String getNachname() {					
        return nachname.get();					
    }					
					
    public void setNachname(String nachname) {					
        this.nachname.set(nachname);					
    }					
    					
    public StringProperty nachnameProperty() {					
        return nachname;					
    }					
					
    public String getStrasse() {					
        return strasse.get();					
    }					
					
    public void setStrasse(String strasse) {					
        this.strasse.set(strasse);					
    }					
    					
    public StringProperty strasseProperty() {					
        return strasse;					
    }					
					
    public int getPostleitzahl() {					
        return postleitzahl.get();					
    }					
					
    public void setPostleitzahl(int postleitzahl) {					
        this.postleitzahl.set(postleitzahl);					
    }					
    					
    public IntegerProperty postleitzahlProperty() {					
        return postleitzahl;					
    }					
					
    public String getOrt() {					
        return ort.get();					
    }					
					
    public void setOrt(String ort) {					
        this.ort.set(ort);					
    }					
    					
    public StringProperty ortProperty() {					
        return ort;					
    }					
					
    @XmlJavaTypeAdapter(LocalDateAdapter.class)					
    public LocalDate getGeburtsdatum() {					
        return geburtsdatum.get();					
    }					
					
    public void setGeburtsdatum(LocalDate geburtsdatum) {					
        this.geburtsdatum.set(geburtsdatum);					
    }					
    					
    public ObjectProperty<LocalDate> geburtsdatumProperty() {					
        return geburtsdatum;					
    }	
    
    public int getNummer() {					
        return nummer.get();					
    }					
					
    public void setNummer(int nummer) {					
        this.nummer.set(nummer);					
    }					
    					
    public IntegerProperty nummerProperty() {					
        return nummer;					
    }
    
    public String getTelefonnummer() {					
        return telefonnummer.get();					
    }					
					
    public void setTelefonnummer(String telefonnummer) {					
        this.telefonnummer.set(telefonnummer);					
    }	
    
    public StringProperty telefonnummerProperty() {					
        return telefonnummer;					
    }	

    public String getSamstag() {					
        return samstag.get();					
    }					
					
    public void setSamstag(String samstag) {					
        this.samstag.set(samstag);					
    }	
    
    public StringProperty samstagProperty() {					
        return samstag;
}
    public String getSonntag() {					
        return sonntag.get();					
    }					
					
    public void setSonntag(String sonntag) {					
        this.sonntag.set(sonntag);					
    }	
    
    public StringProperty sonntagProperty() {					
        return sonntag;
    }
    public String getMontag() {					
        return montag.get();					
    }					
					
    public void setMontag(String montag) {					
        this.montag.set(montag);					
    }	
    
    public StringProperty montagProperty() {					
        return montag;
    }
    public int getEinsatzjahr() {					
        return einsatzjahr.get();					
    }					
					
    public void setEinsatzjahr(int einsatzjahr) {					
        this.einsatzjahr.set(einsatzjahr);					
    }	
    
    public IntegerProperty einsatzjahrProperty() {					
        return einsatzjahr;
    }
    public String getEinsatzort() {					
        return einsatzort.get();					
    }					
					
    public void setEinsatzort(String einsatzort) {					
        this.einsatzort.set(einsatzort);
    }
    public StringProperty einsatzortProperty() {					
        return einsatzort;
        }

    }


