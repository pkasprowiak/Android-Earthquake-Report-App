package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>
{
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes)
    {
        super(context, 0, earthquakes);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        String location = currentEarthquake.getLocation();
        double magnitude = currentEarthquake.getMagnitude();

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(formatMagnitude(magnitude));

        TextView locationOffsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetTextView.setText(locationOffset(location));

        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(primaryLocation(location));

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(formatDate(dateObject));

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(formatTime(dateObject));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;

    }

    public String formatDate(Date dateObject)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
        return dateFormatter.format(dateObject);
    }

    public String formatTime(Date dateObject)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a", Locale.US);
        return dateFormatter.format(dateObject);
    }

    public String locationOffset(String location)
    {
        String locationOffset = "Near the";

        if(location.contains(" of "))
        {
            int ofLocation = location.indexOf(" of ");
            locationOffset = location.substring(0, ofLocation + 4);
        }

        return locationOffset;
    }

    public String primaryLocation(String location)
    {
        String primaryLocation = location;

        if(location.contains(" of "))
        {
            int ofLocation = location.indexOf(" of ");
            primaryLocation = location.substring(ofLocation + 4, primaryLocation.length());
        }

        return primaryLocation;
    }

    public String formatMagnitude(double magnitude)
    {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude)
    {
        int color;
        int value = (int) magnitude;
        switch (value)
        {
            case 0:
            case 1: color = ContextCompat.getColor(getContext(), R.color.magnitude1); break;
            case 2: color = ContextCompat.getColor(getContext(), R.color.magnitude2); break;
            case 3: color = ContextCompat.getColor(getContext(), R.color.magnitude3); break;
            case 4: color = ContextCompat.getColor(getContext(), R.color.magnitude4); break;
            case 5: color = ContextCompat.getColor(getContext(), R.color.magnitude5); break;
            case 6: color = ContextCompat.getColor(getContext(), R.color.magnitude6); break;
            case 7: color = ContextCompat.getColor(getContext(), R.color.magnitude7); break;
            case 8: color = ContextCompat.getColor(getContext(), R.color.magnitude8); break;
            case 9: color = ContextCompat.getColor(getContext(), R.color.magnitude9); break;
            default: color = ContextCompat.getColor(getContext(), R.color.magnitude10plus); break;
        }
        Log.d("testowanie kolorow", "getMagnitudeColor: " + color);

        return color;
    }
}
