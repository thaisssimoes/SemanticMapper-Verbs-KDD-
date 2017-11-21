package br.uniriotec.bsi.models.verbs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import br.uniriotec.bsi.models.Verb;

public class AnalyzingSentences {
	
	Verb verb = new Verb();

	/**
	 * This method is responsible for dividing the sentence in before and after the main verb
	 * 
	 * @param set of verbs no conjugated
	 * 
	 * */
	public String[] splitFromVerbs(String sentence, Set<String> setOfVerbs) {
		String[] splitSentenceWords = sentence.toLowerCase().split(" ");
		String[] splitSentence= {""};
		String auxWord1;
		String auxWord2;
		
		for(int i=0; i< splitSentenceWords.length;i++) {
			auxWord1 = splitSentenceWords[i];
			auxWord2 = splitSentenceWords[i+1];
			if(setOfVerbs.contains(auxWord1) && setOfVerbs.contains(auxWord2) ) {
				splitSentence = sentence.split(auxWord2);
				this.verb.setVerbFromText(auxWord2);
				break;
			}
			else {
				if(setOfVerbs.contains(auxWord1) && !setOfVerbs.contains(auxWord2)) {
				splitSentence = sentence.split(auxWord1);
				this.verb.setVerbFromText(auxWord1);
				break;

				}

			}
		
		}
		return splitSentence;
	}
	
	/**
	 * This method is responsible to return the subject from the sentence
	 * 
	 * @param sentenced used (first part from the split sentence above)
	 * @param set of nouns
	 * @param set of verbs not conjugated
	 * @param set of verbs in gerund (that I think are being used as adjective)
	 * 
	 * @return subject
	 * 
	 * */
	public String getSubject(String sentence, Set<String> setOfNouns, Set<String> setOfVerbs,  Set<String> setOfVerbsGerund) {
		String[] splitSentenceWords = sentence.toLowerCase().split(" ");
		String word="";
		String auxWord1;
		String auxWord2;

		for(int i=0; i< splitSentenceWords.length;i++) {
			auxWord1 = splitSentenceWords[i];
			
			if(auxWord1.equals("a") || auxWord1.equals("an") || auxWord1.equals("the")) {
				auxWord1 = splitSentenceWords[i+1];
				auxWord2 = splitSentenceWords[i+2];
				
				if(setOfNouns.contains(auxWord1) && setOfNouns.contains(auxWord2) ) 
					word = (word + " ")+ (auxWord1 +" " + auxWord2);
				
				else if(setOfVerbs.contains(auxWord1) || setOfNouns.contains(auxWord2) ) {
					word = (word + " ")+ (auxWord1 +" " + auxWord2);
				}
				
				else if(setOfVerbs.contains(auxWord1) && setOfNouns.contains(auxWord2) ) {
					word = (word + " ")+ (auxWord1 +" " + auxWord2);
				}
			
			}
			
			else {
				if(splitSentenceWords.length ==1) {
					word = word = auxWord1;
				}
				else if(setOfNouns.contains(auxWord1) && word.equals("")){ //caso da presentation
					word = word + " " + auxWord1;
				}
				
				
				
			}
			
			
		}
		return word;
		
	}
	
	/**
	 * This method SHOULD BE the one responsible to return the complement from the sentence
	 * 
	 * @param sentenced used (first part from the split sentence above)
	 * @param set of nouns
	 * @param set of verbs not conjugated
	 * 
	 * @return complement	 
	 * */
	
