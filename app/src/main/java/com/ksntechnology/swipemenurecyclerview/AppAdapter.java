package com.ksntechnology.swipemenurecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tubb.smrv.SwipeMenuLayout;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter{
    private static final int VIEW_TYPE_ENABLE = 0;
    private static final int VIEW_TYPE_DISABLE = 1;

    private List<User> users;
    private Context mContext;
    private onItemClickListener mCallBack;

    public interface onItemClickListener{
        void onItemClick(int position, View view);
    }

    public AppAdapter(Context context, List<User> users) {
        this.users = users;
        this.mContext = context;
    }

    @Override
    public long getItemId(int position) {
        //return super.getItemId(position);
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater
                        .from(mContext)
                        .inflate(
                                    R.layout.item_simple,
                                    parent,
                                false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = users.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)holder;

        myViewHolder.tvName.setText(user.userName);
        myViewHolder.tvSwipeEnable.setVisibility(View.GONE);

        /*SwipeMenuLayout itemView = (SwipeMenuLayout) myViewHolder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Hi " + user.userName, Toast.LENGTH_SHORT).show();
            }
        });
        myViewHolder.btGood.setVisibility(View.GONE);
        myViewHolder.btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Open " + user.userName, Toast.LENGTH_SHORT).show();
            }
        });

        myViewHolder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.remove(myViewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(myViewHolder.getAdapterPosition());
            }
        });
        myViewHolder.tvName.setText(user.userName);
        myViewHolder.tvSwipeEnable.setVisibility(View.GONE);

        *//**
         * optional
         *//*
        itemView.setOpenInterpolator(mRecyclerView.getOpenInterpolator());
        itemView.setCloseInterpolator(mRecyclerView.getCloseInterpolator());*/
    }


    public void setOnItemClickListener(onItemClickListener listener) {
        mCallBack = listener;
    }

    @Override
    public int getItemViewType(int position) {
        User user = users.get(position);
        if(user.userId % 2 == 0){
            return VIEW_TYPE_DISABLE;
        }else{
            return VIEW_TYPE_ENABLE;
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public boolean swipeEnableByViewType(int viewType) {
        if(viewType == VIEW_TYPE_ENABLE) return true;
        else if(viewType == VIEW_TYPE_DISABLE) return false;
        else return true; // default
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public SwipeMenuLayout swipeMenuLayout;
        public TextView tvName;
        public TextView tvSwipeEnable;
        public View btGood;
        public View btEdit;
        public View btDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvSwipeEnable = (TextView) itemView.findViewById(R.id.tvSwipeEnable);
            btGood = itemView.findViewById(R.id.btGood);
            btEdit = itemView.findViewById(R.id.btEdit);
            btDelete = itemView.findViewById(R.id.btDelete);

            swipeMenuLayout = (SwipeMenuLayout) itemView;
            /*swipeMenuLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.onItemClick(getAdapterPosition(), v);
                }
            });*/
            btGood.setVisibility(View.GONE);
            btEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext, "Open " + user.userName, Toast.LENGTH_SHORT).show();
                    mCallBack.onItemClick(getAdapterPosition(), v);
                }
            });

            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*users.remove(myViewHolder.getAdapterPosition());
                    mAdapter.notifyItemRemoved(myViewHolder.getAdapterPosition());*/
                    mCallBack.onItemClick(getAdapterPosition(), v);
                }
            });

            /*itemView.setOpenInterpolator(mRecyclerView.getOpenInterpolator());
            itemView.setCloseInterpolator(mRecyclerView.getCloseInterpolator());*/
        }
    }

}
