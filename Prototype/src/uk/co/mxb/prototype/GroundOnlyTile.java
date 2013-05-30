package uk.co.mxb.prototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Point;

public class GroundOnlyTile extends BaseTile
{	
	private Paint _paint;
	private Bitmap _unscaledBitmap;
	private Bitmap _bitmap;
	private Point _screenLocation;
	
	public GroundOnlyTile(Context context, Point screenLocation)
	{
		_screenLocation = screenLocation;
		_unscaledBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ground_only);
		_bitmap = Bitmap.createScaledBitmap(_unscaledBitmap, FINAL_PIXEL_SIZE, FINAL_PIXEL_SIZE, false);
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
		return _screenLocation;
	}
}
