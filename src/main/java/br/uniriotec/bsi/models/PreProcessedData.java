package br.uniriotec.bsi.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.IOUtils;

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
	 * This method removes symbols, numbers and abbreviations in the set of verbs
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
		String[] stopwords = {"\'re", "\'s", "\'t", "\'m", "\'d", "\'ve", "0", "_", ".", ",", "vb", "vbz", "vbd", "vbg", "vbn", "vbp", ""};
				
		
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
	
	
	
}
