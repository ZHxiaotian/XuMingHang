package zy.xuminghang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
public class LinedEditText extends EditText {
    private Paint linePaint;
    private float margin;
    private int paperColor;

    public LinedEditText(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.linePaint = new Paint();
    }
    @Override
    protected void onDraw(Canvas paramCanvas) {
        paramCanvas.drawColor(this.paperColor);
        int i = getLineCount();// 得到总的行數
        int j = getHeight();// 获得TextView的高度
        int k = getLineHeight();// 获得TextView的行高
        int m = j / k + 1;// 总的线数
        if (i < m)
            i = m;
        int n = getCompoundPaddingTop();
        Log.d("wxl", "n----" + n);
        paramCanvas.drawLine(0.0F, n, getRight(), n, this.linePaint);
        for (int i2 = 0;; i2++) {
            if (i2 >= i) {
                setPadding(10 + (int) this.margin, 0, 0, 0);
                super.onDraw(paramCanvas);
                paramCanvas.restore();
                return;
            }
            n += k;
            paramCanvas.drawLine(0.0F, n, getRight(), n, this.linePaint);
            paramCanvas.save();
        }
    }
}
