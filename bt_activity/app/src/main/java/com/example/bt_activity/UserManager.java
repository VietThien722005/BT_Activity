package com.example.bt_activity;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {
    private static final String PREF_NAME = "UserData";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_LOGGED_IN = "logged_in";

    // ====== Thông tin mở rộng ======
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SKYPE = "skype";
    private static final String KEY_WEB = "website";
    private static final String KEY_FRIENDS = "friends";
    private static final String KEY_FOLLOWERS = "followers";
    private static final String KEY_PROFILE_NAME = "profile_name";
    private static final String KEY_AVATAR = "avatar"; // lưu tên hoặc URI ảnh

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public UserManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // ====== Lưu thông tin tài khoản ======
    public void saveUser(String username, String password, String phone) {
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_PHONE, phone);

        // Tự động tạo thông tin bổ sung mặc định
        if (!prefs.contains(KEY_EMAIL)) editor.putString(KEY_EMAIL, username + "@gmail.com");
        if (!prefs.contains(KEY_SKYPE)) editor.putString(KEY_SKYPE, username + "_skype");
        if (!prefs.contains(KEY_WEB)) editor.putString(KEY_WEB, "www." + username + ".com");
        if (!prefs.contains(KEY_FRIENDS)) editor.putString(KEY_FRIENDS, "100");
        if (!prefs.contains(KEY_FOLLOWERS)) editor.putString(KEY_FOLLOWERS, "250");
        if (!prefs.contains(KEY_PROFILE_NAME)) editor.putString(KEY_PROFILE_NAME, "User " + username);
        if (!prefs.contains(KEY_AVATAR)) editor.putString(KEY_AVATAR, "default");
        editor.apply();
    }

    // ====== Kiểm tra đăng nhập ======
    public boolean checkLogin(String username, String password) {
        String savedUser = prefs.getString(KEY_USERNAME, null);
        String savedPass = prefs.getString(KEY_PASSWORD, null);
        if (username.equals(savedUser) && password.equals(savedPass)) {
            setLoggedIn(true);
            return true;
        }
        return false;
    }

    // ====== Lưu trạng thái đăng nhập ======
    public void setLoggedIn(boolean value) {
        editor.putBoolean(KEY_LOGGED_IN, value);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
    }

    // ====== Đổi mật khẩu ======
    public void updatePassword(String newPassword) {
        editor.putString(KEY_PASSWORD, newPassword);
        editor.apply();
    }

    // ====== Getter ======
    public String getUsername() { return prefs.getString(KEY_USERNAME, ""); }
    public String getPhone() { return prefs.getString(KEY_PHONE, ""); }
    public String getEmail() { return prefs.getString(KEY_EMAIL, ""); }
    public String getSkype() { return prefs.getString(KEY_SKYPE, ""); }
    public String getWebsite() { return prefs.getString(KEY_WEB, ""); }
    public String getFriends() { return prefs.getString(KEY_FRIENDS, ""); }
    public String getFollowers() { return prefs.getString(KEY_FOLLOWERS, ""); }
    public String getProfileName() { return prefs.getString(KEY_PROFILE_NAME, ""); }
    public String getAvatar() { return prefs.getString(KEY_AVATAR, ""); }

    // ====== Cập nhật hồ sơ ======
    public void updateProfile(String email, String skype, String web,
                              String friends, String followers,
                              String profileName, String avatar) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_SKYPE, skype);
        editor.putString(KEY_WEB, web);
        editor.putString(KEY_FRIENDS, friends);
        editor.putString(KEY_FOLLOWERS, followers);
        editor.putString(KEY_PROFILE_NAME, profileName);
        editor.putString(KEY_AVATAR, avatar);
        editor.apply();
    }

    // ====== Đăng xuất (chỉ xóa trạng thái, KHÔNG xóa tài khoản) ======
    public void logout() {
        setLoggedIn(false);
    }

    // ====== Xóa toàn bộ (reset app) ======
    public void clearAll() {
        editor.clear();
        editor.commit();
    }
}
