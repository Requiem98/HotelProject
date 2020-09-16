package it.uniroma3.siw.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Transactional
	public Utente getUtenteByCodiceFiscale(String codice) {
		return this.utenteRepository.findByCodiceFiscale(codice);
	}
	
	@Transactional
	public boolean utenteCodiceFiscaleAlreadyExists(String codice) {
		if(this.utenteRepository.findByCodiceFiscale(codice)!=null)
			return true;
		else
			return false;
	}
	
	@Transactional
	public boolean utenteEmailAlreadyExists(String email) {
		if(this.utenteRepository.findByEmail(email)!=null)
			return true;
		else
			return false;
	}
	
	@Transactional
	public boolean utenteTelefonoAlreadyExists(String tel) {
		if(this.utenteRepository.findByTelefono(tel)!=null)
			return true;
		else
			return false;
	}
}
