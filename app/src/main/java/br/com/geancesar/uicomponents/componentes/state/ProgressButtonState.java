package br.com.geancesar.uicomponents.componentes.state;

import android.view.View;

public abstract class ProgressButtonState {
    private final boolean isEnabled;
    private int imageVisibility = View.VISIBLE;
    private int progressVisibility = View.GONE;
    private int textVisibility = View.VISIBLE;

    private ProgressButtonState(boolean isEnabled, int progressVisibility, int textVisibility, int imageVisibility) {
        this.isEnabled = isEnabled;
        this.progressVisibility = progressVisibility;
        this.textVisibility = textVisibility;
        this.imageVisibility = imageVisibility;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public int getProgressVisibility() {
        return progressVisibility;
    }

    public int getTextVisibility() {
        return textVisibility;
    }

    public int getImageVisibility() {
        return imageVisibility;
    }

    public static final class Normal extends ProgressButtonState {
        private Normal() {
            super(true, View.GONE, View.VISIBLE, View.VISIBLE);
        }

        public static final Normal INSTANCE = new Normal();
    }

    public static final class Loading extends ProgressButtonState {
        private Loading() {
            super(false, View.VISIBLE, View.GONE, View.INVISIBLE);
        }

        public static final Loading INSTANCE = new Loading();
    }
}
