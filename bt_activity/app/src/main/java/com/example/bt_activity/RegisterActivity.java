package com.example.bt_activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;

public class RegisterActivity extends Activity {

    private EditText edtUsername, edtPassword, edtPhone;
    private Button btnRegister;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout chính
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(40, 60, 40, 60);
        layout.setBackgroundColor(Color.parseColor("#FAFAFA"));

        // Tiêu đề
        TextView tvTitle = new TextView(this);
        tvTitle.setText("Đăng Ký Tài Khoản");
        tvTitle.setTextSize(24);
        tvTitle.setTextColor(Color.parseColor("#333333"));
        tvTitle.setGravity(Gravity.CENTER);
        layout.addView(tvTitle);

        // Ô nhập tên đăng nhập
        edtUsername = new EditText(this);
        edtUsername.setHint("Tên đăng nhập");
        layout.addView(edtUsername);

        // Ô nhập mật khẩu
        edtPassword = new EditText(this);
        edtPassword.setHint("Mật khẩu");
        edtPassword.setInputType(0x00000081); // TYPE_TEXT_VARIATION_PASSWORD
        layout.addView(edtPassword);

        // Ô nhập số điện thoại
        edtPhone = new EditText(this);
        edtPhone.setHint("Số điện thoại");
        edtPhone.setInputType(0x00000021); // TYPE_CLASS_PHONE
        layout.addView(edtPhone);

        // Nút đăng ký
        btnRegister = new Button(this);
        btnRegister.setText("Đăng ký");
        btnRegister.setBackgroundColor(Color.parseColor("#4CAF50"));
        btnRegister.setTextColor(Color.WHITE);
        layout.addView(btnRegister);

        // Gán layout vào Activity
        setContentView(layout);

        // Tạo UserManager
        userManager = new UserManager(this);

        // Xử lý nút đăng ký
        btnRegister.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            userManager.saveUser(username, password, phone);
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}



