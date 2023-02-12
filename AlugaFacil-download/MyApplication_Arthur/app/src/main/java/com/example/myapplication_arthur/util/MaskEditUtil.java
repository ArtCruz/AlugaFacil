package com.example.myapplication_arthur.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class MaskEditUtil {

    public static final String FORMAT_CPF = "###.###.###-##";
    public static final String FORMAT_FONE = "(##)#####-####";
    public static final String FORMAT_CEP = "#####-###";
    public static final String FORMAT_DATE = "##/##/####";
    public static final String FORMAT_HOUR = "##:##";
    public static final String FORMAT_CNH = "###########";
    public static final String FORMAT_CHASSI = "#################";
    public static final String FORMAT_PLACA = "########";
    public static final String FORMAT_VALOR = "R$##,##";

    /**
     * Método que deve ser chamado para realizar a formatação
     *
     * @param ediTxt
     * @param mask
     * @return
     */
    public static TextWatcher mask(final EditText ediTxt, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {}

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = MaskEditUtil.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
    }

    public static TextWatcher moneyMask(final EditText editText){
        return new TextWatcher() {
            private final WeakReference<EditText> editTextWeakReference = new WeakReference<>(editText);
            private final Locale locale = Locale.getDefault();

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                EditText editText = editTextWeakReference.get();
                if (editText == null) return;
                editText.removeTextChangedListener(this);

                BigDecimal parsed = parseToBigDecimal(editable.toString());
                String formatted = NumberFormat.getCurrencyInstance(locale).format(parsed);
                //Remove o símbolo da moeda e espaçamento pra evitar bug
                String replaceable = String.format("[%s\\s]", getCurrencySymbol());
                String cleanString = formatted.replaceAll(replaceable, "");

                editText.setText(cleanString);
                editText.setSelection(cleanString.length());
                editText.addTextChangedListener(this);
            }

            private BigDecimal parseToBigDecimal(String value) {
                String replaceable = String.format("[%s,.\\s]", getCurrencySymbol());

                String cleanString = value.replaceAll(replaceable, "");

                try {
                    return new BigDecimal(cleanString).setScale(
                            2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
                } catch (NumberFormatException e) {
                    //ao apagar todos valores de uma só vez dava erro
                    //Com a exception o valor retornado é 0.00
                    return new BigDecimal(0);

                }
            }

            public String getCurrencySymbol() {
                return NumberFormat.getCurrencyInstance(Locale.getDefault()).getCurrency().getSymbol();

            }
        };
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
    }
}
