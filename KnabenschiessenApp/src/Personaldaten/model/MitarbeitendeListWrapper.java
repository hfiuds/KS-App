package Personaldaten.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Hilfsklasse zu wrap der Mitarbeitendenliste. Das wird benötigt zum sicher der Mitarbeitendenliste in XML.
 * 
 * Autor Reto Caviezel
 */
@XmlRootElement(name = "personen")
public class MitarbeitendeListWrapper {

	private List<Mitarbeitende> personen;

	@XmlElement(name = "mitarbeitende")
	public List<Mitarbeitende> getPersonen() {
		return personen;
	}

	public void setPersonen(List<Mitarbeitende> personen) {
		this.personen = personen;
	}
}