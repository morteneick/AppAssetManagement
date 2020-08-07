package com.example.asset_management.recycleView;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecycleActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void getConnection(){
    RecycleActivity test = new RecycleActivity();
    test.jsonParse("https://gist.githubusercontent.com/Dziersan/1766cd6c4ab4d61555e63cb34478d888/raw/bddf90ce241a4632cd84d0046866f2cd91367c8b/0device.json");
        System.out.println(test.test);
    }
}
