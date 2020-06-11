package com.example.weinfo.model;

import com.example.weinfo.base.BaseModel;
import com.example.weinfo.bean.MainBean;
import com.example.weinfo.net.ApiService;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.ThreadManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCursorResult;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupInfo;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/6   16:42
 **/
public class MainModel extends BaseModel {
    //获取联系人+群
    public void initData(final CallBack<Map<String, EaseUser>> callBack) {
        ThreadManager.getInstance()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //获取联系人
                            List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                            //获取群
                            //从服务器获取自己加入的和创建的群组列表，此api获取的群组sdk会自动保存到内存和db。
                            List<EMGroup> grouplist = EMClient.getInstance().groupManager().getJoinedGroupsFromServer();//需异步处理
                            //从本地加载群组列表
//                            List<EMGroup> grouplist = EMClient.getInstance().groupManager().getAllGroups();
                            //添加联系人
                            HashMap<String, EaseUser> contacts = new HashMap<>();
                            for (int i = 0; i <usernames.size() ; i++) {
                                EaseUser user = new EaseUser(usernames.get(i));
                                contacts.put(usernames.get(i),user);
                            }
                            //添加群
                            for (int i = 0; i <grouplist.size() ; i++) {
                                String name = grouplist.get(i).getGroupName();
                                String id = grouplist.get(i).getGroupId();
                                EaseUser user = new EaseUser(name);
                                contacts.put(id,user);
                            }
                            callBack.onSuccess(contacts);
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            callBack.onFail(e.getMessage());
                        }

                    }
                });

    }
}
