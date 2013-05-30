package uk.co.mxb.prototype;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener
{	
	private Context _context;
	SurfaceHolder _holder;
	GameThread _gameThread;
	ArrayList<BaseTile> _map;
	BackgroundTile backgroundTile;
	GroundOnlyTile groundOnlyTile;
	GrassSurfaceTile grassSurfaceTile;
	Toast t;
	
	public GameView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		_context = context;
		_holder = getHolder();
		_holder.addCallback(this);
		_map = new ArrayList<BaseTile>(144);
		backgroundTile = new BackgroundTile(context);
		setOnTouchListener(this);
		Setup();
	}
	
	private void Setup() 
	{	
		t = Toast.makeText(_context, "setup", Toast.LENGTH_SHORT);
		Log.i("Doing setup", "Setup");
		int width = BaseTile.FINAL_PIXEL_SIZE;
		int height = BaseTile.FINAL_PIXEL_SIZE;
		Point screenRes = MainActivity.RESOLUTION;
		
		int mapId = 0;;
		for(int x = 0; x < screenRes.x; x += width)
		{
			for(int y = screenRes.y - (height * 3); y < screenRes.y; y += height)
			{
				_map.add(mapId++, new GroundOnlyTile(_context, new Point(x, y)));
			}
			
			for(int y = screenRes.y - (height * 4); y < screenRes.y - (height * 3); y += height)
			{
				_map.add(mapId++, new GrassSurfaceTile(_context, new Point(x, y)));
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
	{
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		_gameThread = new GameThread(holder, _context, this);
		_gameThread.SetRunning(true);
		_gameThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		_gameThread.SetRunning(false);
		boolean retry = true;
		while(retry)
		{
			try
			{
				_gameThread.join();
				retry = false;
			}catch(Exception e)
			{
				Log.v("Exception occured", e.getMessage());
			}
		}
	}
	
	public void OnDraw(Canvas canvas)
	{	
		canvas.drawBitmap(backgroundTile.getBitmap(), 0, 0, backgroundTile.getPaint());
		
		int width = BaseTile.FINAL_PIXEL_SIZE;
		int height = BaseTile.FINAL_PIXEL_SIZE;
		
		for(int x = 0; x < this.getWidth(); x += width)
		{
			for(int y = getHeight() - (height * 3); y < this.getHeight(); y += height)
			{
				GroundOnlyTile groundOnlyTile = new GroundOnlyTile(_context, new Point(x, y));
				canvas.drawBitmap(groundOnlyTile.getBitmap(), x, y, groundOnlyTile.getPaint());
			}
			
			for(int y = getHeight() - (height * 4); y < this.getHeight() - (height *3); y += height)
			{
				GrassSurfaceTile grassSurfaceTile = new GrassSurfaceTile(_context, new Point(x, y));
				canvas.drawBitmap(grassSurfaceTile.getBitmap(), x, y, grassSurfaceTile.getPaint());
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) 
	{
		t.cancel();
		//Toast.makeText(_context, "Touch", Toast.LENGTH_SHORT).show();
		for(int i = 0; i < _map.size(); i++)
		{
			Point tileLocation = _map.get(i).getLocation();
			if(event.getX() >= tileLocation.x && event.getX() <= tileLocation.x + 80)
			{
				if(event.getY() >= tileLocation.y && event.getY() <= tileLocation.y + 80)
				{
					Log.i("Touch", "Touch");
					String classname = _map.get(i).getClass().toString();
					t = Toast.makeText(_context, classname, Toast.LENGTH_SHORT);
					t.show();
					return true;
				}
			}
		}
		return false;
	}
}
