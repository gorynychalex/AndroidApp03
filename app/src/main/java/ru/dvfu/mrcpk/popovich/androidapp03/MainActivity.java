package ru.dvfu.mrcpk.popovich.androidapp03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int REQUEST_CODE_NAME = 1;

    String[] names = { "Иванов Иван", "Петров Петр", "Алексеев Алексей" };
    ListView listView;
    Button buttonEdit;
    ArrayAdapter<String> adapterNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Определение списка из макета activity_main.xml
        listView = (ListView) findViewById(R.id.listView);

        // Установка режима выбора пунктов списка (по одному)
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //Создание адаптера из массива names для назначения их списку (напрямую нельзя!)
        adapterNames = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, names);
        listView.setAdapter(adapterNames);

        //Объект кнопки редактирования
        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(this);


        //Обработка выбора пункта списка (НЕОБЯЗАТЕЛЬНАЯ ЧАСТЬ)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,names[listView.getCheckedItemPosition()] + ", pos = " + position + ", id = " + id,Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,names[listView.getCheckedItemPosition()] + ", pos = " + position + ", id = " + id,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonEdit:

                //Создание объекта "намерение" для порождения нового активити - EditActivity
                Intent intent = new Intent(this, EditActivity.class);

                // Работа с элементом списка. Если не выбран ни один пунк - выдать сообщение (Toast)
                if(listView.getCheckedItemPosition() != -1) {
                    intent.putExtra("name", names[listView.getCheckedItemPosition()]);
                    Toast.makeText(this, names[listView.getCheckedItemPosition()], Toast.LENGTH_LONG).show();
//                startActivity(intent);
                    startActivityForResult(intent, REQUEST_CODE_NAME);
                } else {
                    Toast.makeText(this, "Выберите пунк списка", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(data == null) return;
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE_NAME) {
                String nameResult = data.getExtras().getString("name");
                names[listView.getCheckedItemPosition()]=nameResult;
                Toast.makeText(MainActivity.this, nameResult, Toast.LENGTH_LONG).show();
                listView.setAdapter(adapterNames);
//                textViewName.setText(nameResult);
            }
        }

    }
}
