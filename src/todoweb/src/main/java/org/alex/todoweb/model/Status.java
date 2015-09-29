package org.alex.todoweb.model;

import java.util.ArrayList;
import java.util.List;


public enum Status {
    New,
    InProgress,
    Complete;

    private  static String[] names;

    public static String[] getNames(){
        return names.clone();
    }

    static {
        List<String> names=new ArrayList<>();
        for(Status member: values()){
            names.add(member.name());
        }
        Status.names=names.toArray(new String[names.size()]);
    }
}
