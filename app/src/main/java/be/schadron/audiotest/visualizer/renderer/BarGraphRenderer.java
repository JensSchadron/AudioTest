/**
 * Copyright 2011, Felix Palmer
 * <p>
 * Licensed under the MIT license:
 * http://creativecommons.org/licenses/MIT/
 */
package be.schadron.audiotest.visualizer.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import be.schadron.audiotest.visualizer.AudioData;
import be.schadron.audiotest.visualizer.FFTData;

public class BarGraphRenderer extends Renderer {
    private final static int HEIGHT_AMPLIFIER = 250;

    private int mDivisions;
    private Paint mPaint;

    /**
     * Renders the FFT data as a series of lines, in histogram form
     *
     * @param divisions - must be a power of 2. Controls how many lines to draw
     * @param paint     - Paint to draw lines with
     */
    public BarGraphRenderer(int divisions,
                            Paint paint) {
        super();
        mDivisions = divisions;
        mPaint = paint;
    }

    @Override
    public void onRender(Canvas canvas, AudioData data, Rect rect) {
        // Do nothing, we only display FFT data
    }

    @Override
    public void onRender(Canvas canvas, FFTData data, Rect rect) {
        for (int i = 0; i < data.bytes.length / mDivisions; i++) {

            mFFTPoints[i * 4] = rect.width() * ( (data.bytes.length / mDivisions) * i) / data.bytes.length + mPaint.getStrokeWidth();
            mFFTPoints[i * 4 + 2] = rect.width() * ( (data.bytes.length / mDivisions) * i) / data.bytes.length + mPaint.getStrokeWidth();

            byte rfk = data.bytes[mDivisions * i];
            byte ifk = data.bytes[mDivisions * i + 1];

            float magnitude = (float) Math.sqrt(rfk * rfk + ifk * ifk);
            int dbValue = (int) (HEIGHT_AMPLIFIER * Math.log10(magnitude));

            mFFTPoints[i * 4 + 1] = rect.height();
            mFFTPoints[i * 4 + 3] = rect.height() - (dbValue * 2 - 10);
        }

        canvas.drawLines(mFFTPoints, mPaint);
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }
}
