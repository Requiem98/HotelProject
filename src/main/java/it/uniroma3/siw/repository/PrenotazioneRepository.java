package it.uniroma3.siw.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Camera;
import it.uniroma3.siw.model.Prenotazione;

@Repository
public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{

	public Prenotazione findByNumeroPrenotazione(Long numeroPrenotazione);
	
	public List<Prenotazione> findByArrivo(LocalDate arrivo);
	
	public List<Prenotazione> findByPartenza(LocalDate partenza);
	
	public Prenotazione findByCamera(Camera camera);
	
	public List<Prenotazione> findByArrivoAndPartenza(LocalDate arrivo, LocalDate partenza);
	
	public List<Prenotazione> findByArrivoBetween(LocalDate arrivo, LocalDate partenza);
	
	public List<Prenotazione> findByPartenzaBetween(LocalDate arrivo, LocalDate partenza);
	
}
