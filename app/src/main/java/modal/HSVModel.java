package modal;

import android.graphics.Color;

import java.util.Observable;

/**
 * HSVModal created for maintain all business logic to generate HSV colors
 *
 * @author Raviraj Mangykiya (mang0055@algonquinlive.com)
 */

public class HSVModel extends Observable {
    /*
      * Hue (0 .. 360)
      * Saturation (0...1)
      * Value (0...1)
      */
    // CLASS VARIABLES
    public static final Float MAX_HUE = 360.0f;
    public static final Float MAX_SATURATION = 100.0f;
    public static final Float MAX_LIGHTNESS = 100.0f;
    public static final Float MIN_HUE = 0.0f;
    public static final Float MIN_SATURATION = 0.0f;
    public static final Float MIN_LIGHTNESS = 0.0f;


    // INSTANCE VARIABLES
    private Float hue;
    private Float saturation;
    private Float value;

    /**
     * No argument constructor.
     * <p>
     * Instantiate a new instance of this class, and
     * initialize red, green, blue, and alpha to max values.
     */
    public HSVModel() {
        this(MIN_HUE, MIN_SATURATION, MIN_LIGHTNESS);
    }

    /**
     * Convenience constructor.
     *
     * @param hue        - starting hue value
     * @param saturation - starting saturation value
     * @param value      - starting value for lightness value
     */
    public HSVModel(Float hue, Float saturation, Float value) {
        super();

        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    public void asBlack() {
        this.setHSV(0.0f, 0.0f, 0.0f);
    }

    public void asRed() {
        this.setHSV(0.0f, 1.0f, 1.0f);
    }

    public void asLime() {
        this.setHSV(120.0f, 1.0f, 1.0f);
    }

    public void asBlue() {
        this.setHSV(240.0f, 1.0f, 1.0f);
    }

    public void asYellow() {
        this.setHSV(60.0f, 1.0f, 1.0f);
    }

    public void asCyan() {
        this.setHSV(180.0f, 1.0f, 1.0f);
    }

    public void asMagenta() {
        this.setHSV(300.0f, 1.0f, 1.0f);
    }

    public void asSilver() {
        this.setHSV(0.0f, 0.0f, 0.75f);
    }

    public void asGray() {
        this.setHSV(0.0f, 0.0f, 0.5f);
    }

    public void asMaroon() {
        this.setHSV(0.0f, 1.0f, 0.5f);
    }

    public void asOlive() {
        this.setHSV(60.0f, 1.0f, 0.5f);
    }

    public void asGreen() {
        this.setHSV(120.0f, 1.0f, 0.5f);
    }

    public void asPurple() {
        this.setHSV(300.0f, 1.0f, 0.5f);
    }

    public void asTeal() {
        this.setHSV(180.0f, 1.0f, 0.5f);
    }

    public void asNavy() {
        this.setHSV(240.0f, 1.0f, 0.5f);
    }

    public void setHue(Float hue) {
        this.hue = hue;
        this.updateObservers();
    }

    public void setSaturation(Float saturation) {
        this.saturation = saturation / 100;
        this.updateObservers();
    }

    public void setValue(Float value) {
        this.value = value / 100;
        this.updateObservers();
    }

    public Float getHue() {
        return hue;
    }

    public Float getSaturation() {
        return saturation * 100;
    }

    public Float getValue() {
        return value * 100;
    }

    public int getHSV() {
        return Color.HSVToColor(new float[]{hue, saturation, value});
    }

    // Convenient setter: set Hue, Saturation, Lightness
    public void setHSV(Float hue, Float saturation, Float value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;

        this.updateObservers();
    }

    @Override
    public String toString() {
        return "HSV [H=" + hue + " S=" + saturation + " v=" + value + "]";
    }

    // the model has changed!
    // broadcast the update method to all registered observers
    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }
}
