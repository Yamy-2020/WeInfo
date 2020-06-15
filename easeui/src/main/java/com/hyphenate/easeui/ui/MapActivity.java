package com.hyphenate.easeui.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.baidumap.PoiOverlay;

/**
 * 定位问题：
 * 1.签名文件没配
 * 2.模拟器
 * 3.没开定位
 * 4.设备问题
 * <p>
 * 定位到了几内亚湾:
 * 1.签名文件没有配置
 * 2.位置信息开关没开
 * 3.你用了模拟器
 * 4.设备有问题
 */
/**
 * 定位到了几内亚湾:
 * 1.签名文件没有配置
 * 2.位置信息开关没开
 * 3.你用了模拟器
 * 4.设备有问题
 */

/**
 * 两个用户A, B
 * 结合环信和地图,完成共享位置
 * 对于A来说
 * 1.地图功能,获取A自己位置,获取到之后发送给好友B
 * 2.好友B位置也需要获取到:
 *
 * 对B来说:
 * 1.地图功能,获取B自己位置,获取到之后发送给好友A
 * 2.好友A位置也需要获取到:
 *
 * //页面要求的功能如下:
 * 1.定位自己的位置,将位置发送给好友
 * 2.接收好友位置消息,要求消息不能再聊天页面展示(因为这个是位置消息)
 * 3.收到好友位置后展示marker
 */
public class MapActivity extends AppCompatActivity implements View.OnClickListener {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private Button btnLocate;
    private LatLng userLatLng;
    private EditText et;
    private Button btnSearch;
    private PoiSearch poiSearch = PoiSearch.newInstance();
//    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initView();
//        BaiduMapOptions options = new BaiduMapOptions();
//        options.mapType(18);
//        mMapView = new MapView(this, options);
//        setContentView(mMapView);
        mBaiduMap = mMapView.getMap();
        //设置地图缩放级别
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        //移动到上海
//        LatLng GEO_SHANGHAI = new LatLng(31.227, 121.481);
//        goLatLng(GEO_SHANGHAI);
        //定位
        locate();
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override//点地图
            public void onMapClick(LatLng latLng) {
                Toast.makeText(MapActivity.this, "被点了", Toast.LENGTH_SHORT).show();
                addMarker(latLng);
            }

            @Override//点覆盖物
            public boolean onMapPoiClick(MapPoi mapPoi) {
                Toast.makeText(MapActivity.this, mapPoi.getName() + "被点了", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapActivity.this, "marker被点击了:" + marker.getPosition(), Toast.LENGTH_SHORT).show();
                //地图覆盖物点击事件
                //发起步行导航
                walkNavi(userLatLng, marker.getPosition());
                return false;
            }
        });
    }

    private void walkNavi(final LatLng start, final LatLng end) {
        // 获取导航控制类
        // 引擎初始化
        WalkNavigateHelper.getInstance().initNaviEngine(this,
                new IWEngineInitListener() {
                    @Override
                    public void engineInitSuccess() {
                        //引擎初始化成功的回调
                        //发起算路
                        routeWalkPlanWithParam(start, end);
                    }

                    @Override
                    public void engineInitFail() {
                        //引擎初始化失败的回调
                    }
                });
    }
    //发起算路
    private void routeWalkPlanWithParam(LatLng start, LatLng end) {
        //起终点位置
        //构造WalkNaviLaunchParam
        WalkNaviLaunchParam mParam = new WalkNaviLaunchParam().stPt(start).endPt(end);
        //发起算路
        WalkNavigateHelper.getInstance().routePlanWithParams(mParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                //开始算路的回调
            }

            @Override
            public void onRoutePlanSuccess() {
                //算路成功
                //跳转至诱导页面
                Intent intent = new Intent(MapActivity.this, WNaviGuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError walkRoutePlanError) {
                //算路失败的回调
            }
        });

    }
    private void addMarker(LatLng latLng) {
        //定义Maker坐标点
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .icon(bitmap)
                .animateType(MarkerOptions.MarkerAnimateType.drop);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    private void locate() {
        mBaiduMap.setMyLocationEnabled(true);
        //定位初始化
        mLocationClient = new LocationClient(this);
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();
    }

    private void initView() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        btnLocate = (Button) findViewById(R.id.btn_locate);
        btnLocate.setOnClickListener(this);
        et = (EditText) findViewById(R.id.et);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //easeUi作为library不允许使用switch
        int id = v.getId();
        if (R.id.btn_locate ==id ) {
            goLatLng(userLatLng);
        }else if (R.id.btn_search ==id ) {
            search();
        }
    }

    private void search() {
        String content = et.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
            return;
        }
        //1.创建POI检索实例
        //new 一次就行了,所以放到外面去
        //mPoiSearch = PoiSearch.newInstance();
        //2.创建POI检索监听器
        //3.设置检索监听器
        poiSearch.setOnGetPoiSearchResultListener(listener);
        //4.设置PoiCitySearchOption，发起检索请求
        //城市搜索,周边搜索,区域搜索
        /**
         * 以我为中心，搜索半径10000米以内的餐厅
         */
        if (userLatLng != null) {
            poiSearch.searchNearby(new PoiNearbySearchOption()
                    .location(userLatLng)
                    .radius(100000)
                    .keyword(content)
                    .pageNum(10));
        }
    }

    //创建POI检索监听器
    OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            //检索结果覆盖物
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                mBaiduMap.clear();

                //创建PoiOverlay对象
                PoiOverlay poiOverlay = new PoiOverlay(mBaiduMap);

                //设置Poi检索数据
                poiOverlay.setData(poiResult);

                //将poiOverlay添加至地图并缩放至合适级别
                poiOverlay.addToMap();
                poiOverlay.zoomToSpan();
            }else {
                Toast.makeText(MapActivity.this, "未搜索到内容", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }

        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //BDLocation未知参数
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            //location.getLatitude()纬度
            //location.getLongitude()经度
            userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            mBaiduMap.setMyLocationData(locData);
        }
    }

    private void goLatLng(LatLng latLng) {
        if (latLng != null) {
            MapStatusUpdate status2 = MapStatusUpdateFactory.newLatLng(latLng);
            mBaiduMap.setMapStatus(status2);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        //释放检索实例
        poiSearch.destroy();
    }
}