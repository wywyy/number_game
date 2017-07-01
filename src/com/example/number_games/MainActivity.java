package com.example.number_games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	 private Toast toast;
	RelativeLayout layout,r_tu;
	Button btn_shuzi[];
	int s = 0,count=0;
	String num1, num2;
	public  Integer res,res1;
	public Integer mubiao;
	Result_number  result;
	Result_number1  result1;
	TextView tv_result1, tv_result2, tv_suanshi,score,finish,guanka;
	Intent intent;
	LinearLayout linear1;
	int ids[] = new int[] { R.id.button1, R.id.button2, R.id.button3,
			R.id.button4, R.id.btn_5, R.id.button6, R.id.button7, R.id.button8,
			R.id.button9 };
	ArrayList<String> str = new ArrayList<String>();
	ArrayList<Button> btn_1 = new ArrayList<Button>();
	int width, height;
	ArrayList<Point[]> points = new ArrayList<Point[]>();
	Drawable btnDrawable;
	Resources resources;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
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
					tv_suanshi.setText("");
					break;
				case MotionEvent.ACTION_MOVE:
					int x = (int) arg1.getX();
					int y = (int) arg1.getY();
					getposition(x, y);
					break;

				case MotionEvent.ACTION_UP:
					for (int i = 0; i < btn_shuzi.length;) {
						btn_shuzi[i].setBackgroundColor(Color.parseColor("#ADDBF7"));
						btn_shuzi[i].setTag(1);
						i=i+2;
					}
					for (int i = 1; i < btn_shuzi.length;) {
						btn_shuzi[i].setBackgroundColor(Color.parseColor("#E7D3D6"));
						btn_shuzi[i].setTag(1);
						i=i+2;
					}
					take_out();							
					getScore();		
					btn_1.clear();
					str.clear();
					s = 0;					
					break;
				default:
					break;
				}
				return true;
			}		

			public void getScore() {
				if (count < 5) {
					if (tv_result1.getText().toString().trim().equals(res+"")) {
						score.setText(Integer.valueOf(score.getText().toString()) + 10 + "");
						count++;
					 	getrandom();
						 TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
     					 translate.setDuration(500);
						tv_result1.startAnimation(translate);
						result = new Result_number(tv_result1, btn_shuzi);
						result.aim();
						tv_result2.setTextSize(14f);
						tv_result2.setText("本关卡未开始");				
						if (count >= 5) {
							r_tu = (RelativeLayout) findViewById(R.id.r_tu);
							r_tu.setBackground(btnDrawable);
							tv_result1.setTextSize(14f);
							tv_result1.setText("本关卡结束");;
							guanka.setText("第二关");
							tv_suanshi.setText("");
							tv_result2.setTextSize(25f);
							getrandom();
							 TranslateAnimation translate1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
	     					 translate1.setDuration(500);
							tv_result2.startAnimation(translate1);
							result1 = new Result_number1(tv_result2, btn_shuzi);
							result1.aim1();
							alter();
						}
						tv_suanshi.setText("");
					}else{
						tv_suanshi.setTextSize(14f);
						if(tv_suanshi.getText().toString().trim().length()<3){	
							tv_suanshi.setText("滑动长度不够");
						}else if(tv_suanshi.getText().toString().trim().length()>3){
							tv_suanshi.setText("滑动长度太长");
						}
						else{
							tv_suanshi.setText("输入的算式不正确,要仔细思考哦");
						}
					 
			        
				    }

				} else if (count>=5) {
					
					if (tv_result2.getText().toString().equals(res1 + " ")) {
						score.setText(Integer.valueOf(score.getText()
								.toString()) + 20 + "");
						getrandom();
						 TranslateAnimation translate1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
     					 translate1.setDuration(500);
						 tv_result2.startAnimation(translate1);
						result1 = new Result_number1(tv_result2, btn_shuzi);
						result1.aim1();
						count++;
						tv_suanshi.setText("");
					}else{
						tv_suanshi.setTextSize(14f);
						if(tv_suanshi.getText().toString().trim().length()<5){	
							tv_suanshi.setText("滑动长度不够");
						}else if(tv_suanshi.getText().toString().trim().length()>5){
							tv_suanshi.setText("滑动长度太长");
						}else{
							tv_suanshi.setText("输入的算式不正确,要仔细思考哦");
						}
					}
					if(count==12){
						alter() ;
					}
					
				}
			}
			
	/*
	 * 弹出框
	 */
			private void alter() {
				android.app.AlertDialog.Builder build=new AlertDialog.Builder(MainActivity.this);
				build.setCancelable(false);
				build.setTitle("提示对话框");
				if(count==5){
				build.setMessage("你很棒哦，即将进入下一,是否还要继续？");
				   build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							tv_result1.setTextSize(14f);
							tv_result1.setText("本关卡结束");
						}
						
					});

					build.setNegativeButton("取消",new android.content.DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {						
							MainActivity.this.finish();	
							intent=new Intent();
							intent.setClass(MainActivity.this, Level.class);
							 startActivity(intent);
						}			    	
				    });
				}
				if(count==12){
					build.setMessage("你太厉害了哦，即将进入随机模式,分数将清0,是否还要继续？");
					   build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								MainActivity.this.finish();	
								intent=new Intent();
								intent.setClass(MainActivity.this, Random_moshi.class);
								startActivity(intent);
							}
							
						});

						build.setNegativeButton("取消",new android.content.DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog, int which) {						
								dialog.dismiss();
							}			    	
					    });
					}
	         
				build.show();
				
				
			}

			/*
			 * 获取每个button的位置
			 */
			private void getposition(int x, int y) {
				tv_suanshi.setTextSize(20f);
				if (x > 0 && y > 0) {
					getpoint();
					for (int i = 0; i < btn_shuzi.length; i++) {
						Point[] point_d = points.get(i);
						if (x > point_d[0].x && y > point_d[0].y
								&& x < point_d[1].x && y < point_d[1].y) {
							if (tv_suanshi.getText().toString().trim().equals("")&&
							   (btn_shuzi[i].getText().toString().equals("+") || 
						    	btn_shuzi[i].getText().toString().equals("-"))) {
                                tv_suanshi.setText("");
							} else {
								if (btn_shuzi[i].getTag().equals(1)) {
									btn_shuzi[i].setBackgroundColor(Color.parseColor("#A4C639"));
									btn_1.add(btn_shuzi[i]);
									String test_1 = btn_shuzi[i].getText()
											.toString();
									str.add(test_1);
									if(tv_suanshi.length()<7){
									tv_suanshi.append(str.get(s));
									}
									s++;
									// btn_1.get(i).setTag(0);//这里不能设置btn_1.get(i)，否则要崩
									btn_shuzi[i].setTag(0);
								}
								if (btn_shuzi[i].getTag().equals(0)) {
									if (btn_1.size() >= 2) {
										if (btn_1.get(btn_1.size() - 2) == btn_shuzi[i]) {
											if (tv_suanshi.length() > 1) {
												btn_1.get(btn_1.size() - 1).setTag(1);
												if((btn_1.size() - 1)%2==0){
												btn_1.get(btn_1.size() - 1).setBackgroundColor(Color.parseColor("#ADDBF7"));
												}else{
												btn_1.get(btn_1.size() - 1).setBackgroundColor(Color.parseColor("#E7D3D6"));	
												}
												tv_suanshi.setText(tv_suanshi.getText().toString().substring(0,tv_suanshi.getText().length() - 1));
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

			}

			private void take_out() {
				 List<Integer> list=new ArrayList<Integer>();//符号对应的位置
                if(tv_suanshi.getText().toString().trim().length()==3){

                	String text_num=tv_suanshi.getText().toString().trim();
                	for(int i=0;i<text_num.length();i++){
                		if(isSign(text_num.charAt(i))){
                			list.add(i);
                		}
                	}
                	if(list.size()!=0){
                	String num1=text_num.substring(0,list.get(0));
                	String f1=text_num.substring(list.get(0),list.get(0)+1);
                	String num2=text_num.substring(list.get(0)+1,text_num.trim().length());
                	
                	Log.e(list.size()+"res1res1res1res1res1", num1+"fffff"+f1);
                	if(!num2.equals("+")&&!num2.equals("-")&&!num2.equals("")){
                	if(f1.equals("+")){
                		res = Integer.valueOf(num1) + Integer.valueOf(num2);
                	}
                	else if(f1.equals("-")){
                		res = Integer.valueOf(num1) - Integer.valueOf(num2);
                	}
                	
                	}else{
						 tv_suanshi.setText("");
					}
                
                	}
                }
              
                if(tv_suanshi.getText().toString().trim().length()==5){
                	String text_num=tv_suanshi.getText().toString().trim();
                	for(int i=0;i<text_num.length();i++){
                		if(isSign(text_num.charAt(i))){
                			list.add(i);
                		}
                	}
                	if(list.size()==2){
                	String num1=text_num.substring(0,list.get(0));
                	String f1=text_num.substring(list.get(0),list.get(0)+1);
                	String num2=text_num.substring(list.get(0)+1,list.get(1));
                	String f2=text_num.substring(list.get(1),list.get(1)+1);
                	String num3=text_num.substring(list.get(1)+1,text_num.trim().length());
                	
                	Log.e(list.size()+"res1res1res1res1res1", num1+"fffff"+f1+"fffff"+num2+"fffff"+f2+"fffff"+num3);
                	if(!num2.equals("+")&&!num2.equals("-")&&!num2.equals("")&&!num3.equals("")&&!num3.equals("+")&&!num3.equals("-")){
                	if(f1.equals("+")){
                		res = Integer.valueOf(num1) + Integer.valueOf(num2);
                	}
                	else if(f1.equals("-")){
                		res = Integer.valueOf(num1) - Integer.valueOf(num2);
                	}
                	
                	if(f2.equals("+")){
                		res1 = Integer.valueOf(res) + Integer.valueOf(num3);
                	}
                	else if(f2.equals("-")){
                		res1 = Integer.valueOf(res) - Integer.valueOf(num3);
                	}
                	}else{
						 tv_suanshi.setText("");
					}
                }}
			}
			private boolean isSign(char num){
				switch (num) {
				case '+':
					return true;
				case '-':
					return true;
				default:
					break;
				}
				return false;
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

	public  void getrandom() {

		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();

		while (list.size() < 5) {
			Integer temp = random.nextInt(9)+1;
			if (!list.contains(temp)) {
				list.add(temp);
			}
		}
		for (int i = 0, j = 0; i < list.size(); i++) {
			btn_shuzi[j].setText(list.get(i).toString());
			j += 2;
		}

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
		linear1=(LinearLayout) findViewById(R.id.linear1);
		tv_result1 = (TextView) findViewById(R.id.tv_result1);
		tv_result2 = (TextView) findViewById(R.id.tv_result2);
		tv_suanshi = (TextView) findViewById(R.id.tv_suanshi);
		guanka = (TextView) findViewById(R.id.guanka);
		btn_shuzi = new Button[ids.length];
       score = (TextView) findViewById(R.id.tv_score);
		
		resources =this.getResources();
	    btnDrawable = resources.getDrawable(R.drawable.t4);  
		// Random random = new Random();
		for (int i = 0; i < ids.length; i++) {
			btn_shuzi[i] = (Button) findViewById(ids[i]);
			btn_shuzi[i].setTag(1);
			// a = (Integer) btn_shuzi[i].getTag();
			// String s = new String();
			// int ran = random.nextInt(10);
			// s = String.valueOf(ran);
			// btn_shuzi[i].setText(s);
			// Log.e("====================", s);
		}
		getrandom();
		result=new Result_number(tv_result1,btn_shuzi);
		result.aim();
		finish=(TextView) findViewById(R.id.finish);
		finish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

			  android.app.AlertDialog.Builder build=new AlertDialog.Builder(MainActivity.this);
				build.setMessage("您的得分是:"+score.getText().toString().trim()+"\n你确定要退出了吗");
				   build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							MainActivity.this.finish();
							intent=new Intent();
							intent.setClass(MainActivity.this, Level.class);
							startActivity(intent);
						}
						
					});

					build.setNegativeButton("取消",new android.content.DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {						
							dialog.dismiss();
						}			    	
				    });
			        
					build.show();
			}
		});
	}

}
