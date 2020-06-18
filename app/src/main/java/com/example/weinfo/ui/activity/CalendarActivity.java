package com.example.weinfo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.weinfo.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialCalendarView calendarView;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    private void initView() {
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)//设置一周的第一天是周几
                .setMinimumDate(CalendarDay.from(1949, 10, 1))//最早的日期
                .setMaximumDate(CalendarDay.from(calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)))//最新的日期，动态设置
                .setCalendarDisplayMode(CalendarMode.MONTHS)//一页显示的
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                CalendarDay selectedDate = calendarView.getSelectedDate();
                if (selectedDate!=null){
                    EventBus.getDefault().post(selectedDate);
                    finish();
                }
                break;
        }
    }
}