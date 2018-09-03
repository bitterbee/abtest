package com.netease.libs.abtestbase;

import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zyl06 on 2018/9/1.
 */

public class ViewUtil {

    public static int removeFromParent(View view) {
        int result = -1;
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent == null) {
            return result;
        }

        int childCount = parent.getChildCount();
        for (int i=0; i<childCount; i++) {
            if (parent.getChildAt(i) == view) {
                result = i;
            }
        }

        parent.removeView(view);
        return result;
    }

    public static List<View> getChildren(ViewGroup vg) {
        List<View> children = new LinkedList<>();
        int count = vg.getChildCount();
        for (int i=0; i<count; i++) {
            View child = vg.getChildAt(i);
            children.add(child);
        }

        return children;
    }

    public static void replace(View beReplace, View toReplace) {
        if (beReplace == null || toReplace == null) {
            ABLog.e("replace view but beReplace or toReplace null");
            return;
        }

        ViewGroup beVg = (ViewGroup) beReplace.getParent();
        ViewGroup toVg = (ViewGroup) toReplace.getParent();
        if (beVg == null || toVg != null) {
            ABLog.e("replace view but beReplace or toReplace parent invalid");
            return;
        }

        int index = -1;
        int childCount = beVg.getChildCount();
        for (int i=0; i<childCount; i++) {
            if (beVg.getChildAt(i) == beReplace) {
                index = i;
            }
        }

        beVg.addView(toReplace, index);
        beVg.removeView(beReplace);
    }
}
