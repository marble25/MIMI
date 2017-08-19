package org.iptime.mascore.mimi.etc;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.iptime.mascore.mimi.R;

import java.util.ArrayList;

/**
 * Created by Owner on 2017-08-19.
 */

public class BoardItemAdapter extends BaseAdapter {
    private ArrayList<BoardItem> boardItemArrayList = new ArrayList<>();

    @Override
    public int getCount() {
        return boardItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_board, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageItemFront) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textItemTitle) ;
        TextView typeTextView = (TextView) convertView.findViewById(R.id.textItemType) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textItemDetail) ;
        ImageView iconStarView = (ImageView) convertView.findViewById(R.id.imageItemStar) ;


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        BoardItem boardItem = boardItemArrayList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(boardItem.getIcon());
        titleTextView.setText(boardItem.getTitle());
        typeTextView.setText(boardItem.getType());
        descTextView.setText(boardItem.getDesc());
        iconStarView.setImageDrawable(boardItem.getStar());

        return convertView;

    }

    public void addItem(Drawable icon, String title, String type, String desc, Drawable star) {
        BoardItem item = new BoardItem();

        item.setIcon(icon);
        item.setType(type);
        item.setTitle(title);
        item.setDesc(desc);
        item.setStar(star);

        boardItemArrayList.add(item);
    }
}
