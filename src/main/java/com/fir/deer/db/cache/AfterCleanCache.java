package com.fir.deer.db.cache;

/**
 * Created by havens on 15-8-12.
 */
public interface AfterCleanCache {

    void cleanKeyCache(String key);
}
