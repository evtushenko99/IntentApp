package com.example.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Map2Activity extends AppCompatActivity {
    private static final String SAVED_PARCEL = "PARCEL";
    private static final String SAVED_RECIEVED_PARCEL = "RECIVED_PARCEL";
    private final String TAG = this.getClass().getName();
    private TextView mGenerstedTextView;
    private TextView mRecievedTextView;
    private StringBuilder mStringBuilder;
    private Button mButton;
    private TestModel mGeneratedTestModel;
    private TestModel mReceivedTestModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mStringBuilder = new StringBuilder();

        mGenerstedTextView = findViewById(R.id.map_text_view);
        mRecievedTextView = findViewById(R.id.map_message);
        mButton = findViewById(R.id.map_button);

        mButton.setOnClickListener(this::onClick);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mGeneratedTestModel = new TestModel();
        buildTestModelString(mGeneratedTestModel);
        mGenerstedTextView.setText(mStringBuilder);

        if (getIntent().getExtras() != null) {
            mReceivedTestModel = getIntent().getExtras().getParcelable(SAVED_PARCEL);
            if (mReceivedTestModel != null) {
                buildTestModelString(mReceivedTestModel);
                mRecievedTextView.setText(mStringBuilder);
            }
        }
    }

    private void onClick(@NonNull View view) {
        Intent intent = new Intent(Map2Activity.this, Search3Activity.class);
        intent.putExtra(SAVED_PARCEL, mGeneratedTestModel);
        startActivity(intent);
    }


    private void buildTestModelString(TestModel testModel) {
        mStringBuilder.delete(0, mStringBuilder.length());
        mStringBuilder.append(testModel.getString1());
        mStringBuilder.append(testModel.getString2());
        mStringBuilder.append(testModel.getObjectList1());
        mStringBuilder.append("\n");
        mStringBuilder.append(testModel.getObjectList2());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_PARCEL, mGeneratedTestModel);
        outState.putParcelable(SAVED_RECIEVED_PARCEL, mReceivedTestModel);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mGeneratedTestModel = savedInstanceState.getParcelable(SAVED_PARCEL);
        mReceivedTestModel = savedInstanceState.getParcelable(SAVED_RECIEVED_PARCEL);
        buildTestModelString(mGeneratedTestModel);
        mGenerstedTextView.setText(mStringBuilder);
        buildTestModelString(mReceivedTestModel);
        mRecievedTextView.setText(mStringBuilder);
    }

}

