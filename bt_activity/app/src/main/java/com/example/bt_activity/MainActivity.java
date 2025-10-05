package com.example.bt_activity;
import android.graphics.drawable.GradientDrawable;
import android.app.Activity;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ===== Layout chính =====
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        // ===== Gradient nền =====
        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{Color.parseColor("#64B5F6"), Color.parseColor("#2196F3")}
        );
        layout.setBackground(gradient);

        // ===== Logo (biểu tượng tròn) =====
        TextView logo = new TextView(this);
        logo.setText("🔒");
        logo.setTextSize(70);
        logo.setGravity(Gravity.CENTER);
        layout.addView(logo);

        // ===== Tên ứng dụng =====
        TextView tvSplash = new TextView(this);
        tvSplash.setText("QUẢN LÝ TÀI KHOẢN");
        tvSplash.setTextColor(Color.WHITE);
        tvSplash.setTextSize(28);
        tvSplash.setPadding(0, 30, 0, 0);
        tvSplash.setTypeface(Typeface.DEFAULT_BOLD);
        tvSplash.setGravity(Gravity.CENTER);
        layout.addView(tvSplash);

        // ===== Dòng phụ đề =====
        TextView tvSubtitle = new TextView(this);
        tvSubtitle.setText("Phiên bản Android - Phan Huỳnh Viết Thiện");
        tvSubtitle.setTextColor(Color.parseColor("#E3F2FD"));
        tvSubtitle.setTextSize(16);
        tvSubtitle.setGravity(Gravity.CENTER);
        tvSubtitle.setPadding(0, 10, 0, 0);
        layout.addView(tvSubtitle);

        // ===== Thanh tiến trình (ProgressBar) =====
        ProgressBar progress = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progress.setIndeterminate(false);
        progress.setMax(100);
        progress.setProgress(0);
        progress.setPadding(60, 80, 60, 0);
        progress.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        layout.addView(progress);

        setContentView(layout);

        // ===== Hiệu ứng nạp thanh tiến trình =====
        Handler handler = new Handler();
        Runnable progressRunnable = new Runnable() {
            int value = 0;
            @Override
            public void run() {
                value += 5;
                progress.setProgress(value);
                if (value < 100) {
                    handler.postDelayed(this, 100);
                }
            }
        };
        handler.post(progressRunnable);

        // ===== Chuyển sang LoginActivity sau 2,5 giây =====
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 2500);
    }
}
