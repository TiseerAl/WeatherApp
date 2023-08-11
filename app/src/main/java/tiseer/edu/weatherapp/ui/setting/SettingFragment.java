package tiseer.edu.weatherapp.ui.setting;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import tiseer.edu.weatherapp.R;
import tiseer.edu.weatherapp.ui.weather.WeatherViewModel;

public class SettingFragment extends Fragment {

    private WeatherViewModel weatherViewModel;
    private TextInputEditText cityTextInputET, languageTextInputET;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        initViews(view);
        setListeners();
    }

    private void initViews(View view) {
        cityTextInputET = view.findViewById(R.id.choose_city_text_input_edit_text);
        cityTextInputET.setText(weatherViewModel.cityInput.getValue());
    }

    private void setListeners() {
        cityTextInputET.addTextChangedListener(getTextWatcher(cityTextInputET));
    }

    private TextWatcher getTextWatcher(TextInputEditText textInputEditText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (textInputEditText.getId() == R.id.choose_city_text_input_edit_text) {
                    if (!editable.toString().isEmpty()) {
                        weatherViewModel.cityInput.postValue(textInputEditText.getText().toString());
                    }
                }
            }
        };
    }
}