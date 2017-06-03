package com.example.saber.jsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      {}---JSONObject 对象   []---JSONArray 数组

//        {
//            "data": {
//            "info": [
//            {
//                "bmi": "0",
//                 "contents": "22233",
//                 "time": "2015-05-14",
//                  "title": "2"
//            },
//            {
//                "bmi": "0",
//                 "contents": "new",
//                 "time": "2015-05-14",
//                 "title": "33"
//            }
//            ]
//        },
//            "ret": 0,
//             "msg": "ok"
//        }


        /**
         * 封装JSON
         */
        //1.首先，有三个JSONObject嵌套，一个JSONArray。 最外层一个JSONObject对象，data的一个JSONObject对象，info的JSONArray里每一个元素都是一个JSONObject对象
        //2.封装顺序从里向外，先封装数组中的JSONObject对象，然后用JSONArray.put(JSONObjrct).put()...封装到info的JSONArray数组中。
        //3.然后用data的JSONObject.put("info",JSONArray),封装到data中。
        //4.最外层的JSONObject封装。根据每个索引put各类型数据进去即可。

        JSONObject json = new JSONObject();//最外层
        try {
            JSONObject jsonObject = new JSONObject();//data层
            JSONArray info = new JSONArray();//info层

            JSONObject jsonObjectInfo = new JSONObject();//info层里面的JSONObject
            jsonObjectInfo.put("bmi","0");
            jsonObjectInfo.put("contents","22233");
            jsonObjectInfo.put("time","2015-05-14");
            jsonObjectInfo.put("title","2");
            info.put(jsonObjectInfo);

            JSONObject jsonObjectInfo1 = new JSONObject();
            jsonObjectInfo1.put("bmi","0");
            jsonObjectInfo1.put("contents","new");
            jsonObjectInfo1.put("time","2015-05-14");
            jsonObjectInfo1.put("title","33");
            info.put(jsonObjectInfo1);
            jsonObject.put("info",info);
            json.put("data",jsonObject);
            json.put("ret",0);
            json.put("msg","ok");

            Log.d(TAG, "onCreate: "+json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /**
         * 解析JSON
         */
        try {
            JSONObject json1 = new JSONObject(json.toString());
            JSONObject data = json1.getJSONObject("data");
            JSONArray info = data.getJSONArray("info");
            for(int i=0;i<info.length();i++){
                Log.d(TAG, "----------------------------------------");
                JSONObject obj = info.getJSONObject(i);
                Log.d(TAG, "bmi:"+obj.getString("bmi"));
                Log.d(TAG, "contents: "+obj.getString("contents"));
                Log.d(TAG, "time: "+obj.getString("time"));
                Log.d(TAG, "title: "+obj.getString("title"));
            }
            Log.d(TAG, "ret: "+json1.getString("ret"));
            Log.d(TAG, "msg: "+json1.getString("msg"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
