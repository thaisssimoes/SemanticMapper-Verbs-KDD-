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
 * Class retrieves the nouns from a preprocessed file
 * 
 *  @author Thaïs Simões
 * 
 * 
 * */
public class PreProcessedDataNouns {
	
	

	/**
	 * This method reads the preprocessed file, retrieves the nouns and put them into a set of nouns
	 * 
	 * @param preProcessed Text
	 * @return set of nouns from the preprocessed document
	 * 
	 * */
	
	public Set<String> getLabeledNouns(String preProcessedText) {
		
		Scanner preProcessedTextRead = new Scanner(preProcessedText);
		Set<String> setOfNouns = new HashSet<String>();
		
		while(preProcessedTextRead.hasNext()) {
			String currentWord = preProcessedTextRead.next();
			
			if ((currentWord.equals("NNP"))
				||(currentWord.equals("NN"))
				||(currentWord.equals("NNS"))
				||(currentWord.equals("NNPS"))) {
				setOfNouns.addAll(Arrays.asList(preProcessedTextRead.nextLine().toLowerCase().split(" ")));
			}		
			
		}
		preProcessedTextRead.close();
		
		return removingStopwords(setOfNouns);
		
	}
	
	
	/**
	 * This method removes symbols, numbers, not treated nouns and abbreviations in the set of nouns
	 * 
	 * @param Set of nouns previously removed from the preprocessed text from the Leo application
	 * @return treated set of nouns
	 * 
	 * @throws ConcurrentModificationException
	 * 
	 * TODO: make a universal set of stopwords
	 * 
	 * */

	public Set<String> removingStopwords(Set<String> setOfNouns) throws ConcurrentModificationException{
		String[] stopwords = { "0", "_", ".", ",", "nn", "nnp", "nns", "nnps",  "", "among", "and",
								"an", "which", "who", "the", "its", "a"};
				
		
		for(Iterator<String> i = setOfNouns.iterator(); i.hasNext();) {
			String elementInSet = i.next();
			for (String elementInStopwords : stopwords) {
				if(elementInSet.equals(elementInStopwords)) {
					i.remove();
				}
			}			
		}

	return setOfNouns;
	}
	
	

}
