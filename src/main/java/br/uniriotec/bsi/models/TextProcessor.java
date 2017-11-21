package br.uniriotec.bsi.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.WordToSentenceProcessor;

public class TextProcessor {
	
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
	 *This method separates the text in sentences 
	 * 
	 * @param Original Text
	 * @return List of sentences
	 * 
	 * */
	
	
	
	public List<String> tokenizer(String text) {
		List<String> sentenceList;

		
		List<CoreLabel> tokens = new ArrayList<CoreLabel>();
		PTBTokenizer<CoreLabel> tokenizer = new PTBTokenizer<CoreLabel>(new StringReader(text), new CoreLabelTokenFactory(), "");
		while (tokenizer.hasNext()) {
		    tokens.add(tokenizer.next());
		}
		//// Split sentences from tokens
		List<List<CoreLabel>> sentences = new WordToSentenceProcessor<CoreLabel>().process(tokens);
		//// Join back together
		int end;
		int start = 0;
		sentenceList = new ArrayList<String>();
		for (List<CoreLabel> sentence: sentences) {
		    end = sentence.get(sentence.size()-1).endPosition();
		    sentenceList.add(text.substring(start, end).trim());
		    start = end;
		}
		
		return sentenceList;	
		
	}
	

	public HashMap<String, List<String>> getVerbsFromSentence(List<String> sentencesList, Set<String> setOfVerbs){
		HashMap<String, List<String>> mapOfSentencesAndVerbs = new HashMap<String, List<String>>();
		List<String> listOfVerbs = new ArrayList<String>();
		
//		for (int i=0; i< sentencesList.size(); i++) {
//			for (String verb : setOfVerbs)
//				for(String sentence : sentencesList) {
//					if(sentence.contains(verb));
//					listOfVerbs.add(verb);
//			}
//			
//			mapOfSentencesAndVerbs.put("Frase "+i+ ": " , listOfVerbs);
//		}
							
		return mapOfSentencesAndVerbs;
	}
	
	
}
