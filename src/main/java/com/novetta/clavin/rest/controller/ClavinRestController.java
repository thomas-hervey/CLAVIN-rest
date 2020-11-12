package com.novetta.clavin.rest.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novetta.clavin.GeoParser;
import com.novetta.clavin.resolver.ResolvedLocation;
import com.novetta.clavin.rest.command.BuildIndex;
import com.novetta.clavin.rest.core.ResolvedLocations;
import com.novetta.clavin.rest.core.ResolvedLocationsMinimum;
import com.novetta.clavin.rest.index.IndexService;

@RestController
@RequestMapping("/api/v1")
public class ClavinRestController {

	private final IndexService iService;
	
	@Autowired
	ClavinRestController(IndexService iService) {
		this.iService = iService;
	}
	
	private GeoParser parser;
	
	private final Logger LOG = LoggerFactory.getLogger(ClavinRestController.class);
	
	@GetMapping("/")
    public String index() {
        return "CLAVIN-rest 1.0";
    }
		
	@PostMapping("/geotag")
	public ResponseEntity<Object> extractAndResolveSimpleLocationsFromText(@RequestBody String text) {
	    
		parser = iService.IndexConnection();
		
        ResolvedLocations result = null;
        try {
            List<ResolvedLocation> resolvedLocations = parser.parse(text);
        result = new ResolvedLocations(resolvedLocations);
        
        } catch (Exception e) {
        	LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(
            	      e.getMessage(), 
            	      HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(
      	      result, 
      	      HttpStatus.OK);
    }

	@PostMapping("/geotagmin")
    public ResponseEntity<Object> extractAndResolveSimpleShortLocationsFromText(@RequestBody String text) {
    
		parser = iService.IndexConnection();
		
        ResolvedLocationsMinimum result = null;
        try {
            List<ResolvedLocation> resolvedLocations = parser.parse(text);
            result = new ResolvedLocationsMinimum(resolvedLocations);
            
        
        } catch (Exception e) {
        	LOG.error(e.getMessage(), e);
        	
            return new ResponseEntity<>(
          	      e.getMessage(), 
          	      HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
        	      result, 
        	      HttpStatus.OK);
    }
		
}