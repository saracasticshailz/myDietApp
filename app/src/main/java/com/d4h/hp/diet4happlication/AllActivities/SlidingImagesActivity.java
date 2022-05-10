package com.d4h.hp.diet4happlication.AllActivities;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.d4h.hp.diet4happlication.R;


public class SlidingImagesActivity extends AppCompatActivity {

    //only need to add the images which are give for sliding whwn app is install by user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPage);
        SlideImageAdaptor adapterView = new SlideImageAdaptor(this);
        mViewPager.setAdapter(adapterView);
    }


    public class SlideImageAdaptor extends PagerAdapter {

        public Context mContext;

        SlideImageAdaptor(Context context) {
            this.mContext = context;
        }
        @Override
        public int getCount() {
            return sliderImageId.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, Object object) {
            return view == ((ImageView)object);
        }
        private int[] sliderImageId = new int[]{
                // R.drawable.image1, R.drawable.image2, R.drawable.image3,R.drawable.image4, R.drawable.image5,
        };

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(sliderImageId[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }

    }


}