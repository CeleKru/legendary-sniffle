package com.example.naujas;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;
import android.view.LayoutInflater;
public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder> {
    private Context mContext;
    private List<Template> mTemplates;
    public TemplateAdapter(Context context, List<Template> templates) {
        mContext = context;
        mTemplates = templates;
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
        public TemplateViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.template_title);
            mContentTextView = itemView.findViewById(R.id.template_content);
        }
        public void bind(Template template) {
            mTitleTextView.setText(template.getSubject());
            mContentTextView.setText(template.getBody());
        }
    }
}
