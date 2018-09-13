/** */
package demo.note;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface noteDao {
	public List<notePojo> selectNoteList(notePojo notepojo);//查询当前用户下的所有笔记
	public notePojo queryNoteContent(notePojo notepojo);//查询某一笔记下的笔记内容
	public void addNote(notePojo notepojo);//查询某一笔记下的笔记内容
	public void updateNoteContene(notePojo notepojo);//保存或修改笔记内容
	public List<notePojo> serachNotes(String name);//根据关键词搜索笔记
	
}