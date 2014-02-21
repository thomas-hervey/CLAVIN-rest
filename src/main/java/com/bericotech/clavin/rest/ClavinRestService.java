package com.bericotech.clavin.rest;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.bericotech.clavin.GeoParser;
import com.bericotech.clavin.nerd.StanfordExtractor;
import com.bericotech.clavin.resolver.LuceneLocationResolver;
import com.bericotech.clavin.rest.resource.ClavinRestResource;


public class ClavinRestService extends Service<ClavinRestConfiguration> {
	
    public static void main(String[] args) throws Exception {
        new ClavinRestService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ClavinRestConfiguration> bootstrap) {
        bootstrap.setName("clavin-rest");
        //bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/"));
        
    }

    @Override
    public void run(ClavinRestConfiguration configuration,
                    Environment environment) throws ClassCastException, ClassNotFoundException, IOException, ParseException {
        final String luceneDir = configuration.getLuceneDir();
        final Integer maxHitDepth = configuration.getMaxHitDepth();
        final Integer maxContextWindow = configuration.getMaxContextWindow();
        final Boolean fuzzy = configuration.getFuzzy();
             
        LuceneLocationResolver resolver = new LuceneLocationResolver(new File(luceneDir), maxHitDepth, maxContextWindow);
        StanfordExtractor extractor = new StanfordExtractor();  	   	
        GeoParser parser = new GeoParser(extractor, resolver, fuzzy);
        
        environment.addResource(new ClavinRestResource(parser));
    }

}