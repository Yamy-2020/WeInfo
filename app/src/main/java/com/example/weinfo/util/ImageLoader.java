package com.example.weinfo.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 项目名称：Mvp
 * 作者：Yamy
 * 创建时间：2020/6/2   11:31
 **/
public class ImageLoader {
    /**
     * 设置网络图片
     * @param url
     * @param iv
     */
    public static void setImage(String url, ImageView iv){
        Glide.with(iv.getContext()).load(url).into(iv);
    }

    /**
     *
     * @param resId 设置本地资源
     * @param iv
     */
    public static void setImage( int resId, ImageView iv){
        Glide.with(iv.getContext()).load(resId).into(iv);
    }

    public static void setCircleImage(String url, ImageView iv){
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(iv.getContext()).load(url).apply(options).into(iv);
    }

    public static void setCircleImage(int resId, ImageView iv){
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(iv.getContext()).load(resId).apply(options).into(iv);
    }

    /**
     *
     * @param resId
     * @param iv
     * @param corner 圆角大小
     */
    public static void setRoundedCornerImage(int resId, ImageView iv, int corner){
        //设置圆角
        RequestOptions options = new RequestOptions()
                .transforms(
                        //四个角都需要圆角
                        //RoundedCornersTransformation.CornerType.ALL
                        //左上角
                        //RoundedCornersTransformation.CornerType.TOP_LEFT
                        //右上角
                        //RoundedCornersTransformation.CornerType.TOP_RIGHT
                        new RoundedCornersTransformation(corner,0,
                                RoundedCornersTransformation.CornerType.ALL)
                );
        Glide.with(iv.getContext()).load(resId).apply(options).into(iv);
    }

    /**
     *
     * @param url
     * @param iv
     * @param corner 圆角大小
     */
    public static void setRoundedCornerImage(String url, ImageView iv, int corner){
        //设置圆角
        RequestOptions options = new RequestOptions()
                .transforms(
                        //四个角都需要圆角
                        //RoundedCornersTransformation.CornerType.ALL
                        //左上角
                        //RoundedCornersTransformation.CornerType.TOP_LEFT
                        //右上角
                        //RoundedCornersTransformation.CornerType.TOP_RIGHT
                        new RoundedCornersTransformation(corner,0,
                                RoundedCornersTransformation.CornerType.ALL)
                );
        Glide.with(iv.getContext()).load(url).apply(options).into(iv);
    }
}
