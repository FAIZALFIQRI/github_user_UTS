package com.example.github_user.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.github_user.data.Response.ItemsItem;

import java.util.ArrayList;
import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.ViewHolder> {
    private ArrayList<ItemsItem> listUsers;

    public GithubUserAdapter(ArrayList<ItemsItem> listUsers) {
        this.listUsers = listUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.github_user.R.layout.list_nama, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemsItem user = listUsers.get(position);
        holder.usernameTextView.setText(user.getLogin());
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatarUrl())
                .into(holder.avatarImageView);

        holder.itemView.setOnClickListener(click -> {
            Intent intent = new Intent(click.getContext(), Detail.class);
            intent.putExtra(Detail.EXTRA_USERNAME, user.getLogin());
            click.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public void setUsers(List<ItemsItem> listUsers) {
        this.listUsers = (ArrayList<ItemsItem>) listUsers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        TextView usernameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(com.example.github_user.R.id.Foto);
            usernameTextView = itemView.findViewById(com.example.github_user.R.id.Datanama);
        }
    }
}

