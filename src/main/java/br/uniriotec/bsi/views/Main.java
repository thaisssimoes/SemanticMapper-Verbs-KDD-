package br.uniriotec.bsi.views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.uniriotec.bsi.models.PreProcessedDataNouns;
import br.uniriotec.bsi.models.PreProcessedDataVerbs;
import br.uniriotec.bsi.models.TextProcessor;
import br.uniriotec.bsi.models.WordnetDataRecovery;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		PreProcessedDataVerbs teste = new PreProcessedDataVerbs();
		TextProcessor teste3 = new TextProcessor();		
		String preProcessedText = teste3.readingPreProcessedText("/home/thais/Documentos/TCC/Leo/srlInputConll2008.tokens");
		String Alice = teste3.readingPreProcessedText("/home/thais/Documentos/TCC/Leo/conference_domain.txt");
		PreProcessedDataNouns noun = new PreProcessedDataNouns();
		List<String> sentenceList = teste3.tokenizer(Alice);				
		Set<String> testandoConjuntoInfinitivo = new HashSet<String>();		
		Set<String> testandoConjuntoConjugado = new HashSet<String>();		

		WordnetDataRecovery teste2 = new WordnetDataRecovery();
		
		testandoConjuntoConjugado = teste.getLabeledVerbs(preProcessedText);
		testandoConjuntoInfinitivo=teste.convertVerbsToBaseForm(teste.getLabeledVerbs(preProcessedText));
		
		for (String key : sentenceList) {
			System.out.print( key + "\n\n");

		}
		
		
//		System.out.print(sentenceList + "\n\n\n\n\n");
//		System.out.print(testandoConjuntoConjugado + "\n\n\n\n\n");
//		System.out.print(testandoConjuntoInfinitivo + "\n\n\n\n\n");
//		System.out.println(noun.getLabeledNouns(preProcessedText) + "\n\n\n\n\n");
	
	}

}
