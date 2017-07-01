package com.example.number_games;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class Level extends Activity implements OnClickListener {
	TextView tv_1, tv_2, tv_3, tv_4,tv_5;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.level);
		this.setTitle("Êý×ÖÓÎÏ·");
		inti();
	}

	private void inti() {
		tv_1 = (TextView) findViewById(R.id.textView2);
		tv_2 = (TextView) findViewById(R.id.textView3);
		tv_3 = (TextView) findViewById(R.id.textView4);
		tv_4 = (TextView) findViewById(R.id.textView5);
		tv_5 = (TextView) findViewById(R.id.textView6);
		tv_1.setOnClickListener(this);
		tv_2.setOnClickListener(this);
		tv_3.setOnClickListener(this);
		tv_4.setOnClickListener(this);
		tv_5.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.textView2:
			intent=new Intent();
            intent.setClass(Level.this,Two.class );
            startActivity(intent);
            Level.this.finish();	
			break;
		case R.id.textView3:
			intent=new Intent();
            intent.setClass(Level.this,Three.class );
            startActivity(intent);
            Level.this.finish();	
			break;
		case R.id.textView4:
			intent=new Intent();
            intent.setClass(Level.this,Random_moshi.class );
            startActivity(intent);
            Level.this.finish();
			break;
		case R.id.textView5:
		    intent=new Intent();
            intent.setClass(Level.this,MainActivity.class );
            startActivity(intent);
            Level.this.finish();	
			break;
		case R.id.textView6:
		    intent=new Intent();
            intent.setClass(Level.this,Time.class );
            startActivity(intent);
            Level.this.finish();	
			break;

		default:
			break;
		}
	}
}
