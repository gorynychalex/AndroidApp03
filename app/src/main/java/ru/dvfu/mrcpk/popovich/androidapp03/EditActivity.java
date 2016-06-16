package ru.dvfu.mrcpk.popovich.androidapp03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    EditText editText;
    Button buttonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

//        textView = (TextView) findViewById(R.id.textView);

        editText = (EditText) findViewById(R.id.editText);
        buttonEdit = (Button) findViewById(R.id.buttonEdit);


        Intent intent = getIntent();

        editText.setText(intent.getExtras().getString("name"));

//        textView.setText((String) intent.getExtras().getString("name"));
        buttonEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent();
        intent1.putExtra("name", editText.getText().toString());
        setResult(RESULT_OK, intent1);
        finish();
    }
}
