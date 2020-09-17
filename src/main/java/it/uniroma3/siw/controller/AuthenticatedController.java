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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.controller.session.SessionData;
import it.uniroma3.siw.controller.validators.CameraValidator;
import it.uniroma3.siw.controller.validators.adminPageValidator;
import it.uniroma3.siw.service.CameraService;
import it.uniroma3.siw.service.CredentialService;
import it.uniroma3.siw.service.PrenotazioneAndCameraService;
import it.uniroma3.siw.service.PrenotazioneService;

@Controller
public class AuthenticatedController {
	
	@Autowired
	private PrenotazioneAndCameraService prenotazioneAndCameraService;

	@Autowired
	private SessionData sessionData;
	
	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private adminPageValidator adminPageValidator;
	
	@Autowired
	private CameraValidator CameraValidator;
	

	/*
	 * RICHIESTE GET
	 */
	
	@RequestMapping(value = "/ricercaCamere", method = RequestMethod.GET)
	public String getRicercaCameraPage(Model model) {
		this.sessionData.getLoggedUser();
		model.addAttribute("segreteriaForm", new SegreteriaForm());
		return "ricercaCamere";
	}
	
	@RequestMapping(value = "/segreteriaHome", method = RequestMethod.GET)
	public String getSegreteriaPage(Model model) {
		this.sessionData.getLoggedUser();
		return "segreteriaHome";
	}
	
	@RequestMapping(value = "/addCamera", method = RequestMethod.GET)
	public String addCamera(Model model) {
		this.sessionData.getLoggedUser();
		model.addAttribute("camera", new Camera());
		return "addCamera";
	}
	
	@RequestMapping(value = "/removeCamera", method = RequestMethod.GET)
	public String removeCamera(Model model) {
		this.sessionData.getLoggedUser();
		return "removeCamera";
	}
	
	@RequestMapping(value = "/removePrenotazione", method = RequestMethod.GET)
	public String removePrenotazione(Model model) {
		this.sessionData.getLoggedUser();
		return "removePrenotazione";
	}
	
	@RequestMapping(value = "/elencoUpdate", method = RequestMethod.GET)
	public String updateCamera(Model model) {
		this.sessionData.getLoggedUser();
		List<Camera> out = (List<Camera>) this.cameraService.getAll();
		if(!out.isEmpty())
			model.addAttribute("camere", this.cameraService.getAll());
		return "elencoUpdate";
	}
	
	@RequestMapping(value = "/elencoPrenotazioni", method = RequestMethod.GET)
	public String elencoPrenotazioni(Model model) {
		this.sessionData.getLoggedUser();
		List<Prenotazione> out = (List<Prenotazione>) this.prenotazioneService.getAll();
		if(!out.isEmpty())
			model.addAttribute("prenotazioni", this.prenotazioneService.getAll());
		return "elencoPrenotazioni";
	}
	
	/*
	 * RICHIESTE POST
	 */
	
	@RequestMapping(value = "/elencoUpdate/updateCamera", method = RequestMethod.POST)
	public String updateCameraScelta(@ModelAttribute("cameraId") Long id, Model model, HttpSession session) {
		this.sessionData.getLoggedUser();
		model.addAttribute("newCamera", new Camera());
		model.addAttribute("oldCamera", this.cameraService.getCameraById(id));
		session.setAttribute("oldCameraId", id);
		return "updateCamera";
	}
	
	@RequestMapping(value = "/elencoUpdate/updateCamera/updated", method = RequestMethod.POST)
	public String updatedCamera(@ModelAttribute("newCamera")Camera newCamera, BindingResult bn, @RequestParam("isLibera") String libera, Model model, HttpSession session) {
		this.sessionData.getLoggedUser();
		
		this.CameraValidator.validate(newCamera, bn);
		if(!bn.hasErrors()) {
			this.cameraService.deleteCamera(this.cameraService.getCameraById((Long)session.getAttribute("oldCameraId")));
			if(libera.equals("libera"))
				newCamera.setLibera(true);
			else
				newCamera.setLibera(false);
			
			this.cameraService.saveCamera(newCamera);
			return "segreteriaHome";
		}
		model.addAttribute("oldCamera", this.cameraService.getCameraById((Long)session.getAttribute("oldCameraId")));
		return "updateCamera";
	}
	
	
	
	@RequestMapping(value = "/segreteriaHome/add", method = RequestMethod.POST)
	public String addCameraPost(@ModelAttribute("camera") Camera c, BindingResult b, Model model) {
		this.sessionData.getLoggedUser();
		this.CameraValidator.validate(c, b);
		if(!b.hasErrors())
			this.cameraService.saveCamera(c);
		else
			return "addCamera";
		
		return "segreteriaHome";
	}
	
	@RequestMapping(value = "/segreteriaHome/rem", method = RequestMethod.POST)
	public String removeCameraPost(@RequestParam(required = false, name = "numeroStanza") Integer num, Model model) {
		this.sessionData.getLoggedUser();
		if(num != null) {
			Camera c = this.cameraService.getCameraByNumero(num.intValue());
			if(c != null) {
				this.cameraService.deleteCamera(c);
				return "segreteriaHome";
			}
		}
		
		model.addAttribute("oggetto", "camera");
		return "unless";
		
	}
	
	@RequestMapping(value = "/segreteriaHome/remPren", method = RequestMethod.POST)
	public String removePrenotazionePost(@RequestParam(required = false, name = "numeroPrenotazione") Long num, Model model) {
		this.sessionData.getLoggedUser();
		if(num != null) {
			Prenotazione p = this.prenotazioneService.getPrenotazioneByNumero(num);
			if(p != null) {
				this.prenotazioneService.deletePrenotazione(p);
				return "segreteriaHome";
			}
		}
		
		model.addAttribute("oggetto", "prenotazione");
		return "unless";
	}
	

	@RequestMapping(value = "/segreteriaHome/camere", method = RequestMethod.POST)
	public String getCamera(@ModelAttribute("segreteriaForm") SegreteriaForm sf, BindingResult b, Model model) {
		this.sessionData.getLoggedUser();

		this.adminPageValidator.validate(sf, b);

		if(!b.hasErrors()) {
			List<Camera> out = this.prenotazioneAndCameraService.segreteriaService(sf);

			if(!out.isEmpty())
				model.addAttribute("camere", out);

			return "elencoSegreteria";
		}
		else {
			return "segreteriaHome";
		}
		
	}
	

}
