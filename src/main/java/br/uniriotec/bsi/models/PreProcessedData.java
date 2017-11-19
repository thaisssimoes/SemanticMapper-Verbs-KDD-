package br.uniriotec.bsi.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.IOUtils;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

/**
 * Class that reads the preprocessed text file and retrieve the verbs
 * 
 *  @author Thaïs Simões
 * 
 * 
 * */
public class PreProcessedData {
	
	/**
	 * 
	 * This method intends to read the preprocessed file from Leo and turns it to a String
	 * 
	 * @param File path of the preprocessed text file
	 * @return Preprocessed file as a String
	 * 
	 * @throws IOException
	 * @throws FileNotFoundExcepetion
	 * 
	 * */
	
	public String readingPreProcessedText(String filePath) throws FileNotFoundException, IOException {
		
		try{
			FileInputStream preProcessedText = new FileInputStream(filePath); 
		    String preProcessedTextRead = IOUtils.toString(preProcessedText, StandardCharsets.UTF_8);
		    preProcessedTextRead = preProcessedTextRead.replaceAll("\t", " ");
		    return preProcessedTextRead;
		}
		catch(IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally {}
		
	}

	/**
	 * This method reads the preprocessed file, retrieves the verbs and put them into a set of verbs
	 * 
	 * @param preProcessed Text
	 * @return set of verbs from the preprocessed document
	 * 
	 * */
	
	public Set<String> getLabeledVerbs(String preProcessedText) {
		
		Scanner preProcessedTextRead = new Scanner(preProcessedText);
		Set<String> setOfVerbs = new HashSet<String>();
		
		while(preProcessedTextRead.hasNext()) {
			String currentWord = preProcessedTextRead.next();
			
			if ((currentWord.equals("VB"))
				||(currentWord.equals("VBZ"))
				||(currentWord.equals("VBD"))
				||(currentWord.equals("VBG"))
				||(currentWord.equals("VBN"))
				||(currentWord.equals("VBP"))) {
				setOfVerbs.addAll(Arrays.asList(preProcessedTextRead.nextLine().toLowerCase().split(" ")));
			}		
			
		}
		preProcessedTextRead.close();
		return removingStopwords(setOfVerbs);
		
	}
	
	
	/**
	 * This method removes symbols, numbers, not treated verbs and abbreviations in the set of verbs
	 * 
	 * @param Set of verbs previously removed from the preprocessed text from the Leo application
	 * @return treated set of verbs
	 * 
	 * @throws ConcurrentModificationException
	 * 
	 * TODO: make a universal set of stopwords
	 * 
	 * */

	public Set<String> removingStopwords(Set<String> setOfVerbs) throws ConcurrentModificationException{
		String[] stopwords = {"\'re", "\'s", "\'t", "\'m", "\'d", "\'ve", "0", "_", ".", 
				",", "vb", "vbz", "vbd", "vbg", "vbn", "vbp", "", "scolded", "lying",
				"peeped", "found", "occurred", "tired", "fitted", "knelt", "cheated", 
				"brightned", "dozing", "hung", "pegs", "brightened", "is", "courtsey",
				"would", "lit"};
				
		
		for(Iterator<String> i = setOfVerbs.iterator(); i.hasNext();) {
			String elementInSet = i.next();
			for (String elementInStopwords : stopwords) {
				if(elementInSet.equals(elementInStopwords)) {
					i.remove();
				}
			}			
		}

	return setOfVerbs;
	}
	
	/**
	 * This method returns a set verbs from the preprocessed text in the infinitive form
	 * 
	 * @param set of verbs conjugated
	 * @return set of verns in the infinitive form
	 * 
	 * TODO: figure it out how to treat some verbs: curtsey, will and to be (and the ones in stopword list)
	 * 
	 * */
	
	public Set<String> convertVerbsToBaseForm(Set<String> setOfVerbs){
		Set<String> setOfVerbsInfinitiveForm = new HashSet<String>();
		
		/*Instanciate SimpleNLG objects*/
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);
		SPhraseSpec verb = nlgFactory.createClause();
		
		
		for(String elementInOriginalSet : setOfVerbs) {
			verb.setVerb(elementInOriginalSet);
			verb.setFeature(Feature.FORM, Tense.PRESENT);
			String verbInfinitive = realiser.realiseSentence(verb);
			verbInfinitive = verbInfinitive.replace(".", ""); //removes a dot that comes with the method
			setOfVerbsInfinitiveForm.add(verbInfinitive.toLowerCase());
		}
		
		/**
		 * Pequena gambiarra por enquanto ate segundo momento
		 * Coloca os verbos que nao foram convertidos
		 * */
		setOfVerbsInfinitiveForm.add("be");
		setOfVerbsInfinitiveForm.add("curtsy");
		setOfVerbsInfinitiveForm.add("will");

		
		return removingStopwords(setOfVerbsInfinitiveForm);
		
	}
	
	/**
	 * Pequena gambiarra por enquanto ate segundo momento
	 * Coloca os verbos que nao foram convertidos e remove as outras formas.
	 * */
	
	
	
}
