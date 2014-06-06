/**
 *
 * copyright(C)2014- 
 *
 */
package com.microjixl.demo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * @author jixiaolong<microjixl@gmail.com>
 */
public class DemoActivity extends Activity {
    private ImageView pic;
    private View choseSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView(){
        choseSubject = findViewById(R.id.ll_select_subject);
        choseSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[]{choseSubject.getLeft(),choseSubject.getTop()};
                FragmentManager fm = getFragmentManager();
                SelectFragment sf = SelectFragment.newInstance(location);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.fl_subject_fragment,sf,"choiceSubject");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fg = getFragmentManager().findFragmentByTag("choiceSubject");
        if(fg != null){
            if(!((SelectFragment)fg).onBackPressed()){
                super.onBackPressed();
            }
        }
    }
}
