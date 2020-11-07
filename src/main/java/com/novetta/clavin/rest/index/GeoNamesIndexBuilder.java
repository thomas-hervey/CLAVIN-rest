package com.novetta.clavin.rest.index;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bericotech.clavin.index.IndexDirectoryBuilder;


import java.nio.file.*;


@Service
final class GeoNamesIndexBuilder implements IndexBuilder {

	private final Logger LOG = LoggerFactory.getLogger(GeoNamesIndexBuilder.class);
	
	//final default directory location
	final String DEFAULT_DIRECTORY = "./IndexDirectory";
	
	public boolean indexExists(String location) {
		//check the directory
		if (location.equals(null))
			location = DEFAULT_DIRECTORY;
		
		Path destination = Paths.get(location);
		
		if (Files.exists(destination)) {
		    return true;
		} else {
			return false;
		}
	}

	public boolean buildIndex(String location, String[] opts) {

        try {
			IndexDirectoryBuilder.main(opts);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
		
		return true;
	}
	
}
