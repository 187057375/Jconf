package com.github.wenbo2018.jconf.core;

import com.github.wenbo2018.jconf.common.config.ConfigManager;
import com.github.wenbo2018.jconf.common.config.ConfigManagerLoader;
import com.github.wenbo2018.jconf.core.registry.CuratorRegistry;
import com.github.wenbo2018.jconf.core.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wenbo.shen on 2017/7/2.
 */
public class RegistryManager {
    private static Logger logger = LoggerFactory.getLogger(RegistryManager.class);
    private static RegistryManager instance = new RegistryManager();
    private static volatile boolean isInit = false;
    private static ConfigManager configManager;
    private static List<Registry> registryList = new ArrayList<>();
    private static String ZOOKEEPER_REGISTER_ADDRESS = "zookeeper.register.address";
    private static final Random random = new Random();

    public static RegistryManager getInstance() {
        if (!isInit) {
            synchronized (RegistryManager.class) {
                if (!isInit) {
                    instance.init();
                    isInit = true;
                }
            }
        }
        return instance;
    }

    private void init() {
        this.configManager = ConfigManagerLoader.getConfigManager();
        String addressSplits = configManager.getStringValue(ZOOKEEPER_REGISTER_ADDRESS);
        String[] addressArray = addressSplits.trim().split(",");
        if (addressArray.length <= 0) {
            logger.error("zk curator address is not found");
        } else {
            for (int i = 0; i < addressArray.length; i++) {
                String address = addressArray[i];
                Registry registry = new CuratorRegistry();
                registry.init(address);
                registryList.add(registry);
            }
        }
    }

    public static void registerService(String key, String value) {
        if (registryList.size() < 1) {
            logger.error("not found zk curator");
            return;
        }
        int total = registryList.size();
        int offset = random.nextInt(total);
        registryList.get(offset).registryConfig(key, value);
    }

    public static String getService(String key) {
        if (registryList.size() < 1) {
            logger.error("not found zk curator");
            return null;
        }
        int total = registryList.size();
        int offset = random.nextInt(total);
        return registryList.get(offset).get(key);
    }

    public static void unregisterService(String key) {
        if (registryList.size() < 1) {
            logger.error("not found zk curator");
            return;
        }
        int total = registryList.size();
        int offset = random.nextInt(total);
        registryList.get(offset).unregistryConfig(key);
    }
}
