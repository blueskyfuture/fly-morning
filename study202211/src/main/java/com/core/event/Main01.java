package com.core.event;

import java.util.ArrayList;
import java.util.List;

public class Main01 {
    public static void main(String[] args){
        List<DoorListener> list = new ArrayList<>();
        list.add(new OpenDoorListener());
        list.add(new CloseDoorEvent());
        for(DoorListener listener : list){
            listener.doorEvent(new DoorEvent(-1,-1));
            listener.doorEvent(new DoorEvent(1,1));
        }
    }
}
