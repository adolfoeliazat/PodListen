package com.einmalfel.podlisten;


import android.database.Cursor;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PodcastListAdapter extends CursorRecyclerAdapter<PodcastViewHolder> {
  private static final String TAG = "PLA";

  public PodcastListAdapter(Cursor cursor) {
    super(cursor);
    setHasStableIds(true);
  }

  @Override
  public void onBindViewHolderCursor(PodcastViewHolder holder, Cursor cursor) {
    int state = cursor.getInt(cursor.getColumnIndex(Provider.K_PSTATE));
    if (state == Provider.PSTATE_NEW) {
      holder.titleView.setText(cursor.getString(cursor.getColumnIndex(Provider.K_PFURL)));
      holder.descriptionView.setText("Feed isn't loaded yet");
    } else {
      String title = cursor.getString(cursor.getColumnIndex(Provider.K_PNAME));
      String description = cursor.getString(cursor.getColumnIndex(Provider.K_PDESCR));
      if (title != null) {
        holder.titleView.setText(cursor.getString(cursor.getColumnIndex(Provider.K_PNAME)));
      }
      if (description != null) {
        Spanned spannedText = Html.fromHtml(description);
        holder.descriptionView.setText(spannedText, TextView.BufferType.SPANNABLE);
      }
    }

  }

  @Override
  public PodcastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.podcast_list_element, parent, false);
    return new PodcastViewHolder(v);
  }
}
