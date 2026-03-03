package org.nuoyue.mapper;

import org.apache.ibatis.annotations.*;
import org.nuoyue.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    /*
     * Query all department data
     */

    @Select("Select id, name, create_time, update_time from dept ORDER BY update_time DESC")
    List<Dept> findAll();

    /*
     * Delete department data by id
     */
    @Delete("DELETE FROM dept WHERE id = #{id}")
    void deleteById(Integer id);

    /*
     * Insert department data
     */
    @Insert("INSERT INTO dept(name, create_time, update_time) VALUES(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Select("SELECT id, name, create_time, update_time FROM dept WHERE id = #{deptId}")
    Dept getById(Integer deptId);

    @Update("UPDATE dept SET name = #{name}, update_time = #{updateTime} WHERE id = #{id}")
    void update(Dept dept);
}
