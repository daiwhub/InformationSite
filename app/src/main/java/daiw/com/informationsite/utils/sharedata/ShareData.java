package daiw.com.informationsite.utils.sharedata;

import android.content.SharedPreferences;
import android.text.TextUtils;

import daiw.com.informationsite.App;

/****************************
 * 类描述：缓存工具类
 *
 * @author: daiw
 * @time: 2018/11/7  8:32 PM
 *
 *         ***************************
 */
public class ShareData {

    private final String SETTINGS_NAME = "SHARE";
    public final byte[] _writeLock = new byte[0];

    private static ShareData shareData;

    public static ShareData getInstance() {
        if (shareData == null) {
            synchronized (ShareData.class) {
                if (shareData == null) {
                    shareData = new ShareData();
                }
            }
        }
        return shareData;
    }

    public void saveIntValue(String name, int value) {
        synchronized (_writeLock) {
            if (TextUtils.isEmpty(name)) {
                return;
            }
            SharedPreferences settings = App.getContext().getSharedPreferences(SETTINGS_NAME, 0);
            settings.edit().putInt(name, value).commit();
        }
    }

    public int getIntValue(String name, int defaultValue) {
        synchronized (_writeLock) {
            if (TextUtils.isEmpty(name)) {
                return defaultValue;
            }
            SharedPreferences settings = App.getContext().getSharedPreferences(SETTINGS_NAME, 0);
            return settings.getInt(name, defaultValue);
        }
    }

    public void saveLongValue(String name, long value) {
        synchronized (_writeLock) {
            if (TextUtils.isEmpty(name)) {
                return;
            }
            SharedPreferences settings = App.getContext().getSharedPreferences(SETTINGS_NAME, 0);
            settings.edit().putLong(name, value).commit();
        }
    }

    public long getLongValue(String name, long defaultValue) {
        synchronized (_writeLock) {
            if (TextUtils.isEmpty(name)) {
                return defaultValue;
            }
            SharedPreferences settings = App.getContext().getSharedPreferences(SETTINGS_NAME, 0);
            return settings.getLong(name, defaultValue);
        }
    }

    public void saveStringValue(String name, String value) {
        synchronized (_writeLock) {
            if (TextUtils.isEmpty(name)) {
                return;
            }
            SharedPreferences settings = App.getContext().getSharedPreferences(SETTINGS_NAME, 0);
            settings.edit().putString(name, value).commit();
        }
    }

    public String getStringValue(String name, String defaultValue) {
        synchronized (_writeLock) {
            if (TextUtils.isEmpty(name)) {
                return defaultValue;
            }
            SharedPreferences settings = App.getContext().getSharedPreferences(SETTINGS_NAME, 0);
            return settings.getString(name, defaultValue);
        }
    }

    public void saveBooleanValue(String name, boolean value) {
        synchronized (_writeLock) {
            if (TextUtils.isEmpty(name)) {
                return;
            }
            SharedPreferences settings = App.getContext().getSharedPreferences(SETTINGS_NAME, 0);
            settings.edit().putBoolean(name, value).commit();
        }
    }

    public boolean getBooleanValue(String name, boolean defaultValue) {
        synchronized (_writeLock) {
            if (TextUtils.isEmpty(name)) {
                return defaultValue;
            }
            SharedPreferences settings = App.getContext().getSharedPreferences(SETTINGS_NAME, 0);
            return settings.getBoolean(name, defaultValue);
        }
    }
}
