package com.example.weinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weinfo.base.BaseActivity;
import com.example.weinfo.base.Constants;
import com.example.weinfo.presenter.MainPresenter;
import com.example.weinfo.ui.activity.ChatActivity;
import com.example.weinfo.ui.fragment.DiscoveryFragment;
import com.example.weinfo.ui.fragment.MeFragment;
import com.example.weinfo.util.SpUtil;
import com.example.weinfo.util.UiModeUtil;
import com.example.weinfo.view.MainView;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    @BindView(R.id.btn_conversation)
    Button btnConversation;
    @BindView(R.id.unread_msg_number)
    TextView unreadMsgNumber;
    @BindView(R.id.btn_container_conversation)
    RelativeLayout btnContainerConversation;
    @BindView(R.id.btn_address_list)
    Button btnAddressList;
    @BindView(R.id.btn_container_address_list)
    RelativeLayout btnContainerAddressList;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.btn_container_setting)
    RelativeLayout btnContainerSetting;
    @BindView(R.id.main_bottom)
    LinearLayout mainBottom;
    @BindView(R.id.fragment_container)
    RelativeLayout fragmentContainer;
    @BindView(R.id.btn_me)
    Button btnMe;
    @BindView(R.id.btn_container_me)
    RelativeLayout btnContainerMe;
    private EaseConversationListFragment conversationListFragment;
    private EaseContactListFragment contactListFragment;
    private DiscoveryFragment discoveryFragment;
    private Fragment[] fragments;
    private Button[] mTabs;
    private int index;
    private int currentTabIndex;
    private MeFragment meFragment;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    protected void initView() {
//        int mode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        UiModeUtil.setActModeFromSp(this);
        mTabs = new Button[4];
        mTabs[0] = btnConversation;
        mTabs[1] = btnAddressList;
        mTabs[2] = btnSetting;
        mTabs[3] = btnMe;
        // set first tab as selected
        mTabs[0].setSelected(true);

        //会话的fragment
        conversationListFragment = new EaseConversationListFragment();
        //联系人的fragmnet
        contactListFragment = new EaseContactListFragment();

        discoveryFragment = new DiscoveryFragment();
        meFragment = new MeFragment();
        //设置联系人
        //contactListFragment.setContactsMap(getContacts());
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {
//                EMConversation.EMConversationType type = conversation.getType();
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                if (conversation.isGroup()) {
                    //群聊
                    intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId());
                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
                } else {
                    //单聊
                    intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId());
                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                }
                startActivity(intent);
            }
        });
        contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {
            @Override
            public void onListItemClicked(EaseUser user) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                if (user.getChatType() == EaseConstant.CHATTYPE_SINGLE) {
                    intent.putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername());
                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, user.getChatType());
                } else if (user.getChatType() == EaseConstant.CHATTYPE_GROUP) {
                    intent.putExtra(EaseConstant.EXTRA_USER_ID, user.getGroupId());
                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, user.getChatType());
                }
                startActivity(intent);
            }
        });
        fragments = new Fragment[]{conversationListFragment, contactListFragment, discoveryFragment, meFragment};
        // add and show first fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, conversationListFragment)
                .add(R.id.fragment_container, contactListFragment)
                .add(R.id.fragment_container, discoveryFragment)
                .add(R.id.fragment_container, meFragment)
                .hide(contactListFragment)
                .hide(discoveryFragment)
                .hide(meFragment)
                .show(conversationListFragment)
                .commit();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_conversation, R.id.btn_address_list, R.id.btn_setting, R.id.btn_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_conversation:
                index = 0;
                break;
            case R.id.btn_address_list:
                index = 1;
                break;
            case R.id.btn_setting:
                index = 2;
                break;
            case R.id.btn_me:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // set current tab as selected.
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(final Map<String, EaseUser> contacts) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                contactListFragment.setContactsMap(contacts);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
