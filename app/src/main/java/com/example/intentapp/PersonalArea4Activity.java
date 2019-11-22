package com.example.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalArea4Activity extends AppCompatActivity {
    private static final String SAVED_PARCEL = "PARCEL";
    private static final String SAVED_RECIEVED_PARCEL = "RECIVED_PARCEL";
    private TextView mGeneratedTextView;
    private TextView mRecievedTextView;
    private Button mButton;
    private StringBuilder mStringBuilder;
    private TestModel mGeneretedTestModel;
    private TestModel mReceivedTestModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);
        mStringBuilder = new StringBuilder();
        mGeneratedTextView = findViewById(R.id.personal_area_text_view);
        mRecievedTextView = findViewById(R.id.personal_area_message);
        mButton = findViewById(R.id.personal_area_button);


        mButton.setOnClickListener(this::onClick);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mGeneretedTestModel = new TestModel();
        buildTestModelString(mGeneretedTestModel);
        mGeneratedTextView.setText(mStringBuilder);

        if (getIntent().getExtras() != null) {
            mReceivedTestModel = getIntent().getExtras().getParcelable(SAVED_PARCEL);
            if (mReceivedTestModel != null) {
                buildTestModelString(mReceivedTestModel);
                mRecievedTextView.setText(mStringBuilder);
            }
        }
    }

    private void onClick(@NonNull View view) {
        Intent intent = new Intent(this, Catalog1Activity.class);
        intent.putExtra(SAVED_PARCEL, mGeneretedTestModel);
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
        outState.putParcelable(SAVED_PARCEL, mGeneretedTestModel);
        outState.putParcelable(SAVED_RECIEVED_PARCEL, mReceivedTestModel);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mGeneretedTestModel = savedInstanceState.getParcelable(SAVED_PARCEL);
        mReceivedTestModel = savedInstanceState.getParcelable(SAVED_RECIEVED_PARCEL);

        buildTestModelString(mGeneretedTestModel);
        mGeneratedTextView.setText(mStringBuilder);
        buildTestModelString(mReceivedTestModel);
        mRecievedTextView.setText(mStringBuilder);
    }
}
