/*
 * Copyright (C) 2017 SpiritCroc spiritcroc@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.mail.browse;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.android.mail.R;

public class ThemableWebView extends WebView {

    public ThemableWebView(Context context) {
        super(context);
    }

    public ThemableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThemableWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ThemableWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void loadData(String data, String mimeType, String encoding) {
        super.loadData(getThemedData(data), mimeType, encoding);
    }

    @Override
    public void loadDataWithBaseURL(String baseUrl, String data,
            String mimeType, String encoding, String historyUrl) {
        super.loadDataWithBaseURL(baseUrl, getThemedData(data), mimeType, encoding, historyUrl);
    }

    private String getThemedData(String data) {
        Context context = getContext();
        if (context.getResources().getBoolean(R.bool.theme_webview)) {
            setBackgroundColor(context.getColor(R.color.webview_bg));

            String css = context.getString(R.string.webview_css);

            String body = String.format("#%06X",
                (0xFFFFFF & context.getColor(R.color.webview_body)));
            String a_link = String.format("#%06X",
                (0xFFFFFF & context.getColor(R.color.webview_a_link)));
            String a_active = String.format("#%06X",
                (0xFFFFFF & context.getColor(R.color.webview_a_active)));
            String a_visited = String.format("#%06X",
                (0xFFFFFF & context.getColor(R.color.webview_a_visited)));

            css = css.replaceAll("\\?resources/body", body);
            css = css.replaceAll("\\?resources/a_link", a_link);
            css = css.replaceAll("\\?resources/a_active", a_active);
            css = css.replaceAll("\\?resources/a_visited", a_visited);

            return css + "\n" + data;
        } else {
            return data;
        }
    }
}

