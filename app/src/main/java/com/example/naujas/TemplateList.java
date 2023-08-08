package com.example.naujas;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
public class TemplateList extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TemplateAdapter mAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mTemplatesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private List<Template> mTemplates = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_list);
        getSupportActionBar().hide();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TemplateAdapter(this, mTemplates, mTemplatesDatabaseReference);
        mRecyclerView.setAdapter(mAdapter);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mTemplatesDatabaseReference = mFirebaseDatabase.getReference().child("templates");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mTemplates.add(dataSnapshot.getValue(Template.class));
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //TODO: Implement if needed
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //TODO: Implement if needed
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //TODO: Implement if needed
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO: Implement if needed
            }
        };
        mTemplatesDatabaseReference.addChildEventListener(mChildEventListener);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTemplatesDatabaseReference.removeEventListener(mChildEventListener);
    }
}
