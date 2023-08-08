package com.example.naujas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.ImageButton;
import com.example.naujas.ClientAdapter.OnClientDeleteListener;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {
    private List<Client> clientList;
    private OnClientDeleteListener deleteListener;

    public ClientAdapter(List<Client> clientList, OnClientDeleteListener deleteListener) {
        this.clientList = clientList;
        this.deleteListener = deleteListener;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Client client = clientList.get(position);
        holder.nameTextView.setText(client.getName());
        holder.emailTextView.setText(client.getEmail());
        holder.phoneNumberTextView.setText(client.getPhone());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onClientDelete(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView emailTextView;
        public TextView phoneNumberTextView;
        public ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            emailTextView = itemView.findViewById(R.id.email_text_view);
            phoneNumberTextView = itemView.findViewById(R.id.phone_number_text_view);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

    public interface OnClientDeleteListener {
        void onClientDelete(int position);
    }
}





