package it.uniroma3.siw.model;

import java.time.LocalDate;

public class PrenotazioneForm {

	private LocalDate arrivo;
	private LocalDate partenza;
	private String arrivoString;
	private String partenzaString;
	private int numeroOspiti;
	
	public LocalDate getArrivo() {
		return arrivo;
	}



	public void setArrivo(LocalDate arrivo) {
		this.arrivo = arrivo;
	}



	public LocalDate getPartenza() {
		return partenza;
	}



	public void setPartenza(LocalDate partenza) {
		this.partenza = partenza;
	}



	public String getArrivoString() {
		return arrivoString;
	}



	public void setArrivoString(String arrivoString) {
		this.arrivoString = arrivoString;
	}



	public String getPartenzaString() {
		return partenzaString;
	}



	public void setPartenzaString(String partenzaString) {
		this.partenzaString = partenzaString;
	}



	public int getNumeroOspiti() {
		return numeroOspiti;
	}



	public void setNumeroOspiti(int numeroOspiti) {
		this.numeroOspiti = numeroOspiti;
	}



	public void StringToDate() {
		String anno = this.arrivoString.substring(0, 4);
		String mese = this.arrivoString.substring(5, 7);
		String giorno = this.arrivoString.substring(8);
		
		this.arrivo = LocalDate.of(Integer.parseInt(anno), Integer.parseInt(mese), Integer.parseInt(giorno));
		
		anno = this.partenzaString.substring(0, 4);
		mese = this.partenzaString.substring(5, 7);
		giorno = this.partenzaString.substring(8);
		
		this.partenza = LocalDate.of(Integer.parseInt(anno), Integer.parseInt(mese), Integer.parseInt(giorno));
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivo == null) ? 0 : arrivo.hashCode());
		result = prime * result + ((arrivoString == null) ? 0 : arrivoString.hashCode());
		result = prime * result + numeroOspiti;
		result = prime * result + ((partenza == null) ? 0 : partenza.hashCode());
		result = prime * result + ((partenzaString == null) ? 0 : partenzaString.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrenotazioneForm other = (PrenotazioneForm) obj;
		if (arrivo == null) {
			if (other.arrivo != null)
				return false;
		} else if (!arrivo.equals(other.arrivo))
			return false;
		if (arrivoString == null) {
			if (other.arrivoString != null)
				return false;
		} else if (!arrivoString.equals(other.arrivoString))
			return false;
		if (numeroOspiti != other.numeroOspiti)
			return false;
		if (partenza == null) {
			if (other.partenza != null)
				return false;
		} else if (!partenza.equals(other.partenza))
			return false;
		if (partenzaString == null) {
			if (other.partenzaString != null)
				return false;
		} else if (!partenzaString.equals(other.partenzaString))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "PrenotazioneForm [arrivo=" + arrivo + ", partenza=" + partenza + ", arrivoString=" + arrivoString
				+ ", partenzaString=" + partenzaString + ", numeroOspiti=" + numeroOspiti + "]";
	}
	
	
	
}
