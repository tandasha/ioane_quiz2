package com.example.ioanetandashvili_quiz_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnRead, btnClear;
    EditText title;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);


        title = (EditText)findViewById(R.id.title);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = title.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_Title, name);
                contentValues.put(DBHelper.KEY_Comp, 1);
                contentValues.put(DBHelper.KEY_User_ID, 1);

                database.insert(DBHelper.TABLE, null, contentValues);
                break;


            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int titleIndex = cursor.getColumnIndex(DBHelper.KEY_Title);
                    int UserIndex = cursor.getColumnIndex(DBHelper.KEY_User_ID);
                    int compIndex = cursor.getColumnIndex(DBHelper.KEY_Comp);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", title = " + cursor.getString(titleIndex) +
                                ", User Index = " + cursor.getString(UserIndex) +
                                ", compIied = " + cursor.getString(compIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();
                Toast.makeText(this, "Check console", Toast.LENGTH_SHORT).show();;
                break;
        }
        dbHelper.close();
    }


    public void click(View v){
        getPost();
    };
    public  void getPost(){
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        JsonObjectRequest jsor = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(jsor);
    }

}
