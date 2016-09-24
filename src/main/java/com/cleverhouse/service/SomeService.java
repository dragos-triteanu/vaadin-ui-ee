package com.cleverhouse.service;

import com.cleverhouse.model.SomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xPku on 9/24/16.
 */
public class SomeService {

    private static final SomeService INSTANCE = new SomeService();


    public List<SomeData> spamData(int howMany) {
        List<SomeData> someData = new ArrayList<>();

        for (int i = 0; i < howMany; i++) {
            someData.add(new SomeData(i + "", "SomeData" + i));

        }
    return someData;
    }


    public static SomeService getInstance(){
        return INSTANCE;
    }

}
