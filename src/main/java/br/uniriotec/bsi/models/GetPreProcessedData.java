package br.uniriotec.bsi.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.IOUtils;


public class GetPreProcessedData {
	
	public String readingPreProcessedText(String filePath) throws FileNotFoundException, IOException {
		
		try(FileInputStream preProcessedText = new FileInputStream(filePath)) {     
		    String preProcessedTextRead = IOUtils.toString(preProcessedText, StandardCharsets.UTF_8);
		    preProcessedTextRead = preProcessedTextRead.replaceAll("\t", " ");
		    return preProcessedTextRead;
		}		
		
	}

		
	public Set<String> getLabeledVerbs(String preProcessedText) throws NoSuchElementException {
		
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
//				System.out.println(setOfVerbs);
			}		
			
		}		
		return removingStopwords(setOfVerbs);
		
	}
	

	public Set<String> removingStopwords(Set<String> setOfVerbs) throws ConcurrentModificationException{
		String[] stopwords = {"\'re", "\'s", "\'t", "\'m", "\'d", "\'ve", "0", "_", ".", ",", "vb", "vbz", "vbd", "vbg", "vbn", "vbp", ""};
				
		
		for(Iterator<String> i = setOfVerbs.iterator(); i.hasNext();) {
			String elementInSet = i.next();
			for (String elementInStopwords : stopwords) {
				if(elementInSet.equals(elementInStopwords)) {
					i.remove();
										
//					System.out.println("Opa cheguei aqui atraves do:" + elementInStopwords);
				}
			}			
		}
		System.out.println(setOfVerbs);

	return setOfVerbs;
	}
	
	
	
	
	
	
	
	
	
	
	
}
