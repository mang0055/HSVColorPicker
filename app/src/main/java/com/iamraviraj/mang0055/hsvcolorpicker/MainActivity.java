package com.iamraviraj.mang0055.hsvcolorpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import modal.HSVModel;

/**
 * Application Developed for Android Assignment which shows HSV Color picker
 *
 * @author Raviraj Mangykiya (mang0055@algonquinlive.com)
 */
public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, Observer, View.OnLongClickListener {
    private AboutDialogFragment mAboutDialog;
    private static final String ABOUT_DIALOG_TAG = "About";
    private static final String LOG_TAG = "HSV";
    private HSVModel mModel;
    private TextView mColorSwatch;
    private TextView lblHue, lblSaturation, lblValue;
    private SeekBar mHueSB, mSaturationSB, mValueSB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a new AboutDialogFragment()
        // but do not show it (yet)
        mAboutDialog = new AboutDialogFragment();

        // Instantiate a new HSV model
        // Initialize the model hue (max), saturation (max), brightness (max)
        mModel = new HSVModel();
        mModel.setHue(HSVModel.MIN_HUE);
        mModel.setSaturation(HSVModel.MIN_SATURATION);
        mModel.setValue(HSVModel.MIN_LIGHTNESS);

        mModel.addObserver(this);

        //Bind View elements
        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mColorSwatch.setOnLongClickListener(this);
        lblHue = (TextView) findViewById(R.id.hue);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        lblSaturation = (TextView) findViewById(R.id.saturation);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);
        lblValue = (TextView) findViewById(R.id.value);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);

        // set the domain (i.e. max) for each component
        mHueSB.setMax(HSVModel.MAX_HUE.intValue());
        mSaturationSB.setMax(HSVModel.MAX_SATURATION.intValue());
        mValueSB.setMax(HSVModel.MAX_LIGHTNESS.intValue());

        // register the event handler for each <SeekBar>
        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);

        try {
            LinearLayout buttonGroup1 = ((LinearLayout) findViewById(R.id.buttonGroup1));
            for (int i = 0; i < buttonGroup1.getChildCount(); i++) {
                ((Button) buttonGroup1.getChildAt(i)).setOnClickListener(this);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }

        updateView();

        lblHue.setText(String.format(getResources().getString(R.string.hue), ""));
        lblSaturation.setText(String.format(getResources().getString(R.string.saturation), ""));
        lblValue.setText(String.format(getResources().getString(R.string.value_brightness), ""));
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser == false) {
            return;
        }

        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue((float) mHueSB.getProgress());
                lblHue.setText(String.format(getResources().getString(R.string.hue), String.valueOf(progress) + "\u00B0"));
                break;
            case R.id.saturationSB:
                mModel.setSaturation((float) mSaturationSB.getProgress());
                lblSaturation.setText(String.format(getResources().getString(R.string.saturation), String.valueOf(progress) + "%"));
                break;
            case R.id.valueSB:
                mModel.setValue((float) mValueSB.getProgress());
                lblValue.setText(String.format(getResources().getString(R.string.value_brightness), String.valueOf(progress) + "%"));
                break;
        }

        Log.i(LOG_TAG, mModel.toString());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                lblHue.setText(String.format(getResources().getString(R.string.hue), ""));
                break;
            case R.id.saturationSB:
                lblSaturation.setText(String.format(getResources().getString(R.string.saturation), ""));
                break;
            case R.id.valueSB:
                lblValue.setText(String.format(getResources().getString(R.string.value_brightness), ""));
                break;
        }
    }

    private void updateHueSB() {
        mHueSB.setProgress(mModel.getHue().intValue());
    }

    private void updateSaturationSB() {
        mSaturationSB.setProgress(mModel.getSaturation().intValue());
    }

    private void updateValueSB() {
        mValueSB.setProgress(mModel.getValue().intValue());
    }

    private void updateColorSwatch() {
        mColorSwatch.setBackgroundColor(mModel.getHSV());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blackButton:
                mModel.asBlack();
                break;
            case R.id.redButton:
                mModel.asRed();
                break;
            case R.id.limeButton:
                mModel.asLime();
                break;
            case R.id.blueButton:
                mModel.asBlue();
                break;
            case R.id.yellowButton:
                mModel.asYellow();
                break;
            case R.id.cyanButton:
                mModel.asCyan();
                break;
            case R.id.magentaButton:
                mModel.asMagenta();
                break;
            case R.id.silverButton:
                mModel.asSilver();
                break;
            case R.id.grayButton:
                mModel.asGray();
                break;
            case R.id.maroonButton:
                mModel.asMaroon();
                break;
            case R.id.oliveButton:
                mModel.asOlive();
                break;
            case R.id.greenButton:
                mModel.asGreen();
                break;
            case R.id.purpleButton:
                mModel.asPurple();
                break;
            case R.id.tealButton:
                mModel.asTeal();
                break;
            case R.id.navyButton:
                mModel.asNavy();
                break;
        }
        showToast();

    }

    private void showToast() {
        Toast.makeText(getApplicationContext(), "H: " + mModel.getHue().intValue() + "° S: " + mModel.getSaturation().intValue() + "% V: " + mModel.getValue().intValue() + "%", Toast.LENGTH_SHORT).show();
    }

    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_about:
                mAboutDialog.show(getFragmentManager(), ABOUT_DIALOG_TAG);
                return true;
            default:
                Toast.makeText(this, "MenuItem: " + item.getTitle(), Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.colorSwatch:
                showToast();
                break;
        }
        return false;
    }
}
