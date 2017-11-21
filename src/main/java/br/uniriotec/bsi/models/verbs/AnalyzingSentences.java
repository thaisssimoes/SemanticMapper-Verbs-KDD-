package br.uniriotec.bsi.models.verbs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import br.uniriotec.bsi.models.Verb;

public class AnalyzingSentences {
	
	public List<Verb> stateVerbs(List<String> sentencesList, Set<String> setOfVerbs) {
		List<Verb> verbs = new ArrayList<Verb>();

		List<String> sentences;
		Verb verb = new Verb();
		verb.setVerbBaseForm("be");
		
		for(String sentence : sentencesList) {
			if(sentence.contains("is")) {
				sentences = Arrays.asList(sentence.split(" is ")); 
				if(!getNextWord(sentencesList, setOfVerbs).equals("")) {
					verb.setVerbFromText("is " + getNextWord(sentences, setOfVerbs));
				}
				else {
					verb.setVerbFromText("is");
				}
			}
			else if (sentence.contains("are")) {
				sentences = Arrays.asList(sentence.split(" are ")); 
				if(!getNextWord(sentencesList, setOfVerbs).equals("")) {
					verb.setVerbFromText("are " + getNextWord(sentences, setOfVerbs));
				}
				else {
					verb.setVerbFromText("are");
					
				}	
			}
			
		}
		return verbs;
	}
	
	
	
	public String getNextWord(List<String> sentencesList, Set<String> setOfVerbs) {
		
		outerloop:
		for (int i =0; i < sentencesList.size(); i++) {
			

			Scanner currentSentence = new Scanner(sentencesList.get(i));
			while(currentSentence.hasNext()) {
				if(setOfVerbs.contains(currentSentence.next())) {
					return currentSentence.next();
				} 
				if(sentencesList.get(i).equals("is")
				||sentencesList.get(i).equals("are")) {
//					currentSentence = new Scanner(sentencesList.get(i++));
					return currentSentence.next();
				}
				
			}
		}
		return "";
	}
	
}