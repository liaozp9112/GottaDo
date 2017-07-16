package cn.com.gottado.tool.eventbus.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/16.
 */

public class ArrayListEvent<T> implements ObjectEvent{

    private ArrayList<T> arrayList;

    public ArrayListEvent(ArrayList<T> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<T> arrayList) {
        this.arrayList = arrayList;
    }
}
