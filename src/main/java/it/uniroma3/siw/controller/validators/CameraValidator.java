package it.uniroma3.siw.controller.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Camera;
import it.uniroma3.siw.service.CameraService;

@Component
public class CameraValidator implements Validator{

	@Autowired
	private CameraService cameraService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Camera.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Camera c = (Camera)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroStanza", "required.numeroStanza");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroPosti", "required.numeroPosti");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matrimoniali", "required.matrimoniali");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "singoli", "required.singoli");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prezzo", "required.prezzo");
		
		if(!errors.hasErrors()) {
			if(this.cameraService.getCameraByNumero(c.getNumeroStanza())!=null) {
				errors.rejectValue("numeroStanza", "duplicato");
			}
			
			if((c.getMatrimoniali() * 2) + c.getSingoli() != c.getNumeroPosti()) {
				errors.rejectValue("numeroPosti", "errorePosti");
			}
			
		}
		
	}

}
