package use_case.generate_timetable;


public interface TimetableGeneratorDataAccessInterface {
    float getRouteLength(String origin, String destination);
}