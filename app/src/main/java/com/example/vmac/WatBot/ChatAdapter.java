package com.example.vmac.WatBot;

/**
 * Created Siddhartha Dimania.
 */

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private int SELF = 100;
    private ArrayList<Message> messageArrayList;
    private static final String TAG = "MyOutputMessage";


    public ChatAdapter(ArrayList<Message> messageArrayList) {
        this.messageArrayList = messageArrayList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        // view type is to identify where to render the chat message
        // left or right
        if (viewType == SELF) {
            // self message
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_self, parent, false);
        } else {
            // WatBot message
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item_watson, parent, false);
        }


        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageArrayList.get(position);
        if (message.getId() != null && message.getId().equals("1")) {
            return SELF;
        }
        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Message message = messageArrayList.get(position);
        message.setMessage(message.getMessage());
        String formattedMessage = message.getMessage().replace("next_line ", System.getProperty("line.separator"));
        ((ViewHolder) holder).message.setText(formattedMessage);

        if (message.id.equals("2")) {
            if(formattedMessage.contains("done with")) {
                if(formattedMessage.contains("Saving that you're done with")) {
                    String activityPerformed = formattedMessage.replace("saving that you're done with ", "");
                    ActivitySave saveActivity = new ActivitySave(activityPerformed);
                    saveActivity.save();
                } else if(formattedMessage.contains("Searching if you're done with")) {
                    String activityPerformed = formattedMessage.replace("Searching if you're done with ", "");
                    List<ActivitySave> performedActivityFound = ActivitySave.find(ActivitySave.class, "activity_performed = ? and today_date = ?", activityPerformed, getTodayDate()) ;

                    if(performedActivityFound.size() > 0) {
                        message.setMessage("You already Performed this activity");
                        ((ViewHolder) holder).message.setText(message.getMessage());
                    } else {
                        message.setMessage("You did not perform this activity today");
                        ((ViewHolder) holder).message.setText(message.getMessage());
                    }

                    List<ActivitySave> pastActivities = ActivitySave.find(ActivitySave.class, "today_date != ?", getTodayDate()) ;
                    for (ActivitySave pastActi: pastActivities) {
                        pastActi.delete();
                    }
                }
            } else if(formattedMessage.contains("paid")) {
                if(formattedMessage.contains("")) {

                }
            }

        }
    }

    private String getTodayDate() {
        int style = DateFormat.MEDIUM;
        DateFormat df;
        Date date = new Date();
        df = DateFormat.getDateInstance(style, Locale.JAPAN);
        return df.format(date);
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;

        public ViewHolder(View view) {
            super(view);
            message = (TextView) itemView.findViewById(R.id.message);

            //TODO: Uncomment this if you want to use a custom Font
            /*String customFont = "Montserrat-Regular.ttf";
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), customFont);
            message.setTypeface(typeface);*/

        }
    }


}