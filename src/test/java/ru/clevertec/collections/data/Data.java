package ru.clevertec.collections.data;

import ru.clevertec.collections.ClevertecArrayList;
import ru.clevertec.collections.ClevertecLinkedList;

public class Data {

    public static ClevertecArrayList<String> getDataForCustomArrayList() {
        ClevertecArrayList<String> data = new ClevertecArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add("elem" + i);
        }

        return data;
    }

    public static ClevertecLinkedList<String> getDataForCustomLinkedList() {
        ClevertecLinkedList<String> data = new ClevertecLinkedList<>();

        for (int i = 0; i < 100; i++) {
            data.add("elem" + i);
        }

        return data;
    }
}
