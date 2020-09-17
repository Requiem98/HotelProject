package it.uniroma3.siw.controller.validators;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.UtenteService;

@Component
public class DatiUtenteValidator implements Validator{

	@Autowired
	private UtenteService utenteService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Utente.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Utente u = (Utente)target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required.Utente");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required.Utente");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDiNascitaString", "required.Utente");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codiceFiscale", "required.Utente");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.Utente");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required.Utente");

		if(!errors.hasErrors()) {

			u.StringToDate();

			if(this.utenteService.utenteCodiceFiscaleAlreadyExists(u.getCodiceFiscale())) 
				errors.rejectValue("codiceFiscale", "duplicato");	

			if(this.utenteService.utenteEmailAlreadyExists(u.getEmail()))
				errors.rejectValue("email", "duplicato");
			
			if(!this.checknumero(u.getTelefono()))
				errors.rejectValue("telefono", "erroreTelefono");

			if(this.utenteService.utenteTelefonoAlreadyExists(u.getTelefono()))
				errors.rejectValue("telefono", "duplicato");

			if(u.getNome().length() < 2)
				errors.rejectValue("nome" ,"corto");

			if(u.getCognome().length() < 2)
				errors.rejectValue("cognome", "corto");

			if(LocalDate.now().getYear() - u.getDataDiNascita().getYear() < 18)
				errors.rejectValue("dataDiNascitaString", "minorenne");
		}

	}

	public boolean checknumero(String stringa) {
		boolean numerico = true;
		char[] sequenza = stringa.toCharArray();

		for (int i=0; i< sequenza.length; i++) {

			try {

				Long.parseLong(Character.toString(sequenza[i]));

			} catch (Exception e) {

				numerico = false;

			}

		}

		return numerico;

	}
}
