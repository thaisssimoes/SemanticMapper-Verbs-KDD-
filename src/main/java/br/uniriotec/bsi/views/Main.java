package br.uniriotec.bsi.views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import br.uniriotec.bsi.models.PreProcessedData;
import br.uniriotec.bsi.models.WordnetDataRecovery;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		PreProcessedData teste = new PreProcessedData();

		String preProcessedText = teste.readingPreProcessedText("/home/thais/Documentos/TCC/Leo/srlInputConll2008.tokens");
		String Alice = teste.readingPreProcessedText("/home/thais/Documentos/TCC/alice.txt");
//		teste.tokenizer(Alice);
		
				
		Set<String> testandoConjunto = new HashSet<String>();
//		
//		
//		teste.getLabeledVerbs(preProcessedText);
		WordnetDataRecovery teste2 = new WordnetDataRecovery();
		testandoConjunto=teste.convertVerbsToBaseForm(teste.getLabeledVerbs(preProcessedText));
//		teste.convertVerbsToBaseForm(testandoConjunto);
//		teste.tokenizer(Alice);
		System.out.println(teste2.getSupersense(testandoConjunto));
	
	}

}
