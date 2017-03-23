package com.example.kimhao.first_project.Fragment.AdapterFragment;

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
import com.example.kimhao.first_project.model.ItemUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KimHao on 10/03/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolderItemUser> {

    private final int VIEW_TYPE_TITLE = 1;
    private final int VIEW_TYPE_USER = 2;
    List<ItemUser> mItemUserses = new ArrayList<>();


    Context context;



    public MyRecyclerViewAdapter(List<ItemUser> itemUserses, Context context ) {
        this.mItemUserses = itemUserses;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return mItemUserses.get(position).getType();
    }

    @Override
    public ViewHolderItemUser onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolderItemUser vh = null;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_person,viewGroup,false);
        vh = new ViewHolderItemUser(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolderItemUser viewHolder, int i) {

                viewHolder.mTvName.setText(mItemUserses.get(i).getName());
                viewHolder.mTvAge.setText(mItemUserses.get(i).getAge());
                ((ViewHolderItemUser)viewHolder).mTvAddr.setText(mItemUserses.get(i).getAddr());
                if (!TextUtils.isEmpty(mItemUserses.get(i).getImgAva())){
//                    Picasso.with(context)
//                            .load(new File(item.getImgAva()))
//                            .into(((ViewHolderItemUser)viewHolder).mImgAva);
                    //Log.d("Log đường link", "onBindViewHolder: "+item.getImgAva());
                    Picasso.with(context)
                            .load(mItemUserses.get(i).getImgAva())
                            .into(viewHolder.mImgAva);
                }else {
                    viewHolder.mImgAva.setImageResource(R.drawable.ic_person);
                }
                Log.d("Log đường link", "onBindViewHolder: "+mItemUserses.get(i).getImgAva());
                if (mItemUserses.get(i).isCheck())
                {
                    viewHolder.mImgRibbon.setBackgroundResource(R.drawable.ic_fav_visible);
                }else {
                    viewHolder.mImgRibbon.setBackgroundResource(R.drawable.ic_fav_invisible);
                }
                viewHolder.mImgTrip.setImageResource(mItemUserses.get(i).getImgTrip());
            }

    @Override
    public int getItemCount() {
        //Log.d("size", mItemUserses.size()+"");
        return mItemUserses.size();
    }


    public class ViewHolderItemUser extends RecyclerView.ViewHolder {
        TextView mTvName,mTvAge,mTvAddr;
        ImageView mImgAva,mImgRibbon,mImgTrip;
        public ViewHolderItemUser(View itemView) {
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
        }
    }
//    public ArrayList<Integer> getCheckedPositions() {
//        return checkedPositions;
//    }
}
