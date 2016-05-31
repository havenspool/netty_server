package com.fir.deer.entity.dao.cache;

import com.fir.deer.db.DBException;
import com.fir.deer.db.DataSourceManager;
import com.fir.deer.db.KeyWords;
import com.fir.deer.db.cache.DBObjectCacheDAO;
import com.fir.deer.db.rs.MapToDBObjectHandler;
import com.fir.deer.entity.User;
import com.fir.deer.entity.dao.UserDao;
import com.fir.deer.entity.idGenerator.UserIdGenerator;
import com.fir.deer.server.WorldManager;

import java.sql.SQLException;

/**
 * Created by havens on 15-8-13.
 */
public class UserCacheDao extends DBObjectCacheDAO implements UserDao{

    private static final UserIdGenerator idGenerator = new UserIdGenerator(WorldManager.SERVER_ID);

    public UserCacheDao() {
        super(DataSourceManager.getQueryRunner(WorldManager.User_DB, KeyWords.MASTER),DataSourceManager.getQueryRunner(WorldManager.User_DB,KeyWords.SLAVE));
        this.idGen = idGenerator;
    }

    public User getUser(int id){
        User user=null;
        try {
            user =slaveRunner.query("select * from users where id = ? ",new MapToDBObjectHandler<User>(User.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUser(String name){
        User user=null;
        try {
            user = slaveRunner.query("select * from users where name = ? ",new MapToDBObjectHandler<User>(User.class), name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void insertUser(User user) throws DBException {
        insert(user);
    }

    public void updateUser(User user) throws DBException{
        update(user);
    }

    public void deleteUser(User user) throws DBException{
        delete(user);
    }

    public void deleteUserById(int userId) throws DBException{
        delete(getUser(userId));
    }

}
