package br.uniriotec.bsi.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.IOUtils;

import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.WordToSentenceProcessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;
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
 * Class retrieves the verbs from a preprocessed file
 * 
 *  @author Thaïs Simões
 * 
 * 
 * */
public class PreProcessedDataVerbs {
	
	

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
				",", "vb", "vbz", "vbd", "vbg", "vbn", "vbp", "", "presiding"};
				
		
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
			
			if(verbInfinitive.equals("Is")) {
				mapsVerbsOriginalAndBaseForm("Be", elementInOriginalSet);
				setOfVerbsInfinitiveForm.add("be");
			}
			else {
				mapsVerbsOriginalAndBaseForm(verbInfinitive, elementInOriginalSet);
				setOfVerbsInfinitiveForm.add(verbInfinitive.toLowerCase());

			}

		}		
		return removingStopwords(setOfVerbsInfinitiveForm); 

	}
	/**
	 * This method maps the verb infinitive form and its occurrence on the text
	 * 
	 * 
	 * @param verbBaseForm
	 * @param verbText
	 * @return mapOfVerbs
	 */
	
	public HashMap<String, String> mapsVerbsOriginalAndBaseForm(String verbBaseForm, String verbText){
		
		HashMap<String, String> mapOfVerbs = new HashMap<String,String>();		
		mapOfVerbs.put(verbBaseForm, verbText);
			
		return mapOfVerbs;
		
	}

}
