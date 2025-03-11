package com.example.trivia;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher;
    private  FbModule fbModule;

    private ConstraintLayout ll;
    private Button btnSetting;
    private String backgroundcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.main);

        fbModule = new FbModule(this);

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == RESULT_OK)
                        {
                            Intent data = o.getData();
                            String str = data.getStringExtra("color");
                            fbModule.writeBackgroundColorToFb(str);

                        }
                        //הפעולה מקבלת תשובה (של הצבע שהמשתמש בחר.) מה- MainActivity, והיא שומרת את הצבע ב - FireBase
                    }
                });

    }

    public void onClickStart(View view) {
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("color",backgroundcolor);
        startActivity(intent);

    }
    //בעזרת ה - Intent שיצרנו עוברים לדף המשחק

    public void onClickSetting(View view) {
        Intent i = new Intent(this,SettingActivity.class);
        resultLauncher.launch(i);
    }
    //בעזרת ה - Intent שיצרנו עוברים לדף ההגדרות

    public void onClickInstruction(View view)
    {
        Intent I = new Intent(this,InstructionActivity.class);
        resultLauncher.launch(I);
        I.putExtra("color",backgroundcolor);
    }

    public void setNewColorFromFb(String str) {
        //הפיירבייס קורא לפעולה בפעם הראשונה
        //ואחרי כל פעם שהמשתמש משנה את הצבע

        backgroundcolor = str;
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        setBackgroundColor(str);
    }
    //הפעולה משנה את צבע הרקע בהתאם למה שכתוב ב - FireBase
    public void setBackgroundColor(String color)
    {
        switch (color)
        {
            case "Red":
            {
                ll.setBackgroundColor(Color.RED);
                break;
            }
            case "Blue":
            {
                ll.setBackgroundColor(Color.BLUE);
                break;
            }
            case "Pink":
            {
                ll.setBackgroundColor(Color.argb(255,255,192,203));
                break;
            }
            case "Yellow":
            {
                ll.setBackgroundColor(Color.YELLOW);
                break;
            }
            default:
                ll.setBackgroundColor(Color.WHITE);
        }
    }
    //הפעולה מקבלת - Color מהצומת - "Color" , ומתקיים פה משפט Switch לכלכ מקרה של צבע
}