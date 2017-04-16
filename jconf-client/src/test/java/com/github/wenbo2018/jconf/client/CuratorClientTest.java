package com.github.wenbo2018.jconf.client;

import com.github.wenbo2018.jconf.client.client.CuratorClient;
import com.github.wenbo2018.jconf.client.client.CuratorManager;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class CuratorClientTest {
    public static void main(String[] args) throws Exception {
        CuratorManager curatorManager=new CuratorManager();
        CuratorClient curatorClient=curatorManager.createCuratorClient("202.38.214.166");
        if (!curatorClient.exists("/JCONF/CONFs"))
            curatorClient.creatrPersistentNode("/JCONF/CONFs");
        curatorClient.set("/JCONF/CONFs/test","cccccccccccc");

    }
}