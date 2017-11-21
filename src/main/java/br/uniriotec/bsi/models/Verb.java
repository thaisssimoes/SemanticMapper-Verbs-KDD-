package br.uniriotec.bsi.models;

import java.util.ArrayList;
import java.util.List;

public class Verb {
	private List<String> complement;
	private String subject;
	private String verbText;
	private String baseForm;
	
		
	public String getVerbFromText( ) {
		return this.verbText;
	}
	
	public void setVerbFromText(String verbText) {
		this.verbText = verbText;

	}
	
	public String getVerbBaseForm( ) {
		return this.baseForm;
	}
	
	public void setVerbBaseForm(String verbBaseForm) {
		this.baseForm = verbBaseForm;

	}
	
	public String getSubject( ) {
		return this.subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;

	}
	public List<String> getComplement( ) {
		return this.complement;
	}
	
	public void setComplement(List<String> complement) {
		this.complement = complement;

	}
	


}
