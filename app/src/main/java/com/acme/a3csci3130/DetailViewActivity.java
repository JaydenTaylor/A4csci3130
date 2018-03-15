package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Activity to allow user to update or delete data
 */
public class DetailViewActivity extends Activity {
    private EditText nameField, bNum, adr, prov;
    private Spinner bType;
    private MyApplicationData appState;
    Contact receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Business");
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        bNum = (EditText) findViewById(R.id.bNum);
        bType = (Spinner) findViewById(R.id.bType);
        adr = (EditText) findViewById(R.id.address);
        prov = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            bNum.setText(receivedPersonInfo.businessNum);
            adr.setText(receivedPersonInfo.address);
            prov.setText(receivedPersonInfo.prov);

            int arrayPos = 0;
            switch(receivedPersonInfo.primaryBusiness) {
                case "Fisher":
                    arrayPos = 1;
                    break;
                case "Distributor":
                    arrayPos = 2;
                    break;
                case "Processor":
                    arrayPos = 3;
                    break;
                case "Fish Monger":
                    arrayPos = 4;
                    break;
            }

            bType.setSelection(arrayPos);

        }
    }

    /**
     * Description: Updates current Business with information on screen
     * @param v Current view
     */
    public void updateContact(View v){
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
            appState.firebaseReference.child(receivedPersonInfo.businessNum).removeValue();
            appState.firebaseReference.child(businessNum).setValue(person);
        }
        finish();
    }

    /**
     * Description: Erases current selected business
     * @param v Current view
     */
    public void eraseContact(View v) {
        appState.firebaseReference.child(receivedPersonInfo.businessNum).removeValue();
        finish();
    }
}
