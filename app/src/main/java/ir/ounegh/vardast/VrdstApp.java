package ir.ounegh.vardast;

import android.app.Application;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import java.util.ArrayList;

/**
 * Created by aseme on 13/12/2017.
 */

public class VrdstApp extends Application {
    public  static volatile String APPNAME="vardast";
    public static volatile ArrayList<Mlocation>SELECTEDLOCATIONS=new ArrayList<>();
    public static  String BASEURL="https://ounegh.ir/nazdik/";
    public static  volatile SharedPreferences PREFS;
    public  static String SECTEDCAT="";
    public  static volatile Context CONTEXT;
   public static ArrayList<Category>CATS=new ArrayList<>();
    public static Location APPLOCATION=null;
    @Override
    public void onCreate() {
        super.onCreate();
        this.CONTEXT=getApplicationContext();
        PREFS=getApplicationContext().getSharedPreferences(APPNAME,0);

    }







}
