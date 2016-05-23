package com.fir.deer.entity.dao.cache;

import com.fir.deer.db.KeyWords;
import com.fir.deer.db.cache.DBObjectCacheDAO;
import com.fir.deer.db.DataSourceManager;
import com.fir.deer.db.rs.MapToDBObjectHandler;
import com.fir.deer.entity.User;
import com.fir.deer.entity.dao.UserDao;
import com.fir.deer.server.WorldManager;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * Created by havens on 15-8-13.
 */
public class UserCacheDao extends DBObjectCacheDAO implements UserDao{

    private QueryRunner masterUserRunner;
    private QueryRunner slaveUserRunner;

    public UserCacheDao() {
        super(DataSourceManager.getQueryRunner(WorldManager.User_DB, KeyWords.MASTER),DataSourceManager.getQueryRunner(WorldManager.User_DB,KeyWords.SLAVE));
        this.masterUserRunner = DataSourceManager.getQueryRunner(WorldManager.User_DB, KeyWords.MASTER);
        this.slaveUserRunner = DataSourceManager.getQueryRunner(WorldManager.User_DB, KeyWords.SLAVE);
    }

    public User getUser(int id){
        User user=null;
        try {
            user =slaveUserRunner.query("select * from users where id = ? ",new MapToDBObjectHandler<User>(User.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUser(String name){
        User user=null;
        try {
            user = slaveUserRunner.query("select * from users where name = ? ",new MapToDBObjectHandler<User>(User.class), name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void insert(User user) {
        insert(user);
    }

    public void update(User user) {
        update(user);
    }

    public void delete(User user) {
        delete(user);
    }

    public void deleteById(int id) {
        delete(getUser(id));
    }

}
