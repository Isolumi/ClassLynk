package ai.classlynk.interface_adapter.addToCart;

import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.AddToCart.AddToCartInputBoundary;
import ai.classlynk.use_case.AddToCart.AddToCartOutputBoundary;
import ai.classlynk.use_case.AddToCart.AddToCartOutputData;

public class AddToCartPresenter implements AddToCartOutputBoundary {
    private final AddToCartViewModel addToCartViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddToCartPresenter(AddToCartViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.addToCartViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentResponse(AddToCartOutputData outputData) {
        AddToCartState state = addToCartViewModel.getState();
        state.setCourseCart(outputData.getUpdatedCourseCart());
        state.setsClasses(outputData.getUpdatedClassCart());
        this.addToCartViewModel.setState(state);
        this.addToCartViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(addToCartViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
