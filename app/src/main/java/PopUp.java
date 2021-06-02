import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.cardview.widget.CardView;

public class PopUp extends Activity {


    @SuppressLint("ClickableViewAccessibility")
    public void showPopUp(final View view) {
        view.getContext();
        final LayoutInflater inflater=(LayoutInflater)view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View popupView=inflater.inflate(R.layout.popupad,null);

        int width= CardView.LayoutParams.MATCH_PARENT;
        int height=CardView.LayoutParams.WRAP_CONTENT;
        boolean focusable=true;

        final PopupWindow popupWindow=new PopupWindow(popupView,width, height, true);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);



        // dismisses if clicked outside of window
        popupView.setOnTouchListener((v, event) -> {

            //Close the window when clicked
            popupWindow.dismiss();
            return true;
        });
    }
}
