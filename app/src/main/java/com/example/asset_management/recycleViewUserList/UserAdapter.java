package com.example.asset_management.recycleViewUserList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asset_management.R;
import com.example.asset_management.login.UserInfo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * DeviceAdapter
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private OnNoteListener mOnNoteListener;
    private ArrayList<UserInfo> users;

    public void filterList(ArrayList<UserInfo> filteredList){
        users = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_user,
                parent,false);
        return new ViewHolder(v, mOnNoteListener);
    }

    public UserAdapter(ArrayList<UserInfo>users, OnNoteListener onNoteListener){
        this.users = users;
        this.mOnNoteListener = onNoteListener;
    }



    /**
     * Which informations should be displayed on the view
     * @param holder which informations should be displayed on the view
     * @param position from the Screen
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserInfo user = users.get(position);
        holder.firstname.setText(user.getFirstname());
        holder.surname.setText(user.getSurname());
        holder.role.setText(user.getRole());
        holder.workerId.setText(user.getWorkerIdString());
//        holder.inventoryNumber.setText(device.getInventoryNumber());
//        holder.manufacturer.setText(device.getManufacturer());
//        holder.model.setText(device.getModel());
//        holder.category.setText(device.getCategory());
//        holder.status.setText(device.getStatus());

    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else {
            return 0;
        }
    }

    /**
     * ViewHolder
     * <p>
     *     Version 1.0
     * </p>
     * 11.05.2020
     * creates the view with the TextFields
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View view;
        public final TextView workerId;
        public final TextView firstname;
        public final TextView surname;
        public final TextView role;
//        public final ImageView image;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            this.view = view;
            workerId = view.findViewById(R.id.workerId);
            firstname = view.findViewById(R.id.firstname);
            surname = view.findViewById(R.id.surname);
            role = view.findViewById(R.id.role);
//            image = view.findViewById(R.id.image);
            itemView.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View v) {
            try {
                onNoteListener.onNoteClick(getAdapterPosition());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position) throws IOException;
    }
}
