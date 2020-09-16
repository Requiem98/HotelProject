package it.uniroma3.siw.controller.validators;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.SegreteriaForm;

@Component
public class adminPageValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return SegreteriaForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SegreteriaForm sf = (SegreteriaForm) target;
		if(!errors.hasErrors() && !sf.getArrivoString().isBlank() && !sf.getPartenzaString().isBlank()) {
			sf.StringToDate();
			
			if(!(sf.getArrivo().isBefore(sf.getPartenza()))) 
				errors.rejectValue("arrivoString", "erroreData");
		}
	}

}
