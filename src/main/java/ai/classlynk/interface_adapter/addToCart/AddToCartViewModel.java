package ai.classlynk.interface_adapter.addToCart;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class AddToCartViewModel {
    private AddToCartState state;
    private final PropertyChangeSupport support;

    public AddToCartViewModel() {
        this.state = new AddToCartState(false, "", new ArrayList<>(), new ArrayList<>());
        this.support = new PropertyChangeSupport(this);
    }

    public void setAddToCartState(AddToCartState state) {
        this.state = state;
        firePropertyChanged();
    }

    public AddToCartState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
