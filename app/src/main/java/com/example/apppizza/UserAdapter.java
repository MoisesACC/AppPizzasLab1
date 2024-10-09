package com.example.apppizza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userNameTextView.setText(user.getUserName());
        holder.profileImageView.setImageResource(user.getProfileImage());
        holder.likesTextView.setText(user.getLikes() + " likes");

        // Restablecer el listener del checkbox antes de asignarlo
        holder.likeCheckBox.setOnCheckedChangeListener(null);

        // Sincronizar el estado del checkbox con el estado de 'isLiked' del usuario
        holder.likeCheckBox.setChecked(user.isLiked());

        // Manejar el evento de clic en el CheckBox
        holder.likeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                user.setLikes(user.getLikes() + 1);
                user.setLiked(true);  // Se ha marcado el like
                Toast.makeText(context, "¡Le diste 'me gusta' a " + user.getUserName() + "!", Toast.LENGTH_SHORT).show();
            } else {
                user.setLikes(user.getLikes() - 1);
                user.setLiked(false);  // Se ha desmarcado el like
            }
            holder.likesTextView.setText(user.getLikes() + " likes");
        });
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImageView;
        TextView userNameTextView, likesTextView;
        CheckBox likeCheckBox;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.profileImageView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            likesTextView = itemView.findViewById(R.id.likesTextView);
            likeCheckBox = itemView.findViewById(R.id.likeCheckBox);
        }
    }
}