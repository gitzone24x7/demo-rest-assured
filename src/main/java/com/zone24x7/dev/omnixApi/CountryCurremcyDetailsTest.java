package com.zone24x7.dev.omnixApi;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.port;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

/**
 * @author Shiwanthak
 * @since 9/5/2015
 * Created by user on 9/5/2015.
 */
public class CountryCurremcyDetailsTest {

    public CountryCurremcyDetailsTest() {
        baseURI = "http://api.fixer.io";
        port = 80;
    }

    @Test
    public void test_historical_currency(){
        given().
            parameters("base", "USD").
        when().
            get("/latest").
        then().
            statusCode(200).
            body("base", is("USD"));
    }

    @Test
    public void test_symbols_currency(){
        Response response = given().
            parameters("symbols", "USD,GBP").
        when().
            get("/latest");
        String val = response.asString();
        is(JsonPath.with(val).get("rates.USD"));
    }
}
