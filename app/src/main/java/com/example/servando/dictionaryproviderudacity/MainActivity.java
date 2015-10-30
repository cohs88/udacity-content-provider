package com.example.servando.dictionaryproviderudacity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String[] COLUMNS_TO_BE_BOUND = new String[]{
            UserDictionary.Words.WORD,
            UserDictionary.Words.FREQUENCY
    };

    private static final int[] LAYOUT_ITEMS_TO_FILL = new int[]{
            android.R.id.text1,
            android.R.id.text2
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        ListView dictionaryListView = (ListView)findViewById(R.id.dictionary_list_view);

        // get the textview
        //TextView dictionaryTextView = (TextView)findViewById(R.id.dictionary_text_view);

        // obtener el content resolver que enviara el mensaje al content provide
        ContentResolver contentResolver = getContentResolver();

        // cursor con todos los records in la tabla Words
        Cursor cursor = contentResolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        // creamos el adapter con un layout predefinido de android (two_line_list_item)
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, COLUMNS_TO_BE_BOUND, LAYOUT_ITEMS_TO_FILL, 0);

        // ponemos el adapter
        dictionaryListView.setAdapter(simpleCursorAdapter);
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
