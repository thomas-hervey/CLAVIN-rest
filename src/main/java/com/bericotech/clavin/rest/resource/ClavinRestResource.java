package com.bericotech.clavin.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import com.bericotech.clavin.GeoParser;
import com.bericotech.clavin.resolver.ResolvedLocation;
import com.bericotech.clavin.rest.core.ResolvedLocations;

@Path("/v0")
@Produces(MediaType.APPLICATION_JSON)
public class ClavinRestResource {
    private final GeoParser parser;    

    public ClavinRestResource(GeoParser parser) {
        this.parser = parser;
    }

    @GET
    public String index() {
        return "CLAVIN-rest 0.1";
    }
    
        
    @POST
    @Path("/geotag")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response extractAndResolveSimpleLocationsFromText(String text) {
    
        ResolvedLocations result = null;
        try {
            List<ResolvedLocation> resolvedLocations = parser.parse(text);
        result = new ResolvedLocations(resolvedLocations);
        
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
      
        return Response.status(200).entity(result).build();
        
    }
}