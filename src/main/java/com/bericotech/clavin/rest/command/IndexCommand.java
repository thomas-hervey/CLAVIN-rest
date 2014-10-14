package com.bericotech.clavin.rest.command;

import com.bericotech.clavin.index.IndexDirectoryBuilder;
import com.bericotech.clavin.rest.ClavinRestConfiguration;
import com.yammer.dropwizard.cli.ConfiguredCommand;
import com.yammer.dropwizard.config.Bootstrap;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import static java.lang.System.out;



public class IndexCommand extends ConfiguredCommand<ClavinRestConfiguration> {


    public IndexCommand() {
        super("index", "Index a geonames.org gazetteer");
    }


    @Override
    protected void run(Bootstrap<ClavinRestConfiguration> bootstrap,
                       Namespace namespace,
                       ClavinRestConfiguration configuration) throws Exception {

        // send empty arguments for now
        String[] args = new String[1];
        args[0] = "";

        IndexDirectoryBuilder.main(args);

    }

}

