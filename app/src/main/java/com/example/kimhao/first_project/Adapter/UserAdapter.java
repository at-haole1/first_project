package com.example.kimhao.first_project.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kimhao.first_project.Model.ItemList;
import com.example.kimhao.first_project.Model.ItemTitle;
import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KimHao on 10/03/2017.
 */
public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ItemUser> mLists = new ArrayList<>();

    private final Context mContext;
    private final int VIEW_PROGRESS = 0;
    private boolean mLoading;
    private final int mVisibleThreshold = 2;
    private int mLastVisibleItem;
    private int mTotalItemCount;
    private OnLoadMoreListener mOnLoadMoreListener;
    private final MyOnClickListener mMyOnClickListener;
    private ArrayList<ItemUser> mFavorites = new ArrayList<>();

    public ArrayList<ItemUser> getLists() {
        return mLists;
    }

    public UserAdapter(Context context, ArrayList<ItemUser> lists, RecyclerView recyclerView, MyOnClickListener listener) {
        this.mContext = context;
        this.mLists = lists;
        this.mMyOnClickListener = listener;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    mTotalItemCount = linearLayoutManager.getItemCount();
                    mLastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    Toast.makeText(mContext, "size: " + mTotalItemCount, Toast.LENGTH_SHORT).show();
                    if (!mLoading && mTotalItemCount <= (mLastVisibleItem + mVisibleThreshold)) {

                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                        mLoading = true;
                    }
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        final int VIEW_TITLE = 2;
        final int VIEW_ITEM = 1;
        if (viewType == VIEW_TITLE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
            vh = new TitleViewHolder(v);
        } else if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
            vh = new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progressbar, parent, false);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemList object = mLists.get(position);
        if (holder instanceof TitleViewHolder) {
            if (object instanceof ItemTitle) {
                ItemTitle title = (ItemTitle) object;
                ((TitleViewHolder) holder).mTvTitle.setText(title.getTitle());
            }
        } else if (holder instanceof ViewHolder) {
            if (object instanceof ItemUser) {
                ItemUser user = (ItemUser) object;
                if (!TextUtils.isEmpty(user.getImage())){
                    Picasso.with(mContext)
                            .load(user.getImage())
                            .fit()
                            .centerCrop()
                            .error(R.drawable.ic_person)
                            .into(((ViewHolder) holder).mImgAvatar);
                } else {
                    ((ViewHolder) holder).mImgAvatar.setImageResource(R.drawable.ic_user);
                }
                ((ViewHolder) holder).mTvName.setText(user.getName());
                ((ViewHolder) holder).mTvAge.setText(user.getAge());
                ((ViewHolder) holder).mTvAddress.setText(user.getAddress());
                ((ViewHolder) holder).mImgFavorite.setSelected(user.isFavorite());
            }
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        private final ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.item_progress_bar);
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImgAvatar;
        private final TextView mTvName;
        private final TextView mTvAge;
        private final TextView mTvAddress;
        private final ImageView mImgFavorite;
        private ItemUser user;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImgAvatar = (ImageView) itemView.findViewById(R.id.imgAva);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            mTvAddress = (TextView) itemView.findViewById(R.id.tvAddr);
            mImgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMyOnClickListener.onClickListener(getLayoutPosition());
                }
            });

            mImgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = mLists.get(getLayoutPosition());
                    user.setFavorite(!user.isFavorite());
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void setLoaded() {
        mLoading = false;
    }

    @Override
    public int getItemViewType(int position) {
        if (mLists.get(position) == null) {
            return VIEW_PROGRESS;
        } else {
            return mLists.get(position).getType();
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
    public interface MyOnClickListener {
        void onClickListener(int position);
    }


}
