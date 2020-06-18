package com.example.weinfo.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.weinfo.net.TouchCallBack;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/18   12:09
 **/
public class ItemTouchHelperCallBack extends ItemTouchHelper.Callback {
    private TouchCallBack callBack;
    private boolean mDrag = true;
    private boolean mSwipe = true;

    public ItemTouchHelperCallBack(TouchCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 返回可以滑动的方向
     * 一般使用makeMovementFlags(int,int)或makeFlag(int, int)来构造我们的返回值
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //允许上下滑
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //允许向左滑
        int swipe = ItemTouchHelper.LEFT;
        return makeMovementFlags(drag, swipe);
    }

    /**
     * 上下拖动item时回调,
     * 可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，
     * 最后返回true，表示被拖动的ViewHolder已经移动到了目的位置
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        callBack.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * 当用户左右滑动item时达到删除条件就会调用,一般为一半,条目继续滑动删除,
     * 否则弹回
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        callBack.onItemDelete(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return mDrag;
    }

    //是否允许上下拖拽
    public void setDragEnable(boolean dragEnable) {
        this.mDrag = dragEnable;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return mSwipe;
    }

    public void setSwipeEnabled(boolean b) {
        this.mSwipe = b;
    }
}
