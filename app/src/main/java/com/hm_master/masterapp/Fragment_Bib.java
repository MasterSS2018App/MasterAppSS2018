package com.hm_master.masterapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Fragment_Bib extends Fragment {
    @Nullable

    View myView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.layout_bib,container,false);
        WebView webView= (WebView)v.findViewById(R.id.webViewbib);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.bib.hm.edu/recherche/opac_infoguide/index.de.html");
        return v;
    }
}
