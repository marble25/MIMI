package org.iptime.mascore.mimi.etc;

import android.graphics.drawable.Drawable;

/**
 * Created by Owner on 2017-08-19.
 */

public class BoardItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String typeStr ;
    private String descStr ;
    private Drawable iconStar;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setType(String type) {
        typeStr = type;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setStar(Drawable icon) {
        iconStar = icon;
    }


    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getType() {
        return this.typeStr;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public Drawable getStar() {
        return this.iconStar;
    }
}
