package it.uniroma3.siw;



import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.uniroma3.siw.model.Camera;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.CameraRepository;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.PrenotazioneRepository;
import it.uniroma3.siw.repository.UtenteRepository;
import it.uniroma3.siw.service.CameraService;
import it.uniroma3.siw.service.CredentialService;
import it.uniroma3.siw.service.PrenotazioneAndCameraService;
import it.uniroma3.siw.service.PrenotazioneService;



@RunWith(SpringRunner.class)
@SpringBootTest
class HotelProjectApplicationTests {
	
	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private PrenotazioneAndCameraService prenotazioneAndCameraService;
	
	
	@Autowired
	private CredentialService credentialService;
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	
	@Test
	public void setDatabase() {
	//							  n  m  s					
		Camera c1 = new Camera(1, 2, 1, 0, 20);
		Camera c2 = new Camera(2, 2, 1, 0, 20);
		Camera c3 = new Camera(3, 2, 1, 0, 20);
		Camera c4 = new Camera(4, 3, 1, 1, 30);
		Camera c5 = new Camera(5, 3, 1, 1, 30);
		Camera c6 = new Camera(6, 3, 1, 1, 30);
		Camera c7 = new Camera(7, 3, 0, 3, 40);
		Camera c8 = new Camera(8, 4, 1, 2, 40);
		Camera c9 = new Camera(9, 4, 1, 2, 40);
		Camera c10 = new Camera(10, 4, 2, 0, 40);
		
				
		Utente u = new Utente("Amedeo", "Ranaldi", LocalDate.of(1998, 10, 26), "ghd5dfd44s5xc7s", "3921995941", "ame.ranaldi@stud.uniroma3.it");
		Credentials cred = new Credentials("admin", "admin", u);
		this.credentialService.saveCredentials(cred);
		
		cameraService.saveCamera(c1);
		cameraService.saveCamera(c2);
		cameraService.saveCamera(c3);
		cameraService.saveCamera(c4);
		cameraService.saveCamera(c5);
		cameraService.saveCamera(c6);
		cameraService.saveCamera(c7);
		cameraService.saveCamera(c8);
		cameraService.saveCamera(c9);
		cameraService.saveCamera(c10);
		
	
	}
	
	

}
