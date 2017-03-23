package com.example.kimhao.first_project.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.model.ItemList;
import com.example.kimhao.first_project.model.ItemTitle;
import com.example.kimhao.first_project.model.ItemUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KimHao on 10/03/2017.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_TITLE = 1;
    private final int VIEW_TYPE_USER = 2;
    List<ItemList> mItemUserses = new ArrayList<>();

    private OnBundleTransfer mListener;

    Context context;



    public AdapterRecyclerView(List<ItemList> itemUserses, Context context, OnBundleTransfer listener ) {
        this.mItemUserses = itemUserses;
        this.context = context;
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return mItemUserses.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       RecyclerView.ViewHolder vh = null;
        if(i== VIEW_TYPE_USER){
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View itemView = layoutInflater.inflate(R.layout.item_person,viewGroup,false);
            vh = new ViewHolderItemUser(itemView);
            Log.d("VIEW_TYPE_USER ", "onCreateViewHolder: "+i+"");
        }else if (i == VIEW_TYPE_TITLE){
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View itemView = layoutInflater.inflate(R.layout.item_title,viewGroup,false);
            vh = new ViewHolderItemTitle(itemView);
            Log.d("VIEW_TYPE_TITLE", "onCreateViewHolder: "+i+"");
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ItemList ob = mItemUserses.get(i);
        if (viewHolder instanceof ViewHolderItemTitle) {
            if (ob instanceof ItemTitle) {
                ((ViewHolderItemTitle) viewHolder).mTvTitle.setText(((ItemTitle) ob).getStrTitle());
            }
        }
        if (viewHolder instanceof  ViewHolderItemUser){
            if (ob instanceof ItemUser){
                ItemUser item = (ItemUser) ob;
                ((ViewHolderItemUser)viewHolder).mTvName.setText(item.getName());
                ((ViewHolderItemUser)viewHolder).mTvAge.setText(item.getAge());
                ((ViewHolderItemUser)viewHolder).mTvAddr.setText(item.getAddr());
                if (!TextUtils.isEmpty(item.getImgAva())){
//                    Picasso.with(context)
//                            .load(new File(item.getImgAva()))
//                            .into(((ViewHolderItemUser)viewHolder).mImgAva);
                    //Log.d("Log đường link", "onBindViewHolder: "+item.getImgAva());
                    Picasso.with(context)
                            .load(item.getImgAva())
                            .into(((ViewHolderItemUser)viewHolder).mImgAva);
                }else {
                    ((ViewHolderItemUser)viewHolder).mImgAva.setImageResource(R.drawable.ic_person);

                }
                Log.d("Log đường link", "onBindViewHolder: "+item.getImgAva());
                if (((ItemUser) ob).isCheck())
                {
                    ((ViewHolderItemUser)viewHolder).mImgRibbon.setBackgroundResource(R.drawable.ic_fav_visible);
                }else {
                    ((ViewHolderItemUser)viewHolder).mImgRibbon.setBackgroundResource(R.drawable.ic_fav_invisible);
                }
                ((ViewHolderItemUser)viewHolder).mImgTrip.setImageResource(((ItemUser) ob).getImgTrip());
            }
        }


    }

    @Override
    public int getItemCount() {
        //Log.d("size", mItemUserses.size()+"");
        return mItemUserses.size();
    }
    public class ViewHolderItemUser extends RecyclerView.ViewHolder {
        TextView mTvName,mTvAge,mTvAddr;
        ImageView mImgAva,mImgRibbon,mImgTrip;


        public ViewHolderItemUser(final View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            mTvAddr = (TextView) itemView.findViewById(R.id.tvAddr);
            mImgAva = (ImageView) itemView.findViewById(R.id.imgAva);
            mImgRibbon = (ImageView)itemView.findViewById(R.id.imgRibbon);
            mImgTrip = (ImageView) itemView.findViewById(R.id.imgCusTrip);
            //progressBar = (ProgressBar)itemView.findViewById(R.id.progressbar);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ItemUser itemUsers = (ItemUser)mItemUserses.get(getLayoutPosition());
//                    itemUsers.setCheck(!itemUsers.isCheck());
//                    notifyItemChanged(getLayoutPosition());
//                }
//            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mListener.onClick((ItemUser) mItemUserses.get(getAdapterPosition()));
                }
            });
        }
    }

    public class ViewHolderItemTitle extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        public ViewHolderItemTitle(final View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
//    public ArrayList<Integer> getCheckedPositions() {
//        return checkedPositions;
//    }

        public interface OnBundleTransfer{
            void onClick(ItemUser itemUser);
        }
}
