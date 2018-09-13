/** */
package demo.note;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Blob;

import demo.notebook.notebookPojo;
import demo.util.NoteUtil;
import demo.util.ResponseData;


/**
 * @功能:
 * @项目名:itcast-springboot2
 * @作者:wangjz
 * @日期:2018年6月20日下午2:53:31
 */
@Service
public class noteService {
	 @Autowired
	 private noteDao notedao;
	 //查询某一笔记本下的所有笔记
	 public ResponseData<List<notePojo>>  selectAllNote(notePojo notepojo){
		 ResponseData<List<notePojo>> data=new ResponseData<List<notePojo>>();
		
		 List<notePojo> list=notedao.selectNoteList(notepojo);
		 System.out.println(list);
		 if(list!=null&&list.size()>0){
			 data.setMessage("笔记查询成功");
			 data.setState(0);
			 data.setData(list);
		 }else{
			 data.setMessage("笔记为空");
			 data.setState(1);
		 }
		 return data;
		 
	 }
	 
	 //查询某一笔记下的笔记内容
	 public ResponseData<notePojo> queryContent(notePojo notepojo) {
		 ResponseData<notePojo> data=new ResponseData<notePojo>();
		 notePojo noteContent=notedao.queryNoteContent(notepojo);
			 if(noteContent!=null){
			 data.setMessage("笔记内容查询成功");
			 data.setState(0);
			 data.setData(noteContent);
		 }else{
			 data.setMessage("笔记为空");
			 data.setState(1);
			
		 }
		 
		 return data;
		 
	 }
	 
	 //新增笔记
	 public ResponseData<notePojo> insertNote(notePojo notepojo) {
		 ResponseData<notePojo> data=new ResponseData<notePojo>();
		 notePojo newNote=new notePojo();

		 boolean ok=true;
		 String cn_note_id=null;
		 if(ok){
			 Random rand = new Random();  
			 cn_note_id=(rand.nextInt(100)+100)+"";
			 newNote.setCn_note_id(cn_note_id);
		     notePojo pojo=notedao.queryNoteContent(newNote);
		     if(pojo==null){
		    	 ok=false;
		     }
		 }
		 
		 Date date = new Date();
		 long time = date.getTime();
		 
		 notePojo note=new notePojo();
		 note.setCn_note_id(cn_note_id);
		 note.setCn_notebook_id(notepojo.getCn_notebook_id());
		 note.setCn_note_title(notepojo.getCn_note_title());
		 note.setCn_user_id(notepojo.getCn_user_id());
		 note.setCn_note_create_time(time);
		 note.setCn_note_last_modify_time(time);
		 notedao.addNote(note);
		 
		 data.setMessage("新增笔记成功");
		 data.setData(note);
		 data.setState(0);
		 return data;
		 
	 }
	 
	//查询某一笔记下的笔记内容
	 public ResponseData<notePojo> saveOrUpdate(notePojo notepojo) {
		ResponseData<notePojo> data=new ResponseData<notePojo>();
		notePojo pojo=new notePojo();
		Date date = new Date();
		long time = date.getTime();
		 
		pojo.setCn_note_body(notepojo.getCn_note_body());
		pojo.setCn_note_title(notepojo.getCn_note_title());
		pojo.setCn_note_id(notepojo.getCn_note_id());
		pojo.setCn_note_last_modify_time(time);
		notedao.updateNoteContene(pojo);
		
		data.setData(pojo);
		data.setMessage("保存或修改内容成功");
		data.setState(0);
		return data;
			 
		 }
		 
	//根据关键词搜索笔记
	 public ResponseData<List<notePojo>> serachNotesByKey(String  name) {
		ResponseData<List<notePojo>> data=new ResponseData<List<notePojo>>();
		
	    List<notePojo> list=notedao.serachNotes(name);
	     System.out.println(list);
	    if(list==null||list.size()<=0){
	    	data.setState(1);
	    	data.setMessage("要所搜的笔记不存在");
	    }else{
	    	data.setState(0);
	    	data.setMessage("笔记搜索成功");
	    	data.setData(list);
	    }
		
		
		return data;
			 
		 }
		 
}
