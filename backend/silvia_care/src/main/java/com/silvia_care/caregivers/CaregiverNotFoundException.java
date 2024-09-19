package com.silvia_care.caregivers;

public class CaregiverNotFoundException extends RuntimeException{
    public CaregiverNotFoundException(String msg){
        super("Usuario no encontrado: " + msg);
    }
}
