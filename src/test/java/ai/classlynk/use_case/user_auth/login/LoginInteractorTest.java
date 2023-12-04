package ai.classlynk.use_case.user_auth.login;

import ai.classlynk.entity.Timetable;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetablesDataAccessInterface;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {
    class FakeUser{
        private final String Name;
        private final String Password;

        FakeUser(String name, String password) {
            Name = name;
            Password = password;
        }
        public String GetName(){
            return this.Name;
        }
        public String GetPassword(){
            return this.Password;
        }
    }
    class FakeFirebaseDataAccessObject implements LoginDataAccessInterface, SaveViewTimetablesDataAccessInterface{
        private ArrayList<FakeUser> fakeUsers;

        FakeFirebaseDataAccessObject(ArrayList<FakeUser> users) {
            fakeUsers = users;
        }


        FakeFirebaseDataAccessObject(){
            fakeUsers = new ArrayList<>();
        }

        @Override
        public boolean existsByName(String Name) {
            for(FakeUser i: fakeUsers){
                if (Objects.equals(i.GetName(), Name)){
                    return true;
                };
            }

            return false;
        }

        @Override
        public boolean verifyPassword(String Name, String Password) {
            for(FakeUser i: fakeUsers){
                if (Objects.equals(i.GetName(), Name)){
                    return Password.equals(i.GetPassword());
                };
            }
            return false;
        }

        @Override
        public void saveTimetable(Timetable timetable) throws IOException {

        }

        @Override
        public void deleteTimetable(Timetable timetable) {

        }

        @Override
        public Timetable getTimetable(String userId) {
            return null;
        }
    }

    @Test
    void successTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        ArrayList<FakeUser> users = new ArrayList<>();
        FakeUser user = new FakeUser("Paul", "password");
        users.add(user);
        LoginDataAccessInterface userRepository = new FakeFirebaseDataAccessObject(users);
        SaveViewTimetablesDataAccessInterface timetableRepository = new FakeFirebaseDataAccessObject(users);
        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertFalse(loginOutputData.getStatus());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, timetableRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password1");
        ArrayList<FakeUser> users = new ArrayList<>();
        FakeUser user = new FakeUser("Paul", "password");
        users.add(user);
        LoginDataAccessInterface userRepository = new FakeFirebaseDataAccessObject(users);
        SaveViewTimetablesDataAccessInterface timetablesDataAccessInterface = new FakeFirebaseDataAccessObject(users);
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {        // This creates a presenter that tests whether the test case is as we expect.
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for " + inputData.getUsername() + ".", error);
            }
        };

        LoginInteractor interactor = new LoginInteractor(userRepository, timetablesDataAccessInterface, failurePresenter);
        interactor.execute(inputData);
    }
    @Test
    void failureUserNotExistTest() {
        LoginInputData inputData = new LoginInputData("Paul1", "password1");
        LoginDataAccessInterface userRepository = new FakeFirebaseDataAccessObject();
        SaveViewTimetablesDataAccessInterface saveViewTimetablesDataAccessInterface = new FakeFirebaseDataAccessObject();
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {        // This creates a presenter that tests whether the test case is as we expect.
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(inputData.getUsername() + ": Account does not exist.", error);
            }
        };

        LoginInteractor interactor = new LoginInteractor(userRepository, saveViewTimetablesDataAccessInterface, failurePresenter);
        interactor.execute(inputData);
    }
}
