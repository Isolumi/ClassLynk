package ai.classlynk.data_access;

<<<<<<< HEAD

import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import com.google.cloud.firestore.Firestore;
=======
import ai.classlynk.entity.*;
import ai.classlynk.use_case.GenerateStaticImage.GenerateStaticImageDataAccessInterface;
import ai.classlynk.use_case.explore_courses.ExploreCoursesDataAccessInterface;
import ai.classlynk.use_case.generate_timetable.TimetableGeneratorDataAccessInterface;
import ai.classlynk.use_case.save_view_time_tables.SaveViewTimetablesDataAccessInterface;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import ai.classlynk.entity.ClassBundle;
import ai.classlynk.entity.Course;
import ai.classlynk.entity.SClass;
import org.checkerframework.checker.units.qual.C;
>>>>>>> feature/firebase-repo-implementation
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.concurrent.ExecutionException;
=======
import java.util.Map;
>>>>>>> feature/firebase-repo-implementation

@Repository
public class FirebaseRepository implements ExploreCoursesDataAccessInterface, TimetableGeneratorDataAccessInterface, SaveViewTimetablesDataAccessInterface {
    @Resource
    private Firestore firestore;

    // Test code for inserting one course into firebase
<<<<<<< HEAD
    public void insertCourse() throws ExecutionException, InterruptedException {

        //TODO: CSC111
//        SClass tempClass1 = new SClass("CSC111", "LEC0101", "9:00:00", "11:00:00", "Tuesday", "Brennan Hall", "81A St. Mary Street, Toronto, ON M5S1J4", false);
//        SClass tempClass2 = new SClass("CSC111", "LEC0101", "10:00:00", "11:00:00", "Thursday", "Sandford Fleming", "10 King's College Rd, Toronto, ON M5S 3G4", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        lectureList1.add(tempClass2);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//        SClass tempClass3 = new SClass("CSC111", "LEC0201", "15:00:00", "17:00:00", "Tuesday", "Medical Sciences Building", "1 King's College Cir, Toronto, ON M5S 1A8", false);
//        SClass tempClass4 = new SClass("CSC111", "LEC0201", "15:00:00", "16:00:00", "Thursday", "Health Sciences Building", "155 College Street, Toronto, ON M5T 1P8", false);
//        List<SClass> lectureList2 = new ArrayList<>();
//        lectureList2.add(tempClass1);
//        lectureList2.add(tempClass2);
//        ClassBundle lectureBundle2 = new ClassBundle("LEC0201", lectureList2);
//
//        SClass tempTut1 = new SClass("CSC111", "TUT0101", "9:00:00", "11:00:00", "Friday", "Bancroft Building", "4 Bancroft Ave, Toronto, ON M5S 1C1", true);

//
//        SClass tempTut2 = new SClass("CSC111", "TUT0102", "9:00:00", "11:00:00", "Friday", "Bahen Centre for Information Technology", "40 St George St, Toronto, ON M5S 2E4", true);

//
//        SClass tempTut3 = new SClass("CSC111", "TUT0201", "11:00:00", "13:00:00", "Friday", "Bahen Centre for Information Technology", "40 St George St, Toronto, ON M5S 2E4", true);

//
//        SClass tempTut4 = new SClass("CSC111", "TUT0203", "11:00:00", "13:00:00", "Friday", "Haultain Building", "170 College St, Toronto, ON M5S 3E3", true);

//
//        SClass tempTut5 = new SClass("CSC111", "TUT0301", "13:00:00", "15:00:00", "Friday", "Bahen Centre for Information Technology", "40 St George St, Toronto, ON M5S 2E4", true);

//
//        SClass tempTut6 = new SClass("CSC111", "TUT0401", "15:00:00", "17:00:00", "Friday", "Bahen Centre for Information Technology", "40 St George St, Toronto, ON M5S 2E4", true);
//        List<SClass> tutorialList6 = new ArrayList<>();
//        tutorialList6.add(tempTut1);
//        tutorialList6.add(tempTut2);
//        tutorialList6.add(tempTut3);
//        tutorialList6.add(tempTut4);
//        tutorialList6.add(tempTut5);
//        tutorialList6.add(tempTut6);
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//        bundleList.add(lectureBundle2);
//
//
//        this.firestore.document("courses/CSC111")
//                .set(new Course("Foundations of Computer Science II", "CSC111", "A continuation of CSC110Y1 to extend principles of programming and mathematical analysis to further topics in computer science.\n" +
//                        "Topics include: object-oriented programming (design principles, encapsulation, composition and inheritance); binary representation of numbers; recursion and mathematical induction; abstract data types and data structures (stacks, queues, linked lists, trees, graphs); the limitations of computation.", bundleList, tutorialList6));

//TODO: PSY100

//        SClass tempClass1 = new SClass("PSY100", "LEC0101", "11:00:00", "12:00:00", "Tuesday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        SClass tempClass2 = new SClass("PSY100", "LEC0101", "11:00:00", "13:00:00", "Thursday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        lectureList1.add(tempClass2);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);

        //        List<SClass> tutorialList6 = new ArrayList<>();
//
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//
//        this.firestore.document("courses/PSY100")
//                .set(new Course("Introductory Psychology", "PSY100", "A brief introductory survey of psychology as both a biological and social science. Topics will include physiological, learning, perceptual, motivational, cognitive, developmental, personality, abnormal, and social psychology.", bundleList, tutorialList6));



        //TODO: ECO101
//        SClass tempClass1 = new SClass("ECO101", "LEC0101", "15:00:00", "17:00:00", "Monday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//        SClass tempClass2 = new SClass("ECO101", "LEC0201", "11:00:00", "13:00:00", "Tuesday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        List<SClass> lectureList2 = new ArrayList<>();
//        lectureList2.add(tempClass2);
//        ClassBundle lectureBundle2 = new ClassBundle("LEC0201", lectureList2);
//
//        SClass tempClass3 = new SClass("ECO101", "LEC0301", "13:00:00", "15:00:00", "Tuesday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        List<SClass> lectureList3 = new ArrayList<>();
//        lectureList3.add(tempClass3);
//        ClassBundle lectureBundle3 = new ClassBundle("LEC0301", lectureList3);
//
//        SClass tempClass4 = new SClass("ECO101", "LEC0401", "11:00:00", "13:00:00", "Wednesday", "Myhal Centre for Engineering Innovation and Entrepreneurship", "55 St George St, Toronto, ON M5S 0C9", false);
//        List<SClass> lectureList4 = new ArrayList<>();
//        lectureList4.add(tempClass4);
//        ClassBundle lectureBundle4 = new ClassBundle("LEC0401", lectureList4);
//
//        SClass tempClass5 = new SClass("ECO101", "LEC0501", "13:00:00", "15:00:00", "Wednesday", "Medical Sciences Building", "1 King's College Cir, Toronto, ON M5S 1A8", false);
//        List<SClass> lectureList5 = new ArrayList<>();
//        lectureList5.add(tempClass5);
//        ClassBundle lectureBundle5 = new ClassBundle("LEC0501", lectureList5);
//
//        SClass tempClass6 = new SClass("ECO101", "LEC2501", "18:00:00", "20:00:00", "Monday", "Myhal Centre for Engineering Innovation and Entrepreneurship", "55 St George St, Toronto, ON M5S 0C9", false);
//        List<SClass> lectureList6 = new ArrayList<>();
//        lectureList6.add(tempClass6);
//        ClassBundle lectureBundle6 = new ClassBundle("LEC2501", lectureList6);
//
//
//
//        SClass tempTut1 = new SClass("ECO101", "TUT0101", "13:00:00", "14:00:00", "Tuesday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut2 = new SClass("ECO101", "TUT5302", "19:00:00", "20:00:00", "Tuesday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut3 = new SClass("ECO101", "TUT0601", "11:00:00", "12:00:00", "Wednesday", "Ramsay Wright Laboratories", "25 Harbord street, Toronto, ON M5S 3G5", true);

//
//        SClass tempTut4 = new SClass("ECO101", "TUT5402", "17:00:00", "18:00:00", "Wednesday", "McLennan Physical Laboratories", "255 Huron St, Toronto, ON M5S 1A7", true);

//
//        SClass tempTut5 = new SClass("ECO101", "TUT1202", "11:00:00", "12:00:00", "Thursday", "Myhal Centre for Engineering Innovation and Entrepreneurship", "55 St George St, Toronto, ON M5S 0C9", true);

//
//        SClass tempTut6 = new SClass("ECO101", "TUT5601", "17:00:00", "18:00:00", "Thursday", "Ramsay Wright Laboratories", "25 Harbord street, Toronto, ON M5S 3G5", true);
//        List<SClass> tutorialList6 = new ArrayList<>();
//        tutorialList6.add(tempTut1);
//        tutorialList6.add(tempTut2);
//        tutorialList6.add(tempTut3);
//        tutorialList6.add(tempTut4);
//        tutorialList6.add(tempTut5);
//        tutorialList6.add(tempTut6);
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//        bundleList.add(lectureBundle2);
//        bundleList.add(lectureBundle3);
//        bundleList.add(lectureBundle4);
//        bundleList.add(lectureBundle5);
//        bundleList.add(lectureBundle6);
//
//
//        this.firestore.document("courses/ECO101")
//                .set(new Course("Principles of Microeconomics", "ECO101", "An introduction to economic analysis and its applications: price determination, market structure, decision making by individuals and firms, public policy.", bundleList, tutorialList6));



        //TODO: ECO102

//        SClass tempClass1 = new SClass("ECO102", "LEC0101", "10:00:00", "11:00:00", "Monday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        SClass tempClass2 = new SClass("ECO102", "LEC0101", "10:00:00", "11:00:00", "Wednesday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        lectureList1.add(tempClass2);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//        SClass tempClass3 = new SClass("ECO102", "LEC0201", "11:00:00", "12:00:00", "Monday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        SClass tempClass4 = new SClass("ECO102", "LEC0201", "11:00:00", "12:00:00", "Wednesday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        List<SClass> lectureList2 = new ArrayList<>();
//        lectureList2.add(tempClass3);
//        lectureList2.add(tempClass4);
//        ClassBundle lectureBundle2 = new ClassBundle("LEC0201", lectureList2);
//
//        SClass tempClass5 = new SClass("ECO102", "LEC0301", "12:00:00", "13:00:00", "Monday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        SClass tempClass6 = new SClass("ECO102", "LEC0301", "12:00:00", "13:00:00", "Wednesday", "Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", false);
//        List<SClass> lectureList3 = new ArrayList<>();
//        lectureList3.add(tempClass5);
//        lectureList3.add(tempClass6);
//        ClassBundle lectureBundle3 = new ClassBundle("LEC0301", lectureList3);
//
//        SClass tempClass7 = new SClass("ECO102", "LEC0401", "15:00:00", "17:00:00", "Monday", "Medical Sciences Building", "1 King's College Cir, Toronto, ON M5S 1A8", false);
//        List<SClass> lectureList4 = new ArrayList<>();
//        lectureList4.add(tempClass7);
//        ClassBundle lectureBundle4 = new ClassBundle("LEC0401", lectureList4);
//
//        SClass tempClass8 = new SClass("ECO102", "LEC2501", "17:00:00", "19:00:00", "Monday", "Medical Sciences Building", "1 King's College Cir, Toronto, ON M5S 1A8", false);
//        List<SClass> lectureList5 = new ArrayList<>();
//        lectureList5.add(tempClass8);
//        ClassBundle lectureBundle5 = new ClassBundle("LEC2501", lectureList5);
//
//
//
//
//        SClass tempTut1 = new SClass("ECO102", "TUT0201", "16:00:00", "17:00:00", "Monday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut2 = new SClass("ECO102", "TUT0401", "12:00:00", "13:00:00", "Tuesday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut3 = new SClass("ECO102", "TUT5401", "18:00:00", "19:00:00", "Tuesday", "Ramsay Wright Laboratories", "25 Harbord street, Toronto, ON M5S 3G5", true);

//
//        SClass tempTut4 = new SClass("ECO102", "TUT1001", "14:00:00", "15:00:00", "Wednesday", "Earth Sciences Centre", "22 Ursula Franklin Street, Toronto, ON M5S 3B1", true);

//
//        SClass tempTut5 = new SClass("ECO102", "TUT5602", "18:00:00", "19:00:00", "Wednesday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut6 = new SClass("ECO102", "TUT1301", "11:00:00", "12:00:00", "Thursday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut7 = new SClass("ECO102", "TUT5201", "18:00:00", "19:00:00", "Monday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut8 = new SClass("ECO102", "TUT5701", "18:00:00", "19:00:00", "Thursday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut9 = new SClass("ECO102", "TUT2002", "11:00:00", "12:00:00", "Friday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut10 = new SClass("ECO102", "TUT2401", "15:00:00", "16:00:00", "Friday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);
//        List<SClass> tutorialList6 = new ArrayList<>();
//        tutorialList6.add(tempTut1);
//        tutorialList6.add(tempTut2);
//        tutorialList6.add(tempTut3);
//        tutorialList6.add(tempTut4);
//        tutorialList6.add(tempTut5);
//        tutorialList6.add(tempTut6);
//        tutorialList6.add(tempTut7);
//        tutorialList6.add(tempTut8);
//        tutorialList6.add(tempTut9);
//        tutorialList6.add(tempTut10);
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//        bundleList.add(lectureBundle2);
//        bundleList.add(lectureBundle3);
//        bundleList.add(lectureBundle4);
//        bundleList.add(lectureBundle5);
//
//
//        this.firestore.document("courses/ECO102")
//                .set(new Course("Principles of Macroeconomics", "ECO102", "An introduction to economic analysis and its applications from a macroeconomic (economy-wide) perspective. Topics covered include international trade and finance, role of money and the banking system, monetary and fiscal policy.", bundleList, tutorialList6));

        //TODO: lin101

//        SClass tempClass1 = new SClass("LIN101", "LEC0101", "13:00:00", "15:00:00", "Tuesday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        SClass tempClass2 = new SClass("LIn101", "LEC5101", "17:00:00", "19:00:00", "Tuesday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        lectureList1.add(tempClass2);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//
//        SClass tempTut1 = new SClass("LIN101", "TUT0101", "09:00:00", "10:00:00", "Thursday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut2 = new SClass("LIN101", "TUT0402", "13:00:00", "14:00:00", "Thursday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);

//
//        SClass tempTut3 = new SClass("LIN101", "TUT0602", "15:00:00", "16:00:00", "Thursday", "University College", "15 King's College Circle, Toronto, ON M5S 3H7", true);

//
//        SClass tempTut4 = new SClass("LIN101", "TUT0702", "16:00:00", "17:00:00", "Thursday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);
//        List<SClass> tutorialList4 = new ArrayList<>();
//        tutorialList4.add(tempTut1);
//        tutorialList4.add(tempTut2);
//        tutorialList4.add(tempTut3);
//        tutorialList4.add(tempTut4);
//
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//
//        this.firestore.document("courses/LIN101")
//                .set(new Course("Introduction to Linguistics: Sound Structure", "LIN101", "Introduction to fundamental principles of linguistics with particular attention to sound structure and its acquisition, processing, and variation; practice in elementary analytic techniques using data from a broad spectrum of languages.", bundleList, tutorialList4));

        //TODO: HPS100

//        SClass tempClass1 = new SClass("HPS100", "LEC0101", "11:00:00", "13:00:00", "Tuesday", "Northrop Frye Hall", "73 Queen's Park Crescent East, Toronto, ON M5S 1K7", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//
//
//
//        SClass tempTut1 = new SClass("HPS100", "TUT0101", "16:00:00", "17:00:00", "Tuesday", "Northrop Frye Hall", "73 Queen's Park Crescent East, Toronto, ON M5S 1K7", true);

//
//        SClass tempTut2 = new SClass("HPS100", "TUT0201", "10:00:00", "11:00:00", "Thursday", "Northrop Frye Hall", "73 Queen's Park Crescent East, Toronto, ON M5S 1K7", true);

//
//        SClass tempTut3 = new SClass("HPS100", "TUT0401", "15:00:00", "16:00:00", "Thursday", "Northrop Frye Hall", "73 Queen's Park Crescent East, Toronto, ON M5S 1K7", true);

//
//        SClass tempTut4 = new SClass("HPS100", "TUT0601", "11:00:00", "12:00:00", "Friday", "Northrop Frye Hall", "73 Queen's Park Crescent East, Toronto, ON M5S 1K7", true);

//
//        SClass tempTut5 = new SClass("HPS100", "TUT0901", "15:00:00", "16:00:00", "Friday", "Northrop Frye Hall", "73 Queen's Park Crescent East, Toronto, ON M5S 1K7", true);
//        List<SClass> tutorialList5 = new ArrayList<>();
//        tutorialList5.add(tempTut1);
//        tutorialList5.add(tempTut2);
//        tutorialList5.add(tempTut3);
//        tutorialList5.add(tempTut4);
//        tutorialList5.add(tempTut5);
//
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//
//        this.firestore.document("courses/HPS100")
//                .set(new Course("Introduction to History and Philosophy of Science and Technology", "HPS100", "An investigation of some pivotal periods in the history of science with an emphasis on the influences of philosophy on the scientists of the period, and the philosophical and social implications of the scientific knowledge, theory and methodology that emerged.", bundleList, tutorialList5));

        //TODO: BIO120

//        SClass tempClass1 = new SClass("BIO120", "LEC0101", "10:00:00", "11:00:00", "Monday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        SClass tempClass2 = new SClass("BIO120", "LEC0101", "11:00:00", "13:00:00", "Wednesday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        lectureList1.add(tempClass2);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//        SClass tempClass3 = new SClass("BIO120", "LEC5101", "17:00:00", "19:00:00", "Wednesday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        List<SClass> lectureList2 = new ArrayList<>();
//        lectureList1.add(tempClass3);
//        ClassBundle lectureBundle2 = new ClassBundle("LEC5101", lectureList2);
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//        bundleList.add(lectureBundle2);
//
        //        List<SClass> tutorialList6 = new ArrayList<>();
//        this.firestore.document("courses/BIO120")
//                .set(new Course("Adaptation and Biodiversity", "BIO120", "Principles and concepts of evolution and ecology related to origins of adaptation and biodiversity. Mechanisms and processes driving biological diversification illustrated from various perspectives using empirical and theoretical approaches. Topics include: genetic diversity, natural selection, speciation, physiological, population, and community ecology, maintenance of species diversity, conservation, species extinction, global environmental change, and invasion biology.", bundleList, tutorialList6));

        //TODO: csb202
//        SClass tempClass1 = new SClass("CSB202", "LEC0101", "13:00:00", "14:00:00", "Monday", "Ramsay Wright Laboratories", "25 Harbord street, Toronto, ON M5S 3G5", true);
//        SClass tempClass2 = new SClass("CSB202", "LEC0101", "13:00:00", "15:00:00", "Thursday", "Ramsay Wright Laboratories", "25 Harbord street, Toronto, ON M5S 3G5", true);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        lectureList1.add(tempClass2);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//
        //        List<SClass> tutorialList6 = new ArrayList<>();

//        this.firestore.document("courses/BIO120")
//                .set(new Course("Further Exploration in Biotechnology", "CSB202", "Provides non-science students with an additional opportunity to explore biotechnology and its applications in agriculture, the environment, and human health including: genetically modified organisms, drug discovery and aging. Most lectures are viewed online before class and students work in groups during class on problem sets and case studies designed to stimulate further learning, enhance evidence-based reasoning, and promote reflection on the role of biotechnology in society. ", bundleList, tutorialList6));

        //TODO: PHY131
//        SClass tempClass1 = new SClass("PHY131", "LEC0101", "11:00:00", "12:00:00", "Monday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        SClass tempClass2 = new SClass("PHY131", "LEC0101", "11:00:00", "12:00:00", "Wednesday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        SClass tempClass3 = new SClass("PHY131", "LEC0101", "11:00:00", "12:00:00", "Friday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        lectureList1.add(tempClass2);
//        lectureList1.add(tempClass3);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//
//        SClass tempTut1 = new SClass("PHY131", "PRA0101", "13:00:00", "15:00:00", "Monday", "Mclennan Physical Laboratories", "255 Huron St, Toronto, ON M5S 1A7", true);

//
//        SClass tempTut2 = new SClass("PHY131", "PRA0301", "10:00:00", "12:00:00", "Tuesday", "Mclennan Physical Laboratories", "255 Huron St, Toronto, ON M5S 1A7", true);

//
//        SClass tempTut3 = new SClass("PHY131", "PRA0701", "13:00:00", "15:00:00", "Wednesday", "Mclennan Physical Laboratories", "255 Huron St, Toronto, ON M5S 1A7", true);

//
//        SClass tempTut4 = new SClass("PHY131", "PRA1001", "11:00:00", "13:00:00", "Thursday", "Mclennan Physical Laboratories", "255 Huron St, Toronto, ON M5S 1A7", true);

//
//        SClass tempTut5 = new SClass("PHY131", "PRA1301", "13:00:00", "15:00:00", "Friday", "Mclennan Physical Laboratories", "255 Huron St, Toronto, ON M5S 1A7", true);
//        List<SClass> tutorialList5 = new ArrayList<>();
//        tutorialList5.add(tempTut1);
//        tutorialList5.add(tempTut2);
//        tutorialList5.add(tempTut3);
//        tutorialList5.add(tempTut4);
//        tutorialList5.add(tempTut5);
//
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//
//        this.firestore.document("courses/PHY131")
//                .set(new Course("Introduction to Physics I", "PHY131", "A first university physics course primarily for students not intending to pursue a Specialist or Major program in Physical or Mathematical Sciences. Topics include: classical kinematics & dynamics, momentum, energy, force, friction, work, power, angular momentum, oscillations, waves, sound.", bundleList, tutorialList5));

        //TODO:ENG100
//        SClass tempClass1 = new SClass("ENG100", "LEC5101", "18:00:00", "21:00:00", "Thursday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC5101", lectureList1);
//
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//
        //        List<SClass> tutorialList6 = new ArrayList<>();

//        this.firestore.document("courses/ENG100")
//                .set(new Course("Effective Writing", "ENG100", "Practical tools for writing in university and beyond. Students will gain experience in generating ideas, clarifying insights, structuring arguments, composing paragraphs and sentences, critiquing and revising their writing, and communicating effectively to diverse audiences.", bundleList, tutorialList6));

        //TODO:AST101

//        SClass tempClass1 = new SClass("AST101", "LEC0101", "14:00:00", "15:00:00", "Tuesday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        SClass tempClass2 = new SClass("AST101", "LEC0101", "14:00:00", "15:00:00", "Thursday", "Convocation Hall", "31 King's College Cir, Toronto, ON M5S 1A1", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        lectureList1.add(tempClass2);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//        SClass tempTut1 = new SClass("AST101", "TUT0201", "11:00:00", "12:00:00", "Monday", "Bahen Centre for Information Technology", "40 St George St, Toronto, ON M5S 2E4", true);
//
//
//        SClass tempTut2 = new SClass("AST101", "TUT0601", "15:00:00", "16:00:00", "Monday", "Bahen Centre for Information Technology", "40 St George St, Toronto, ON M5S 2E4", true);
//
//
//        SClass tempTut3 = new SClass("AST101", "TUT0701", "10:00:00", "11:00:00", "Tuesday", "Earth Sciences Centre", "22 Ursula Franklin Street, Toronto, ON M5S 3B1", true);
//
//
//        SClass tempTut4 = new SClass("AST101", "TUT1002", "13:00:00", "14:00:00", "Tuesday", "Mclennan Physical Laboratories", "255 Huron St, Toronto, ON M5S 1A7", true);
//
//
//        SClass tempTut5 = new SClass("AST101", "TUT1301", "12:00:00", "13:00:00", "Wednesday", "Sidney Smith Hall", "100 St George St, Toronto, ON M5S 3G3", true);
//
//
//        SClass tempTut6 = new SClass("AST101", "TUT1501", "14:00:00", "15:00:00", "Wednesday", "Myhal Centre for Engineering Innovation and Entrepreneurship", "55 St George St, Toronto, ON M5S 0C9", true);
//        List<SClass> tutorialList6 = new ArrayList<>();
//                tutorialList6.add(tempTut1);
//                tutorialList6.add(tempTut2);
//                tutorialList6.add(tempTut3);
//                tutorialList6.add(tempTut4);
//                tutorialList6.add(tempTut5);
//        tutorialList6.add(tempTut6);
//
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//
//        this.firestore.document("courses/AST101")
//                .set(new Course("The Sun and Its Neighbours", "AST101", "Our place in the Universe. Phenomena we see in the sky. What we know about the Sun, the planets and comets, and the formation of the solar system – and how we know it. What makes planets suitable for life. Finding out about the nearest stars and their planets.", bundleList, tutorialList6));

        //TODO:ESS105
//        SClass tempClass1 = new SClass("ESS105", "LEC0101", "11:00:00", "13:00:00", "Wednesday", "Isabel Bader Theatre", "93 Charles St W, Toronto, ON M5S 2C7", false);
//        List<SClass> lectureList1 = new ArrayList<>();
//        lectureList1.add(tempClass1);
//        ClassBundle lectureBundle1 = new ClassBundle("LEC0101", lectureList1);
//
//
//        List<ClassBundle> bundleList = new ArrayList<>();
//        bundleList.add(lectureBundle1);
//
        //        List<SClass> tutorialList6 = new ArrayList<>();

//        this.firestore.document("courses/ESS105")
//                .set(new Course("Our home planet", "ESS105", "The nature and evolution of the Earth; plate tectonics; rocks and minerals; volcanism; geological time; fossils; geology of Ontario; environmental issues; and human interactions with the planet.", bundleList, tutorialList6));
    }
}