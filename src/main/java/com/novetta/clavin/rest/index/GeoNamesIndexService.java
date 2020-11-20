package com.novetta.clavin.rest.index;

import java.io.File;
import java.io.IOException;
import com.novetta.clavin.resolver.ClavinLocationResolver;
import com.novetta.clavin.ClavinException;
import com.novetta.clavin.GeoParser;
import com.novetta.clavin.gazetteer.query.Gazetteer;
import com.novetta.clavin.gazetteer.query.LuceneGazetteer;
import com.novetta.clavin.extractor.AdaptNlpExtractor;
import com.novetta.clavin.extractor.ApacheExtractor;
import com.novetta.clavin.extractor.LocationExtractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
final class GeoNamesIndexService implements IndexService {

	@Value( "${luceneDir}" )
	private String luceneDir;

	@Value( "${maxHitDepth}" )
	private int maxHitDepth;

	@Value( "${maxContextWindow}" )
	private int maxContextWindow;	
	
	@Value( "${fuzzy}" )
	private boolean fuzzy;	

	private LocationExtractor extractor; 
	
	private final Logger LOG = LoggerFactory.getLogger(GeoNamesIndexService.class);
	
	public ClavinLocationResolver IndexResolver() {
		
        Gazetteer gazetteer = null;
        
		try {
			gazetteer = new LuceneGazetteer(new File(luceneDir));
		} catch (ClavinException e) {
			e.printStackTrace();
		}
		
		ClavinLocationResolver resolver = new ClavinLocationResolver(gazetteer);
		
		return resolver;
	}
	
	public GeoParser IndexConnection() {
	        
        Gazetteer gazetteer = null;
        
		try {
			gazetteer = new LuceneGazetteer(new File(luceneDir));
		} catch (ClavinException e) {
			LOG.error(e.getMessage(), e);
		}

		try {
			
			/*
			 * OpenNLP is the default extractor. If you want to use a different
			 * text extractor, comment this line out, and uncomment out one of the
			 * other extractors below.
			 */
			extractor = new ApacheExtractor();
			
			/*
			 * Using the AdaptNLP Extractor requires an instance of AdaptNLP
			 * running, and accessible to web calls.  The default constructor for
			 * AdaptNlpExtractor sets host and port to 'localhost' and 5000 
			 * respectively. You can alter this by passing (host, port) to
			 * AdaptNlpExtractor(host, port). 
			 */
			//extractor = new AdaptNlpExtractor();
			
			
			/*
			 * Using the StanfordExtractor requires substituting the CLAVIN 
			 * dependency in the POM with one for CLAVIN-NERD, which can be found on
			 * GitHub at the following URL, and is published to Maven Central. 
			 * (https://github.com/Novetta/CLAVIN-NERD). A reference to 
			 * CLAVIN-NERD is commented out in the POM under the CLAVIN dependency.
			 * 
			 * We are in the process of updating CLAVIN-NERD's dependencies and 
			 * will release a new version under com.novetta in the near term.
			 */
			//extractor = new StanfordExtractor();

		} catch (ClassCastException e) {
			LOG.error(e.getMessage(), e);
		//} catch (ClassNotFoundException e) {
		//	LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		
        GeoParser parser = new GeoParser(extractor, gazetteer, maxHitDepth, maxContextWindow, false);
        
        return parser;
	}
	
}
