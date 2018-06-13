package work.sunxu.personalblog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import work.sunxu.personalblog.R;

/**
 * class description here
 *
 * @author sunxu
 * @since 2018-06-14
 */
public class WebViewActivity extends AppCompatActivity {

    private static final String LINK_TAG = "link_tag";
    private String linkUrl;

    private WebView mWebView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initData();
        initWebview();
    }


    private void initData() {
        if (getIntent() != null && !TextUtils.isEmpty(getIntent().getStringExtra(LINK_TAG))) {
            linkUrl = getIntent().getStringExtra(LINK_TAG);
        }
    }


    private void initWebview() {
        mWebView = findViewById(R.id.webview);
        mWebView.loadUrl(linkUrl);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("sunxu", "url = " + url);
                return true;
            }
        });
    }

    public static void startWebViewActivity(Context context,String linkUrl) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(LINK_TAG, linkUrl);
        context.startActivity(intent);
    }
}
