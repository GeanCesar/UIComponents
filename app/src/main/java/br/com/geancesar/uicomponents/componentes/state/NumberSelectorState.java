package br.com.geancesar.uicomponents.componentes.state;

public abstract class NumberSelectorState {
    private final boolean isEnabled;

    private NumberSelectorState(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public static final class Normal extends NumberSelectorState {
        private Normal() {
            super(true);
        }

        public static final Normal INSTANCE = new Normal();
    }
}
