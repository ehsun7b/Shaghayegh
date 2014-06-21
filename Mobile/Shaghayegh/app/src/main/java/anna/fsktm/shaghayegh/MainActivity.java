package anna.fsktm.shaghayegh;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    private static final String SERVICE = "service/contact/save";
    private Button btnSend;
    private EditText txtServer;
    private TextView txvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getViews();
        setListeners();
    }

    private void setListeners() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBtnSend(view);
            }
        });
    }

    private void onClickBtnSend(View view) {
        String serverAddress = txtServer.getText().toString().trim();

        if (serverAddress.length() <= 0) {
            Toast.makeText(this, R.id.please_enter_server_address, Toast.LENGTH_LONG).show();
        } else {
            new SendNameAndNumberTask().execute(new String[] {serverAddress});
        }
    }

    private void getViews() {
        btnSend = (Button) findViewById(R.id.btnSend);
        txtServer = (EditText) findViewById(R.id.txtServer);
        txvLog = (TextView) findViewById(R.id.txvLog);
    }

    private class SendNameAndNumberTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.d("synctask", "do in background is started ....");
            String address = strings[0];

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;

            List<Contact> contacts = new ArrayList<Contact>();
            try {
                String phoneNumber = null;
                String email = null;
                Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
                String _ID = ContactsContract.Contacts._ID;
                String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
                String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
                Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
                String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
                Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
                String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
                String DATA = ContactsContract.CommonDataKinds.Email.DATA;

                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);

                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                        String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                        Contact contact = new Contact(contact_id, name);

                        String number = "";
                        int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                        if (hasPhoneNumber > 0) {
                            Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
                            while (phoneCursor.moveToNext()) {
                                phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                                contact.getNumbers().add(phoneNumber);
                            }
                            phoneCursor.close();
                            // Query and loop for every email of the contact
                            Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);
                            while (emailCursor.moveToNext()) {
                                email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
                                contact.getEmails().add(email);

                            }

                            emailCursor.close();
                        }


                        contacts.add(contact);

                    }
                }

                //response = httpclient.execute(new HttpGet(MessageFormat.format("http://{0}/{1}", address, SERVICE)));
                HttpPost httpPost = new HttpPost(MessageFormat.format("http://{0}/{1}", address, SERVICE));

                String json = Contact.toJSON(contacts);
                //showToast(json);
                StringEntity entity = new StringEntity(json);
                httpPost.setEntity(entity);

                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");

                //ResponseHandler responseHandler = new BasicResponseHandler();
                response = httpclient.execute(httpPost);

                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    responseString = out.toString();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (Exception e) {
                return e.getMessage();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            System.out.println(string);
            showToast(string);
        }
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT);
        txvLog.setText(string + "\n------\n" + txvLog.getText().toString());

        if (txvLog.getText().toString().length() > 500) {
            txvLog.setText(txvLog.getText().toString().substring(0, 500));
        }
    }
}
