package com.example.bilal.school;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


/**
 * Created by Bilal on 5/7/2017.
 */

public class GPSService extends Service {
    Context _c;
    private static final String TAG = "SysOPSGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 5 * 60 * 1000;
    private static final float LOCATION_DISTANCE = 0;
    private LocationListener listner;

    public GPSService(){
//        super("GPSService");
        _c = this;
    }

    private class LocationListener implements android.location.LocationListener{
        Location mLastLocation;
        public LocationListener(String provider)
        {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }
        @Override
        public void onLocationChanged(Location location) {
        Log.e(TAG, "onLocationChanged: " + location);
//            mLastLocation.set(location);
            try {
                if (location != null) {
                    final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    JSONObject obj = new JSONObject();
                    obj.put("user_id", 1);
                    obj.put("latitude", location.getLatitude());
                    obj.put("longitude", location.getLongitude());
                    Log.i("Lat|long", location.getLatitude() + "," + location.getLongitude());
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    Bitmap photo;
//                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Messenger/received_10208491122103748.jpeg";
//                    photo = BitmapFactory.decodeFile(path);//                    photo.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm
//                    byte[] b = baos.toByteArray();
//                    String encodedImage = Base64.encodeToString(b,
//                            Base64.DEFAULT);
//                    obj.put("base64", "");

                    JsonObjectRequest jsObjRequest = new JsonObjectRequest
                            (Request.Method.POST, "http://gw.sysops.com.pk/tracker/track-api/public/spy", obj, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.i("Json", response.toString());
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i("Error", error.toString());
                                }
                            });
//                    queue.add(jsObjRequest);
                } else {
                    Log.e("Error:", "Null Location");
                }
            } catch (Exception e) {
                Log.e("Error: ", e.toString());
            }
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.e(TAG, "onProviderDisabled: " + provider);
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        @Override
        public void onProviderEnabled(String provider)
        {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Log.e(TAG, "onStatusChanged: " + provider + " " + status);
        }

    }

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }
    @Override
    public void onCreate()
    {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
        listner = new LocationListener(LocationManager.GPS_PROVIDER);
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    listner);
        } catch (SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            try {
                mLocationManager.removeUpdates(listner);
            } catch (Exception ex) {
                Log.i(TAG, "fail to remove location listners, ignore", ex);
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }
//        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


//
//    protected void onHandleIntent(Intent i) {
//        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
////        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
////        final ProgressDialog pd = new ProgressDialog(this);
////        pd.setMessage("Please wait!!");
////        pd.setTitle("Saving Record");
//        try {
//            LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
//            boolean enabled = service
//                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//            if (!enabled) {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            } else {
////                Criteria criteria = new Criteria();
////                criteria.setAccuracy(Criteria.ACCURACY_FINE);
////                criteria.setAltitudeRequired(false);
////                criteria.setBearingRequired(false);
////                criteria.setCostAllowed(true);
////                criteria.setPowerRequirement(Criteria.POWER_LOW);
////                String provider = service.getBestProvider(criteria, true);
////                if (provider != null) {
////                service.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
////                    @Override
////                    public void onLocationChanged(Location location) {
////                        Log.i("Lat,Long", location.getLatitude() + "," + location.getLongitude());
////                    }
////
////                    @Override
////                    public void onStatusChanged(String provider, int status, Bundle extras) {
////
////                    }
////
////                    @Override
////                    public void onProviderEnabled(String provider) {
////
////                    }
////
////                    @Override
////                    public void onProviderDisabled(String provider) {
////
////                    }
////                });
//
//                Location location = service.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                //            pd.show();
//
//                if (location != null) {
//                    JSONObject obj = new JSONObject();
//                    obj.put("user_id", 2);
//                    obj.put("latitude", location.getLatitude());
//                    obj.put("longitude", location.getLongitude());
//                    Log.i("Lat|long", location.getLatitude() + "," + location.getLongitude());
////                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
////                    Bitmap photo;
////                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Messenger/received_10208491122103748.jpeg";
////                    photo = BitmapFactory.decodeFile(path);
////                    photo.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm
////                    byte[] b = baos.toByteArray();
////                    String encodedImage = Base64.encodeToString(b,
////                            Base64.DEFAULT);
//                    obj.put("base64", "");
//
//                    JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                            (Request.Method.POST, "http://192.168.111.37:8080/track-api/public/spy", obj, new Response.Listener<JSONObject>() {
//
//                                @Override
//                                public void onResponse(JSONObject response) {
////                            Log.i("Json", response.toString());
////                            pd.cancel();
//                                }
//                            }, new Response.ErrorListener() {
//
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    // TODO Auto-generated method stub
////                            Log.i("Error", error.toString());
////                            Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_LONG).show();
////                            pd.cancel();
//                                }
//                            });
////                    queue.add(jsObjRequest);
//                } else {
//                    Log.e("Error:", "Null Location");
//                }
////                }
//            }
//        }catch (SecurityException e){
//
//        } catch(Exception e){
////            pd.cancel();
//            Log.e("Error: " , e.toString());
//        }
//    }
}

