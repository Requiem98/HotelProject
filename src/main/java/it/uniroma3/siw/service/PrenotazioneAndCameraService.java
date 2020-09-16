package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Camera;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.SegreteriaForm;

@Service
public class PrenotazioneAndCameraService {

	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Transactional
	public List<Camera> getListaCamerePrenotate(LocalDate arrivo, LocalDate partenza, int numeroPosti){
		List<Camera> listaCamere = new ArrayList<>();
		List<Prenotazione> listaPrenotazioni = new ArrayList<>();
		listaCamere = this.cameraService.getCameraByNumeroPostiMaggioreUgualeA(numeroPosti);
		listaPrenotazioni = this.prenotazioneService.getPrenotazioneByArrivoOrPartenzaBetween(arrivo, partenza);
		
		for(Prenotazione p : listaPrenotazioni) {
			listaCamere.remove(p.getCamera());
		}
		
		return listaCamere;
	}
	
	@Transactional
	public List<Camera> segreteriaService(SegreteriaForm sf){
		List<Camera> out = new ArrayList<>();
		List<Camera> rem = new ArrayList<>();

		if(!sf.getArrivoString().isBlank() && !sf.getPartenzaString().isBlank()) {
			sf.StringToDate();
			for(Prenotazione p : this.prenotazioneService.getPrenotazioneByArrivoAndPartenza(sf.getArrivo(), sf.getPartenza())) {
				out.add(p.getCamera());
			}
		}
		else {
			if(!sf.getArrivoString().isBlank()) {
				sf.StringToDate();
				for(Prenotazione p : this.prenotazioneService.getPrenotazioneByArrivo(sf.getArrivo())) {
					out.add(p.getCamera());
				}		
			}

			if(!sf.getPartenzaString().isBlank()) {
				sf.StringToDate();
				for(Prenotazione p : this.prenotazioneService.getPrenotazioneByPartenza(sf.getPartenza())) {
					out.add(p.getCamera());
				}
			}

			if(sf.getArrivoString().isBlank() && sf.getPartenzaString().isBlank()) {
				for(Camera c : this.cameraService.getAll()) {
					out.add(c);
				}
			}

		}

		if(sf.getNumeroOspiti() != null) {
			for(Camera c : out) {
				if(c.getNumeroPosti() != sf.getNumeroOspiti().intValue())
					rem.add(c);
			}
		}

		if(sf.getLibera() != null && !sf.getLibera().equals("na")) {
			if(sf.libera()) {
				for(Camera c : out) {                
					if(!c.isLibera())
						rem.add(c);
				}
			}
			else {
				for(Camera c : out) {
					if(c.isLibera())
						rem.add(c);
				}
			}
		}

		for(Camera c : rem) {
			out.remove(c);
		}
		
		return out;
	}
}
