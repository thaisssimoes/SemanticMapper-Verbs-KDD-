package br.uniriotec.bsi.models;

import java.util.ArrayList;

public class Verb {
	private String verbText;
	private String baseForm;
	private String supersense;
	private String semanticType;
	private ArrayList<String> context;
		
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
	
	public String getVerbSupersense( ) {
		return this.supersense;
	}
	
	public void setVerbSupersense(String supersense) {
		this.supersense = supersense;

	}
	public String getSemanticType( ) {
		return this.verbText;
	}
	
	public void setSemanticType(String semanticType) {
		this.semanticType = semanticType;

	}
	
	public ArrayList<String> sentencesWithVerbs(String sentence){
		this.context.add(sentence);
		return this.context;
	}

}
