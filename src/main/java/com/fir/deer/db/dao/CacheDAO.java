package com.fir.deer.db.dao;

import com.fir.deer.db.DBException;
import com.fir.deer.db.DBObject;

/**
 * Created by havens on 15-8-12.
 */
public interface CacheDAO extends DAO {

    // cache operation

    void insertCacheObject(DBObject obj) throws DBException;

    void deleteCacheObject(DBObject obj) throws DBException;

    void updateCacheObject(DBObject obj) throws DBException;

    void insertCacheObject(DBObject obj, String table) throws DBException;

    void deleteCacheObject(DBObject obj, String table) throws DBException;

    void updateCacheObject(DBObject obj, String table) throws DBException;

    // clean cache to db
    void cleanCacheObject(String table, String _key) throws DBException;

    void cleanCacheObject(String table) throws DBException;

    void cleanCacheObjectByKey(String key) throws DBException;

    // just clean backup data

    void cleanCacheObjectNoSync(String table, String _key) throws DBException;

    void cleanCacheObjectNoSync(String table) throws DBException;

    void cleanCacheObjectByKeyNoSync(String key) throws DBException;
}
