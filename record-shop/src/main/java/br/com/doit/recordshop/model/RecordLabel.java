package br.com.doit.recordshop.model;

import org.apache.log4j.Logger;

import com.webobjects.foundation.NSValidation;

public class RecordLabel extends _RecordLabel {
  private static final long serialVersionUID = 1L
		  ;
  private static Logger log = Logger.getLogger(RecordLabel.class);
  
  @Override
	public void validateForSave() throws ValidationException {
		super.validateForSave( )   ;
		if (name() == null || name().trim().isEmpty()) {
			throw new NSValidation.ValidationException("Preencha o nome da gravadora.");
		}
		if(country() == null || country().trim().isEmpty()) {
			throw new NSValidation.ValidationException("Preencha o País.");
		}
		
	}
}
