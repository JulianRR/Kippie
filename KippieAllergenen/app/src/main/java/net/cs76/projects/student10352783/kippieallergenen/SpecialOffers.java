package net.cs76.projects.student10352783.kippieallergenen;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
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

import java.util.ArrayList;

/**
 * SpecialOffers.java
 *
 * This class is used to display the special offers as a slideshow.
 *
 * Name: Julian Ruger
 * Student ID: 10352783
 * E-mail: julian.ruger@student.uva.nl
 */
public class SpecialOffers {

    private ImageSwitcher imageSwitcher;
    private ImageView imageView;
    private Button previous, next;
    private Bitmap bitmap;
    Queries queries;

    private int[] images = {R.drawable.ic_launcher, R.drawable.android_icon, R.drawable.android_vector};
    int current = 0;

    public void setView(View rootView, final Activity activity, final LayoutInflater inflater) {

        queries = Queries.getInstance();
        bitmap = queries.getBitmap();

        imageSwitcher = (ImageSwitcher) rootView.findViewById(R.id.imageSwitcher);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        previous = (Button) rootView.findViewById(R.id.previous);
        next = (Button) rootView.findViewById(R.id.next);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView view = new ImageView(activity);
                return view;
            }
        });
        imageSwitcher.setImageResource(images[0]);
        imageView.setImageBitmap(bitmap);


        /* Previous image*/
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation in = AnimationUtils.loadAnimation(activity, android.R.anim.fade_in);
                imageSwitcher.setInAnimation(in);
                /* Set index of array to the previous index, if index is 0 go to last item in
                 * the array.
                 */
                if (current == 0) {
                    current = images.length - 1;
                } else {
                    current = (current - 1) % images.length;
                }

                imageSwitcher.setImageResource(images[current]);
            }
        });

        /* Next image*/
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation in = AnimationUtils.loadAnimation(activity, android.R.anim.fade_in);
                imageSwitcher.setInAnimation(in);
                /* Set index to next index in array. */
                current = (current + 1) % images.length;
                imageSwitcher.setImageResource(images[current]);
            }
        });

    }
}
