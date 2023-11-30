package ai.classlynk.interface_adapter.addToCart;

import ai.classlynk.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddToCartViewModel extends ViewModel {
    private AddToCartState state;

    public static final String TITLE_LABEL = "Add to cart";

    public AddToCartViewModel() {
        super("Add to cart");
    }

    public void setState(AddToCartState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddToCartState getState() {
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
