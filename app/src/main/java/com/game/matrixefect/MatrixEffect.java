package com.game.matrixefect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class MatrixEffect extends View {

    private static final Random random = new Random();
    Canvas canvas;
    Bitmap canvasBmp;
    private int width, height;
    private int fontSize = 5;
    private int columnSize;
    private char[] chars = "+-*([])!@#$%^&:|?};,".toCharArray();
    private int[] txtByColumn;
    private Paint paintTxt, paintBg, paintBgBmp, paintInitBg;

    public MatrixEffect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        paintTxt = new Paint();
        paintTxt.setStyle(Paint.Style.FILL);
        paintTxt.setColor(Color.GREEN);
        paintTxt.setTextSize(17);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setAlpha(5);
        paintBg.setStyle(Paint.Style.FILL);

        paintBgBmp = new Paint();
        paintBgBmp.setColor(Color.BLACK);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setAlpha(255);
        paintBg.setStyle(Paint.Style.FILL);
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        canvasBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBmp);

        canvas.drawRect(0, 0, width, height, paintBg);

        columnSize = width / fontSize;

        txtByColumn = new int[columnSize + 1];

        for (int i = 0; i < columnSize; i++) {
            txtByColumn[i] = random.nextInt(height / 2) + 1;
        }
    }

    private void drawText() {
        for (int i = 0; i < txtByColumn.length; i++) {
            canvas.drawText("" + chars[random.nextInt(chars.length)], i * fontSize, txtByColumn[i] * fontSize, paintTxt);
            if (txtByColumn[i] * fontSize > height && Math.random() > 0.975){
                txtByColumn[i] = 0;
            }

            txtByColumn[i]++;
        }
    }

    private void drawCanvas(){
        canvas.drawRect(0,0,width,height,paintBg);
        drawText();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBmp, 0,0,paintBgBmp);
        drawCanvas();
        invalidate();
    }
}
