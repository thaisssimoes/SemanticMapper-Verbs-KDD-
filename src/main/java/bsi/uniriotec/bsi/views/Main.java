package bsi.uniriotec.bsi.views;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.uniriotec.bsi.models.GetPreProcessedData;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		GetPreProcessedData teste = new GetPreProcessedData();
		String preProcessedText = teste.readingPreProcessedText("/home/thais/Documentos/TCC/Leo/srlInputConll2008.tokens");
		//System.out.println(preProcessedText);
//		System.out.println(teste.gettingVerbs(preProcessedText));
		teste.getLabeledVerbs(preProcessedText);
		
	}

}
