package com.simplesoftware.regradetres;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentSimples extends Fragment {

    EditText valor1ASimples, valor1BSimples, valor2ASimples, resultadoSimples;
    TextView tv_explicacao_simples;
    Button calcSimples, limparSimples;
    SwitchCompat aSwitch;

    SimplesViewModel mViewModel;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public FragmentSimples() {

    }

    public static FragmentSimples newInstance(String param1, String param2) {
        FragmentSimples fragment = new FragmentSimples();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_simples, container, false);

        instanciarComponentes(view);
        viewModelObserver();
        calcClick();
        cleanClick();

        return view;
    }

    @SuppressLint("DefaultLocale")
    public void viewModelObserver() {
        mViewModel = new ViewModelProvider(this).get(SimplesViewModel.class);
        mViewModel.getmResultado().observe(getViewLifecycleOwner(), value -> {
            if (value == 0) {
                resultadoSimples.setText("X");
                resultadoSimples.setTextColor(Color.parseColor("#FFFF8800"));
            } else {
                resultadoSimples.setText(String.format("%.2f", value).replace(".", ","));
            }
        });
    }

    public void instanciarComponentes(View view) {
        valor1ASimples = view.findViewById(R.id.valor1ASimples);
        valor1BSimples = view.findViewById(R.id.valor1BSimples);
        valor2ASimples = view.findViewById(R.id.valor2ASimples);
        resultadoSimples = view.findViewById(R.id.resultadoSimples);
        calcSimples = view.findViewById(R.id.bt_CalcSimples);
        limparSimples = view.findViewById(R.id.bt_LimparSimples);
        aSwitch = view.findViewById(R.id.switch_ocpao);
        tv_explicacao_simples = view.findViewById(R.id.tv_explicacao_simples);
    }

    public void calcClick() {
        calcSimples.setOnClickListener(v -> {
            mViewModel.setResult(valor1ASimples, valor1BSimples, valor2ASimples, aSwitch);
        });
    }

    public void cleanClick() {
        limparSimples.setOnClickListener(v -> {
            valor1ASimples.setText("");
            valor1BSimples.setText("");
            valor2ASimples.setText("");
            resultadoSimples.setText("");
        });
    }

}