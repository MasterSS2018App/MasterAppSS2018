package com.hm_master.masterapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Fragment_PCRoom_List.Instance != null)
            Fragment_PCRoom_List.Instance.Refresh();

        if(Fragment_ClassRoom_List.Instance != null)
            Fragment_ClassRoom_List.Instance.Refresh();
    }
}
