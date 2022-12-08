package com.core.event;

public class CloseDoorEvent implements DoorListener{
    @Override
    public void doorEvent(DoorEvent doorEvent){
        if(doorEvent.getState() == -1){
            System.out.println("close door...");
        }
    }
}

