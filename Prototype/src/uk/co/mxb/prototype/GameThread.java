package uk.co.mxb.prototype;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread
{
	boolean _run;
	Canvas _canvas;
	SurfaceHolder _surfaceHolder;
	Context _context;
	GameView _gameView;
	
	public GameThread(SurfaceHolder holder, Context context, GameView gameView)
	{
		_surfaceHolder = holder;
		_context = context;
		_run = false;
		_gameView = gameView;
	}
	
	public void SetRunning(boolean run)
	{
		_run = run;
	}
	
	@Override
	public void run()
	{
		super.run();
		while(_run)
		{
			_canvas = _surfaceHolder.lockCanvas();
			if(_canvas != null)
			{
				_gameView.OnDraw(_canvas);
				_surfaceHolder.unlockCanvasAndPost(_canvas);
			}
		}
	}

}
