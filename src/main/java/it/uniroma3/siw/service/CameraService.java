package it.uniroma3.siw.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Camera;
import it.uniroma3.siw.repository.CameraRepository;

@Service
public class CameraService {
	
	@Autowired
	private CameraRepository cameraRepository;
	
	@Transactional
	public Camera getCameraById(Long id) {
		return cameraRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Camera getCameraByNumero(int numero) {
		return cameraRepository.findByNumeroStanza(numero);
	}
	
	@Transactional
	public void saveCamera(Camera c) {
		this.cameraRepository.save(c);
	}
	
	@Transactional
	public List<Camera> getCameraByLetti(int matrimoniali, int singoli) {
		return cameraRepository.findByMatrimonialiAndSingoli(matrimoniali, singoli);
	}
	
	@Transactional
	public void deleteCamera(Camera camera) {
		this.cameraRepository.delete(camera);
	}
	
	@Transactional
	public void deleteCameraById(Long id) {
		this.cameraRepository.deleteById(id);
	}
	
	@Transactional
	public void deleteCameraByNumeroStanza(int num) {
		this.cameraRepository.delete(this.cameraRepository.findByNumeroStanza(num));
	}
	
	@Transactional
	public List<Camera> getCameraByNumeroPosti(int numeroPosti) {
		return this.cameraRepository.findByNumeroPosti(numeroPosti);
	}
	
	@Transactional
	public List<Camera> getCameraByNumeroPostiMaggioreUgualeA(int numeroPosti) {
		return this.cameraRepository.findByNumeroPostiGreaterThanEqual(numeroPosti);
	}
	
	@Transactional
	public Iterable<Camera> getAll() {
		return this.cameraRepository.findAll();
	}
	
}
