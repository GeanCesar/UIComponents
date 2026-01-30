package br.com.geancesar.uicomponents.componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import br.com.geancesar.uicomponents.R;
import br.com.geancesar.uicomponents.componentes.listeners.ProgressButtonListener;
import br.com.geancesar.uicomponents.componentes.state.ProgressButtonState;
import br.com.geancesar.uicomponents.databinding.ProgressButtonBinding;


public class ProgressButton extends LinearLayout {

    private Drawable fundo;
    private Drawable icone;
    private String texto;

    private ProgressButtonListener listener;

    private final ProgressButtonBinding binding;

    private ProgressButtonState state = ProgressButtonState.Normal.INSTANCE;

    public ProgressButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        binding = ProgressButtonBinding.inflate(LayoutInflater.from(context), this, true);
        inicia(attrs);
    }

    private void inicia(AttributeSet attrs){
        setLayout(attrs);
        setState(ProgressButtonState.Normal.INSTANCE);
    }

    private void setState(ProgressButtonState state) {
        this.state = state;
        refreshState();
    }

    public void setLoading(){
        setState(ProgressButtonState.Loading.INSTANCE);
        if(listener != null)
            listener.onLoading(this);
    }

    public void setNormal(){
        setState(ProgressButtonState.Normal.INSTANCE);
        if(listener != null)
            listener.onNormal(this);
    }

    private void setLayout(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray atributos = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressButton);

            int textoResId = atributos.getResourceId(R.styleable.ProgressButton_texto, 0);
            if (textoResId != 0) {
                texto = getContext().getString(textoResId);
            } else {
                texto = atributos.getString(R.styleable.ProgressButton_texto);
            }

            int iconeResId = atributos.getResourceId(R.styleable.ProgressButton_icone, 0);
            if (iconeResId != 0) {
                icone = getContext().getDrawable(iconeResId);
            }

            int backgroundResId = atributos.getResourceId(R.styleable.ProgressButton_fundo, 0);
            if (backgroundResId != 0) {
                fundo = getContext().getDrawable(backgroundResId);
            }

            atributos.recycle();
        }
    }

    private void refreshState() {
        setEnabled(state.isEnabled());
        setClickable(state.isEnabled());

        binding.tvTexto.setEnabled(state.isEnabled());
        binding.tvTexto.setText(texto);

        setBackground(fundo);

        binding.llSeparador.setVisibility(state.getImageVisibility());
        binding.llAreaIcone.setVisibility(state.getImageVisibility() == VISIBLE ? VISIBLE : GONE);
        binding.ivIcone.setVisibility(state.getImageVisibility());
        binding.tvTexto.setVisibility(state.getTextVisibility());
        binding.progressBar.setVisibility(state.getProgressVisibility());

        if(icone != null) {
            binding.ivIcone.setImageDrawable(icone);
        } else {
            binding.ivIcone.setVisibility(GONE);
            binding.llSeparador.setVisibility(GONE);
        }

        refreshDrawableState();
    }

    public void setListener(ProgressButtonListener listener) {
        this.listener = listener;
    }
}


