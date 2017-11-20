package br.uniriotec.bsi.models;

import lombok.Getter;
import lombok.Setter;

public class Verb {
	@Getter @Setter private String verbText;
	@Getter @Setter private String baseForm;
	@Getter	@Setter	private String superSense;
	@Getter	@Setter	private String semanticType;

}
