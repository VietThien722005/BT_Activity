package com.example.bt_activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;

public class ForgotActivity extends Activity {

    private EditText edtUsername, edtPhone, edtNewPass;
    private Button btnReset;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // === Layout chính ===
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(40, 60, 40, 60);
        layout.setBackgroundColor(Color.parseColor("#FAFAFA"));

        // === Tiêu đề ===
        TextView tvTitle = new TextView(this);
        tvTitle.setText("Khôi Phục Mật Khẩu");
        tvTitle.setTextSize(24);
        tvTitle.setTextColor(Color.parseColor("#333333"));
        tvTitle.setGravity(Gravity.CENTER);
        layout.addView(tvTitle);

        // Ô nhập tên đăng nhập
        edtUsername = new EditText(this);
        edtUsername.setHint("Nhập tên đăng nhập");
        layout.addView(edtUsername);

        // Ô nhập số điện thoại
        edtPhone = new EditText(this);
        edtPhone.setHint("Nhập số điện thoại đã đăng ký");
        edtPhone.setInputType(0x00000021); // TYPE_CLASS_PHONE
        layout.addView(edtPhone);

        // Ô nhập mật khẩu mới (ẩn ban đầu)
        edtNewPass = new EditText(this);
        edtNewPass.setHint("Nhập mật khẩu mới");
        edtNewPass.setInputType(0x00000081); // TYPE_TEXT_VARIATION_PASSWORD
        edtNewPass.setVisibility(EditText.GONE);
        layout.addView(edtNewPass);

        // Nút xác nhận
        btnReset = new Button(this);
        btnReset.setText("Xác nhận");
        btnReset.setBackgroundColor(Color.parseColor("#03A9F4"));
        btnReset.setTextColor(Color.WHITE);
        layout.addView(btnReset);

        setContentView(layout);

        // === Quản lý người dùng ===
        userManager = new UserManager(this);

        // Biến trạng thái
        final boolean[] daXacNhan = {false};

        // === Xử lý nút ===
        btnReset.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();

            // Nếu chưa xác minh tài khoản
            if (!daXacNhan[0]) {
                if (username.equals(userManager.getUsername()) && phone.equals(userManager.getPhone())) {
                    Toast.makeText(this, "✅ Xác minh thành công! Vui lòng nhập mật khẩu mới.", Toast.LENGTH_LONG).show();
                    edtNewPass.setVisibility(EditText.VISIBLE);
                    btnReset.setText("Đổi mật khẩu");
                    daXacNhan[0] = true;
                } else {
                    Toast.makeText(this, "❌ Tên đăng nhập hoặc số điện thoại không đúng!", Toast.LENGTH_SHORT).show();
                }
            }
            // Nếu đã xác minh -> tiến hành đổi mật khẩu
            else {
                String newPass = edtNewPass.getText().toString().trim();
                if (newPass.isEmpty()) {
                    Toast.makeText(this, "Vui lòng nhập mật khẩu mới!", Toast.LENGTH_SHORT).show();
                    return;
                }

                userManager.updatePassword(newPass);
                Toast.makeText(this, "🔐 Mật khẩu đã được đổi thành công!", Toast.LENGTH_LONG).show();
                finish(); // Quay lại màn hình đăng nhập
            }
        });
    }
}





