package com.mauriciotogneri.greencoffeeexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mauriciotogneri.greencoffeeexample.R;
import com.mauriciotogneri.greencoffeeexample.database.ContactDatabase;
import com.mauriciotogneri.greencoffeeexample.model.Contact;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity
{
    private static final String PARAMETER_CONTACT_ID = "parameter.contact.id";

    public static Intent create(Context context, String contactId)
    {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(PARAMETER_CONTACT_ID, contactId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        setTitle(R.string.details_title);

        String contactId = getIntent().getStringExtra(PARAMETER_CONTACT_ID);

        ContactDatabase contactDatabase = new ContactDatabase();
        Contact contact = contactDatabase.contact(contactId);

        TextView contactName = findViewById(R.id.contact_detail_name);
        contactName.setText(contact.name());

        TextView contactAge = findViewById(R.id.contact_detail_age);
        contactAge.setText(contact.age());

        TextView contactWeight = findViewById(R.id.contact_detail_weight);
        contactWeight.setText(contact.weight());

        TextView contactMarried = findViewById(R.id.contact_detail_married);
        contactMarried.setText(contact.married(this));
    }
}