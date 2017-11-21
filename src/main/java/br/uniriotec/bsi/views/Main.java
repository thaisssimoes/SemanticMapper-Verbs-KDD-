package br.uniriotec.bsi.views;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.uniriotec.bsi.models.PreProcessedDataOthers;
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
		Set<String> setOfVerbs = new HashSet<String>();		
		Set<String> setOfNouns = new HashSet<String>();	
		Set<String> setOfArt = new HashSet<String>();		
		Set<String> setOfVerbsGerund = new HashSet<String>();		

		PreProcessedDataOthers teste4 = new PreProcessedDataOthers();
		WordnetDataRecovery teste2 = new WordnetDataRecovery();
		HashMap<String, String> mapOfVerbs = new HashMap<String, String>();
		setOfNouns = noun.getLabeledNouns(preProcessedText);
		setOfVerbs=teste.getLabeledVerbs(preProcessedText);
		setOfVerbsGerund=teste.getLabeledVerbsGerund(preProcessedText);

		teste.getLabeledVerbs(preProcessedText);
		AnalyzingSentences syntax = new AnalyzingSentences();

		
		
		Verb verb;
		setOfArt = teste4.getLabeledArticle(preProcessedText);
		for(String sentence : sentencesList) {
//			System.out.print(setOfVerbs);
//
//			System.out.print("Subject: "+verb.getSubject()+ "\n");
//			System.out.print("Verb: "+verb.getVerbFromText()+ "\n");

//			System.out.print("Complement: "+verb.getComplement()+ "\n");
			
//				System.out.print(blah.splitFromVerbs(sentence, setOfVerbs)[0]);
			
			
		}
		PrintWriter writer = new PrintWriter("/home/thais/Documentos/TCC/SemanticMapper-Verbs/syntax.txt", "UTF-8");
		
            for(String sentence : sentencesList) {
    			verb=syntax.joinAnalysis(sentence, setOfNouns, setOfVerbs, setOfArt, setOfVerbsGerund);
            	writer.println("Sentence: "+sentence);
            	writer.println("Subject: "+verb.getSubject());
            	writer.println("Verb: "+verb.getVerbFromText()+ "\n");

            }
            writer.close();
            
        	
	}

}
