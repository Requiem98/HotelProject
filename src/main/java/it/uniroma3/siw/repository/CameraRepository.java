package it.uniroma3.siw.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.uniroma3.siw.model.Camera;

@Repository
public interface CameraRepository extends CrudRepository<Camera, Long> {
	
	public Camera findByNumeroStanza(int numeroStanza);
	
	public List<Camera> findByNumeroPosti(int numeroPosti);
	
	public List<Camera> findByMatrimoniali(int matrimoniali);
	
	public List<Camera> findBySingoli(int singoli);
	
	public List<Camera> findByMatrimonialiAndSingoli(int matrimoniali, int singoli);
	
	public List<Camera> findByNumeroPostiGreaterThanEqual(int numeroPosti);
	
	
}
