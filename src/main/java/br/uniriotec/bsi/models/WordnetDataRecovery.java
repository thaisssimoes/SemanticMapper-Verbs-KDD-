package br.uniriotec.bsi.models;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import br.uniriotec.bsi.models.ConstructWordnetDictionary;
import edu.mit.jwi.*;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Word;











import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;



public class WordnetDataRecovery {

	
//	public void getLemma() throws IOException{
//		ConstructWordnetDictionary newDictionary = new ConstructWordnetDictionary();   
//		IDictionary dict = newDictionary.getDictionary(); 
//		 
//	   // look up first sense of the word "dog"
//		    IIndexWord idxWord = dict.getIndexWord("bought", POS.VERB); //look after the word
//		    	    
//		    
//		    IWordID wordID = idxWord.getWordIDs().get(0); //get the meaning in a sentence
//		    IWord word = dict.getWord(wordID);
//		    System.out.println("Id = " + wordID);
//		    System.out.println("Lemma = " + word.getLemma());
//		    System.out.println("Gloss = " + word.getSynset().getGloss());
//		    ISynset synset = word.getSynset ();
//		    
//		    
//		    for (IWord w : synset.getWords()) {
//		    	System.out.println(w.getSynset().getGloss()+"\n") ;
//		    }
//		    
//		    
//	  }
	
	public void convertVerbsToBaseForm(Set<String> setOfVerbs){
		
		  Lexicon lexicon = Lexicon.getDefaultLexicon();
          NLGFactory nlgFactory = new NLGFactory(lexicon);
          Realiser realiser = new Realiser(lexicon);

		
		
	}
	
	
}
