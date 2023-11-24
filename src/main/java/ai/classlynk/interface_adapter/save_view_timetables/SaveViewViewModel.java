package ai.classlynk.interface_adapter.save_view_timetables;

import ai.classlynk.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SaveViewViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public SaveViewViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
