package uk.co.mxb.prototype;

import android.graphics.Bitmap;
import android.graphics.Point;

abstract class BaseTile
{
	public static final int FINAL_PIXEL_SIZE = 80;
	public abstract int getHeight();
	public abstract int getWidth();
	public abstract Bitmap getBitmap();
	public abstract Point getLocation();
}
