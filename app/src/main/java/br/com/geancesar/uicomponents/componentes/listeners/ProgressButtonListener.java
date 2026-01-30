package br.com.geancesar.uicomponents.componentes.listeners;


import br.com.geancesar.uicomponents.componentes.ProgressButton;

public interface ProgressButtonListener {

    void onLoading(ProgressButton botao);
    void onNormal(ProgressButton botao);

}
