package br.uniriotec.bsi.views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.uniriotec.bsi.models.PreProcessedDataNouns;
import br.uniriotec.bsi.models.PreProcessedDataVerbs;
import br.uniriotec.bsi.models.TextProcessor;
import br.uniriotec.bsi.models.Verb;
import br.uniriotec.bsi.models.WordnetDataRecovery;
import br.uniriotec.bsi.models.verbs.AnalyzingSentences;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		PreProcessedDataVerbs teste = new PreProcessedDataVerbs();
		TextProcessor teste3 = new TextProcessor();		
		String preProcessedText = teste3.readingPreProcessedText("/home/thais/Documentos/TCC/Leo/srlInputConll2008.tokens");
		String Alice = teste3.readingPreProcessedText("/home/thais/Documentos/TCC/Leo/conference_domain.txt");
		PreProcessedDataNouns noun = new PreProcessedDataNouns();
		List<String> sentencesList = teste3.tokenizer(Alice);				
		Set<String> testandoConjuntoInfinitivo = new HashSet<String>();		
		Set<String> testandoConjuntoConjugado = new HashSet<String>();		

		WordnetDataRecovery teste2 = new WordnetDataRecovery();
		HashMap<Integer, List<String>> mapOfSentencesAndVerbs = new HashMap<Integer, List<String>>();

		testandoConjuntoConjugado = teste.getLabeledVerbs(preProcessedText);
		testandoConjuntoInfinitivo=teste.convertVerbsToBaseForm(teste.getLabeledVerbs(preProcessedText));
		

		AnalyzingSentences blah = new AnalyzingSentences();
		List<Verb> stateVerb;
		
		stateVerb= blah.stateVerbs(sentencesList, testandoConjuntoConjugado);
		
		for(Verb verb : stateVerb) {
			System.out.print(verb.getVerbBaseForm()+ "\n");
			System.out.print(verb.getVerbFromText() + "\n\n");
		}
		

	
	}

}
