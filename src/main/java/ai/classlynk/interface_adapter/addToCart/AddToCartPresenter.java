package ai.classlynk.interface_adapter.addToCart;


import ai.classlynk.interface_adapter.Presenter;
import ai.classlynk.interface_adapter.ViewManagerModel;
import ai.classlynk.use_case.AddToCart.AddToCartOutputBoundary;
import ai.classlynk.use_case.AddToCart.AddToCartOutputData;
import kotlin.jvm.internal.PackageReference;

public class AddToCartPresenter implements AddToCartOutputBoundary, Presenter {
    private final AddToCartViewModel addToCartViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddToCartPresenter(AddToCartViewModel addToCartViewModel, ViewManagerModel viewManagerModel) {
        this.addToCartViewModel = addToCartViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentResponse(AddToCartOutputData outputData) {
        addToCartViewModel.setAddToCartState(new AddToCartState(outputData.isSuccess(), outputData.getMessage(), outputData.getUpdatedCourseCart(), outputData.getUpdatedClassCart()));
    }

    @Override
    public ViewManagerModel getViewManagerModel() {
        return this.viewManagerModel;
    }
}