	public String getComplement(String sentence, Set<String> setOfNouns , Set<String> setOfVerbs) {
		String[] splitSentenceWords = sentence.toLowerCase().split(" ");
		String complement="";
		String auxWord1;
		String auxWord2;

		for(int i=0; i< splitSentenceWords.length;i++) {
			auxWord1 = splitSentenceWords[i];
			if(auxWord1.equals("a") || auxWord1.equals("an") || auxWord1.equals("the") 	|| auxWord1.equals("by")) {
				auxWord1 = splitSentenceWords[i+1];
				auxWord2 = splitSentenceWords[i+2];

				if(setOfNouns.contains(auxWord1) || setOfNouns.contains(auxWord2)) {
					complement = complement + " " + auxWord1 +  " " + auxWord2 ;
				}
				if(setOfVerbs.contains(auxWord1)) {
					complement = complement + " " + auxWord1;
				}
			}
			else {
				if(! setOfVerbs.contains(auxWord1)) {
					i++;
				}
				else if(! setOfNouns.contains(auxWord1)) {
					i++;
				}
			
		}
		}
		
		return complement;
		
	}
	
	
	/**
	 * 
	 * Method is responsible to set the verb values to a Verb object
	 * 
	 * @param sentenced used (first part from the split sentence above)
	 * @param set of nouns
	 * @param set of verbs not conjugated
	 * @param set of verbs in gerund (that I think are being used as adjective)
	 * @param set of adjectives
	 * 
	 * return verb object with verb, subject and (TODO)complement
	 * 
	 * */
	
	
	public Verb joinAnalysis( String sentence, Set<String> setOfNouns, Set<String> setOfVerbs, Set<String> setOfAdj, Set<String> setOfVerbsGerund) {
		
		String[] sentences = splitFromVerbs(sentence, setOfVerbs);
		List<String> complement = new ArrayList<String>();
		
		verb.setSubject(getSubject(sentences[0], setOfNouns, setOfVerbs, setOfVerbsGerund));
		for(int i =0 ; i < sentences.length; i++) {
			System.out.println("FRASE: " + sentences[1]);
			System.out.println("COMPLEMENTO: "+getComplement(sentences[1], setOfNouns , setOfVerbs));
		}
		
		
//		complement = verb.getComplement();
//		complement.add(getComplement(sentence, setOfNouns , setOfVerbs));
//		verb.setComplement(complement);
		return verb;
	}
	
	
//	
//	
//	//string sentence [1] da separacao de verbos
//	public List<String> complement(String sentence, Set<String> setOfNouns) {
//		List<String> complemento= new ArrayList<String>();
//
//		if(hasPreposition(sentence)) {
//			complemento.add(findingPreposition(sentence, setOfNouns));
//			return complemento;
//		}
//		else {
//			complemento.add(findingArticles(sentence));
//
//			return complemento;
//		}
//	}
//	
//	public List<String> complementOR(String sentence , Set<String> setOfNouns) {
//		String sentenceArray[] = sentence.split(" or ");
//		List<String> complemento= new ArrayList<String>();
//		for(String phrase : sentenceArray) {
//			complemento.addAll(complement(sentence, setOfNouns));
//		}
//		return complemento;
//	}
//	
//	public List<String> complementAND(String sentence , Set<String> setOfNouns) {
//		String sentenceArray[] = sentence.split(" and ");
//		List<String> complemento= new ArrayList<String>();
//
//		for(String phrase : sentenceArray) {
//			complemento.addAll(complement(sentence, setOfNouns));
//		}
//		
//		return complemento;
//	}
//	
//	public List<String> complementWHO(String sentence , Set<String> setOfNouns) {
//		String sentenceArray[] = sentence.split("who");
//		List<String> complemento= new ArrayList<String>();
//
//		for(String phrase : sentenceArray) {
//			complemento.addAll(complement(sentence, setOfNouns));
//		}
//		
//		return complemento;
//
//	}
//		
//	public List<String> complementWHICH(String sentence , Set<String> setOfNouns) {
//		String sentenceArray[] = sentence.split("which");
//		List<String> complemento= new ArrayList<String>();
//
//		for(String phrase : sentenceArray) {
//			complemento.addAll(complement(sentence, setOfNouns));
//		}
//		
//		return complemento;
//	}
//	
//	public String findingArticles(String sentence) {
////		int firstWord;
////		int lastWord = sentence.length()-1;
////		if(sentence.contains("the") ) {
////			firstWord=sentence.indexOf("the");			
////			return sentence.substring(firstWord, lastWord);
////
////		}
////		else if (sentence.contains("a")){
////			firstWord=sentence.indexOf("a");
////			return sentence.substring(firstWord, lastWord);
////
////		}
////		else {
////			if(sentence.contains("an")) {
////		}
////			firstWord=sentence.indexOf("an");
////			return sentence.substring(firstWord, lastWord);
////		}
////		
////		
//		return "";
//		
//	}
//	
//	public boolean hasArticle(String sentence) {
//		if(sentence.contains("the")||(sentence.contains("a"))||(sentence.contains("an"))){
//			return  true;	
//		}
//				
//		else
//			return false;
//		}
//	
//	public String findingPreposition(String sentence, Set<String> setOfNouns) {
////		int firstWord = sentence.indexOf("by");;
////		String lastWord = sentence.substring(sentence.lastIndexOf(" ")+1);
////		int lastWordIndex = sentence.indexOf(lastWord);
////		
////		sentence = sentence.substring(firstWord, lastWordIndex);
////		
////		sentence.split("by");
////		
////		Scanner currentWord = new Scanner(sentence);
////		String complement ="";
////		
////		while(currentWord.hasNext()) {
////			if(setOfNouns.contains(currentWord.next())) {
////				complement = complement + currentWord.next();
////			}
////			else
////				break;
////		}
//		return "";
//		
//		
//	}
//	
//	public boolean hasPreposition(String sentence) {
//		if(sentence.contains("by")||(sentence.contains("on"))){
//			return  true;	
//		}
//				
//		else
//			return false;
//		}
	
	
}