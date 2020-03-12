package de.vh.util;

import de.vh.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Subject> filterBySubject(Iterable<Subject> subjects, String filter) {
        List<Subject> subs = new ArrayList<Subject>();
        for (Subject s :subjects) {
            if (s.getSubject().equals(filter)) {
                subs.add(s);
            }
        }
        return subs;
    }


}
