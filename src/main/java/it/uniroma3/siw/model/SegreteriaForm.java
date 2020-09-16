package it.uniroma3.siw.model;

import java.time.LocalDate;

public class SegreteriaForm {

	private LocalDate arrivo;
	private LocalDate partenza;
	private String arrivoString;
	private String partenzaString;
	private Integer numeroOspiti;
	private String libera;
	
	public SegreteriaForm () {
		this.libera = "occupata";
	}
	
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

	public Integer getNumeroOspiti() {
		return numeroOspiti;
	}

	public void setNumeroOspiti(Integer numeroOspiti) {
		this.numeroOspiti = numeroOspiti;
	}

	public String getLibera() {
		return libera;
	}

	public void setLibera(String libera) {
		this.libera = libera;
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
	
	public boolean libera() {
		if(this.libera.equals("libera"))
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivo == null) ? 0 : arrivo.hashCode());
		result = prime * result + ((arrivoString == null) ? 0 : arrivoString.hashCode());
		result = prime * result + ((libera == null) ? 0 : libera.hashCode());
		result = prime * result + ((numeroOspiti == null) ? 0 : numeroOspiti.hashCode());
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
		SegreteriaForm other = (SegreteriaForm) obj;
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
		if (libera == null) {
			if (other.libera != null)
				return false;
		} else if (!libera.equals(other.libera))
			return false;
		if (numeroOspiti == null) {
			if (other.numeroOspiti != null)
				return false;
		} else if (!numeroOspiti.equals(other.numeroOspiti))
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
		return "SegreteriaForm [arrivo=" + arrivo + ", partenza=" + partenza + ", arrivoString=" + arrivoString
				+ ", partenzaString=" + partenzaString + ", numeroOspiti=" + numeroOspiti + ", libera=" + libera + "]";
	}
	
	
	
	
}
