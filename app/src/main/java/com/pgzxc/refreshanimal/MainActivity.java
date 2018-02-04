package com.pgzxc.refreshanimal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rl_top_toast)
    RelativeLayout rlTopToast;
    @BindView(R.id.tv_toast)
    TextView tvToast;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                showToast(new Random().nextInt(10) + 1);
                refreshlayout.finishRefresh();
            }
        });
    }

    private void showToast(int num) {
        tvToast.setText(String.format(getResources().getString(R.string.live_toast), num + ""));
        rlTopToast.setVisibility(View.VISIBLE);
        ViewAnimator.animate(rlTopToast)
                .newsPaper()
                .duration(1000)
                .start()
                .onStop(() -> ViewAnimator.animate(rlTopToast)
                        .bounceOut()
                        .duration(1000)
                        .start());
    }
}
