package com.directory.mapper;

import com.directory.domain.Directory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DirectoryMapper {

    @Insert("insert into directory(id, name, abbreviation) values(#{id},#{name},#{abbreviation})")
    Integer insert(Directory directory);

    @Select("select * from directory WHERE id=#{id}")
    Directory findById(Long id);

    @Select("select * from directory")
    List<Directory> findAll();

    @Delete("delete from directory where id=#{id}")
    void delete(Long id);

    @Update("Update directory set name=#{name}, abbreviation=#{abbreviation} where id=#{id}")
    void edit(Directory reference);
}
