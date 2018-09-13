/** */
package demo.notebook;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface notebookDao {
	public List<notebookPojo> selectNoteBookList(notebookPojo notebook);//查询当前用户下的所有笔记
	public notebookPojo selectNoteBookById(String id);//根据id查找笔记本
	public void addNoteBook(notebookPojo notebook);//新增笔记本
	public void deleteNoteBook(notebookPojo notebook);//删除笔记本
   
}