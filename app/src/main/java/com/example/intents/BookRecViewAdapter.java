package com.example.intents;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;
    private String parentActivity;

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getUrl())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookActivity.class);
                intent.putExtra("bookId", books.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtPages.setText(Integer.toString(books.get(position).getPages()));
        holder.txtShort.setText(books.get(position).getShortDesc());

        if(books.get(position).getExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);

            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downBtn.setVisibility(View.GONE);

            if(parentActivity.equals("allBooks")){
                holder.btnDelete.setVisibility(View.GONE);
            }
            else if(parentActivity.equals("alreadyRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance().removeAlreadyRead(books.get(position))){
                                    Toast.makeText(mContext, "Book removed!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else{
                                    Toast.makeText(mContext, "Some error occurred!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //nothing here. just dismiss
                            }
                        });

                        builder.create().show();
                    }
                });
            }
            else if(parentActivity.equals("currentlyReading")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance().removeCurrentlyReading(books.get(position))){
                                    Toast.makeText(mContext, "Book removed!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else{
                                    Toast.makeText(mContext, "Some error occurred!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //nothing here. just dismiss
                            }
                        });

                        builder.create().show();
                    }
                });
            }
            else if(parentActivity.equals("wantToRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance().removeWantToRead(books.get(position))){
                                    Toast.makeText(mContext, "Book removed!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else{
                                    Toast.makeText(mContext, "Some error occurred!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //nothing here. just dismiss
                            }
                        });

                        builder.create().show();
                    }
                });
            }
            else{
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance().removeFavorite(books.get(position))){
                                    Toast.makeText(mContext, "Book removed!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else{
                                    Toast.makeText(mContext, "Some error occurred!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //nothing here. just dismiss
                            }
                        });

                        builder.create().show();
                    }
                });
            }

        }
        else{
            TransitionManager.beginDelayedTransition(holder.parent);

            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgBook, upBtn, downBtn;
        private TextView txtName, txtAuthor, txtPages, txtShort, btnDelete;
        private RelativeLayout expandedRelLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtName);
            upBtn = itemView.findViewById(R.id.upBtn);
            downBtn = itemView.findViewById(R.id.downBtn);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtPages = itemView.findViewById(R.id.txtPages);
            txtShort = itemView.findViewById(R.id.txtShort);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            downBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
