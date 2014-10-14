package net.cs76.projects.student10352783.kippieallergenen;

import android.app.ActionBar;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

/**
 * Created by julianruger on 12-10-14.
 * This class is used to display the special offers as a slideshow.
 */
public class SpecialOffers {

    private ImageSwitcher imageSwitcher;
    private Button previous, next;

    public void setView(View rootView, final Activity activity, final LayoutInflater inflater) {

        imageSwitcher = (ImageSwitcher) rootView.findViewById(R.id.imageSwitcher);
        previous = (Button) rootView.findViewById(R.id.previous);
        next = (Button) rootView.findViewById(R.id.next);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView view = new ImageView(activity);
                return view;
            }
        });
        imageSwitcher.setImageResource(R.drawable.ic_launcher);

        /* Previous image*/
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation in = AnimationUtils.loadAnimation(activity, android.R.anim.fade_out);
                imageSwitcher.setInAnimation(in);
                imageSwitcher.setImageResource(R.drawable.ic_launcher);
            }
        });

        /* next image*/
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation in = AnimationUtils.loadAnimation(activity, android.R.anim.fade_in);
                imageSwitcher.setInAnimation(in);
                imageSwitcher.setImageResource(R.drawable.ic_launcher);
            }
        });

    }
}
