package br.com.geancesar.uicomponents.componentes;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import br.com.geancesar.uicomponents.R;
import br.com.geancesar.uicomponents.componentes.listeners.CheckSelectListener;
import br.com.geancesar.uicomponents.databinding.CheckSelectBinding;

public class CheckSelect extends ConstraintLayout implements View.OnClickListener {

    boolean selecionado;

    ColorStateList corFundo;

    CheckSelectListener listener;

    private final CheckSelectBinding binding;

    public CheckSelect(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CheckSelect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        binding = CheckSelectBinding.inflate(LayoutInflater.from(context), this, true);
        setLayout(attrs);
        refreshState();
    }
    private void setLayout(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray atributos = getContext().obtainStyledAttributes(attrs, R.styleable.CheckSelect);

            selecionado = atributos.getBoolean(R.styleable.CheckSelect_checked_check_select, false);
            int corId = atributos.getColor(R.styleable.CheckSelect_tint_check_select, getContext().getColor(R.color.desabilitado));
            if(!selecionado) {
                corId = getContext().getColor(R.color.desabilitado);
            }

            corFundo = ColorStateList.valueOf(corId);
            binding.llItem.setOnClickListener(this);
            atributos.recycle();
        }
    }

    private void refreshState() {
        if(!selecionado) {
            int corId = getContext().getColor(R.color.desabilitado);
            binding.llFundo.setBackgroundTintList(ColorStateList.valueOf(corId));
        } else {
            binding.llFundo.setBackgroundTintList(corFundo);
        }

        binding.llCheck.setVisibility(selecionado ? VISIBLE : GONE);
        refreshDrawableState();
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        refreshState();
    }

    @Override
    public void onClick(View v) {
        selecionado = !selecionado;
        if(listener != null) {
            listener.selecionado(selecionado);
        }
        refreshState();
    }

    public void setListener(CheckSelectListener listener) {
        this.listener = listener;
    }
}
