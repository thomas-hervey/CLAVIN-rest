package com.bericotech.clavin.rest;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class ClavinRestConfiguration extends Configuration {

	@NotEmpty
    @JsonProperty
    private String luceneDir;
    
    @NotNull
    @JsonProperty
    private Integer maxHitDepth;
    
    @NotNull
    @JsonProperty
    private Integer maxContextWindow; 
    
    @NotNull
    @JsonProperty
    private Boolean fuzzy; 
    
    public String getLuceneDir() {
        return luceneDir;
    }
    
    public Integer getMaxHitDepth() {
        return maxHitDepth;
    }
    
    public Boolean getFuzzy() {
    	return fuzzy;
    }
    
    public Integer getMaxContextWindow() {
    	return maxContextWindow;
    }
    
}