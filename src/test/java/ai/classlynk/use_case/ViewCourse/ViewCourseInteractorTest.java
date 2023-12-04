package ai.classlynk.use_case.ViewCourse;

import ai.classlynk.IntegrationTest;
import ai.classlynk.data_access.FirebaseDataAccessObject;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import ai.classlynk.interface_adapter.ViewCourse.ViewCoursePresenter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewCourseInteractorTest extends IntegrationTest {
    @Autowired
    FirebaseDataAccessObject database;
    @Test
    void execute() {
        Collection<Course> courses = database.getAllCourses().values();
        List<Course> classList = new ArrayList<>(courses);

        ViewCourseOutputBoundary presenter = new ViewCourseOutputBoundary() {
            @Override
            public void presentResponse(ViewCourseOutputData outputData) {
                for(Course course: outputData.getAllCourses())
                {
                    if(!classList.contains(course))
                    {
                        fail("Courses aren't the same");
                    }
                }
                //if all courses are contained in classList, then they are equal
                assert true;
            }
        };

        ViewCourseInteractor interactor = new ViewCourseInteractor(database, presenter);
        interactor.execute();
    }
}