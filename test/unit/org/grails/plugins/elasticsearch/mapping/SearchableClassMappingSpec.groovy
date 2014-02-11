package org.grails.plugins.elasticsearch.mapping

import grails.test.mixin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.codehaus.groovy.grails.commons.GrailsDomainClass
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import spock.lang.Specification
import test.Photo
import test.upperCase.UpperCase

@Mock([Photo, UpperCase])
public class SearchableClassMappingSpec extends Specification {
    private GrailsDomainClass dc

    void setup() {
        dc = new DefaultGrailsDomainClass(Photo.class)
        dc.grailsApplication = grailsApplication
    }

    void cleanup() {
        config.elasticSearch.index.name = null
    }

    def "testGetIndexName"() {
        when:
        SearchableClassMapping mapping = new SearchableClassMapping(dc,null)

        then:
        "test" == mapping.getIndexName()
    }

    def "testManuallyConfiguredIndexName"() {
        when:
        config.elasticSearch.index.name = "index-name"
        SearchableClassMapping mapping = new SearchableClassMapping(dc,null)

        then:
        "index-name" == mapping.getIndexName()
    }

    def "testIndexNameIsLowercaseWhenPackageNameIsLowercase"() {
        when:
        SearchableClassMapping mapping = new SearchableClassMapping(dc,null)
        String indexName = mapping.getIndexName()

        then:
        "test" == indexName
    }

}
