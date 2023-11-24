package ai.classlynk.interface_adapter.save_view_timetables;

import ai.classlynk.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SaveViewTimetableViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SaveViewTimetableState state = new SaveViewTimetableState();

    public SaveViewTimetableViewModel() {
        super("Your Timetables");
    }
    public void setState(SaveViewTimetableState state) {
        this.state = state;
    }
    public SaveViewTimetableState getState() {
        return this.state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
