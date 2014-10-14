package com.bericotech.clavin.rest.core;

import com.bericotech.clavin.resolver.ResolvedLocation;

public class ResolvedLocationMinimum {
	
	public final int geonameID;
	public final String name;
	public final String countryCode;
	public final double latitude;
	public final double longitude;
	
	ResolvedLocationMinimum(ResolvedLocation rl) {
		this.geonameID = rl.geoname.geonameID;
		this.name = rl.geoname.name;
		this.countryCode = rl.geoname.primaryCountryCode.toString();
		this.latitude = rl.geoname.latitude;
		this.longitude= rl.geoname.longitude;
	}

}
