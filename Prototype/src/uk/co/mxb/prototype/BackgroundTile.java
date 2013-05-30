package uk.co.mxb.prototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Point;

public class BackgroundTile extends BaseTile
{
	Paint _paint;
	Bitmap _unscaledBitmap;
	Bitmap _bitmap;
	public BackgroundTile(Context context)
	{
		_unscaledBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
		_bitmap = Bitmap.createScaledBitmap(_unscaledBitmap, FINAL_PIXEL_SIZE * 16, FINAL_PIXEL_SIZE * 9, false);
		_paint = new Paint();
	}
	
	public int getHeight()
	{
		if(_bitmap != null)
		{
			return _bitmap.getHeight();
		}
		return -1;
	}
	
	public int getWidth()
	{
		if(_bitmap != null)
		{
			return _bitmap.getWidth();
		}
		return -1;
	}
	
	public Bitmap getBitmap()
	{
		return _bitmap;
	}

	public Paint getPaint() 
	{
		return _paint;
	}

	@Override
	public Point getLocation() 
	{
		//Current not needed.
		return new Point(-1,-1);
	}

}
