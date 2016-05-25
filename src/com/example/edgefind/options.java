package com.example.edgefind;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class options extends Activity {
	//private SharedPreferences edg;
	private Button Btn_correct=null;
	private Button Btn_measure=null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        Btn_correct=(Button)findViewById(R.id.Btn_correct);
        //edg = this.getSharedPreferences("edge", Context.MODE_WORLD_READABLE); 
        Btn_correct.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(options.this,MainActivity.class);  
				options.this.startActivity(intent);
			}
        	
        });
        Btn_measure=(Button)findViewById(R.id.Btn_measure);
        Btn_measure.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(options.this,measure.class);  
				options.this.startActivity(intent);
			}
        	
        });
        
        
	}
}
