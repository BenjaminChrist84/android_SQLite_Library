package se.benjaminchrist.bibliotekv52;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static se.benjaminchrist.bibliotekv52.R.id.editText;
import static se.benjaminchrist.bibliotekv52.R.id.textView;

public class EditBook extends AppCompatActivity {

    Button delete, edit;
    TextView textViewTitle, textViewAuthor;
    EditText editTextTitle, editTextAuthor;

    int position;
    DatabankHelper databankH;
    Bock selectedBock;

    private DatabankHelper databankHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        databankH = new DatabankHelper(this);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            position = bundle.getInt("position");
        }

        delete = (Button) findViewById(R.id.button);
        edit = (Button) findViewById(R.id.button2);

        textViewTitle = (TextView)findViewById(textView);
        textViewAuthor = (TextView)findViewById(R.id.textView2);

        editTextTitle = (EditText) findViewById(editText);
        editTextAuthor = (EditText) findViewById(R.id.editText2);


        selectedBock = databankH.getBock(position);
        Toast.makeText(this, selectedBock.getTitle(), Toast.LENGTH_SHORT).show();

        textViewTitle.setText("Title:\n" + selectedBock.getTitle());
        textViewAuthor.setText("Author:\n" + selectedBock.getAuthor());

        if(selectedBock.getTitle() == null){
            editTextTitle.setText("Title:\n");
        }
        else
            editTextTitle.setText(selectedBock.getTitle());

        if(selectedBock.getAuthor() == null){
            editTextAuthor.setText("Author:\n");
        }
        else
            editTextAuthor.setText(selectedBock.getAuthor());


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String author = editTextAuthor.getText().toString();

                selectedBock.setTitle(title);
                selectedBock.setAuthor(author);
                databankH.upateData(selectedBock);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databankH.deleteBock(selectedBock);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(EditBook.this, "Book is deleted", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });

    }

    public void finish(){super.finish();}
}
