package com.c_comachi.inused.domain.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        ClassPathResource resource = new ClassPathResource("/firebase/inused-firebase-adminsdk-fmndq-871e73fc28.json");
        try(InputStream serviceAccount = resource.getInputStream()){
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            return FirebaseApp.initializeApp(options);
        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }
    }
}
//        ClassPathResource resource = new ClassPathResource("firebase/inused-firebase-adminsdk-fmndq-871e73fc28.json");

//        InputStream refreshToken = resource.getInputStream();
//
//        FirebaseApp firebaseApp = null;
//        List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();
//
//        if (firebaseAppList != null && !firebaseAppList.isEmpty()) {
//            for (FirebaseApp app : firebaseAppList) {
//                if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
//                    firebaseApp = app;
//                }
//            }
//        } else {
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
//                    .build();
//
//            firebaseApp = FirebaseApp.initializeApp(options);
//        }
//        return FirebaseMessaging.getInstance(firebaseApp);
//    }
//}
