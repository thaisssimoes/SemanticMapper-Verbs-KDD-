package br.uniriotec.bsi.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.IOUtils;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.WordToSentenceProcessor;
import edu.stanford.nlp.util.CoreMap;

public class GetPreProcessedData {
	
	protected StanfordCoreNLP pipeline;


	public String readingPreProcessedText(String filePath) throws FileNotFoundException, IOException {
		
		try(FileInputStream preProcessedText = new FileInputStream(filePath)) {     
		    String preProcessedTextRead = IOUtils.toString(preProcessedText, StandardCharsets.UTF_8);
		    return preProcessedTextRead;
		}		
		
	}

	

	
	
	
	public void metodoTeste(String textoTeste) throws NoSuchElementException {
		textoTeste = textoTeste.replaceAll("\t", " ");
		Scanner scannerTeste1 = new Scanner(textoTeste);
		Set<String> listaTeste = new HashSet<String>();
		
		while(scannerTeste1.hasNextLine()) {
			String currentWord = scannerTeste1.next();
			
			if ((currentWord.equals("VB"))
				||(currentWord.equals("VBZ"))
				||(currentWord.equals("VBD"))
				||(currentWord.equals("VBG"))
				||(currentWord.equals("VBN"))
				||(currentWord.equals("VBP"))) {
				listaTeste.addAll(Arrays.asList(scannerTeste1.nextLine().split(" ")));
				System.out.println(currentWord + "\nOLAAAAAAAAAAAAAAAAAAAAAAA\n" + listaTeste  + "\nOLAAAAAAAAAAAAAAAAAAAAAAA\n");
			}
			else
				System.out.println(currentWord);

			
			
			
			
		}
		
	}
	
//	public ArrayList<String> searchingForVerbs(String preProcessedText){
//		ArrayList<String> listOfSentences = new ArrayList<String>();
//		preProcessedText = preProcessedText.replaceAll("\t", " ");
//		
//		Scanner preProcessedTextRead = new Scanner(preProcessedText);
//		
//		
//		String currentLine = preProcessedTextRead.nextLine();
//		
//	
//		
//		
//		
//		while(preProcessedTextRead.hasNextLine()) {
//
//			String[] arrayOfWordsInASentence = preProcessedTextRead.nextLine().split(" ");
//			listOfSentences.addAll(Arrays.asList(arrayOfWordsInASentence));
//			preProcessedTextRead.nextLine();
//			System.out.println(listOfSentences);
//		
//		}
//				
//		return listOfSentences;
//	}
//	
//	
//	public String getNextWord(String text) {
//		Scanner currentWord = new Scanner(text);
//		
//
//		return currentWord.next();
//		
//	}

}
