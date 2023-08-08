package com.example.naujas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import com.example.naujas.ClientAdapter.OnClientDeleteListener;
public class ClientList extends AppCompatActivity implements ClientAdapter.OnClientDeleteListener {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private List<Client> clientList;
    private ClientAdapter clientAdapter;

    @Override
    public void onClientDelete(int position) {
        Client client = clientList.get(position);
        clientList.remove(position);

        clientAdapter.notifyItemRemoved(position);
        clientAdapter = new ClientAdapter(clientList, this);
        databaseReference.child(client.getId()).removeValue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);
        recyclerView = findViewById(R.id.recycler_view);
        databaseReference = FirebaseDatabase.getInstance().getReference("clients");
        clientList = new ArrayList<>();
        clientAdapter = new ClientAdapter(clientList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(clientAdapter);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clientList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Client client = dataSnapshot.getValue(Client.class);
                    clientList.add(client);
                }
                clientAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ClientList.this, "Failed to retrieve client data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


