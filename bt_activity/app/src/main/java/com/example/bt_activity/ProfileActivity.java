package com.example.bt_activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.*;

public class ProfileActivity extends Activity {

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userManager = new UserManager(this);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(Color.parseColor("#F0F4FF"));

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setPadding(40, 60, 40, 60);
        rootLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        scrollView.addView(rootLayout);

        // ===== Thanh tiêu đề =====
        LinearLayout topBar = new LinearLayout(this);
        topBar.setOrientation(LinearLayout.HORIZONTAL);
        topBar.setGravity(Gravity.END);
        topBar.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        ImageView btnBack = new ImageView(this);
        btnBack.setImageResource(android.R.drawable.ic_media_previous);
        btnBack.setColorFilter(Color.parseColor("#37474F"));
        btnBack.setPadding(16, 16, 16, 16);
        topBar.addView(btnBack);

        ImageView btnSetting = new ImageView(this);
        btnSetting.setImageResource(android.R.drawable.ic_menu_manage);
        btnSetting.setColorFilter(Color.parseColor("#37474F"));
        btnSetting.setPadding(16, 16, 16, 16);
        topBar.addView(btnSetting);

        rootLayout.addView(topBar);

        // ===== Avatar + Tên người dùng =====
        LinearLayout profileHeader = new LinearLayout(this);
        profileHeader.setOrientation(LinearLayout.VERTICAL);
        profileHeader.setGravity(Gravity.CENTER);
        profileHeader.setPadding(0, 40, 0, 20);

        FrameLayout avatarFrame = new FrameLayout(this);
        avatarFrame.setLayoutParams(new FrameLayout.LayoutParams(240, 240));

        ImageView avatar = new ImageView(this);
        avatar.setImageResource(android.R.drawable.sym_def_app_icon);
        avatar.setLayoutParams(new FrameLayout.LayoutParams(240, 240));
        avatar.setBackground(getCircleBackground("#FFFFFF"));
        avatar.setPadding(20, 20, 20, 20);
        avatar.setElevation(6f);
        avatarFrame.addView(avatar);

        TextView badge = new TextView(this);
        badge.setText("3");
        badge.setTextColor(Color.WHITE);
        badge.setTextSize(12);
        badge.setGravity(Gravity.CENTER);
        badge.setBackground(getCircleBackground("#F44336"));
        FrameLayout.LayoutParams badgeParams = new FrameLayout.LayoutParams(40, 40);
        badgeParams.gravity = Gravity.TOP | Gravity.END;
        badgeParams.setMargins(0, 8, 8, 0);
        badge.setLayoutParams(badgeParams);
        avatarFrame.addView(badge);

        profileHeader.addView(avatarFrame);

        TextView tvName = new TextView(this);
        tvName.setText(userManager.getProfileName());
        tvName.setTextSize(22);
        tvName.setTypeface(Typeface.DEFAULT_BOLD);
        tvName.setTextColor(Color.parseColor("#0D47A1"));
        tvName.setPadding(0, 16, 0, 0);
        profileHeader.addView(tvName);

        rootLayout.addView(profileHeader);

        // ===== Thống kê =====
        LinearLayout statsLayout = new LinearLayout(this);
        statsLayout.setOrientation(LinearLayout.HORIZONTAL);
        statsLayout.setGravity(Gravity.CENTER);
        statsLayout.setPadding(0, 20, 0, 20);
        statsLayout.addView(createStatBox("125", "Friends"));
        statsLayout.addView(createStatBox("250", "Followers"));
        rootLayout.addView(statsLayout);

        // ===== Thông tin liên hệ =====
        rootLayout.addView(sectionTitle("Thông tin liên hệ"));

        LinearLayout contactBox = sectionCard();

// Thông tin mẫu đã điền đầy đủ
        addInfo(contactBox, "📧 Email", "phanhuynhvietthien@gmail.com");
        addInfo(contactBox, "📞 Phone", "+84 912 345 678");
        addInfo(contactBox, "💬 Skype", "phanthien.designer");
        addInfo(contactBox, "🌐 Website", "www.phanthienUTE.com");

        rootLayout.addView(contactBox);


        // ===== Giới thiệu bản thân =====
        rootLayout.addView(sectionTitle("Giới thiệu"));
        TextView tvAbout = new TextView(this);
        tvAbout.setText("Xin chào! Tôi là một nhà thiết kế UI/UX với niềm đam mê sáng tạo và công nghệ. Tôi thích tạo ra những trải nghiệm người dùng trực quan và hiệu quả.");
        tvAbout.setTextSize(14);
        tvAbout.setTextColor(Color.parseColor("#37474F"));
        tvAbout.setPadding(0, 0, 0, 20);
        rootLayout.addView(tvAbout);

