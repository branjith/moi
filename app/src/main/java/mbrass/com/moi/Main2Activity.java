package mbrass.com.moi;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    TableLayout tl;
    EditText guestName;
    //Typeface face = Typeface.createFromAsset(getAssets(),"font/rammetto.ttf");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String gname="";

        guestName = (EditText) findViewById(R.id.guestName);

        guestName.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    System.out.println("---> "+s);
                readGuests(s.toString());
            }
        });
        readGuests("");
        guestName.clearFocus();
    }

    public void readGuests(String gname){

        Typeface typeface = null;

        // Typeface typeface = getResources().getFont(R.font.rammetto);
        try {
            typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "rammetto.ttf");

           /* AssetManager am = getAssets();
            String[] list = am.list("");
            for(String str:list)
                System.out.println("MI------- "+str);*/
        }catch (Exception e){
            e.printStackTrace();
        }

        tl = (TableLayout) findViewById(R.id.guestList);
        tl.removeAllViews();

        Resources res = getResources();
        TypedArray guestnames = res.obtainTypedArray(R.array.guestnames);
        TypedArray guestamount = res.obtainTypedArray(R.array.guestamount);
        TableRow tr1, tr2, tr3;
        TextView t1,t2,t3;

        String name="",amount="";
        boolean flag=true,toggle=true;
        int padding = 50;

        for(int i=0;i<guestnames.length();i++){
            name = guestnames.getString(i);
            amount=guestamount.getString(i);

            if(gname.length()>0)
                flag = name.toLowerCase().contains(gname.toLowerCase());

            if(flag) {
                tr1 = new TableRow(this);
                tr2 = new TableRow(this);
                tr3 = new TableRow(this);

                tr1.setGravity(Gravity.CENTER_VERTICAL);
                tr2.setGravity(Gravity.CENTER_VERTICAL);

                t1 = new TextView(this);
                t2 = new TextView(this);
                t3 = new TextView(this);

              //  t1.setBackgroundResource(R.drawable.box);
              //  t2.setBackgroundResource(R.drawable.box);
              //  t3.setBackgroundResource(R.drawable.box);

                t1.setText("");
                //t2.setTypeface(Typeface.SERIF);
                t2.setTypeface(typeface);
                t2.setText("\n"+name+"\n"+amount);
                t2.setPadding(10,0,10,0);
                t3.setText("");
                //t1.setTextSize(36);
                t2.setTextSize(36);
                t1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                t2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tr1.addView(t1);
                tr2.addView(t2);
                tr3.addView(t3);

                if (toggle) {
                    t1.setTextColor(Color.BLACK);
                    t2.setTextColor(Color.WHITE);
                    t2.setBackgroundResource(R.drawable.box);
                   // tr1.setBackgroundColor(Color.WHITE);
                   // tr2.setBackgroundColor(Color.WHITE);
                } else {
                    t1.setTextColor(Color.BLACK);
                    t2.setTextColor(Color.WHITE);
                    t2.setBackgroundResource(R.drawable.box1);
                    //tr1.setBackgroundColor(Color.GRAY);
                    //tr2.setBackgroundColor(Color.GRAY);
                }
                toggle=!toggle;

                tl.addView(tr1);
                tl.addView(tr2);
                tl.addView(tr3);
            }
        }

    }
}
