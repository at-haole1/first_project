package com.example.kimhao.first_project.API;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimhao.first_project.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KimHao on 05/04/2017.
 */

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder>  {
    private List<Item> mItems;
    private Context mContext;
    private PostItemListener mItemListener;
    public AnswersAdapter(Context context, List<Item> posts, PostItemListener postItemListener){
        this.mContext = context;
        this.mItems = posts;
        this.mItemListener = postItemListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.api_detail,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(postView,this.mItemListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Item item = mItems.get(i);
        Picasso.with(mContext)
                .load(item.getOwner().getProfileImage())
                .into(viewHolder.mImgApi);
        viewHolder.mTvNameApi.setText(item.getOwner().getDisplayName());
        viewHolder.mTvUserIdApi.setText(String.valueOf(item.getOwner().getUserId()));

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvNameApi,mTvUserIdApi;
        private ImageView mImgApi;
        PostItemListener mPostItemListener;
        public ViewHolder(View itemView, PostItemListener postItemListener){
            super(itemView);
            mImgApi = (ImageView) itemView.findViewById(R.id.imgApi);
            mTvUserIdApi =(TextView)itemView.findViewById(R.id.tvUserIdApi);
            mTvNameApi = (TextView) itemView.findViewById(R.id.tvNameApi);

            this.mPostItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Item item = getItem(getAdapterPosition());
            this.mPostItemListener.onPostClick(item.getAnswerId());
            notifyDataSetChanged();
        }
    }
    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }
    public interface PostItemListener{
        void onPostClick(long id);
    }
    public void updateAnswers(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}
