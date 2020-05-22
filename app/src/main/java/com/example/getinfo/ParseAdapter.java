package com.example.getinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {

    private ArrayList<String> parseItems;
    private ArrayList<String> URLS;
    WebView wv1;
    private Context context;

    public ParseAdapter(ArrayList<String> parseItems,ArrayList<String> URLS, Context context) {
        this.parseItems = parseItems;
        this.context = context;
        this.URLS= URLS;
    }

    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
         String parseItem = parseItems.get(position);

        if (position==0){
        holder.textView.setTextColor(Color.BLUE);
        holder.textView.setTextSize(16);
            holder.textView.setText(parseItem);
        holder.textView.append("\n");
            holder.textView.append("\n");
        }

        else if (position==1){

            holder.textView.setText("Coronavirus Cases\n"+parseItem);
            holder.textView.setTextSize(25);}

        else if (position==2){
            holder.textView.setText("Deaths \n"+parseItem);
            holder.textView.setTextColor(Color.RED);
            holder.textView.setTextSize(25);}
        else if (position==3){
            holder.textView.setText("Recovered \n"+parseItem);
            holder.textView.setTextColor(Color.rgb(0,100,0));
            holder.textView.setTextSize(25);}

        else if (position==4){
            holder.textView.setText("Active Cases \n"+parseItem);
            holder.textView.setTextColor(Color.rgb(255,140,0));
            holder.textView.setTextSize(25);}

    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textView;

        public ViewHolder(@NonNull View view) {
            super(view);
            //wv1= view.findViewById(R.id.webView);
            textView = view.findViewById(R.id.textView);
            textView.setClickable(true);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            String parseurl = URLS.get(position);
            //String url = ed1.getText().toString();
         //   Intent i = new Intent(Intent.ACTION_VIEW);
          //  i.setData(Uri.parse(parseurl));
           // context.startActivity(i);

          //  wv1.getSettings().setLoadsImagesAutomatically(true);
          //  wv1.getSettings().setJavaScriptEnabled(true);
          //  wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
          //  wv1.loadUrl(parseurl);

            //Intent intent = new Intent(context, DetailActivity.class);
            //intent.putExtra("title", parseItem);
           // intent.putExtra("image", parseItem.getImgUrl());
           // intent.putExtra("detailUrl", parseItem.getDetailUrl());
           // context.startActivity(intent);
        }
    }

    public void setFilter (ArrayList<String> newList) {
        parseItems = new ArrayList<>();
        parseItems.addAll(newList);
        notifyDataSetChanged();
    }
}