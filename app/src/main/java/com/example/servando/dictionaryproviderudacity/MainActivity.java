package com.example.servando.dictionaryproviderudacity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the textview
        TextView dictionaryTextView = (TextView)findViewById(R.id.dictionary_text_view);

        // obtener el content resolver que enviara el mensaje al content provide
        ContentResolver contentResolver = getContentResolver();

        // cursor con todos los records in la tabla Words
        Cursor cursor = contentResolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);


        try {
            dictionaryTextView.setText("The UserDictionary contains " + Integer.toString(cursor.getCount()) + " words\n");
            dictionaryTextView.append("Columns " + UserDictionary.Words._ID + " - " + UserDictionary.Words.FREQUENCY + " - " + UserDictionary.Words.WORD + "\n");

            //cursor.moveToNext();

            int idColumnIndex = cursor.getColumnIndex(UserDictionary.Words._ID);
            int frequencyColumnIndex = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY);
            int wordColumnIndex = cursor.getColumnIndex(UserDictionary.Words.WORD);

            int idColumn = 0;
            int frequencyColumn = 0;
            String wordColumn = "";

            while(cursor.moveToNext())
            {
                idColumn =  cursor.getInt(idColumnIndex);

                frequencyColumn = cursor.getInt(frequencyColumnIndex);

                wordColumn = cursor.getString(wordColumnIndex);

                dictionaryTextView.append(Integer.toString(idColumn) + " " + Integer.toString(frequencyColumn) + " " + wordColumn + "\n");
            }
        }
        catch (Exception ex)
        {

        }
        finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
