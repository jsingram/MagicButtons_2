package net.ingramintegrations.magicbuttons;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MagicButton extends FrameLayout {

//  Variables our MagicButton uses
    private TextView text;
    private LinearLayout button;
    private LinearLayout buttonIcon;
    private ImageView icon;
    private Drawable drawable;
    private TypedArray typedArray;
    private String buttonText;
    private int expandableButtonColor;
    private int iconButtonColor;
    private int textColor;
    private OnClickListener onClickListener;

//  Here I set my own variables to customize the MagicButton functionality.
    private int autoCloseDuration = 5;
    private static int DEFAULT_AUTO_CLOSE_DURATION = 5;
    private boolean animateIcon = true;

    Utils.DelayCallback mDelayCallBack;

//  ============================= Base Methods for the MagicButton =============================
    public MagicButton(Context context) {
        super(context);
        initView(context);
    }

    public MagicButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.MagicButton);
        if(typedArray != null) {
            buttonText = typedArray.getString(R.styleable.MagicButton_hide_text);
            expandableButtonColor = typedArray.getColor(R.styleable.MagicButton_expandable_area_color, Color.parseColor("#FFE6E4E4"));
            iconButtonColor = typedArray.getColor(R.styleable.MagicButton_icon_button_color, Color.parseColor("#FFE6E4E4"));
            textColor = typedArray.getColor(R.styleable.MagicButton_hide_text_color, Color.parseColor("#FFE6E4E4"));
            drawable = typedArray.getDrawable(R.styleable.MagicButton_button_icon);
        }
        initView(context);
    }

    public void setMagicButtonClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.magic_button, this, true);
        button = (LinearLayout) findViewById(R.id.expandable_button);
        buttonIcon = (LinearLayout) findViewById(R.id.icon_button);
        text = (TextView) findViewById(R.id.btn_text);
        icon = (ImageView) findViewById(R.id.btn_icon);
        setButtonContent();
        baseAction();
    }

    private void setButtonContent() {
        if(drawable != null) {
            icon.setBackground(drawable);
        }
        ((GradientDrawable) button.getBackground()).setColor(expandableButtonColor);
        ((GradientDrawable) buttonIcon.getBackground()).setColor(iconButtonColor);
        text.setText(buttonText);
        text.setTextColor(textColor);
        setSizes();
    }

    private void setSizes() {
        button.getLayoutParams().width = (int) typedArray.getDimension(R.styleable.MagicButton_magic_button_size, 50);
        button.getLayoutParams().height = (int) typedArray.getDimension(R.styleable.MagicButton_magic_button_size, 50);
        buttonIcon.getLayoutParams().width = (int) typedArray.getDimension(R.styleable.MagicButton_magic_button_size, 50);
        buttonIcon.getLayoutParams().height = (int) typedArray.getDimension(R.styleable.MagicButton_magic_button_size, 50);
        icon.getLayoutParams().width = (int) typedArray.getDimension(R.styleable.MagicButton_button_icon_width, 25);
        icon.getLayoutParams().height = (int) typedArray.getDimension(R.styleable.MagicButton_button_icon_height, 25);
        text.setTextSize(typedArray.getDimension(R.styleable.MagicButton_hide_text_size , 15));
    }

//  ================= Custom baseAction method and other custom methods ========================

//  I customized the baseAction() method to add the auto close and animate feature.
    private void baseAction() {
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
//              When the button is clicked, see if animateIcon is true or false
                if (animateIcon) {
//                  If true, animate the icon outward.
                    rotateIconOut();

                }else {
//                  If false do nothing
                }

                if(v.getWidth() == v.getHeight()) {
//                  If the button width and height are equal, the button is in it's small state,
//                  so this condition will be false and will un-hide your button's text.
                    text.setVisibility(VISIBLE);
                } else {
//                  When the width and height are equal we know the magic button has collapsed so
//                  we need to hide the button text and hide the expanded area.
                    text.setVisibility(GONE);
                    if(onClickListener != null) {
//                      Here we make sure we set a click listener for the MagicButton's second click
                        onClickListener.onClick(button);
                    }

                }
/*
                Look at the next method call, MagicKt.doWith(v);

                This method animates the MagicButton's expansion or compression.

                It looks a little different because it is a Kotlin method. Kotlin is the new
                official programming language on Android. To learn more about Kotlin, check out
                the links below:

                Kotlin Info From developer.android.com
                https://developer.android.com/kotlin/index.html

                Try Kotlin online at:
                https://try.kotlinlang.org/#/Examples/Hello,%20world!/Simplest%20version/Simplest%20version.kt

                Info From Wikipedia
                https://en.wikipedia.org/wiki/Kotlin_(programming_language)

*/
                MagicKt.doWith(v);

//              Here we set our DelayCallback we created in our Utils class.
                mDelayCallBack = new Utils.DelayCallback() {

//                  The most important method to OverRide is the afterDelay() method.
                    @Override
                    public void afterDelay() {
//                      After we've delayed for our set amount of seconds, hide the button text,
//                      check to see if the button is expanded or not, if animation is set to true,
//                      rotate the icon inward, and finally compress the button it it's normal size.
                        text.setVisibility(GONE);
                        if (v.getWidth() == v.getHeight()) {
                            // already hidden
                        }else
                        {
                            if(animateIcon) {
                                rotateIconIn();
                                MagicKt.doWith(v);
                            }else
                            MagicKt.doWith(v);
                        }

                    }
                };

//              We then call our delay and pass in the number of seconds to delay and our
//              DelayCallBack.
                Utils.delay(autoCloseDuration, mDelayCallBack);

            }
        });
    }

//  Method to set our MagicButtons auto close time. The default is 5 seconds.
    public void setAutoCloseDuration(int autoCloseDuration) {
        if (autoCloseDuration < 0) {
            Log.d("Magic Button Error", "Auto Close Duration must be great than 0! Setting to default of 5 seconds");
            autoCloseDuration = DEFAULT_AUTO_CLOSE_DURATION;
            this.autoCloseDuration = autoCloseDuration;
        }else
        this.autoCloseDuration = autoCloseDuration;
    }

//  Method to turn animations on or off. Pass in true to activate them and false to turn off animations.
//  Animations are turned on by default.
    public void setAnimateIcon(boolean animateIcon) {
        this.animateIcon = animateIcon;
    }

//  This method rotates the icon clockwise
    public void rotateIconOut(){
        icon.animate()
                .setDuration(200) // How long it should take to perform the animation in milliseconds.
                .rotation(360) // How many degrees to rotate the icon. 360 is a full circle.
                .start(); // Start the animation.
    }

//  This method rotates the icon counter-clockwise
    public void rotateIconIn(){

        icon.animate()
                .setDuration(200) // How long it should take to perform the animation in milliseconds.
                .rotation(-360) // How many degrees to rotate the icon. -360 is a full circle counter-clockwise.
                .start(); // Start the animation.
    }
}
