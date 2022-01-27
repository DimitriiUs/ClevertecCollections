package ru.clevertec.collections.data;

import ru.clevertec.collections.CustomArrayList;
import ru.clevertec.collections.CustomLinkedList;

public class Data {

    public static CustomArrayList<String> getDataForCustomArrayList() {
        CustomArrayList<String> data = new CustomArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add("elem" + i);
        }

        return data;
    }

    public static CustomLinkedList<String> getDataForCustomLinkedList() {
        CustomLinkedList<String> data = new CustomLinkedList<>();

        for (int i = 0; i < 100; i++) {
            data.add("elem" + i);
        }

        return data;
    }
}
