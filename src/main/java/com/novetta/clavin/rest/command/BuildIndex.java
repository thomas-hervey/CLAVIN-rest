package com.novetta.clavin.rest.command;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.novetta.clavin.rest.index.IndexBuilder;

@Profile("Build")
@Component
public class BuildIndex implements CommandLineRunner {

	final String DEFAULT_DIRECTORY = "./IndexDirectory";
	
	private final IndexBuilder iBuilder;
	
    @Autowired
    BuildIndex(IndexBuilder iBuilder) {
        this.iBuilder = iBuilder;
    }
    
    private final Logger LOG = LoggerFactory.getLogger(BuildIndex.class);
	
    public void run(String... args) throws Exception {

    	LOG.info("IndexSetup running...");
        // get location from args, or use default
        String destination = DEFAULT_DIRECTORY;

        // Test to see if 'allCountries.txt' is in local directory
        LOG.info("Test for 'allCountries.txt' in local directory..."); 
        File f = new File("./allCountries.txt");
        if(f.exists()) { 
                        
            LOG.info("Creating options...");
            // send gazetteer file locations
            String[] opts = new String[] { "--gazetteer-files",
                    "./allCountries.txt:./SupplementaryGazetteer.txt"};
                    //"--with-full-ancestry"};

            LOG.info("Testing to see if IndexDirectory exists, if not, create it...");
            // Test to see if the directory exists
            // if the directory does not exist, create it
            if (!iBuilder.indexExists(destination)) {
                LOG.info("Building Index...");
                
                iBuilder.buildIndex(destination, opts);
                
                LOG.info("Index Build Complete");
            } else {
            	LOG.error("Index already exists. Remove " + DEFAULT_DIRECTORY + " before proceeding.");
            }
            
        } else {
        	LOG.error("allCountries.txt does not exist in local directory");
        }
        
        System.exit(0);
        
    }
}

