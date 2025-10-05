package com.example.bt_activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.*;

public class LoginActivity extends Activity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin, btnRegister;
    private TextView tvForgot;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userManager = new UserManager(this);

        if (userManager.isLoggedIn()) {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
            return;
        }

        // === ScrollView để tránh tràn màn hình ===
        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(60, 80, 60, 80);
        layout.setBackgroundColor(Color.parseColor("#FAFAFA"));
        scrollView.addView(layout);

        // === Tiêu đề ===
        TextView tvTitle = new TextView(this);
        tvTitle.setText("Đăng Nhập");
        tvTitle.setTextSize(28);
        tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
        tvTitle.setTextColor(Color.parseColor("#3F51B5"));
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setPadding(0, 0, 0, 40);
        layout.addView(tvTitle);

        // === Ô nhập tên đăng nhập ===
        edtUsername = new EditText(this);
        edtUsername.setHint("Tên đăng nhập");
        edtUsername.setText(userManager.getUsername());
        styleInputField(edtUsername);
        layout.addView(edtUsername);

        // === Ô nhập mật khẩu ===
        edtPassword = new EditText(this);
        edtPassword.setHint("Mật khẩu");
        edtPassword.setInputType(0x00000081); // TYPE_TEXT_VARIATION_PASSWORD
        styleInputField(edtPassword);
        layout.addView(edtPassword);

        // === Nút đăng nhập ===
        btnLogin = new Button(this);
        btnLogin.setText("Đăng nhập");
        styleButton(btnLogin, "#4CAF50");
        layout.addView(btnLogin);

        // === Nút đăng ký ===
        btnRegister = new Button(this);
        btnRegister.setText("Đăng ký tài khoản");
        styleButton(btnRegister, "#2196F3");
        layout.addView(btnRegister);

        // === Text Quên mật khẩu ===
        tvForgot = new TextView(this);
        tvForgot.setText("Quên mật khẩu?");
        tvForgot.setTextColor(Color.parseColor("#FF5722"));
        tvForgot.setTextSize(14);
        tvForgot.setPadding(0, 20, 0, 0);
        tvForgot.setGravity(Gravity.CENTER);
        layout.addView(tvForgot);

        setContentView(scrollView);

        // === Xử lý sự kiện ===
        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userManager.checkLogin(username, password)) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class))
        );

        tvForgot.setOnClickListener(v ->
                startActivity(new Intent(this, ForgotActivity.class))
        );
    }

    // === Hàm tạo style cho EditText ===
    private void styleInputField(EditText editText) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 16, 0, 16);
        editText.setLayoutParams(params);
        editText.setPadding(30, 20, 30, 20);
        editText.setBackgroundColor(Color.parseColor("#FFFFFF"));
        editText.setTextColor(Color.parseColor("#212121"));
        editText.setTextSize(16);
        editText.setElevation(4f);
    }

    // === Hàm tạo style cho Button ===
    private void styleButton(Button button, String colorHex) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 16, 0, 0);
        button.setLayoutParams(params);
        button.setTextColor(Color.WHITE);
        button.setTextSize(16);
        button.setPadding(20, 20, 20, 20);
        button.setBackgroundColor(Color.parseColor(colorHex));
        button.setAllCaps(false);
    }
}
