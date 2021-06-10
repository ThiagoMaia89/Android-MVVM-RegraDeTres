package com.simplesoftware.regradetres;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import es.dmoral.toasty.Toasty;

public class CompostoViewModel extends ViewModel {

    private final MutableLiveData<Double> mResultado = new MutableLiveData<>();
    private double result;

    public CompostoViewModel(){
        mResultado.setValue(result);
    }

    public MutableLiveData<Double> getmResultado() {
        return mResultado;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void setResult(EditText valor1AComposta, EditText valor1BComposta, EditText valor2AComposta, EditText valor2BComposta, EditText valor3AComposta,
                          SwitchCompat switch_valor1, SwitchCompat switch_valor2, SwitchCompat switch_valor3, TextView tv_explicacao_composto){

        try {

            double grupo1, grupo2;
            double vlr1A = Double.parseDouble(String.valueOf(valor1AComposta.getText()));
            double vlr1B = Double.parseDouble(String.valueOf(valor1BComposta.getText()));
            double vlr2A = Double.parseDouble(String.valueOf(valor2AComposta.getText()));
            double vlr2B = Double.parseDouble(String.valueOf(valor2BComposta.getText()));
            double vlr3A = Double.parseDouble(String.valueOf(valor3AComposta.getText()));

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
                result = (grupo1 * grupo2) * vlr3A;
            } else {
                result = vlr3A / (grupo1 * grupo2);
            }

            mResultado.setValue(result);

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
                            String.format("%.2f", result).replace(".", ","));

        } catch (Exception e){

            failureMessage("Preencha todos os campos!", valor1AComposta.getContext());

        }

    }

    public void failureMessage(String message, Context context){

        Toasty.error(context, message, Toasty.LENGTH_SHORT).show();

    }

}
