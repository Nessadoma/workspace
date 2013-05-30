package uk.co.mxb.prototype;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.graphics.Point;

public class MainActivity extends Activity
{
	public static Point RESOLUTION = new Point(-1,-1);
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		this.getWindowManager().getDefaultDisplay().getSize(RESOLUTION);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
	}
}
