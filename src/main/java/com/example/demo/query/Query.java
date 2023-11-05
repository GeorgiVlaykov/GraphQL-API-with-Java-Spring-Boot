package com.example.demo.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.request.SampleRequest;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
    // http://localhost:8081/graphiql?query=query%20%7B%0A%20%20firstQuery%0A%7D
    public String firstQuery() {
        return "First Query";
    }

    public String fullName(SampleRequest request) {
        return request.getFirstName() + " " + request.getLastName();
    }

}
