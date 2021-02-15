package com.dictionary.mapper;

import com.dictionary.domain.Dictionary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictionaryMapper {

    @Insert("insert into dictionary(id, name, abbreviation) values(#{id},#{name},#{abbreviation})")
    Integer insert(Dictionary dictionary);

    @Select("select * from dictionary WHERE id=#{id}")
    Dictionary findById(Long id);

    @Select("select * from dictionary")
    List<Dictionary> findAll();

    @Delete("delete from dictionary where id=#{id}")
    void delete(Long id);

    @Update("Update dictionary set name=#{name}, abbreviation=#{abbreviation} where id=#{id}")
    void edit(Dictionary reference);
}
