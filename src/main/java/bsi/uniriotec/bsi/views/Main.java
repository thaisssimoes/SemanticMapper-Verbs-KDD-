package bsi.uniriotec.bsi.views;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.uniriotec.bsi.models.PreProcessedData;
import br.uniriotec.bsi.models.WordnetDataRecovery;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		PreProcessedData teste = new PreProcessedData();
		String preProcessedText = teste.readingPreProcessedText("/home/thais/Documentos/TCC/Leo/srlInputConll2008.tokens");
		
		
		
		
		WordnetDataRecovery teste2 = new WordnetDataRecovery();
//		teste2.getLemma();
		teste2.convertVerbsToBaseForm(teste.getLabeledVerbs(preProcessedText));
		
//		teste.getLabeledVerbs(preProcessedText);
		
	}

}
