package be.schadron.audiotest;

import android.graphics.Color;
import android.graphics.Paint;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import be.schadron.audiotest.visualizer.VisualizerView;
import be.schadron.audiotest.visualizer.renderer.BarGraphRenderer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Visualizer audioOutput;
    private VisualizerView mVisualizerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createVisualizer() {
        audioOutput = new Visualizer(0); // get output audio stream
        mVisualizerView = (VisualizerView) findViewById(R.id.visualizerview);
        mVisualizerView.link(audioOutput);

        addBarGraphRenderers();
    }

    // Methods for adding renderers to visualizer
    private void addBarGraphRenderers() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(255, 56, 138, 252));
        BarGraphRenderer barGraphRendererBottom = new BarGraphRenderer(32, paint);
        mVisualizerView.addRenderer(barGraphRendererBottom);
    }

    @Override
    protected void onStart() {
        super.onStart();
        createVisualizer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVisualizerView.release();
        mVisualizerView.clearRenderers();
    }
}
