package work.sunxu.personalblog.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import work.sunxu.personalblog.R;
import work.sunxu.personalblog.bean.Blog;

public class BlogItemFragment extends Fragment {

    private static final String LIST_BLOG_TAG = "list_blog_tag";
    ArrayList<Blog> mBlogUrlList;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blogitem, container, false);
        TextView textView = rootView.findViewById(R.id.textview);
        textView.setText(mBlogUrlList.get(0).getTitle());
        return rootView;
    }

    // fragment init
     public static BlogItemFragment newInstace(ArrayList<Blog> blogUrlList) {
        BlogItemFragment blogItemFragment = new BlogItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LIST_BLOG_TAG,blogUrlList);
        blogItemFragment.setArguments(bundle);
        return blogItemFragment;
    }


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mBlogUrlList = arguments.getParcelableArrayList(LIST_BLOG_TAG);
        }
    }
}
