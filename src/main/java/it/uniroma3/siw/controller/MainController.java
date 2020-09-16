package it.uniroma3.siw.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.service.PrenotazioneService;

@Controller
public class MainController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;

	public MainController() {}
	
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		
		List<Prenotazione> prenotazioniScadute = this.prenotazioneService.getTodayPrenotazionePartenza();

		if(!prenotazioniScadute.isEmpty() || prenotazioniScadute != null)
			for(Prenotazione p : prenotazioniScadute) 
				this.prenotazioneService.deletePrenotazione(p);
			
		return "index.html";
	}
	
}
