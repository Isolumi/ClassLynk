package ai.classlynk.interface_adapter.ViewCourse;

import ai.classlynk.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewCourseViewModel extends ViewModel {
    public static final String VIEW_COURSES_LABEL = "View Courses";

    private ViewCourseState state;

    public ViewCourseViewModel() {
        super("viewCourses");
        this.state = new ViewCourseState();
    }

    public void setState(ViewCourseState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ViewCourseState getState() {
        return state;
    }

}
