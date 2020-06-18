package com.example.weinfo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weinfo.R;
import com.example.weinfo.bean.DailyNewsBean;
import com.example.weinfo.util.TimeUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   16:20
 **/
public class DailyNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<DailyNewsBean.TopStoriesBean> topList;
    private ArrayList<DailyNewsBean.StoriesBean> list;
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_DATE = 1;
    public static final int TYPE_NEWS = 2;
    private String date = "今日新闻";
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public DailyNewsAdapter(Context context, ArrayList<DailyNewsBean.TopStoriesBean> topList, ArrayList<DailyNewsBean.StoriesBean> list) {
        this.context = context;
        this.topList = topList;
        this.list = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //viewType:这个是系统帮我们调用getItemViewType之后的返回值
        if (viewType == TYPE_BANNER) {
            //parent要给,不要给null,如果给了null,条目水平方向撑不满
            return new BannerVH(inflater.inflate(R.layout.item_banner, parent, false));
        } else if (viewType == TYPE_DATE) {
            return new DateVH(inflater.inflate(R.layout.item_date, parent, false));
        } else {
            return new NewsVH(inflater.inflate(R.layout.item_news, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == TYPE_BANNER) {
            BannerVH bannerVH = (BannerVH) holder;
            bannerVH.banner.setImages(topList)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            //path 类型是mBannerList 的泛型
                            DailyNewsBean.TopStoriesBean bean =
                                    (DailyNewsBean.TopStoriesBean) path;
                            Glide.with(context).load(bean.getImage()).into(imageView);
                        }
                    })
                    .start();
        } else if (itemViewType == TYPE_DATE) {
            DateVH dateVH = (DateVH) holder;
            dateVH.tvDate.setText(date);
        } else {
            NewsVH newsVH = (NewsVH) holder;
            int p = position - 1;
            if (topList.size() > 0) {
                p -= 1;
            }
            DailyNewsBean.StoriesBean bean = list.get(p);
            List<String> images = bean.getImages();
            if (images != null && images.size() > 0) {
                Glide.with(context).load(images.get(0)).into(newsVH.iv);
            }
            newsVH.tv.setText(bean.getTitle());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (onItemClickListener!=null){
                   onItemClickListener.onItemClick(position);
               }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (topList.size() > 0) {
            //有banner
            if (position == 0) {
                return TYPE_BANNER;
            } else if (position == 1) {
                return TYPE_DATE;
            } else {
                return TYPE_NEWS;
            }
        } else {
            if (position == 0) {
                return TYPE_DATE;
            } else {
                return TYPE_NEWS;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (topList.size() > 0) {
            return list.size() + 1 + 1;
        } else {
            return list.size() + 1;
        }
    }

    public void setData(DailyNewsBean dailyNewsBean) {
        date = dailyNewsBean.getDate();
        if (TimeUtil.checkTimeIsTotay(dailyNewsBean.getDate())) {
            date = "今日新闻";
        }
        topList.clear();
        list.clear();
        if (dailyNewsBean.getTop_stories() != null && dailyNewsBean.getTop_stories().size() > 0) {
            topList.addAll(dailyNewsBean.getTop_stories());
        }
        if (dailyNewsBean.getStories() != null && dailyNewsBean.getStories().size() > 0) {
            list.addAll(dailyNewsBean.getStories());
        }
        notifyDataSetChanged();
    }
    class BannerVH extends RecyclerView.ViewHolder {
        public Banner banner;

        public BannerVH(View itemView) {
            super(itemView);
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }

    class DateVH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;

        DateVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class NewsVH extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv)
        TextView tv;

        NewsVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
