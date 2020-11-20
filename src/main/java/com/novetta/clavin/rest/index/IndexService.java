package com.novetta.clavin.rest.index;

import com.novetta.clavin.GeoParser;
import com.novetta.clavin.resolver.ClavinLocationResolver;

public interface IndexService {

	GeoParser IndexConnection();
	ClavinLocationResolver IndexResolver();
	
}