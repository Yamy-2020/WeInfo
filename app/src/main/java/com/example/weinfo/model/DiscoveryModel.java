package com.example.weinfo.model;

import com.example.weinfo.R;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.BaseModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.LogUtil;
import com.example.weinfo.util.ThreadManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMGroupOptions;
import com.hyphenate.exceptions.HyphenateException;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/11   20:26
 **/
public class DiscoveryModel extends BaseModel {
    public void addFriend(final String friendName, final CallBack<String> callBack) {
        ThreadManager.getInstance()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        //参数为要添加的好友的username和添加理由
                        try {
                            EMClient.getInstance().contactManager().addContact(friendName, "做个朋友，好不好嘛");
                            callBack.onSuccess(BaseApp.getInstance().getString(R.string.add_success));
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            callBack.onFail(BaseApp.getInstance().getString(R.string.add_fail));
                            LogUtil.logd("添加失败", e.getMessage());
                        }
                    }
                });
    }

    public void createGroup(final String name, final CallBack<String> callBack) {
        ThreadManager.getInstance()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * 创建群组
                         * @param groupName 群组名称
                         * @param desc 群组简介
                         * @param allMembers 群组初始成员，如果只有自己传空数组即可
                         * @param reason 邀请成员加入的reason
                         * @param option 群组类型选项，可以设置群组最大用户数(默认200)及群组类型@see {@link EMGroupStyle}
                         *               option.inviteNeedConfirm表示邀请对方进群是否需要对方同意，默认是需要用户同意才能加群的。
                         *               option.extField创建群时可以为群组设定扩展字段，方便个性化订制。
                         * @return 创建好的group
                         * @throws HyphenateException
                         */
                        EMGroupOptions option = new EMGroupOptions();
                        option.maxUsers = 200;
                        option.style = EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;
/**
 * EMGroupStyle：
 *EMGroupStylePrivateOnlyOwnerInvite——私有群，只有群主可以邀请人；
 *EMGroupStylePrivateMemberCanInvite——私有群，群成员也能邀请人进群；
 *EMGroupStylePublicJoinNeedApproval——公开群，加入此群除了群主邀请，只能通过申请加入此群；
 *EMGroupStylePublicOpenJoin ——公开群，任何人都能加入此群。
 */
                        try {
                            EMGroup group = EMClient.getInstance().groupManager().createGroup(name, "不就加个群么，磨叽啥", new String[]{}, "想进就进", option);
                            LogUtil.print("id:" + group);
                            callBack.onSuccess("创建成功");
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            callBack.onFail("创建失败");
                        }
                    }
                });
    }

    public void addGroup(final String group, final CallBack<String> callBack) {
        ThreadManager.getInstance()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        //如果群开群是自由加入的，即group.isMembersOnly()为false，直接join
                        try {
                            EMClient.getInstance().groupManager().joinGroup(group);//需异步处理
                            callBack.onSuccess("加入成功");
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            callBack.onFail("加入失败");
                            LogUtil.print(e.getMessage());
                        }
//需要申请和验证才能加入的，即group.isMembersOnly()为true，调用下面方法
//                        EMClient.getInstance().groupManager().applyJoinToGroup(groupid, "求加入");//需异步处理
                    }
                });
    }
}
