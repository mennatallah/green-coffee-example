package com.mauriciotogneri.greencoffeeexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mauriciotogneri.greencoffeeexample.R;
import com.mauriciotogneri.greencoffeeexample.adapters.ContactAdapter;
import com.mauriciotogneri.greencoffeeexample.database.ContactDatabase;
import com.mauriciotogneri.greencoffeeexample.model.Contact;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ContactListActivity extends AppCompatActivity
{
    private static final String PARAMETER_USERNAME = "parameter.username";

    public static Intent create(Context context, String username)
    {
        Intent intent = new Intent(context, ContactListActivity.class);
        intent.putExtra(PARAMETER_USERNAME, username);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts);
        setTitle(R.string.contacts_title);

        String username = getIntent().getStringExtra(PARAMETER_USERNAME);

        ContactDatabase contactDatabase = new ContactDatabase();
        List<Contact> contacts = contactDatabase.contacts(username);

        ListView listView = findViewById(R.id.contacts_list);

        if (!contacts.isEmpty())
        {
            listView.setAdapter(new ContactAdapter(this, contacts));
            listView.setOnItemClickListener((parent, view, position, id) ->
                                            {
                                                Contact contact = (Contact) parent.getItemAtPosition(position);
                                                onContactSelected(contact);
                                            });
        }
        else
        {
            listView.setVisibility(View.GONE);

            TextView labelEmptyList = findViewById(R.id.contacts_label_emptyList);
            labelEmptyList.setVisibility(View.VISIBLE);
        }
    }

    private void onContactSelected(Contact contact)
    {
        startActivity(DetailsActivity.create(this, contact.id()));
    }
}