package marsphotogallery;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;

@Route
public class MainView extends VerticalLayout {
  private static final long SOL_MAX_LIMIT = 1000;
  private static final String PHOTO_SIZE = "500px";
  private static final int MAX_ITEM_INDEX_IN_PAGE = 24;
  
  private Image crtPhoto;
  private Button btnPrev;
  private Button btnNext;
  private Text btnImageId;
  private long crtSol;
  private long crtPageIndex = 1;
  private int crtItemIndex;
  private List<MarsPhoto> marsPhotoList = new ArrayList<>();

  private transient IRestAPIService restAPIService;

  public MainView(RestAPIService restAPIService) {
    this.restAPIService = restAPIService;
    btnPrev = new Button("Previous", e -> prev());
    add(btnPrev);
    btnNext = new Button("Next", e -> next());
    add(btnNext);
    crtPhoto = new Image();
    crtPhoto.setMaxHeight(PHOTO_SIZE);
    crtPhoto.setMaxWidth(PHOTO_SIZE);
    add(crtPhoto);
    btnImageId = new Text("Image ID:");
    add(btnImageId);
    NumberField crtSolField = new NumberField("Sol:", this::solValueChange);
    crtSolField.setHasControls(true);
    crtSolField.setStep(1d);
    crtSolField.setMin(0);
    crtSolField.setMax(SOL_MAX_LIMIT);
    crtSolField.setValue(0d);
    add(crtSolField);
  }

  private void solValueChange(HasValue.ValueChangeEvent e) {
    if (!e.getHasValue().isEmpty()) {
      long newSol = (long) Double.parseDouble(e.getValue().toString());
      crtItemIndex = 0;
      crtPageIndex = 1;
      crtSol = newSol;
      btnPrev.setEnabled(false);
      marsPhotoList = restAPIService.fetchNewPhotoList(crtSol, crtPageIndex);
      if (!marsPhotoList.isEmpty()) {
        btnNext.setEnabled(true);
        crtPhoto.setSrc(marsPhotoList.get(0).getImg_src());
        btnImageId.setText(Long.toString(marsPhotoList.get(0).id));
      } else {
        btnNext.setEnabled(false);
        crtPhoto.setSrc("");
        btnImageId.setText("No image!");
      }
    }
  }

  private void prev() {
    refreshPhoto(crtItemIndex - 1);
  }

  private void next() {
    refreshPhoto(crtItemIndex + 1);
  }

  private void refreshPhoto(int newItem) {
    String imageSrc = fetchNewPhoto(newItem);
    crtPhoto.setSrc(imageSrc);
    if (imageSrc != null && !imageSrc.isEmpty()) {
      btnImageId.setText(Long.toString(marsPhotoList.get(crtItemIndex).id));
    }
  }

  private String fetchNewPhoto(int newItemIndex) {
    String result = crtPhoto.getSrc();

    if (newItemIndex >= 0 && newItemIndex < marsPhotoList.size()) {
      crtItemIndex = newItemIndex;
      result = marsPhotoList.get(crtItemIndex).img_src;
    } else if (newItemIndex < 0 && crtPageIndex > 1) {
      crtPageIndex--;
      crtItemIndex = MAX_ITEM_INDEX_IN_PAGE;
      marsPhotoList = restAPIService.fetchNewPhotoList(crtSol, crtPageIndex);
      result = marsPhotoList.get(crtItemIndex).img_src;
    } else if (newItemIndex >= marsPhotoList.size()) {
      List<MarsPhoto> tmpMarsPhotoList = restAPIService.fetchNewPhotoList(crtSol, crtPageIndex + 1);
      if (!tmpMarsPhotoList.isEmpty()) {
        crtItemIndex = 0;
        crtPageIndex++;
        marsPhotoList = tmpMarsPhotoList;
        result = marsPhotoList.get(crtItemIndex).img_src;
      } else {
        btnNext.setEnabled(false);
        return result;
      }
    }

    btnNext.setEnabled(true);
    btnPrev.setEnabled(!(crtPageIndex == 1 && crtItemIndex == 0));

    return result;
  }
  
}
