package zy.xuminghang.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

import zy.xuminghang.R;


public class SearchView extends EditText {

	float searchSize = 0;
	float textSize = 0;
	int textColor = 0xFF000000;
	Drawable mDrawable;
	Paint paint;

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		InitResource(context, attrs);
		InitPaint();
	}

	private void InitResource(Context context, AttributeSet attrs) {
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.Searchedit);
		float density = context.getResources().getDisplayMetrics().density;
		searchSize=mTypedArray.getDimension(R.styleable.Searchedit_searchedit_imagewidth,18 * density + 0.5F);
//		searchSize = mTypedArray.getDimension(R.styleable.searchedit_imagewidth, 18 * density + 0.5F);
		textColor = mTypedArray.getColor(R.styleable.Searchedit_searchedit_textColor, 0xFF848484);
		textSize = mTypedArray.getDimension(R.styleable.Searchedit_searchedit_textSize, 14 * density + 0.5F);
		mTypedArray.recycle();
	}

	private void InitPaint() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(textColor);
		paint.setTextSize(textSize);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		DrawSearchIcon(canvas);
	}

	private void DrawSearchIcon(Canvas canvas) {
		if (this.getText().toString().length() == 0) {
			float textWidth = paint.measureText("点击搜索商品");
			float textHeight = getFontLeading(paint);

			float dx = (getWidth() - searchSize - textWidth - 8) / 2;
			float dy = (getHeight() - searchSize) / 2;

			canvas.save();
			canvas.translate(getScrollX() + dx, getScrollY() + dy);
			if (mDrawable != null) {
				mDrawable.draw(canvas);
			}

			canvas.drawText("点击搜说商品", getScrollX() + searchSize + 8, getScrollY() + (getHeight() - (getHeight() - textHeight) / 2) - paint.getFontMetrics().bottom - dy, paint);
			canvas.restore();
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (mDrawable == null) {
			try {
				mDrawable = getContext().getResources().getDrawable(R.mipmap.search);
				mDrawable.setBounds(0, 0, (int) searchSize, (int) searchSize);
			} catch (Exception e) {

			}
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		if (mDrawable != null) {
			mDrawable.setCallback(null);
			mDrawable = null;
		}
		super.onDetachedFromWindow();
	}

	public float getFontLeading(Paint paint) {
		Paint.FontMetrics fm = paint.getFontMetrics();
		return fm.bottom - fm.top;
	}

}