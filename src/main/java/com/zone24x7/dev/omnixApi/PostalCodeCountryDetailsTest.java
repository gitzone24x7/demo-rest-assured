package com.zone24x7.dev.omnixApi;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;
import com.jayway.restassured.matcher.RestAssuredMatchers.*;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
/**
 * @author Shiwanthak
 * @since 9/5/2015
 * Created by user on 9/5/2015.
 */
public class PostalCodeCountryDetailsTest {

    public PostalCodeCountryDetailsTest() {
        baseURI = "http://api.geonames.org";
        port = 80;
    }


    @Test
    public void test_geonames_country_code(){
        given().
            parameters("postalcode", "6700", "country", "AT","username","demo").
        when().
            get("/postalCodeLookupJSON").
        then().
            statusCode(200).
            body("postalcodes.countryCode", hasItems("AT"));
    }

    @Test
    public void test_genomes_different_country_code(){
        given().
            parameters("postalcode", "8775", "country", "CH","username","demo").
        when().
            get("/postalCodeLookupJSON").
        then().
            statusCode(200).
            body("postalcodes.countryCode", hasItems("CH")).
            body("postalcodes.postalcode", hasItems("8775"));
    }

    @Test
    public void test_geonames_country_admin(){
        given().
            parameters("postalcode", "6700", "country", "AT", "username", "demo").
        when().
            get("/postalCodeLookupJSON").
        then().
            statusCode(200).
            body("postalcodes.adminCode3", hasItems("80103", "80114", "80125")).
            body("postalcodes.adminCode2[0]", is("801"));
    }

    @Test
    public void test_geonames_postal_code(){
        given().
            parameters("postalcode", "6700", "country", "AT", "username", "demo").
        when().
            get("/postalCodeLookupJSON").
        then().
            statusCode(200).
            body("postalcodes.postalcode", hasItems("6700"));
    }






}
