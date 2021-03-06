package com.example.doancna.service;

import androidx.annotation.NonNull;

import com.example.doancna.Common;
import com.example.doancna.utils.UserUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    //generate Token to catch exceptions
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            UserUtils.updateToken(this, s);
        }
    }

    // Recived Messsage thorugh getting token
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> dataRev = remoteMessage.getData();
        if (dataRev != null) {
            Common.ShowNofication(this, new Random().nextInt(),
                    dataRev.get(Common.NOTI_TITLE),
                    dataRev.get(Common.NOTI_CONTENT),
                    null);
        }
    }
}