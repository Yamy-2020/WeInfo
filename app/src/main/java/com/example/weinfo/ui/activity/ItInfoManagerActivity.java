package com.example.weinfo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.weinfo.R;
import com.example.weinfo.adapter.ItInfoManagerAdapter;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.Constants;
import com.example.weinfo.bean.DataBean;
import com.example.weinfo.bean.ItInfoTabBean;
import com.example.weinfo.db.DataBeanDao;
import com.example.weinfo.util.ItemTouchHelperCallBack;

import java.util.ArrayList;

public class ItInfoManagerActivity extends AppCompatActivity {

    private RecyclerView rv;
    private Toolbar toolbar;
    private ItInfoManagerAdapter adapter;
    private ArrayList<DataBean> list;

    //只能用ArrayList
    public static void startAct(Activity activity, ArrayList<DataBean> list) {
        Intent intent = new Intent(activity, ItInfoManagerActivity.class);
        intent.putExtra(Constants.DATA, list);
        activity.startActivityForResult(intent, 100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_info_manager);
        initView();
    }

    /**
     * 全量刷新
     * 我们平常使用RecyclerView适配器刷新时一般使用:notifyDataSetChanged();
     * notifyItemInserted(position);//通知插入了一条数据
     * 2
     * notifyItemMoved(fromPosition,toPosition);//通知两条数据换位置
     * 3
     * notifyItemRemoved(position);//通知移除了一条数据
     * 4
     * notifyItemChanged(position);//通知某个数据发生了改变
     * 5
     * notifyItemRangeInserted(positionStart,itemCount);//通知从某个位置开始插入数据,共插入了几条
     * 6
     * notifyItemRangeChanged(positionStart,itemCount);//通知某个位置开始数据发生了改变,共有几个改变了
     * 7
     * notifyItemRangeRemoved(positionStart,itemCount);//通知从某个位置开始移除itemCount个数据
     */
    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("兴趣爱好");
        setSupportActionBar(toolbar);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        list = (ArrayList<DataBean>) getIntent().getSerializableExtra(Constants.DATA);
        adapter = new ItInfoManagerAdapter(this, list);
        rv.setAdapter(adapter);
        ItemTouchHelperCallBack back = new ItemTouchHelperCallBack(adapter);
        //不允许左滑删除
//        back.setSwipeEnabled(false);
        ItemTouchHelper helper = new ItemTouchHelper(back);
        helper.attachToRecyclerView(rv);
    }

    @Override
    public void onBackPressed() {
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setIndex(i);
        }
        saveData();
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA, list);
        setResult(RESULT_OK, intent);
//        finish();
        super.onBackPressed();
    }

    private void saveData() {
        DataBeanDao dao = BaseApp.getInstance().getDaoSession().getDataBeanDao();
        dao.insertOrReplaceInTx(list);
    }
}