package com.novetta.clavin.rest.index;

import com.bericotech.clavin.GeoParser;
import com.bericotech.clavin.resolver.ClavinLocationResolver;

public interface IndexService {

	GeoParser IndexConnection();
	ClavinLocationResolver IndexResolver();
	
}