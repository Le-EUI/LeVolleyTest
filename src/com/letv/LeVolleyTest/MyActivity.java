package com.letv.LeVolleyTest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.letv.LeVolleyTest.R;
import com.android.volley.toolbox.DownloadRequest;

public class MyActivity extends Activity implements View.OnClickListener{
    String url = "http://pic32.nipic.com/20130829/12906030_124355855000_2.png"
            /*"http://sw.bos.baidu.com/sw-search-sp/software/0a2a3945bde/jdk_8u91_windows_i586_8.0.910.15.exe"*/;
    String savePath = "";

    String fileName = "lalala.png";


    TextView name;
    TextView progress;
    ProgressBar progressBar;
    Button button;

    DownloadRequest request;
    RequestQueue requestQueue;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        initData();
    }

    private void initView(){
        name = (TextView)findViewById(R.id.name);
        progress = (TextView)findViewById(R.id.progress);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        button = (Button)findViewById(R.id.btn);

        name.setText(fileName);

        button.setText("开始");
        button.setOnClickListener(this);
    }

    private void initData(){
        savePath = getCacheDir().getPath();
        requestQueue = Volley.newRequestQueue(this);
        request = new DownloadRequest(
                requestQueue,
                url,
                savePath,
                fileName,
                new Response.Listener<Long>() {
                    @Override
                    public void onResponse(Long response) {
                        android.util.Log.d("ccc", response+"sss");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        request.setProgressListener(new Response.ProgressListener() {
            @Override
            public void onStart(long fileSize) {

            }

            @Override
            public void onProgress(long completeSize, int progress) {
                android.util.Log.d("ccc", "progress: " + progress+ ", completeSize: "+ completeSize);
                progressBar.setProgress(progress);
                MyActivity.this.progress.setText(progress+"%");
            }

            @Override
            public void onEnd() {

            }
        });
        request.supportBreakpoint(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                if (request.isLoading()){
                    request.pause();
                    button.setText("开始");
                } else {
                    //request.resume();
                    requestQueue.add(request);
                    button.setText("暂停");
                }
                break;
        }
    }
}
