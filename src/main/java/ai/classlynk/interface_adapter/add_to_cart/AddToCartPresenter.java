package ai.classlynk.interface_adapter.add_to_cart;

import ai.classlynk.interface_adapter.Presenter;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.add_to_cart.AddToCartOutputBoundary;
import ai.classlynk.use_case.add_to_cart.AddToCartOutputData;

public class AddToCartPresenter implements AddToCartOutputBoundary, Presenter {
    private final AddToCartViewModel addToCartViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddToCartPresenter(AddToCartViewModel addToCartViewModel, ViewManagerModel viewManagerModel) {
        this.addToCartViewModel = addToCartViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentResponse(AddToCartOutputData outputData) {
        addToCartViewModel.setAddToCartState(new AddToCartState(outputData.isSuccess(), outputData.getMessage(), outputData.getUpdatedCourseCart()));
    }

    @Override
    public ViewManagerModel getViewManagerModel() {
        return this.viewManagerModel;
    }
}
