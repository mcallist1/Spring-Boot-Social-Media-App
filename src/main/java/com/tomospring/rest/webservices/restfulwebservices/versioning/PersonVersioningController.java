package com.tomospring.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    /*
        URI Versioning
        Used by Twitter
        Pollutes the URI Space
        Caching not an issue as the version is part of the uri
        Easy for non-tech user to use
        Documentation is easier
     */
    @GetMapping("v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Bob Murphy");
    }
    @GetMapping("v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob", "Murphy"));
    }

    /*
        Request Param Versioning
        Used by Amazon
        Pollutes the URI Space
        Caching not an issue as the version is part of the uri
        Easy for non-tech user to use
        Documentation is easier
     */
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Bob Murphy");
    }
    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Bob", "Murphy"));
    }

    /*
        Header Param Versioning AKA Mime/Media type Versioning AKA "Content negotiation" or "Accept Header"
        Used by Github
        Not polluting URI Space
        Misuse of HTTP Headers (They were never intended for versioning)
        Caching is an issue (Version not in the URI)
        Cannot use with just a browser, would need a plugin and user would need some tech knowledge. (I was testing with PostMan)
        Documentation is a bit tricky
     */
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Bob Murphy");
    }
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Bob", "Murphy"));
    }

    /*
        Producers Versioning AKA Accept Headers Versioning
        Not polluting URI Space
        Misuse of HTTP Headers (They were never intended for versioning)
        Caching is an issue (Version not in the URI)
        Cannot use with just a browser, would need a plugin and user would need some tech knowledge. (I was testing with PostMan)
        Documentation is a bit tricky
     */
    @GetMapping(value = "/person/produces", produces = "application/tomo.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Bob Murphy");
    }
    @GetMapping(value = "/person/produces", produces = "application/tomo.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Bob", "Murphy"));
    }
}
