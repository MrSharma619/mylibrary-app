package com.example.intents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private TextView textView2, textView4, textView6, textView7, textView8;
    private Button button, button2, button3, button4;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        Intent intent = getIntent();
        if(null != intent){
            int bookId = intent.getIntExtra("bookId", -1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance().getBook(bookId);
                if(null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToRead(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleFavorites(incomingBook);

                }
            }
        }

    }

    private void handleCurrentlyReading(final Book book) {
        ArrayList<Book> currentlyReading = Utils.getInstance().getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBook = false;

        for(Book b : currentlyReading){
            if(b.getId() == book.getId()){
                existInCurrentlyReadingBook = true;
            }
        }

        if(existInCurrentlyReadingBook){
            button.setEnabled(false);
        }
        else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book added!", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(BookActivity.this, CurrentlyReading.class);
                        startActivity(in);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Some error occurred!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavorites(final Book book) {
        ArrayList<Book> favouriteBooks = Utils.getInstance().getFavoriteBooks();
        boolean existInFavoriteBooks = false;

        for(Book b : favouriteBooks){
            if(b.getId() == book.getId()){
                existInFavoriteBooks = true;
            }
        }

        if(existInFavoriteBooks){
            button4.setEnabled(false);
        }
        else {
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToFavoriteBook(book)){
                        Toast.makeText(BookActivity.this, "Book added!", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(BookActivity.this, FavoriteBook.class);
                        startActivity(in);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Some error occurred!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToRead(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();
        boolean existInWantToReadBook = false;

        for(Book b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existInWantToReadBook = true;
            }
        }

        if(existInWantToReadBook){
            button2.setEnabled(false);
        }
        else {
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book added!", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(BookActivity.this, WantToReadBooks.class);
                        startActivity(in);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Some error occurred!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBook = Utils.getInstance().getAlreadyReadBooks();
        boolean existInAlreadyReadBook = false;

        for(Book b : alreadyReadBook){
            if(b.getId() == book.getId()){
                existInAlreadyReadBook = true;
            }
        }

        if(existInAlreadyReadBook){
            button3.setEnabled(false);
        }
        else {
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book added!", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(BookActivity.this, AlreadyReadBooks.class);
                        startActivity(in);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Some error occurred!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        textView2.setText(book.getName());
        textView4.setText(book.getAuthor());
        textView6.setText(Integer.toString(book.getPages()));
        textView7.setText(book.getShortDesc());
        textView8.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getUrl())
                .into(imageView);
    }

    private void initViews() {
        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        imageView = findViewById(R.id.imageView);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}