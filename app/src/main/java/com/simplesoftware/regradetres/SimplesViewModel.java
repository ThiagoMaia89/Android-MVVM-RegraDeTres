package com.simplesoftware.regradetres;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import es.dmoral.toasty.Toasty;

public class SimplesViewModel extends ViewModel {

    private final MutableLiveData<Double> mResultado = new MutableLiveData<>();
    private double result;

    public SimplesViewModel() {

        mResultado.setValue(result);

    }

    public MutableLiveData<Double> getmResultado() {

        return mResultado;

    }


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void setResult(EditText valor1ASimples, EditText valor1BSimples, EditText valor2ASimples, SwitchCompat aSwitch, TextView tv_explicacao_simples) {

        try {

            double vlr1A = Double.parseDouble(String.valueOf(valor1ASimples.getText()));
            double vlr1B = Double.parseDouble(String.valueOf(valor1BSimples.getText()));
            double vlr2A = Double.parseDouble(String.valueOf(valor2ASimples.getText()));

            if (aSwitch.isChecked()) {
                result = vlr2A / (vlr1B / vlr1A);
            } else {
                result = (vlr1B * vlr2A) / vlr1A;
            }
            mResultado.setValue(result);

            tv_explicacao_simples.setText(
                    String.format("%.2f", vlr1A).replace(".", ",")
                            + " está para " +
                            String.format("%.2f", vlr2A).replace(".", ",")
                            + "\nAssim como\n" +
                            String.format("%.2f", vlr1B).replace(".", ",")
                            + " está para " +
                            String.format("%.2f", result).replace(".", ","));


        } catch (Exception e){

            failureMessage("Preencha todos os campos!", valor1ASimples.getContext());

        }

    }


    public void failureMessage(String message, Context context){

        Toasty.error(context, message, Toasty.LENGTH_SHORT).show();

    }


}
