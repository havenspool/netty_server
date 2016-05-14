package com.fir.deer.entity.dao;

import com.fir.deer.entity.Role;

/**
 * Created by havens on 16-5-14.
 */
public interface RoleDao {
    Role getHero(int id);
    Role getHero(String name);
    Role createHero(int userID,int serverID,String roleName,int headImage);
    void update(Role role);
    void delete(Role role);
    void deleteById(int id);
}
