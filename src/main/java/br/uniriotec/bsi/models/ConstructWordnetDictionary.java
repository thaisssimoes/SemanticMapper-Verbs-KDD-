package br.uniriotec.bsi.models;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;

public class ConstructWordnetDictionary {

	public IDictionary getDictionary() throws IOException{
		
		return openDictionary(constructTheURLDictionary());
	}  
	
	public URL constructTheURLDictionary() throws IOException {
		String wnhome = System.getenv("WNHOME");
		String path = wnhome + File.separator + "dict";
		URL url = null;
		try{ 
			url = new URL("file", null, path);
			return url;
			} 
		catch(MalformedURLException e){
			e.printStackTrace();
			}
		return url;
	}
	
	public IDictionary openDictionary(URL url) throws IOException {
		// construct the dictionary object and open it
		IDictionary dict = new Dictionary(url);
		dict.open();
		
		return dict;
		
	}
}
