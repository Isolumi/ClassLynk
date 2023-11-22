package ai.classlynk.interface_adapter.static_maps;

import ai.classlynk.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MapsViewModel extends ViewModel {

    private MapsState state = new MapsState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public MapsViewModel() {
        super("view maps");
    }

    public MapsState getState() {
        return state;
    }

    public void setState(MapsState state) {
        this.state = state;
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
