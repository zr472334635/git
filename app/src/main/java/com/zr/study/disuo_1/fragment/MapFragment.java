package com.zr.study.disuo_1.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zr.study.disuo_1.Activity.leftmenu.function.RentalActivity;
import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.bean.LockPointEntity;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import static com.zr.study.disuo_1.global.GlobalContants.LOAD_URL;

/**
 * Created by 土豆泥 on 2017/8/28.
 */

public class MapFragment extends Fragment implements AMap.OnMyLocationChangeListener, AMap.OnMapClickListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter, INaviInfoCallback {
    private MapView mMapView;
    private AMap aMap;

    String[] PLNo;
    String[] PLgroupNo;
    String[] DamagedCondition;
    String[] Takenup;
    String[] ExpiringDate;
    String[] Profit;
    String[] Booked;
    String[] Longitude;
    String[] Latitude;
    String[] Position;

    private ArrayList<LockPointEntity> list;
    int locknumbers;


    private SharedPreferences sp;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) view.findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        PLNo = new String[100];
        PLgroupNo = new String[100];
        DamagedCondition = new String[100];
        Takenup = new String[100];
        ExpiringDate = new String[100];
        Profit = new String[100];
        Booked = new String[100];
        Longitude = new String[100];
        Latitude = new String[100];
        Position = new String[100];
        download();

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    public void download() {
        RequestParams params = new RequestParams(LOAD_URL + "/servlet/LockPointServlet");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Json的解析类对象
                JsonParser parser = new JsonParser();
                //将JSON的String 转成一个JsonArray对象
                JsonArray jsonArray = parser.parse(result).getAsJsonArray();
                //解析result
                Log.e("TAG", "onSuccess" + result);
                Gson gson = new Gson();
                ArrayList<LockPointEntity> list = new ArrayList<>();
                for (JsonElement model : jsonArray) {
                    LockPointEntity lockPointEntity = gson.fromJson(model, LockPointEntity.class);
                    list.add(lockPointEntity);
                }


                locknumbers = list.size();
                for (int i = 0; i < locknumbers; i++) {
                    PLNo[i] = list.get(i).getPLNo();
                    PLgroupNo[i] = list.get(i).getPLgroupNo();
                    DamagedCondition[i] = list.get(i).getDamagedCondition();
                    Takenup[i] = list.get(i).getTakenup();
                    ExpiringDate[i] = list.get(i).getExpiringDate();
                    Profit[i] = list.get(i).getProfit();
                    Booked[i] = list.get(i).getBooked();
                    Longitude[i] = list.get(i).getLongitude();
                    Latitude[i] = list.get(i).getLatitude();
                    Position[i] = list.get(i).getPosition();
                }


                Log.e("TAG", "onSuccess" + list);

                initMapService();


            }

            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError" + ex.getMessage());
                Toast.makeText(getContext(), "200", Toast.LENGTH_SHORT).show();

            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished");
            }
        });
    }

    private void initMapService() {

        AMap aMap = null;
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。\
        aMap.moveCamera(CameraUpdateFactory.zoomTo(12));

        aMap.setOnMyLocationChangeListener(this);


        getMarker(aMap);


    }

    @Override
    public void onMyLocationChange(Location location) {
        if (location != null) {
            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());

            Bundle bundle = location.getExtras();

            sp = getActivity().getSharedPreferences("location", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("latitude", String.valueOf(location.getLatitude()));
            editor.putString("longitude", String.valueOf(location.getLongitude()));
            editor.commit();


            if (bundle != null) {
                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);

                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
            } else {
                Log.e("amap", "定位信息， bundle is null ");
            }
        } else {
            Log.e("amap", "定位失败");
        }


    }




    public void getMarker(AMap aMap) {
        for (int i = 0; i < locknumbers; i++) {
            LatLng latLng = new LatLng(Double.parseDouble(Longitude[i]), Double.parseDouble(Latitude[i]));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("地锁号：" + PLNo[i]);
            markerOptions.snippet("地点：" + Position[i] + "\n");

            final Marker marker = aMap.addMarker(markerOptions);
            marker.setObject(PLNo[i]);
        }
        AMap.OnInfoWindowClickListener listener = new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getObject() != null) {
                    String m = (String) marker.getObject();
                    Intent intent = new Intent(getActivity(), RentalActivity.class);
                    intent.putExtra("plno", m);
                    getActivity().startActivity(intent);
                }
                return;
            }
        };
        aMap.setOnInfoWindowClickListener(listener);


    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }


}
