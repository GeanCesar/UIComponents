package br.com.geancesar.uicomponents.componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.geancesar.uicomponents.R;
import br.com.geancesar.uicomponents.databinding.ValueButtonBinding;

public class ValueButton extends LinearLayout {
    private Drawable fundo;
    private String texto;
    private BigDecimal valor;
    private final ValueButtonBinding binding;
    private boolean enabled;

    public ValueButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ValueButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        binding = ValueButtonBinding.inflate(LayoutInflater.from(context), this, true);
        setLayout(attrs);
        refreshState();
    }

    private void setLayout(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray atributos = getContext().obtainStyledAttributes(attrs, R.styleable.ValueButton);

            texto = atributos.getString(R.styleable.ValueButton_texto_value_button);
            valor = BigDecimal.valueOf(atributos.getFloat(R.styleable.ValueButton_valor_value_button, 0F));
            int backgroundResId = atributos.getResourceId(R.styleable.ValueButton_fundo_value_button, 0);
            if (backgroundResId != 0) {
                fundo = getContext().getDrawable(backgroundResId);
            }

            atributos.recycle();
        }
    }

    private void refreshState() {
        valor = valor.setScale(2, RoundingMode.HALF_UP);
        binding.tvTexto.setText(texto);
        binding.tvValor.setText("R$ " + valor.toString().replace(".", ","));
        setBackground(fundo);
        refreshDrawableState();
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
        refreshState();
    }

    public void setTexto(String texto) {
        this.texto = texto;
        refreshState();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getTexto() {
        return texto;
    }
}
