package shop.mtcoding.blog.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoveRepository {
    public Love findByBoardIdAndUserId(@Param("boardId") int boardId, @Param("userId") int userId);

    public List<Love> findAll();

    public Love findById(int id);

    public int insert(Love love);

    public int updateById(Love love);

    public int deleteById(int id);
}