package graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.entity.Subject;
import graphql.request.SampleRequest;
import graphql.response.StudentResponse;
import graphql.response.SubjectResponse;
import graphql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

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

    @SchemaMapping // -> LearningSubjects Resolver
    public List<SubjectResponse> learningSubjects(StudentResponse studentResponse) {
        List<SubjectResponse> learningSubjects = new ArrayList<>();
        if (studentResponse.getStudent().getLearningSubjects() != null) {
            for (Subject subject : studentResponse.getStudent().getLearningSubjects()) {
                learningSubjects.add(new SubjectResponse(subject));
            }
        }
        return learningSubjects;
    }

    @SchemaMapping // -> LearningSubjects Resolver
    public String fullName(StudentResponse studentResponse) {
        return studentResponse.getFirstName() + " " + studentResponse.getLastName();
    }
}
