package com.fir.deer.entity.dao.cache;

import com.fir.deer.Constants;
import com.fir.deer.db.DBException;
import com.fir.deer.db.DataSourceManager;
import com.fir.deer.db.KeyWords;
import com.fir.deer.db.cache.DBObjectCacheDAO;
import com.fir.deer.db.rs.MapToDBObject;
import com.fir.deer.entity.Role;
import com.fir.deer.entity.dao.RoleDao;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by havens on 16-5-14.
 */
public class RoleCacheDao extends DBObjectCacheDAO implements RoleDao {

    public RoleCacheDao() {
        super(DataSourceManager.getQueryRunner(KeyWords.MASTER),DataSourceManager.getQueryRunner(KeyWords.SLAVE));
    }

    private static RoleCacheDao innerDao = null;
    private static LoadingCache<Integer, List<Role>> userHeroes = CacheBuilder.newBuilder().maximumSize(Constants.MAX_CACHE_NUM_SIZE).build(new CacheLoader<Integer, List<Role>>() {
        public List<Role> load(Integer userId) throws Exception {
            if (innerDao == null) {
                innerDao = new RoleCacheDao();
            }
            return innerDao._getHeroes(userId);
        }
    });

    private List<Role> _getHeroes(Integer userId) {
        List<Role> roleList;
        try {
            roleList = slaveRunner.query("select * from roles where userId = ?", MapToDBObject.newListHandler(Role.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Role>();
        }
        return roleList;
    }

    public Role getHero(int id) {
        return null;
    }

    public Role getHero(String name) {
        return null;
    }

    public Role createHero(int userID, int serverID, String roleName, int headImage) {
        Role role = new Role();
        try {
            role.userid = userID;
            role.serverid = serverID;
            role.rolename = roleName;
            role.headimage = headImage;
            role.createtime = System.currentTimeMillis()+"";
            insert(role);
        } catch (DBException e) {
            e.printStackTrace();
            return null;
        }
        return role;
    }

    public void update(Role role) {

    }

    public void delete(Role role) {

    }

    public void deleteById(int id) {

    }
}
