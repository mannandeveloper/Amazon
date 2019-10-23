package com.example.amazon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AmazonAdapter extends RecyclerView.Adapter<AmazonAdapter.ContentViewHolder>
{
    private Context mContext;
    private ArrayList<Content> mContentArrayList;
    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mItemClickListener = listener;
    }

    public AmazonAdapter(Context mContext, ArrayList<Content> mContentArrayList)
    {
        this.mContext = mContext;
        this.mContentArrayList = mContentArrayList;

    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_layout, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position)
    {
        Content contentList = mContentArrayList.get(position);
        String imageUrl = contentList.getImages();
        String name = contentList.getNames();
        int price = contentList.getPrices();

        Picasso.get().load(imageUrl).fit().centerCrop().into(holder.images);
        holder.names.setText("Name : "+ name);
        holder.prices.setText(String.valueOf("Price :"+ price));
    }

    @Override
    public int getItemCount()
    {
        return mContentArrayList.size();
    }

    public  class ContentViewHolder extends RecyclerView.ViewHolder
    {
        ImageView images;
        TextView names, prices;

        public ContentViewHolder(@NonNull View itemView)
        {
            super(itemView);
            images = itemView.findViewById(R.id.images);
            names = itemView.findViewById(R.id.name);
            prices = itemView.findViewById(R.id.price);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (mItemClickListener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            mItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
