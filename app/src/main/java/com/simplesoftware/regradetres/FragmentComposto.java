package com.simplesoftware.regradetres;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        calcComposta.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onClick(View v) {
                try {
                    double grupo1, grupo2;
                    vlr1A = Double.parseDouble(String.valueOf(valor1AComposta.getText()));
                    vlr1B = Double.parseDouble(String.valueOf(valor1BComposta.getText()));
                    vlr2A = Double.parseDouble(String.valueOf(valor2AComposta.getText()));
                    vlr2B = Double.parseDouble(String.valueOf(valor2BComposta.getText()));
                    vlr3A = Double.parseDouble(String.valueOf(valor3AComposta.getText()));
                    if (switch_valor1.isChecked()) {
                        grupo1 = vlr1B / vlr1A;
                    } else {
                        grupo1 = vlr1A / vlr1B;
                    }
                    if (switch_valor2.isChecked()) {
                        grupo2 = vlr2B / vlr2A;
                    } else {
                        grupo2 = vlr2A / vlr2B;
                    }
                    if (switch_valor3.isChecked()) {
                        resultado = (grupo1 * grupo2) * vlr3A;
                    } else {
                        resultado = vlr3A / (grupo1 * grupo2);
                    }
                    resultadoComposta.setText(String.format("%.2f", resultado).replace(".", ","));

                    tv_explicacao_composto.setText(
                            String.format("%.2f", vlr1A).replace(".", ",")
                                    + " está para " +
                                    String.format("%.2f", vlr2A).replace(".", ",")
                                    + " que está para " +
                                    String.format("%.2f", vlr3A).replace(".", ",")
                                    + "\nAssim como\n" +
                                    String.format("%.2f", vlr1B).replace(".", ",")
                                    + " está para " +
                                    String.format("%.2f", vlr2B).replace(".", ",")
                                    + " que está para " +
                                    String.format("%.2f", resultado).replace(".", ","));

                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        limparComposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor1AComposta.setText("");
                valor1BComposta.setText("");
                valor2AComposta.setText("");
                valor2BComposta.setText("");
                valor3AComposta.setText("");
                resultadoComposta.setText("");
            }
        });


        return view;
    }
}