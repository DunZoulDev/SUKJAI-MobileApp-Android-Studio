package com.example.SUKJAI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

class SliderAdp extends SliderViewAdapter<SliderAdp.Holder> {
    int[] images;
    public SliderAdp(int[] images){
        this.images = images;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.z_item_slider,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imageView.setImageResource(images[position]);

    }

    @Override
    public int getCount() {

        return images.length;
    }

public class Holder extends SliderViewAdapter.ViewHolder {
    ImageView imageView;
    public Holder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_slider);
    }
}
}
