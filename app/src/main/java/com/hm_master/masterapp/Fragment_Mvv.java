package com.hm_master.masterapp;

        import android.app.Fragment;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.webkit.WebView;
        import android.app.Activity;
        import android.os.Bundle;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;

public class Fragment_Mvv extends Fragment {

public Fragment_Mvv(){

}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

View v= inflater.inflate(R.layout.layout_mvv,container,false);
WebView webView= (WebView)v.findViewById(R.id.webView);
webView.getSettings().setJavaScriptEnabled(true);
webView.setWebViewClient(new WebViewClient());
webView.loadUrl("https://www.mvv-muenchen.de");
        return v;

    }

}