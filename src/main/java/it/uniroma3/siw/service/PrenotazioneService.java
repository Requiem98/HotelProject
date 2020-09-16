package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Camera;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	@Transactional
	public void savePrenotazione(Prenotazione p) {
		Camera c = p.getCamera();
		c.setLibera(false);
		p.setCamera(c);
		this.prenotazioneRepository.save(p);
		p = this.prenotazioneRepository.findByCamera(p.getCamera());
		p.setNumeroPrenotazione(p.getId());
		this.prenotazioneRepository.save(p);
	}
	
	@Transactional
	public void deletePrenotazione(Prenotazione p) {
		Camera c = p.getCamera();
		c.setLibera(true);
		p.setCamera(c);
		this.prenotazioneRepository.save(p);
		this.prenotazioneRepository.delete(p);
	}
	
	@Transactional
	public void deletePrenotazioneByNumero(Long num) {
		Prenotazione p = this.prenotazioneRepository.findByNumeroPrenotazione(num);
		Camera c = p.getCamera();
		c.setLibera(true);
		p.setCamera(c);
		this.prenotazioneRepository.save(p);
		this.prenotazioneRepository.delete(p);
	}
	
	@Transactional
	public List<Prenotazione> getPrenotazioneByArrivoAndPartenza(LocalDate arrivo, LocalDate partenza){
		return this.prenotazioneRepository.findByArrivoAndPartenza(arrivo, partenza);
	}
	
	@Transactional
	public List<Prenotazione> getPrenotazioneByArrivoOrPartenzaBetween(LocalDate arrivo, LocalDate partenza){
		List<Prenotazione> arrivoB = this.prenotazioneRepository.findByArrivoBetween(arrivo, partenza);
		List<Prenotazione> partenzaB = this.prenotazioneRepository.findByPartenzaBetween(arrivo, partenza);
		
		for(Prenotazione p : arrivoB) {
			partenzaB.add(p);
		}
		
		return partenzaB;
	}
	
	@Transactional
	public List<Prenotazione> getPrenotazioneByArrivo(LocalDate arrivo){
		return this.prenotazioneRepository.findByArrivo(arrivo);
	}
	
	@Transactional
	public List<Prenotazione> getPrenotazioneByPartenza(LocalDate partenza){
		return this.prenotazioneRepository.findByPartenza(partenza);
	}
	
	@Transactional
	public Iterable<Prenotazione> getAll(){
		return this.prenotazioneRepository.findAll();
	}
	
	@Transactional
	public List<Prenotazione> getTodayPrenotazionePartenza(){
		return this.prenotazioneRepository.findByPartenza(LocalDate.now());
	}
	
	@Transactional
	public Prenotazione getPrenotazioneByCamera(Camera c){
		return this.prenotazioneRepository.findByCamera(c);
	}
	
	@Transactional
	public Prenotazione getPrenotazioneByNumero(Long num){
		return this.prenotazioneRepository.findByNumeroPrenotazione(num);
	}

}
