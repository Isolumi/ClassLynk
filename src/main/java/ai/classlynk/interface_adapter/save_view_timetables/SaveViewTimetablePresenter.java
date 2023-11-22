package ai.classlynk.interface_adapter.save_view_timetables;

import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputBoundary;
import ai.classlynk.use_case.save_view_timetables.SaveViewTimetableOutputData;

public class SaveViewTimetablePresenter implements SaveViewTimetableOutputBoundary {
    @Override
    public void prepareLoggedInView(SaveViewTimetableOutputData timetables) {

    }

    @Override
    public void prepareNotLoggedInView() {

    }
}
