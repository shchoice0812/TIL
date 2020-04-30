package com.example.prefsetting;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class SettingsFragment extends PreferenceFragment {

    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;
    ListPreference vibrate_intensity;
    ListPreference bell_choice;
    EditTextPreference volume;

    // 프래그먼트가 생성된 후에 액티비티에 붙을 때
    @Override
    public void onStart() {
        super.onStart();

        // 공유 프레퍼런스를 생성
        // getActivity() - 현재 프로그먼트를 포함하는 엑티비티를 리턴
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = mPrefs.edit();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        final CheckBoxPreference vibrate_setup = (CheckBoxPreference) findPreference("vibrate");
        if (vibrate_setup != null){
            vibrate_setup.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean isVibrateOn = ((Boolean) newValue).booleanValue();  // 새로 설정한 값을 boolean 값으로 변환

                    editor.putBoolean("vibrate", isVibrateOn);
                    editor.commit();

                    return true;
                }
            });
        }

        vibrate_intensity = (ListPreference)findPreference("vibrate_intensity");

        if(vibrate_intensity != null){
            vibrate_intensity.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    int value = Integer.parseInt(newValue.toString());

                    String[] vibArray = getResources().getStringArray(R.array.entry_list_vibrate);
                    vibrate_intensity.setSummary(vibArray[value]);

                    editor.putString("vibrate_intensity", newValue.toString());
                    editor.commit();

                    return true;
                }
            });
        }

        bell_choice = (ListPreference)findPreference("bell_sound");

        if(bell_choice != null){
            bell_choice.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    String value = newValue.toString();

                    String[] bellArray = getResources().getStringArray(R.array.entryvalues_list_sound);
                    String[] bellEntries = getResources().getStringArray(R.array.entries_list_sound);

                    for (int i=0; i < bellArray.length; i++){
                        if(value.equals(bellArray[i])){
                            bell_choice.setSummary(bellEntries[i]);
                            break;
                        }
                    }

                    editor.putString("bell_choice", newValue.toString());
                    editor.commit();

                    return true;
                }
            });
        }

        volume = (EditTextPreference)findPreference("volume");

        if(volume != null){
            volume.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    String value = newValue.toString();
                    volume.setSummary(value);

                    editor.putString("volume", newValue.toString());
                    editor.commit();

                    return true;
                }
            });
        }


    }
    @Override
    public void onResume() {
        super.onResume();

        if (mPrefs.contains("vibrate_intensity")){
            int value = Integer.parseInt(mPrefs.getString("vibrate_intensity", "0"));

            String txtValue = getResources().getStringArray(R.array.entry_list_vibrate)[value];
            vibrate_intensity.setSummary(txtValue);
        }

        if (mPrefs.contains("bell_sound")){
            bell_choice.setSummary(mPrefs.getString("bell_sound", ""));
        }

        if(mPrefs.contains("volume")){
            volume.setSummary(mPrefs.getString("volume", ""));
        }
    }
}
