package com.example.clive.studentplanner;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Clive on 6/06/2017.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DetailViewHolder> {
    //Variable declaration
    private List<CourseDetails> studyDetails;

    //Constructor
    RecyclerViewAdapter(List<CourseDetails> details){
        this.studyDetails = details;
    }

    @Override
    //Method to get the number of items in the recycler view
    public int getItemCount() {
        //Return the size of the event information array list
        return studyDetails.size();
    }

    @Override
    //A view holder method. Each view represents one item in the recycler view
    public DetailViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Create a new and assign it the inflated event recycler view layout
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.study_details_recycler_view, viewGroup, false);
        //Return an event holder view instance with the view as a parameter
        return new DetailViewHolder(v);
    }

    @Override
    //A method used to set the data held in all the GUI objects
    public void onBindViewHolder(DetailViewHolder detailViewHolder, int i) {
        //Set the event title and event name from the event information array list
        detailViewHolder.textCourseDay.setText(studyDetails.get(i).getCourseDay());
        detailViewHolder.textCourseId.setText(studyDetails.get(i).getCourseId());
        detailViewHolder.textRoom.setText(studyDetails.get(i).getRoom());
        detailViewHolder.textStartHour.setText(String.valueOf(studyDetails.get(i).getCourseStartTimeHour()));
        detailViewHolder.textStartMinute.setText(String.valueOf(studyDetails.get(i).getCourseStartTimeMinute()));
        detailViewHolder.textFinishHour.setText(String.valueOf(studyDetails.get(i).getCourseFinishTimeHour()));
        detailViewHolder.textFinishMinute.setText(String.valueOf(studyDetails.get(i).getCourseFinishTimeMinute()));

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    //A static class that creates and instantiates the GUI objects used in the recycler view.
    static class DetailViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView textCourseDay;
        TextView textCourseId;
        TextView textRoom;
        TextView textStartHour;
        TextView textStartMinute;
        TextView textFinishHour;
        TextView textFinishMinute;

        DetailViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardViewDetails);
            textCourseDay = (TextView)itemView.findViewById(R.id.textCourseDay);
            textCourseId = (TextView)itemView.findViewById(R.id.textCourseId);
            textRoom = (TextView)itemView.findViewById(R.id.textRoom);
            textStartHour = (TextView)itemView.findViewById(R.id.textStartHour);
            textStartMinute = (TextView)itemView.findViewById(R.id.textStartMinute);
            textFinishHour = (TextView)itemView.findViewById(R.id.textFinishHour);
            textFinishMinute = (TextView)itemView.findViewById(R.id.textFinishMinute);
        }

    }
}