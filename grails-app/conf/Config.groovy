// configuration for plugin testing - will not be included in the plugin zip
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error 'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'

    warn 'org.mortbay.log'
    debug 'org.grails.plugins.elasticsearch'
}
elasticSearch {
    /**
     * Date formats used by the unmarshaller of the JSON responses
     */
    date.formats = ["yyyy-MM-dd'T'HH:mm:ss.S'Z'"]

    /**
     * Hosts for remote ElasticSearch instances.
     * Will only be used with the "transport" client mode.
     * If the client mode is set to "transport" and no hosts are defined, ["localhost", 9300] will be used by default.
     */
    client.hosts = [
            [host: 'localhost', port: 9300]
    ]

    disableAutoIndex = false
    index {
        compound_format = true
    }
    unmarshallComponents = true
}

environments {
    development {
        elasticSearch {
            /**
             * Possible values : "local", "node", "transport"
             */
            client.mode = 'local'
            client.transport.sniff = true
            bulkIndexOnStartup = true
        }
    }

    test {
        elasticSearch {
            client.mode = 'local'
            client.transport.sniff = true
            index.store.type = 'memory'
        }
    }

    production {
        elasticSearch.client.mode = 'node'
    }
}
// The following properties have been added by the Upgrade process...
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"

grails.doc.authors = 'Manuarii Stein, Stephane Maldini, Serge P. Nekoval'
grails.doc.license = 'Apache License 2.0'
// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line 
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */
