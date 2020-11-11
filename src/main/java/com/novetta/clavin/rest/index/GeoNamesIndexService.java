package com.novetta.clavin.rest.index;

import java.io.File;
import java.io.IOException;
import com.bericotech.clavin.resolver.ClavinLocationResolver;
import com.bericotech.clavin.ClavinException;
import com.bericotech.clavin.GeoParser;
import com.bericotech.clavin.gazetteer.query.Gazetteer;
import com.bericotech.clavin.gazetteer.query.LuceneGazetteer;
import com.bericotech.clavin.extractor.ApacheExtractor;

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

	private ApacheExtractor extractor; 
//	private StanfordExtractor extractor;
	
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
			extractor = new ApacheExtractor();
//			extractor = new StanfordExtractor();
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
