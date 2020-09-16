package it.uniroma3.siw.controller.validators;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.PrenotazioneForm;

@Component
public class PrenotazioneFormValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PrenotazioneForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PrenotazioneForm pf = (PrenotazioneForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "arrivoString", "required.PrenotazioneForm");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partenzaString", "required.PrenotazioneForm");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroOspiti", "required.PrenotazioneForm");
		
		if(!errors.hasErrors()) {
			
			pf.StringToDate();
			
			if(!(pf.getArrivo().isBefore(pf.getPartenza())) || pf.getArrivo().isBefore(LocalDate.now())) 
				errors.rejectValue("arrivoString", "erroreData");
			
			if(pf.getNumeroOspiti() < 1)
				errors.reject("erroreOspiti");
				
		}
		
	}
	
}
