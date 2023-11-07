package graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.request.SampleRequest;
import graphql.response.StudentResponse;
import graphql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class QueryController {

    @Autowired
    StudentService studentService;

    @QueryMapping
    public String firstQuery() {
        return "First Query";
    }

    @QueryMapping
    public String secondQuery() {
        return "Second Query";
    }

    @QueryMapping
    public String fullName(@Argument SampleRequest sampleRequest) {
        return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
    }
    @QueryMapping
    public StudentResponse student(@Argument long id) {
        return new StudentResponse(studentService.getStudentById(id));
    }
}
