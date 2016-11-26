package com.anew.note.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pig on 2016/11/26.
 */

public class TipsModel implements Serializable {
    private int id;
    private ArrayList<TipModel>list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TipModel> getList() {
        return list;
    }

    public void setList(ArrayList<TipModel> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TipsModel{" +
                "id=" + id +
                ", list=" + list +
                '}';
    }
}
