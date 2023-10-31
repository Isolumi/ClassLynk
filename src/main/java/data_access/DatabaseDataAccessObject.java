package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import entity.Course;
import entity.Timetable;
import entity.User;
import use_case.explore_courses.ExploreCoursesDataAccessInterface;
import use_case.save_view_time_tables.SaveViewTimetablesDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class DatabaseDataAccessObject implements ExploreCoursesDataAccessInterface, SaveViewTimetablesDataAccessInterface {

    Firestore db;
    public DatabaseDataAccessObject()
    {
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.getApplicationDefault();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setProjectId("timetablegenerator-e55f3")
                .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }

    public void test()
    {
        // Create a Map to store the data we want to set
        Map<String, Object> docData = new HashMap<>();
        docData.put("name", "Los Angeles");
        docData.put("state", "CA");
        docData.put("country", "USA");
        docData.put("regions", Arrays.asList("west_coast", "socal"));
// Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = db.collection("cities").document("LA").set(docData);
// ...
// future.get() blocks on response
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Course> loadCourses() {
        return null;
    }

    @Override
    public void save(User user, Timetable timetable) {

    }

    @Override
    public void load(User user) {

    }
}
