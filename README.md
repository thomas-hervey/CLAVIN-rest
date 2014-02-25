CLAVIN Rest
===========

## Quick Start 

### Download the CLAVIN Rest Server 

    curl http://clavin.s3.amazonaws.com/snapshots/com/bericotech/clavin-rest/0.1.0-SNAPSHOT/clavin-rest-0.1.0-20140225.135705-2.jar -o clavin-rest.jar 

### Download CLAVIN Bundle snapshot 

    curl http://clavin.s3.amazonaws.com/snapshots/com/bericotech/clavin/1.1.0-20140220-3/clavin-1.1.0-20140220-3-jar-with-dependencies.jar -o clavin.jar 

### Download Geonames 
  
    curl -O http://download.geonames.org/export/dump/allCountries.zip

### Create a CLAVIN gazetteer 

    java -Xmx2048m -jar clavin.jar index

### Download CLAVIN yaml configuration file 

    curl -O https://raw2.github.com/Berico-Technologies/CLAVIN-rest/master/clavin-rest.yml 

### Run the CLAVIN rest server 

    java -Xmx2048m -jar clavin-rest.jar server clavin-rest.yml 

### Geotag an article  

    curl -O https://raw2.github.com/Berico-Technologies/CLAVIN/master/src/test/resources/sample-docs/Somalia-doc.txt

    curl -s --data @Somalia-doc.txt --header "Content-Type: text/plain" http://localhost:9090/api/v0/geotag



## Package creation 

    git clone https://github.com/Berico-Technologies/CLAVIN-rest
    cd CLAVIN-rest
    mvn package 


