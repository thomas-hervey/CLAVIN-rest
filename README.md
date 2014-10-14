CLAVIN Rest
===========

## Quick Start 

### Download the CLAVIN Rest Server 
 
### Download Geonames 
  
    curl -O http://download.geonames.org/export/dump/allCountries.zip

### Unzip Geonames 

    unzip allCountries.zip

### Download CLAVIN yaml configuration file 

    curl -O https://raw2.github.com/Berico-Technologies/CLAVIN-rest/master/clavin-rest.yml 

### Create a CLAVIN gazetteer 
    
    java -Xmx2048m -jar clavin-rest.jar clavin-rest.yml

### Run the CLAVIN rest server 

    java -Xmx2048m -jar clavin-rest.jar server clavin-rest.yml 

### Geotag an article  

    curl -O https://raw2.github.com/Berico-Technologies/CLAVIN/master/src/test/resources/sample-docs/Somalia-doc.txt

    curl -s --data @Somalia-doc.txt --header "Content-Type: text/plain" http://localhost:9090/api/v0/geotag


## Package creation 

    git clone https://github.com/Berico-Technologies/CLAVIN-rest
    cd CLAVIN-rest
    mvn package 


