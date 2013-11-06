/**
 * Author       :   VenomVendor
 * Dated        :   7 Nov, 2013 - 2:45:48 AM
 * Project      :   String-Downloader
 * Contact      :   info@VenomVendor.com
 * URL          :   https://www.google.com/search?q=VenomVendor
 * Copyright(c) :   2013-Present, VenomVendor.
 * License      :   DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE - Version 2.
 **/

package vee.vee.string.downloader.sample;

import static vee.vee.string.downloader.sample.utils.APIs.imageURL;
import static vee.vee.string.downloader.sample.utils.APIs.jsonURL;
import static vee.vee.string.downloader.sample.utils.APIs.xmlURL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.impl.client.BasicCookieStore;

import vee.vee.string.downloader.StringDownloader;
import vee.vee.string.downloader.StringDownloaderListener;
import vee.vee.string.downloader.sample.utils.TagGen;

public class StringDownloaderSample extends Activity {

    static StringDownloader mStringDownloader;
    final static boolean debuggable = true;

    static String tag = "Unknown";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tag = TagGen.getTag(getClass());

        setContentView(R.layout.activity_main);

        final TextView mTextView = (TextView) findViewById(R.id.push_text);

        mStringDownloader = new StringDownloader(this, debuggable);

        mStringDownloader.setStringDownloadListener(new StringDownloaderListener() {

            @Override
            public void errorInServerSide(int statusCode, String statusDescription,
                    String url, int position, BasicCookieStore cookieStore) {

                Log.wtf(tag, statusDescription);

            }

            /**
             * YOU CAN UPDATE THE UI HERE.
             */
            @Override
            public void isAllStringsDownloadedSuccessfully(boolean status,
                    String lastFetchedResult) {

                if (status)
                {
                    // DO SOMETHING
                    mTextView.setText(lastFetchedResult);
                }

            }

            /**
             * DO NOT UPDATE THE UI HERE. Remember you are still in
             * doInBackground();
             */
            @Override
            public String stringDownloadedSuccessfully(String result, String url,
                    int position, BasicCookieStore cookie) {

                switch (position) {
                    case 0:
                        // Start JSON Parsing
                        break;
                    case 1:
                        // Start XML Parsing
                        break;
                    case 2:
                        // Start IMAGE Processing
                        break;

                    default:
                        break;
                }

                Log.w(tag, "==================================");
                Log.w(tag, result);
                Log.w(tag, "==================================");

                if (cookie != null)
                {
                    for (int i = 0; i < cookie.getCookies().size(); i++) {
                        // TODO - Try cookie.getCookies().get(i).getDomain();
                        Log.i(tag, "COOKIE" + i + " : " + cookie.getCookies().get(i).toString());
                    }
                }

                return null; // TODO return "null" will always work fine
            }

            @Override
            public void stringFetchingFailed(String reasonForFailure, String url,
                    int position, BasicCookieStore cookieStore) {
                Log.wtf(tag, reasonForFailure);

            }
        });

       final String[] multiURLs = {
                jsonURL,
                xmlURL,
                imageURL
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mStringDownloader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                    multiURLs);
        } else {
            mStringDownloader.execute(multiURLs);
        }

    }

}
