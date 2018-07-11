package mbrass.com.moi;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    TableLayout tl;
    EditText searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String gname="";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchName = (EditText) findViewById(R.id.searchName);

        if(searchName!=null)
            gname = searchName.getText().toString();
        else
            System.out.println(searchName);
        System.out.println("-----------------");
        System.out.println("RONAN "+gname);
        System.out.println("-----------------");


        searchName.addTextChangedListener(new TextWatcher() {

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
        readGuests("a");

    }

    public void readGuests(String gname){
        tl = (TableLayout) findViewById(R.id.guestTable);
        tl.removeAllViews();

        Resources res = getResources();
        TypedArray guestnames = res.obtainTypedArray(R.array.guestnames);
        TypedArray guestamount = res.obtainTypedArray(R.array.guestamount);
        TableRow tr;
        TextView t1,t2;

        String name="",amount="";

        tr = new TableRow(this);
        t1 = new TextView(this);
        t2 = new TextView(this);
        t1.setText("NAME");
        t2.setText("AMOUNT");
        t1.setTypeface(null, Typeface.BOLD);
        t2.setTypeface(null, Typeface.BOLD);
        t1.setTextSize(24);
        t2.setTextSize(24);
        t1.setTextColor(Color.BLACK);
        t2.setTextColor(Color.BLACK);
        tr.addView(t1);
        tr.addView(t2);
        tr.setId(-1);
        tr.setBackgroundColor(Color.YELLOW);
        tl.addView(tr);

        boolean flag=true,toggle=true;
        TableLayout.LayoutParams param = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f);

        for(int i=0;i<guestnames.length();i++){
            name = guestnames.getString(i).trim();
            amount=guestamount.getString(i).trim();

            if(gname.length()>0)
                flag = name.toLowerCase().contains(gname.toLowerCase());

            if(flag) {
                tr = new TableRow(this);
                t1 = new TextView(this);
                t2 = new TextView(this);

                t1.setText(name);
                //t1.setMaxLines(100);
                //t1.canScrollHorizontally();

                t2.setText(amount);
                t1.setTextSize(18);
                t2.setTextSize(18);
                tr.addView(t1);
                tr.addView(t2);
                tr.setLayoutParams(param);
                tr.setId(i);
                if (toggle) {
                    t1.setTextColor(Color.BLACK);
                    t2.setTextColor(Color.BLACK);
                    tr.setBackgroundColor(Color.WHITE);
                } else {
                    t1.setTextColor(Color.WHITE);
                    t2.setTextColor(Color.WHITE);
                    tr.setBackgroundColor(Color.GRAY);
                }
                toggle=!toggle;
                tl.addView(tr);
            }
        }
    }

}
