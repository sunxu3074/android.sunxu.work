package work.sunxu.personalblog.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import work.sunxu.personalblog.R;
import work.sunxu.personalblog.adapter.BlogAdapter;
import work.sunxu.personalblog.bean.Blog;
import work.sunxu.personalblog.view.activity.WebViewActivity;
import work.sunxu.personalblog.view.listener.RecyclerItemClickListener;

public class BlogItemFragment extends Fragment {

    private static final String LIST_BLOG_TAG = "list_blog_tag";
    ArrayList<Blog> mBlogUrlList;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blogitem, container, false);
        // init RecyclerView
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new BlogAdapter(mBlogUrlList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.textview:
                        WebViewActivity.startWebViewActivity(getActivity(),mBlogUrlList.get(position).getLink());
                        break;

                }
            }
        }));
        return rootView;
    }


    // fragment init
    public static BlogItemFragment newInstace(ArrayList<Blog> blogUrlList) {
        BlogItemFragment blogItemFragment = new BlogItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LIST_BLOG_TAG, blogUrlList);
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
