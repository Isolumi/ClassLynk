package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import entity.Class;
import entity.Course;
import entity.Timetable;
import entity.User;
import use_case.explore_courses.ExploreCoursesDataAccessInterface;
import use_case.save_view_time_tables.SaveViewTimetablesDataAccessInterface;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import static java.lang.Float.parseFloat;
import static java.lang.String.valueOf;

public class DatabaseDataAccessObject implements ExploreCoursesDataAccessInterface, SaveViewTimetablesDataAccessInterface {

    Firestore db;

    public DatabaseDataAccessObject() {
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.getApplicationDefault();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .setProjectId("timetablegenerator-e55f3")
                .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }


    //ONLY used for adding new courses into database (not for user use)
    public void saveCourse(Course course) {
        //ArrayList<Map<String, String>>
        DocumentReference docRef = db.collection("classes").document(course.getCourseName());
        ApiFuture<DocumentSnapshot> readRes = docRef.get();
        try {
            DocumentSnapshot document = readRes.get();
            if (document.exists()) {
                System.out.println("This course already exists in the database!");
            } else {
                Map<String, Object> dataToUpload = new HashMap<>();
                dataToUpload.put("courseName", course.getCourseName());
                dataToUpload.put("courseId", course.getCourseId());
                dataToUpload.put("courseDescription", course.getCourseDescription());

                Map<String, List<Class>> sectionData = new HashMap<>();
//                sectionData.put("sections", course.getClassBundles());
                Map<String, ArrayList<Map<String, String>>> convertedSectionData = convertTimetableToData(sectionData);
                dataToUpload.put("classes", convertedSectionData.get("sections"));

                docRef.set(dataToUpload);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Course> loadCourses() {
        ApiFuture<QuerySnapshot> response = db.collection("classes").get();

        try {
            List<QueryDocumentSnapshot> documents = response.get().getDocuments();
            Map<String, Course> returnData = new HashMap<>();
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> courseData= document.getData();
                ArrayList<Class> classes = new ArrayList<>();
                for(Object oneClass : (ArrayList)courseData.get("classes"))
                {
                    Map<String, String> oneClassMap = (Map<String, String>) oneClass;
                    classes.add(new Class(oneClassMap.get("courseId"), oneClassMap.get("classId"), parseFloat(oneClassMap.get("duration"))
                            , LocalTime.parse(oneClassMap.get("time")), oneClassMap.get("weekday"),
                            oneClassMap.get("building"), oneClassMap.get("location"), (!Objects.equals(oneClassMap.get("isTutorial"), "false"))));
                }
//                returnData.put((String)courseData.get("courseId"), new Course((String)courseData.get("courseName"), (String)courseData.get("courseId"), (String)courseData.get("courseDescription"), classes));
            }
            return returnData;

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(User user, Timetable timetable) {
        DocumentReference docRef = db.collection("Users").document(user.getId());
        ApiFuture<DocumentSnapshot> readRes = docRef.get();
        try {
            DocumentSnapshot document = readRes.get();
            if (document.exists()) {
                Map<String, ArrayList<Map<String, ArrayList<Map<String, String>>>>> timetables = new HashMap<>();

                timetables.put("Timetables", getMaps(timetable, document));

                docRef.set(timetables);
            } else {
                Map<String, ArrayList<Map<String, String>>> convertedTimeTable = convertTimetableToData(timetable.getClasses());
                ArrayList<Map<String, ArrayList<Map<String, String>>>> convertedTimetableArray = new ArrayList<>();
                convertedTimetableArray.add(convertedTimeTable);
                Map<String, ArrayList<Map<String, ArrayList<Map<String, String>>>>> timetables = new HashMap<>();
                timetables.put("Timetables", convertedTimetableArray);
                docRef.set(timetables);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    private Map<String, ArrayList<Map<String, String>>> convertTimetableToData(Map<String, List<Class>> timetableData) {
        Map<String, ArrayList<Map<String, String>>> converted = new HashMap<>();
        for (String key : timetableData.keySet()) {
            ArrayList<Map<String, String>> currClasses = new ArrayList<>();
            for (Class classData : timetableData.get(key)) {
                Map<String, String> temp = new HashMap<>();
                temp.put("courseId", classData.getCourseId());
                temp.put("classId", classData.getClassId());
                temp.put("duration", String.valueOf(classData.getDuration()));
                temp.put("isTutorial", valueOf(classData.isTutorial()));
                temp.put("time", classData.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                temp.put("weekday", classData.getWeekday());
                temp.put("location", classData.getLocation());
                temp.put("building", classData.getBuilding());
                currClasses.add(temp);
            }
            converted.put(key, currClasses);
        }
        return converted;
    }

    private ArrayList<Map<String, ArrayList<Map<String, String>>>> getMaps(Timetable timetable, DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        //the returned object from the data is guaranteed to be of this form (an array of maps which represent
        //individual timetables)
        ArrayList<Map<String, ArrayList<Map<String, String>>>> timetables = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) data.get("Timetables");
        Map<String, ArrayList<Map<String, String>>> converted = convertTimetableToData(timetable.getClasses());

        timetables.add(converted);

        //removes duplicate timetables
        Set<Map<String, ArrayList<Map<String, String>>>> noDuplicatesTimetables = new LinkedHashSet<>(timetables);
        timetables.clear();
        timetables.addAll(noDuplicatesTimetables);
        return timetables;
    }


    @Override
    public ArrayList<Timetable> load(User user) {

        DocumentReference docRef = db.collection("Users").document(user.getId());
        ApiFuture<DocumentSnapshot> res = docRef.get();
        try {
            DocumentSnapshot document = res.get();
            if (document.exists()) {
                ArrayList<Map<String, ArrayList<Map<String, String>>>> data = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) document.getData().get("Timetables");
                ArrayList<Timetable> timetables = new ArrayList<>();

                for (Map<String, ArrayList<Map<String, String>>> timetableData : data) {
                    Map<String, List<Class>> timetable = new HashMap<>();
                    for (String key : timetableData.keySet()) {
                        ArrayList<Class> classes = new ArrayList<>();
                        for (Map<String, String> d : timetableData.get(key)) {
                            classes.add(new Class(d.get("courseId"), d.get("classId"), parseFloat(d.get("duration")),
                                    LocalTime.parse(d.get("time")), d.get("weekday"), d.get("building"), d.get("location"), (!Objects.equals(d.get("isTutorial"), "false"))));
                        }
                        timetable.put(key, classes);
                    }
                    timetables.add(new Timetable(timetable));
                }
                return timetables;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        //if there are no saved timetables for this user
        return null;
    }

    @Override
    public void delete(User user, Timetable timetable) {
        DocumentReference docRef = db.collection("Users").document(user.getId());

        try {
            DocumentSnapshot data = docRef.get().get();
            if (data.exists()) {
                Map<String, ArrayList<Map<String, String>>> convertedTimetable = convertTimetableToData(timetable.getClasses());
                ArrayList<Map<String, ArrayList<Map<String, String>>>> updatedTimetableArray = (ArrayList<Map<String, ArrayList<Map<String, String>>>>) data.getData().get("Timetables");
                updatedTimetableArray.remove(convertedTimetable);

                docRef.update("Timetables", updatedTimetableArray);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
