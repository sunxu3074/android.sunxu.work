package work.sunxu.personalblog.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import work.sunxu.personalblog.BuildConfig;
import work.sunxu.personalblog.R;
import work.sunxu.personalblog.bean.Blog;
import work.sunxu.personalblog.view.activity.MainActivity;
import work.sunxu.personalblog.view.fragment.WebviewFragment;

/**
 * class description 首页adapter
 *
 * @author sunxu
 * @since 2018-06-13
 */
public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<Blog> mBlogArrayList;
    private static final String TAG = "SUNXU";

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;


        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public BlogAdapter(ArrayList<Blog> myDataset ,Context context) {
        mBlogArrayList = myDataset;
        mContext = context;
    }


    // Create new views (invoked by the layout manager)
    @Override public BlogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog_fragment, parent, false);

        ViewHolder vh = new ViewHolder(v);
        Log.d(TAG, "onCreateViewHolder");
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mBlogArrayList.get(position).getTitle());
        Log.d(TAG, "onBindViewHolder");
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override public int getItemCount() {
        return mBlogArrayList.size();
    }
}
