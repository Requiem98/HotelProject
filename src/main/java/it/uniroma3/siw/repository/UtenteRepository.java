package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.uniroma3.siw.model.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long> {
	
	public Utente findByNome(String nome);
	
	public Utente findByCognome(String cognome);
	
	public Utente findByNomeAndCognome(String nome, String cognome);
	
	public Utente findByCodiceFiscale(String codiceFiscale);
	
	public Utente findByEmail(String email);
	
	public Utente findByTelefono(String tel);
}
