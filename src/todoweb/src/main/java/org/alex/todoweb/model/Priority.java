package org.alex.todoweb.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public enum  Priority {
    Low,
    Medium,
    High,
    Urgent;

    private  static String[] names;

    public static String[] getNames(){
        return names.clone();
    }

    static {
        List<String> names=new ArrayList<>();
        for(Priority member: values()){
            names.add(member.name());
        }
        Priority.names=names.toArray(new String[names.size()]);
    }


    public static Priority parse(String name) {
        for(Priority member:values()){
            if(StringUtils.equalsIgnoreCase(member.name(),name)){
                return member;
            }
        }
        return null;
    }
}
