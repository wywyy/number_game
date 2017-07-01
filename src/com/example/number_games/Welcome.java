package com.example.number_games;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Welcome extends Activity {
	RelativeLayout layout;
	Button btn_shuzi[];
	int s = 0;
	TextView  tv_welcome;
	int ids[] = new int[] { R.id.button1, R.id.button2, R.id.button3,
			R.id.button4, R.id.btn_5, R.id.button6, R.id.button7, R.id.button8,
			R.id.button9 };
	ArrayList<String> str = new ArrayList<String>();
	ArrayList<Button> btn_1 = new ArrayList<Button>();
	int width, height;
	ArrayList<Point[]> points = new ArrayList<Point[]>();
	Thread thread;
	/**
	 * big
	 */
	Boolean isRunning  =true;
	/**
	 * small
	 */
	Boolean Running=true;
	int colorposition=0;
	Handler handler=new Handler(){
		@Override
	public void handleMessage(Message msg) {
			colorposition=msg.what;
		switch (msg.what) {
			case 1:
			btn_shuzi[0].setBackgroundColor(Color.parseColor("#A4C639"));
			btn_shuzi[1].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[2].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[3].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[5].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[6].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[7].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[8].setBackgroundColor(Color.parseColor("#BDE4E8"));
			break;
		  case 2:
		    btn_shuzi[0].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[1].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[2].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[3].setBackgroundColor(Color.parseColor("#A4C639"));
			btn_shuzi[5].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[6].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[7].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[8].setBackgroundColor(Color.parseColor("#BDE4E8"));
			break;
		  case 3:
		    btn_shuzi[0].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[1].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[2].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[3].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[5].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[6].setBackgroundColor(Color.parseColor("#A4C639"));
			btn_shuzi[7].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[8].setBackgroundColor(Color.parseColor("#BDE4E8"));
				break;
		  case 4:
		    btn_shuzi[0].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[1].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[2].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[3].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[5].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[6].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[7].setBackgroundColor(Color.parseColor("#A4C639"));
			btn_shuzi[8].setBackgroundColor(Color.parseColor("#BDE4E8"));
				break;
		  case 5:
		    btn_shuzi[0].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[1].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[2].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[3].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[5].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[6].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[7].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[8].setBackgroundColor(Color.parseColor("#A4C639"));
				break;
		  case 6:
		    btn_shuzi[0].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[1].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[2].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[3].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[5].setBackgroundColor(Color.parseColor("#A4C639"));
			btn_shuzi[6].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[7].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[8].setBackgroundColor(Color.parseColor("#BDE4E8"));
				break;
		  case 7:
		    btn_shuzi[0].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[1].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[2].setBackgroundColor(Color.parseColor("#A4C639"));
			btn_shuzi[3].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[5].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[6].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[7].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[8].setBackgroundColor(Color.parseColor("#BDE4E8"));
				break;
		  case 8:
		    btn_shuzi[0].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[1].setBackgroundColor(Color.parseColor("#A4C639"));
			btn_shuzi[2].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[3].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[5].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[6].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[7].setBackgroundColor(Color.parseColor("#BDE4E8"));
			btn_shuzi[8].setBackgroundColor(Color.parseColor("#BDE4E8"));
				break;
				
		default:
			break;
		}
	}};
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		init();
		this.setTitle("数字游戏");
		initiposition();
		setOnlistenr();
	}

	
	
	/*
	 * 给布局设置监听事件
	 */

	private void setOnlistenr() {
		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:	
					Running  = false;
					Log.e("colorpositionolorposition", colorposition+"");
					switch (colorposition) {
					case 1:btn_shuzi[0].setBackgroundColor(Color.parseColor("#BDE4E8"));break;
					case 2:btn_shuzi[3].setBackgroundColor(Color.parseColor("#BDE4E8"));break;
					case 3:btn_shuzi[6].setBackgroundColor(Color.parseColor("#BDE4E8"));break;
					case 4:btn_shuzi[7].setBackgroundColor(Color.parseColor("#BDE4E8"));break;
					case 5:btn_shuzi[8].setBackgroundColor(Color.parseColor("#BDE4E8"));break;
					case 6:btn_shuzi[5].setBackgroundColor(Color.parseColor("#BDE4E8"));break;
					case 7:btn_shuzi[2].setBackgroundColor(Color.parseColor("#BDE4E8"));break;
					case 8:btn_shuzi[1].setBackgroundColor(Color.parseColor("#BDE4E8"));break;
					default:
						break;
	}
				
					break;
				case MotionEvent.ACTION_MOVE:
					int x = (int) arg1.getX();
					int y = (int) arg1.getY();
					getposition(x, y);
					break;

				case MotionEvent.ACTION_UP:
                     if(tv_welcome.getText().toString().trim().equals("欢迎进入数字游戏")){
                    	 Intent intent=new  Intent();
                    	 intent.setClass(Welcome.this, Level.class);
         				 startActivity(intent);
         				Welcome.this.finish();	
         				Running=false;
                     }
                     else{
                    	 tv_welcome.setText("请根据高亮提示画出\n 欢迎进入数字游戏！");
                    	 Running=true; 	 
                     }
					
                     for (int i = 0; i < btn_shuzi.length;i++) {
 						btn_shuzi[i].setBackgroundColor(Color.parseColor("#BDE4E8"));
 						btn_shuzi[i].setTag(1);
 					}		
					btn_1.clear();
					str.clear();
					s = 0;					
					break;
				default:
					break;
				}
				return true;
				
		}
			/*
			 * 获取每个button的位置
			 */
			private void getposition(int x, int y) {
				if (x > 0 && y > 0) {
					getpoint();
					for (int i = 0; i < btn_shuzi.length; i++) {
						Point[] point_d = points.get(i);
						if (x > point_d[0].x && y > point_d[0].y&& x < point_d[1].x && y < point_d[1].y) {
								if (btn_shuzi[i].getTag().equals(1)) {
									btn_shuzi[i].setBackgroundColor(Color.parseColor("#A4C639"));
									btn_1.add(btn_shuzi[i]);
									String test_1 = btn_shuzi[i].getText().toString().trim();
									str.add(test_1);
									int tv_welcomelength = tv_welcome.getText().toString().trim().length();
									if("请根据高亮提示画出\n 欢迎进入数字游戏！".equals(tv_welcome.getText().toString().trim())){
										tv_welcome.setText("");
									}
									if(tv_welcomelength<25){
//										Log.e("str.get(s)", str.get(s));
										tv_welcome.append(str.get(s));
									}
										s++;
									btn_shuzi[i].setTag(0);
								}
								if (btn_shuzi[i].getTag().equals(0)) {
									if (btn_1.size() >= 2) {
										if (btn_1.get(btn_1.size() - 2) == btn_shuzi[i]) {
											if (tv_welcome.getText().toString().trim().length() > 1) {
												btn_1.get(btn_1.size() - 1).setTag(1);
													btn_1.get(btn_1.size() - 1).setBackgroundColor(Color.parseColor("#BDE4E8"));
													tv_welcome.setText(tv_welcome.getText().toString().substring(0,tv_welcome.getText().length() - 1));
												btn_1.remove(btn_1.size() - 1);
												s = s - 1;
												str.remove(str.size() - 1);
											}
										}
									}
								}

							
						}
					}

				}

			}
			private void getpoint() {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						Point point = new Point(j * width / 3, i * height / 3);
						Point point1 = new Point((j + 1) * width / 3, (i + 1)
								* height / 3);
						Point[] pointa = new Point[] { point, point1 };
						points.add(pointa);
					}
				}
			}
		});

	}
	/*
	 * 获取布局的宽和高
	 */
	private void initiposition() {
		layout.post(new Runnable() {
			@Override
			public void run() {
				width = layout.getWidth();
				height = layout.getHeight();
			}
		});
	}

	private void init() {
		layout = (RelativeLayout) findViewById(R.id.layout_a);
		tv_welcome = (TextView) findViewById(R.id.textView1);
		btn_shuzi = new Button[ids.length];
		for (int i = 0; i < ids.length; i++) {
			btn_shuzi[i] = (Button) findViewById(ids[i]);
			btn_shuzi[i].setTag(1);
		}
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isRunning) {
					if(Running){
					try {
						if (Running){handler.sendEmptyMessage(1);Thread.sleep(500);}
						if (Running){handler.sendEmptyMessage(2);Thread.sleep(500);}
						if (Running){handler.sendEmptyMessage(3);Thread.sleep(500);}
						if (Running){handler.sendEmptyMessage(4);Thread.sleep(500);}
						if (Running){handler.sendEmptyMessage(5);Thread.sleep(500);}
						if (Running){handler.sendEmptyMessage(6);Thread.sleep(500);}
						if (Running){handler.sendEmptyMessage(7);Thread.sleep(500);}
						if (Running){handler.sendEmptyMessage(8);Thread.sleep(500);}
						if (Running){handler.sendEmptyMessage(1);Thread.sleep(500);}
					}
					 catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				else{
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}

			}
		});
		thread.start();
	}

}
