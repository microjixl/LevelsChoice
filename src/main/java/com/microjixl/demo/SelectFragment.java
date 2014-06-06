/**
 *
 * copyright(C)2014- 
 *
 */
package com.microjixl.demo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jixiaolong<microjixl@gmail.com>
 */
public class SelectFragment extends Fragment implements View.OnClickListener{
    private ListView menuLv;
    private ListView subjectLv;
    private DataAdapter menuAdapter;
    private DataAdapter subjectAdapter;
    private View foldBtn;
    private View foldContent;
    private View lamp;
    private int[] location;

    public static SelectFragment newInstance(int [] location){
        SelectFragment fg = new SelectFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("LOCATION", location);
        fg.setArguments(bundle);
        return fg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            int [] l = args.getIntArray("LOCATION");
            if(l != null && l.length == 2)location = l;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.select_choice, container,false);
        initView(layout);
        return layout;
    }

    private void initView(View layout){
        lamp = layout.findViewById(R.id.v_lamp);
        menuLv = (ListView) layout.findViewById(R.id.lv_menu);
        subjectLv = (ListView) layout.findViewById(R.id.lv_subject);
        foldBtn = layout.findViewById(R.id.ll_title_sp);
        foldContent = layout.findViewById(R.id.ll_lv_sp);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)foldBtn.getLayoutParams();
        lp.leftMargin = location[0];
        lp.topMargin = location[1];
        foldBtn.setLayoutParams(lp);
        foldBtn.setOnClickListener(this);
        lamp.setOnClickListener(this);
        menuAdapter = new DataAdapter(getActivity().getBaseContext());

        menuAdapter.setData(allocateData());
        menuLv.setAdapter(menuAdapter);
        menuLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                menuAdapter.checked(i);
                subjectAdapter.setData(allocateSubject());
                subjectAdapter.notifyDataSetChanged();
            }
        });
        subjectAdapter = new DataAdapter(getActivity().getBaseContext());
        subjectAdapter.checked(-1);
        subjectAdapter.setData(allocateSubject());
        subjectLv.setAdapter(subjectAdapter);
    }

    @Deprecated
    private List<String> allocateSubject(){
        //init test data
        List<String> mList = new ArrayList<String>();
        String g = "语文";
        mList.add(g);

        g = "数学";
        mList.add(g);

        g = "英语";
        mList.add(g);

        g = "化学";
        mList.add(g);

        g = "政治";
        mList.add(g);

        g = "物理";
        mList.add(g);

        g = "英语";
        mList.add(g);
        for(int i=0;i<3;i++){
            Random random = new Random();
            int d = random.nextInt(7-i);
            mList.remove(d);
        }
        return mList;
    }
    @Deprecated
    private List<String> allocateData(){
        //init test data
        List<String> mList = new ArrayList<String>();
        String g = "小学";
        mList.add(g);

        g = "初一";
        mList.add(g);

        g = "初二";
        mList.add(g);

        g = "初三";
        mList.add(g);

        g = "高一";
        mList.add(g);

        g = "高二";
        mList.add(g);

        g = "高三";
        mList.add(g);
        return mList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_title_sp:
            case R.id.v_lamp:
                exitAnim();
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        enterAnim();
    }

    public boolean onBackPressed(){
        exitAnim();
        return true;
    }

    private void enterAnim(){
        Animation turnOn = AnimationUtils.loadAnimation(getActivity().getBaseContext(),R.anim.fade_in);
        lamp.startAnimation(turnOn);

        Animation in = AnimationUtils.loadAnimation(getActivity().getBaseContext(),R.anim.popupwindow_slide_in_from_top);
        foldContent.startAnimation(in);
    }

    private void exitAnim(){
        Animation turnOff = AnimationUtils.loadAnimation(getActivity().getBaseContext(),R.anim.fade_out);
        lamp.startAnimation(turnOff);
        Animation out = AnimationUtils.loadAnimation(getActivity().getBaseContext(),R.anim.popupwindow_slide_out_to_top);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                lamp.setVisibility(View.INVISIBLE);
                foldContent.setVisibility(View.INVISIBLE);
                getFragmentManager().popBackStack();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        foldContent.startAnimation(out);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
