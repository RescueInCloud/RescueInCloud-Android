package es.ucm.ric.tools.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 
 * @author Ricardo Champa
 *
 */
public class RobotoBlackTextView extends TextView {

    public RobotoBlackTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RobotoBlackTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoBlackTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Black.ttf");
        setTypeface(tf);
    }

}