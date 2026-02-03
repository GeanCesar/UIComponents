package br.com.geancesar.uicomponents.componentes.state;

public abstract class NumberSelectorState {
    private final boolean isEnabled;
    private Boolean minusEnabled;
    private Boolean plusEnabled;

    private NumberSelectorState(boolean isEnabled, Boolean minusEnabled, Boolean plusEnabled) {
        this.isEnabled = isEnabled;
        this.minusEnabled = minusEnabled;
        this.plusEnabled = plusEnabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public Boolean getMinusEnabled() {
        return minusEnabled;
    }

    public Boolean getPlusEnabled() {
        return plusEnabled;
    }

    public static final class Normal extends NumberSelectorState {
        private Normal() {
            super(true, null, null);
        }

        public static final Normal INSTANCE = new Normal();
    }

    public static final class PlusLocked extends NumberSelectorState {
        private PlusLocked() {
            super(true, true, false);
        }

        public static final PlusLocked INSTANCE = new PlusLocked();
    }

    public static final class MinusLocked extends NumberSelectorState {
        private MinusLocked() {
            super(true, false, true);
        }

        public static final MinusLocked INSTANCE = new MinusLocked();
    }
}