        // ===== Kỹ năng =====
        rootLayout.addView(sectionTitle("Kỹ năng"));
        rootLayout.addView(createSkillBar("Thiết kế đồ họa", 90));
        rootLayout.addView(createSkillBar("UI/UX", 85));
        rootLayout.addView(createSkillBar("Lập trình Android", 70));

        // ===== Mạng xã hội =====
        rootLayout.addView(sectionTitle("Mạng xã hội"));
        LinearLayout socialBox = sectionCard();
        addInfo(socialBox, "📘 Facebook", "facebook.com/phan.designer");
        addInfo(socialBox, "📸 Instagram", "instagram.com/phan.uiux");
        addInfo(socialBox, "💼 LinkedIn", "linkedin.com/in/phan");
        rootLayout.addView(socialBox);

        // ===== Nút chỉnh sửa hồ sơ =====
        Button btnEdit = new Button(this);
        btnEdit.setText("Chỉnh sửa hồ sơ");
        btnEdit.setTextColor(Color.WHITE);
        btnEdit.setTextSize(16);
        btnEdit.setBackgroundColor(Color.parseColor("#03A9F4"));
        btnEdit.setPadding(20, 20, 20, 20);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        btnParams.setMargins(0, 40, 0, 60);
        btnEdit.setLayoutParams(btnParams);
        rootLayout.addView(btnEdit);

        btnEdit.setOnClickListener(v ->
                Toast.makeText(this, "Chức năng chỉnh sửa đang được phát triển!", Toast.LENGTH_SHORT).show()
        );

        setContentView(scrollView);

        btnBack.setOnClickListener(v -> finish());

        btnSetting.setOnClickListener(v -> {
            Toast.makeText(this, "Đang quay lại đăng nhập...", Toast.LENGTH_SHORT).show();
            userManager.logout();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        });
    }

    // ===== Tạo box thống kê =====
    private LinearLayout createStatBox(String value, String label) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        box.setLayoutParams(params);

        TextView tvValue = new TextView(this);
        tvValue.setText(value);
        tvValue.setTextSize(18);
        tvValue.setTypeface(Typeface.DEFAULT_BOLD);
        tvValue.setTextColor(Color.parseColor("#1A237E"));
        box.addView(tvValue);

        TextView tvLabel = new TextView(this);
        tvLabel.setText(label);
        tvLabel.setTextSize(14);
        tvLabel.setTextColor(Color.parseColor("#607D8B"));
        box.addView(tvLabel);

        return box;
    }

    // ===== Tạo tiêu đề từng phần =====
    private TextView sectionTitle(String title) {
        TextView tv = new TextView(this);
        tv.setText(title);
        tv.setTextSize(16);
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        tv.setTextColor(Color.parseColor("#37474F"));
        tv.setPadding(0, 30, 0, 10);
        return tv;
    }

    // ===== Tạo khung trắng bo góc =====
    private LinearLayout sectionCard() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(40, 30, 40, 30);
        box.setBackground(getRoundedBackground("#FFFFFF", 30));
        box.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return box;
    }

    // ===== Hiển thị thông tin từng dòng =====
    private void addInfo(LinearLayout parent, String label, String value) {
        TextView tv = new TextView(this);
        tv.setText(label + ": " + value);
        tv.setTextSize(14);
        tv.setTextColor(Color.parseColor("#37474F"));
        tv.setPadding(0, 8, 0, 8);
        parent.addView(tv);
    }

    // ===== Tạo nền bo góc =====
    private GradientDrawable getRoundedBackground(String colorHex, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor(colorHex));
        drawable.setCornerRadius(radius);
        return drawable;
    }

    // ===== Tạo nền hình tròn =====
    private GradientDrawable getCircleBackground(String colorHex) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(Color.parseColor(colorHex));
        return drawable;
    }

    // ===== Tạo thanh kỹ năng =====
    private LinearLayout createSkillBar(String skillName, int percent) {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 8, 0, 8);

        TextView label = new TextView(this);
        label.setText(skillName);
        label.setTextSize(14);
        label.setTextColor(Color.parseColor("#37474F"));
        layout.addView(label);

        ProgressBar bar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        bar.setMax(100);
        bar.setProgress(percent);
        bar.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        bar.setProgressDrawable(getResources().getDrawable(android.R.drawable.progress_horizontal));
        layout.addView(bar);

        return layout;
    }
}
