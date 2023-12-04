package ai.classlynk.use_case.user_auth.register;

import ai.classlynk.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RegisterInteractorTest {
    class FakeUser extends User{
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
    class FakeFirebaseDataAccessObject implements  RegisterDataAccessInterface{
        private ArrayList<FakeUser> fakeUsers;

        FakeFirebaseDataAccessObject(ArrayList<FakeUser> users) {
            fakeUsers = users;
        }


        FakeFirebaseDataAccessObject(){
            fakeUsers = new ArrayList<FakeUser>();
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
        public User userCreate(String Name, String password) {
            FakeUser user = new FakeUser(Name, password);
            fakeUsers.add(user);
            return user;
        }
    }

    @Test
    void successTest() {
        RegisterInputData inputData = new RegisterInputData("Paul", "password", "password");


        RegisterDataAccessInterface userRepository = new FakeFirebaseDataAccessObject();// This creates a successPresenter that tests whether the test case is as we expect.
        RegisterOutputBoundary successPresenter = new RegisterOutputBoundary() {
            @Override
            public void success() {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertTrue(userRepository.existsByName("Paul"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        RegisterInputBoundary interactor = new RegisterInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        RegisterInputData inputData = new RegisterInputData("Paul1", "password1", "wrong");
        RegisterDataAccessInterface userRepository = new FakeFirebaseDataAccessObject();
        RegisterOutputBoundary failurePresenter = new RegisterOutputBoundary() {        // This creates a presenter that tests whether the test case is as we expect.
            @Override
            public void success() {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords do not match", error);
            }
        };

        RegisterInputBoundary interactor = new RegisterInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        RegisterDataAccessInterface userRepository = new FakeFirebaseDataAccessObject();
        userRepository.userCreate("Paul", "password");
        RegisterInputData inputData = new RegisterInputData("Paul", "password", "wrong");


        // This creates a presenter that tests whether the test case is as we expect.
        RegisterOutputBoundary failurePresenter = new RegisterOutputBoundary() {
            @Override
            public void success() {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Name has been taken", error);
            }
        };

        RegisterInputBoundary interactor = new RegisterInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
