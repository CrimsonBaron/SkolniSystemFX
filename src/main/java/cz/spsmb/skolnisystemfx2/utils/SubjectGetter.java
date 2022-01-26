package cz.spsmb.skolnisystemfx2.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubjectGetter {

    public static  List<String> getSubjects(){
        List<String> list = new ArrayList<>();
        list.add("math");
        list.add("language");
        list.add("chemistry");
        return list;
    }

}
