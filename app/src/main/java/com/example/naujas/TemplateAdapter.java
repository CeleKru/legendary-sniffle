package com.example.naujas;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import com.google.firebase.database.DatabaseReference;

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder> {
    private Context mContext;
    private List<Template> mTemplates;
    private DatabaseReference mDatabaseReference;

    public TemplateAdapter(Context context, List<Template> templates, DatabaseReference databaseReference) {
        mContext = context;
        mTemplates = templates;
        mDatabaseReference = databaseReference;
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.template_item, parent, false);
        return new TemplateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = mTemplates.get(position);
        holder.bind(template);
    }

    @Override
    public int getItemCount() {
        return mTemplates.size();
    }

    public class TemplateViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mContentTextView;
        private ImageButton mDeleteButton;

        public TemplateViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.template_title);
            mContentTextView = itemView.findViewById(R.id.template_content);
            mDeleteButton = itemView.findViewById(R.id.delete_button);

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Template template = mTemplates.get(position);
                    if (mDatabaseReference != null) {
                        mDatabaseReference.child(template.getId()).removeValue();
                    }
                    mTemplates.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }

        public void bind(Template template) {
            mTitleTextView.setText(template.getSubject());
            mContentTextView.setText(template.getBody());
        }
    }
}

