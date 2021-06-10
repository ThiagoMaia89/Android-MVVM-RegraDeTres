package com.simplesoftware.regradetres;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentComposto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentComposto extends Fragment {

    EditText valor1AComposta, valor1BComposta, valor2AComposta, valor2BComposta, valor3AComposta, resultadoComposta;
    Button calcComposta, limparComposta;
    TextView tv_explicacao_composto;
    SwitchCompat switch_valor1, switch_valor2, switch_valor3;

    CompostoViewModel mViewModel;

    double vlr1A, vlr1B, vlr2A, vlr2B, vlr3A, resultado;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentComposto() {
        // Required empty public constructor
    }

    public static FragmentComposto newInstance(String param1, String param2) {
        FragmentComposto fragment = new FragmentComposto();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_composto, container, false);

        instanciarComponentes(view);
        viewModelObserver();
        calcCLick();
        cleanClick();

        return view;

    }

    @SuppressLint("DefaultLocale")
    public void viewModelObserver() {

        mViewModel = new ViewModelProvider(this).get(CompostoViewModel.class);
        mViewModel.getmResultado().observe(getViewLifecycleOwner(), value -> {
            if (value == 0) {
                resultadoComposta.setText("X");
                resultadoComposta.setTextColor(Color.parseColor("#FFFF8800"));
            } else {
                resultadoComposta.setText(String.format("%.2f", value).replace(".", ","));
            }
        });

    }

    public void instanciarComponentes(View view) {

        valor1AComposta = view.findViewById(R.id.valor1AComposta);
        valor1BComposta = view.findViewById(R.id.valor1BComposta);
        valor2AComposta = view.findViewById(R.id.valor2AComposta);
        valor2BComposta = view.findViewById(R.id.valor2BComposta);
        valor3AComposta = view.findViewById(R.id.valor3AComposta);
        resultadoComposta = view.findViewById(R.id.resultadoComposta);
        calcComposta = view.findViewById(R.id.bt_CalcComposta);
        limparComposta = view.findViewById(R.id.bt_LimparComposta);
        switch_valor1 = view.findViewById(R.id.switch_valor1);
        switch_valor2 = view.findViewById(R.id.switch_valor2);
        switch_valor3 = view.findViewById(R.id.switch_valor3);
        tv_explicacao_composto = view.findViewById(R.id.tv_explicacao_composto);

    }

    public void calcCLick() {

        calcComposta.setOnClickListener(v -> {

            mViewModel.setResult(valor1AComposta, valor1BComposta, valor2AComposta, valor2BComposta, valor3AComposta,
                    switch_valor1, switch_valor2, switch_valor3, tv_explicacao_composto);
            InputMethodManager inputMethodManager = (InputMethodManager) requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });

    }

    public void cleanClick() {

        limparComposta.setOnClickListener(v -> {
            valor1AComposta.setText("");
            valor1BComposta.setText("");
            valor2AComposta.setText("");
            valor2BComposta.setText("");
            valor3AComposta.setText("");
            resultadoComposta.setText("");
        });

    }

}