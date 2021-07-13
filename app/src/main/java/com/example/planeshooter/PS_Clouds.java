package com.example.planeshooter;

import java.util.ArrayList;
import java.util.Random;

public class PS_Clouds {
    ArrayList<Integer> cloudx,cloudy;
    Random random;
    int cloud_speed;

    public PS_Clouds() {
        cloudx=new ArrayList<>();
        cloudy=new ArrayList<>();
        random = new Random();
        cloud_speed=10;

    }
    public void add_cloud(int screen_height){
        if(random.nextInt(20)==2){
            if(cloudx.size()<random.nextInt(5)) {
                cloudy.add(random.nextInt(screen_height));
                cloudx.add(-300);
            }
        }
    }
    public void remove_cloud(int screen_width){
        if(cloudx.size()>0){
            for(int i=0;i<cloudx.size();i++) {
                if (cloudx.get(i) > screen_width) {
                    cloudx.remove(i);
                    cloudy.remove(i);
                }
            }
        }
    }


    public void update_cloud(int screen_width,int screen_height){
        remove_cloud(screen_width);
        add_cloud(screen_height);
        if(cloudx.size()>0){
            for(int i=0;i<cloudx.size();i++) {
                cloudx.set(i,cloudx.get(i)+cloud_speed);
            }
        }
    }

}
