package com.vishnu.sensorunity;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends UnityPlayerActivity {
	public static String output;
	public static String data="test";
	public static int punchcount=0;
	public static int walkcount=0;
	public static int leftcount=0;
	public static int rightcount=0;
	private Activity _activity;
	public static double Xvalue=0.0;
	public static double Yvalue=0.0;
	public static boolean leftkey=false;
	public static boolean rightkey=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		_activity=UnityPlayer.currentActivity;
		startService(new Intent(this,DataService.class));
	}
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			if (bundle!=null) {
				String datas = bundle.getString("data");
				
				Log.i("data in main class", datas);
				data=datas;
				if("punch".equalsIgnoreCase(data))
				{
					punchcount=punchcount+1;
					
				}else if("walk".equalsIgnoreCase(data))
				{
					walkcount=walkcount+1;
					
				}else if("left".equalsIgnoreCase(data))
				{
					leftcount=leftcount+1;
					
				}else if("right".equalsIgnoreCase(data))
				{
					rightcount=rightcount+1;
					
				}
				
				
				//Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
			}else{
				Log.i("data in main class", "bundle null");
				//Toast.makeText(getApplicationContext(), "not", Toast.LENGTH_SHORT).show();
			}
			//handleResult(bundle);
		}

		
	};
	

    @Override
    protected void onResume() {
    	super.onResume();
    	registerReceiver(receiver, new IntentFilter("myproject"));
       
    	/*
         * We need to enforce that Bluetooth is first enabled, and take the
         * user to settings to enable it if they have not done so.
         */
        /*
         * Check for Bluetooth LE Support.  In production, our manifest entry will keep this
         * from installing on these devices, but this will allow test devices or other
         * sideloads to report whether or not the feature exists.
         */
       
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Make sure dialog is hidden
        //mProgress.dismiss();
        //Cancel any scans in progress
       //ds.Pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Disconnect from any active tag connection
        //ds.Stop();
        stopService(new Intent(this, DataService.class));
    }


	

	public static String getAccelerometerData() {
		
		
		
		return data;
	}
	public static void setAccelerometerData(String text)
	{
		data=text;
	}
	public static double getX()
	{
		return Xvalue;
	}
	public static void setX(Double val)
	{
		Xvalue=val;
	}
	public static double getY()
	{
		return Yvalue;
	}
	public static void setY(Double val)
	{
		Yvalue=val;
	}
	public static int getPunchcount() {
		return punchcount;
	}

	public static int getWalkcount() {
		return walkcount;
	}

	public static int getLeftcount() {
		return leftcount;
	}

	public static int getRightcount() {
		return rightcount;
	}

	public static boolean isLeftkey() {
		return leftkey;
	}

	public static void setLeftkey(boolean leftkey) {
		MainActivity.leftkey = leftkey;
	}

	public static boolean isRightkey() {
		return rightkey;
	}

	public static void setRightkey(boolean rightkey) {
		MainActivity.rightkey = rightkey;
	}

	public static void setPunchcount(int pu) {
		punchcount = pu;
	}
	
}
