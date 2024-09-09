package com.silvia_care.notes;

public class NoteException extends RuntimeException{

    public NoteException(String msg){
        super("Error de notas - " + msg);
    }
}
