package it.uniroma3.siw.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.controller.validators.DatiUtenteValidator;
import it.uniroma3.siw.controller.validators.PrenotazioneFormValidator;
import it.uniroma3.siw.model.Camera;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.PrenotazioneForm;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CameraService;
import it.uniroma3.siw.service.PrenotazioneAndCameraService;
import it.uniroma3.siw.service.PrenotazioneService;

@Controller
public class PrenotazioneController {

	@Autowired
	private CameraService camserv;

	@Autowired
	private PrenotazioneService prenserv;
	
	@Autowired
	private PrenotazioneAndCameraService prenotazioneAndCameraService;

	@Autowired
	private PrenotazioneFormValidator prenotazioneFormValidator;

	@Autowired
	private DatiUtenteValidator datiUtenteValidator;


	@RequestMapping(value = "/prenotazione", method = RequestMethod.GET)
	public String prenotazione(Model model) {
		model.addAttribute("prenotazioneForm", new PrenotazioneForm());
		return "prenotazione";
	}

	@RequestMapping(value = "/index/prenotazione/elencoStanze", method = RequestMethod.POST)
	public String findCamere(@ModelAttribute("prenotazioneForm") PrenotazioneForm pf, BindingResult bindingResult, Model model, HttpSession session) {

		this.prenotazioneFormValidator.validate(pf, bindingResult);

		if(!bindingResult.hasErrors()) {
			pf.StringToDate();
			List<Camera> listaCamere = this.prenotazioneAndCameraService.getListaCamerePrenotate(pf.getArrivo(), pf.getPartenza(), pf.getNumeroOspiti());
	
			if(!listaCamere.isEmpty())
				model.addAttribute("camere", listaCamere);
			
			session.setAttribute("prenotazioneForm", pf);
			return "elenco";
		}
		else {
			return "prenotazione";
		}
	}

	@RequestMapping(value = "/index/prenotazione/elencoStanze/dati", method = RequestMethod.POST)
	public String sceltaCamera(@ModelAttribute("cameraId") Long id, BindingResult bindingResultIdCamera, Model model, HttpSession session) {

		PrenotazioneForm pf = (PrenotazioneForm) session.getAttribute("prenotazioneForm");

		Prenotazione p = new Prenotazione(pf.getArrivo(), pf.getPartenza(), this.camserv.getCameraById(id), null);

		session.setAttribute("prenotazione", p);
		model.addAttribute("utenteForm", new Utente());

		return "datiUtente";
	}

	@RequestMapping(value = "/index/prenotazione/elencoStanze/dati/registrazione", method = RequestMethod.POST)
	public String creaPrenotazione(@ModelAttribute("utenteForm") Utente u, BindingResult brU, Model model, HttpSession session) {

		this.datiUtenteValidator.validate(u, brU);

		if(!brU.hasErrors()) {

			Prenotazione p = (Prenotazione) session.getAttribute("prenotazione");

			u.StringToDate();

			p.setOspite(u);
			prenserv.savePrenotazione(p);
			model.addAttribute("prenotazione", p);

			return "fine";
		}
		else {
			return "datiUtente";
		}
	}

}
