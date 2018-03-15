package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Creates a contact
 */
public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, bNum, adr, prov;
    private Spinner bType;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        bNum = (EditText) findViewById(R.id.bNum);
        bType = (Spinner)findViewById(R.id.bType);
        adr = (EditText) findViewById(R.id.address);
        prov = (EditText) findViewById(R.id.province);
    }

    /**
     * Description: Creates new business with info on screen
     * @param v Current view
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        //String personID = appState.firebaseReference.push().getKey();
        String businessNum = bNum.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = bType.getSelectedItem().toString();
        String address = adr.getText().toString();
        String province = prov.getText().toString();
        Contact person = new Contact(businessNum, name, primaryBusiness, address, province);
        if(businessNum.length() != 9 || name.length() < 2 || name.length() > 48 || address.length() >= 50 || province.length() != 2)
            return;
        if(province.equalsIgnoreCase("NS") ||
                province.equalsIgnoreCase("AB") ||
                province.equalsIgnoreCase("BC") ||
                province.equalsIgnoreCase("MB") ||
                province.equalsIgnoreCase("NB") ||
                province.equalsIgnoreCase("NL") ||
                province.equalsIgnoreCase("NT") ||
                province.equalsIgnoreCase("NU") ||
                province.equalsIgnoreCase("ON") ||
                province.equalsIgnoreCase("PE") ||
                province.equalsIgnoreCase("QC") ||
                province.equalsIgnoreCase("SK") ||
                province.equalsIgnoreCase("YT")
                ) {
            appState.firebaseReference.child(businessNum).setValue(person);
        }

        finish();

    }
}
