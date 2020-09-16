package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class Prenotazione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long numeroPrenotazione;
	
	@Column(nullable = false)
	private LocalDate arrivo;
	
	@Column(nullable = false)
	private LocalDate partenza;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Camera camera;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Utente> ospiti;

	
	public Prenotazione() {
		this.ospiti = new ArrayList<>();
	}

	public Prenotazione(LocalDate arrivo, LocalDate partenza, Camera camera, List<Utente> ospiti) {
		this.arrivo = arrivo;
		this.partenza = partenza;
		this.camera = camera;
		
		if(this.ospiti == null)
			this.ospiti = new ArrayList<>();
		else
			this.ospiti = ospiti;
	}

	public Long getId() {
		return id;
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

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public List<Utente> getOspiti() {
		return ospiti;
	}

	public void setOspiti(List<Utente> ospiti) {
		this.ospiti = ospiti;
	}
	
	public void setOspite(Utente u) {
		this.ospiti.add(u);
	}
	
	public Long getNumeroPrenotazione() {
		return this.numeroPrenotazione;
	}

	public void setNumeroPrenotazione(Long num) {
		this.numeroPrenotazione = num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivo == null) ? 0 : arrivo.hashCode());
		result = prime * result + ((camera == null) ? 0 : camera.hashCode());
		result = prime * result + ((ospiti == null) ? 0 : ospiti.hashCode());
		result = prime * result + ((partenza == null) ? 0 : partenza.hashCode());
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
		Prenotazione other = (Prenotazione) obj;
		if (arrivo == null) {
			if (other.arrivo != null)
				return false;
		} else if (!arrivo.equals(other.arrivo))
			return false;
		if (camera == null) {
			if (other.camera != null)
				return false;
		} else if (!camera.equals(other.camera))
			return false;
		if (ospiti == null) {
			if (other.ospiti != null)
				return false;
		} else if (!ospiti.equals(other.ospiti))
			return false;
		if (partenza == null) {
			if (other.partenza != null)
				return false;
		} else if (!partenza.equals(other.partenza))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prenotazione [arrivo=" + arrivo + ", partenza=" + partenza + ", camera=" + camera + ", ospiti=" + ospiti
				+ "]";
	}


	
}
