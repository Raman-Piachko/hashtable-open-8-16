package com.epam.rd.autocode.hashtableopen816;

public class HashTableEntry {
    private Integer key;
    private Object value;
    private boolean isRemoved;

    public HashTableEntry(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public void remove() {
        isRemoved = true;
    }

    public boolean notRemoved() {
        return !isRemoved;
    }

    public int getKey() {
        if(key!=null){
            return key;
        }
        return 0;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
