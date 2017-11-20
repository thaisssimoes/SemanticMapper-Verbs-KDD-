package br.uniriotec.bsi.models;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import br.uniriotec.bsi.models.Verb;
import br.uniriotec.bsi.models.ConstructWordnetDictionary;
import edu.mit.jwi.*;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ILexFile;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IVerbFrame;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Word;

public class WordnetDataRecovery {

	/**
	 * This method gets the Semantic Type of each verb supplied by wordnet and populate a hashmap with the verb [key] = semanticType [value]
	 * 
	 * @param Set of strings on the infinitive (must be in infinitive, otherwise wordnet will not read)
	 * 
	 * */
	
	public HashMap<String,List<IVerbFrame>> getSemanticTypeOfVerbs(Set<String> verbsInInfinitiveForm) throws IOException{
		ConstructWordnetDictionary newDictionary = new ConstructWordnetDictionary(); //constructs the wordnet dictionary  
		IDictionary dict = newDictionary.getDictionary(); //opens the dictionary
		
		HashMap<String,List<IVerbFrame>> semanticType = new HashMap<String,List<IVerbFrame>>();
 
		for(String currentVerb : verbsInInfinitiveForm) {
			IIndexWord idxWord = dict.getIndexWord(currentVerb, POS.VERB); //get the ID of the word on WordNet
			IWordID wordID = idxWord.getWordIDs().get(0); //get the meaning in a sentence
		    IWord word = dict.getWord(wordID); //get word on the dictionary by ID
		    semanticType.put(word.getLemma(),  word.getVerbFrames());	
		}
		 return semanticType;	    		    
	  }
	

	public HashMap<String,ILexFile> getSupersense(Set<String> verbsInInfinitiveForm) throws IOException{
		ConstructWordnetDictionary newDictionary = new ConstructWordnetDictionary(); //constructs the wordnet dictionary  
		IDictionary dict = newDictionary.getDictionary(); //opens the dictionary
		
		HashMap<String,ILexFile> wordSupersense = new HashMap<String,ILexFile>();
 
		for(String currentVerb : verbsInInfinitiveForm) {
			IIndexWord idxWord = dict.getIndexWord(currentVerb, POS.VERB); //get the ID of the word on WordNet
			IWordID wordID = idxWord.getWordIDs().get(0); //get the meaning in a sentence
		    IWord word = dict.getWord(wordID); //get word on the dictionary by ID
		    ISynset supersense = word.getSynset();
		    System.out.print(word.getLemma() +":  " + supersense.getLexicalFile() +"\n");
		    
		    
		    
		    wordSupersense.put(word.getLemma(),  supersense.getLexicalFile());	
		}
		 return wordSupersense;	    		    
	  }
	
}
