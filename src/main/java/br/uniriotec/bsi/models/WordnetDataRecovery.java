package br.uniriotec.bsi.models;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.uniriotec.bsi.models.ConstructWordnetDictionary;
import edu.mit.jwi.*;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ILexFile;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Word;

public class WordnetDataRecovery {

	
	public void getSupersense(Set<String> verbsInBaseForm) throws IOException{
		ConstructWordnetDictionary newDictionary = new ConstructWordnetDictionary();   
		IDictionary dict = newDictionary.getDictionary(); 
		Map<String,ILexFile> supersense = new HashMap<String,ILexFile>();
 
		for(String verb : verbsInBaseForm) {
			IIndexWord idxWord = dict.getIndexWord(verb, POS.VERB); //look after the word
			IWordID wordID = idxWord.getWordIDs().get(0); //get the meaning in a sentence
		    IWord word = dict.getWord(wordID);
		    ISynset synset = word.getSynset ();
		    System.out.println("Lemma: "+ word.getLemma());
		    System.out.println("SuperSense: "+ synset.getLexicalFile());

		    System.out.println("Verb Frames: "+word.getVerbFrames());
		    supersense.put(word.getLemma(), synset.getLexicalFile());
		    
			
		}
		    	    
		    
		    
		System.out.println(supersense);
		    
	  }
	

	
	
}
