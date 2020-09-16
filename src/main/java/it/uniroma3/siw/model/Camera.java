package it.uniroma3.siw.model;

import javax.persistence.*;

@Entity
public class Camera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private int numeroPosti;
	
	@Column(nullable = false)
	private int numeroStanza;
	
	@Column(nullable = false)
	private int matrimoniali;
	
	@Column(nullable = false)
	private int singoli;
	
	@Column(nullable = false)
	private boolean libera;
	
	@Column(nullable = false)
	private int prezzo;
	
	public Camera() {
		this.libera = true;
	}
	
	public Camera(int numeroStanza, int numeroPosti, int matrimoniali, int singoli, int prezzo) {
		this.numeroStanza = numeroStanza;
		this.numeroPosti = numeroPosti;
		this.matrimoniali = matrimoniali;
		this.singoli = singoli;
		this.prezzo = prezzo;
		this.libera = true;
	}

	public Long getId() {
		return id;
	}

	public int getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public int getNumeroStanza() {
		return numeroStanza;
	}

	public void setNumeroStanza(int numeroStanza) {
		this.numeroStanza = numeroStanza;
	}

	public int getMatrimoniali() {
		return matrimoniali;
	}

	public void setMatrimoniali(int matrimoniali) {
		this.matrimoniali = matrimoniali;
	}

	public int getSingoli() {
		return singoli;
	}

	public void setSingoli(int singoli) {
		this.singoli = singoli;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public boolean isLibera() {
		return libera;
	}

	public void setLibera(boolean libera) {
		this.libera = libera;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (libera ? 1231 : 1237);
		result = prime * result + matrimoniali;
		result = prime * result + numeroPosti;
		result = prime * result + numeroStanza;
		result = prime * result + prezzo;
		result = prime * result + singoli;
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
		Camera other = (Camera) obj;
		if (libera != other.libera)
			return false;
		if (matrimoniali != other.matrimoniali)
			return false;
		if (numeroPosti != other.numeroPosti)
			return false;
		if (numeroStanza != other.numeroStanza)
			return false;
		if (prezzo != other.prezzo)
			return false;
		if (singoli != other.singoli)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Camera [numeroPosti=" + numeroPosti + ", numeroStanza=" + numeroStanza + ", matrimoniali="
				+ matrimoniali + ", singoli=" + singoli + ", libera=" + libera + ", prezzo=" + prezzo + "]";
	}

	


}
