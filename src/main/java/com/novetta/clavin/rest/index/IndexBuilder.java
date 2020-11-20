package com.novetta.clavin.rest.index;

public interface IndexBuilder {
	
	boolean indexExists(String location);
	
	boolean buildIndex(String location, String[] args);
	
}
