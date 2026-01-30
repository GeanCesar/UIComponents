package br.com.geancesar.uicomponents.componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import br.com.geancesar.uicomponents.R;
import br.com.geancesar.uicomponents.componentes.listeners.NumberSelectorListener;
import br.com.geancesar.uicomponents.componentes.state.NumberSelectorState;
import br.com.geancesar.uicomponents.databinding.NumberSelectorBinding;

public class NumberSelector extends LinearLayout {

    private int valor;
    private Integer valorMinimo;
    private Integer valorMaximo;
    private NumberSelectorState state = NumberSelectorState.Normal.INSTANCE;
    private final NumberSelectorBinding binding;

    private NumberSelectorListener listener;

    public NumberSelector(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumberSelector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        binding = NumberSelectorBinding.inflate(LayoutInflater.from(context), this, true);
        inicia(attrs);
    }

    private void inicia(AttributeSet attrs){
        setLayout(attrs);
        setState(NumberSelectorState.Normal.INSTANCE);
    }

    private void setState(NumberSelectorState state) {
        this.state = state;
        refreshState();
    }

    private void refreshState() {
        setEnabled(state.isEnabled());
        setClickable(state.isEnabled());

        binding.tvValor.setText(valor + "");
        binding.ivMais.setEnabled(state.isEnabled());
        binding.ivMenos.setEnabled(state.isEnabled());

        if(valorMinimo != null && valorMinimo == valor) {
            binding.ivMenos.setEnabled(false);
        }

        if(valorMaximo != null && valorMaximo == valor) {
            binding.ivMais.setEnabled(false);
        }

        binding.ivMais.setOnClickListener(l -> {
            if(valorMaximo == null || valor + 1 <= valorMaximo) {
                valor = valor + 1;
                listener.adiciona();
            }
            refreshState();
        });

        binding.ivMenos.setOnClickListener(l -> {
            if(valorMinimo == null || valor - 1 >= valorMinimo) {
                valor = valor - 1;
                listener.remove();
            }
            refreshState();
        });

        refreshDrawableState();
    }

    private void setLayout(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray atributos = getContext().obtainStyledAttributes(attrs, R.styleable.NumberSelector);

            int valorResId = atributos.getResourceId(R.styleable.NumberSelector_valor, 0);
            if (valorResId != 0) {
                valor = Integer.getInteger(getContext().getString(valorResId));
            } else {
                valor = Integer.parseInt(atributos.getString(R.styleable.NumberSelector_valor));
            }

            int valorMinimoResId = atributos.getResourceId(R.styleable.NumberSelector_valor_minimo, 0);
            if (valorMinimoResId != 0) {
                valorMinimo = Integer.getInteger(getContext().getString(valorMinimoResId));
            } else {
                if(atributos.getString(R.styleable.NumberSelector_valor_minimo) != null) {
                    valorMinimo = Integer.parseInt(atributos.getString(R.styleable.NumberSelector_valor_minimo));
                }
            }

            int valorMaximoResId = atributos.getResourceId(R.styleable.NumberSelector_valor_maximo, 0);
            if (valorResId != 0) {
                valorMaximo = Integer.getInteger(getContext().getString(valorMaximoResId));
            } else {
                if(atributos.getString(R.styleable.NumberSelector_valor_maximo) != null) {
                    valorMaximo = Integer.parseInt(atributos.getString(R.styleable.NumberSelector_valor_maximo));
                }
            }

            atributos.recycle();
        }
    }

    public int getValor() {
        return valor;
    }

    public Integer getValorMaximo() {
        return valorMaximo;
    }

    public Integer getValorMinimo() {
        return valorMinimo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setValorMaximo(Integer valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setValorMinimo(Integer valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public void setListener(NumberSelectorListener listener) {
        this.listener = listener;
    }
}
