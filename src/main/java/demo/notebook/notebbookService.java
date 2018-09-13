/** */
package demo.notebook;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import demo.util.NoteUtil;
import demo.util.ResponseData;


/**
 * @功能:
 * @项目名:itcast-springboot2
 * @作者:wangjz
 * @日期:2018年6月20日下午2:53:31
 */
@Service
public class notebbookService {
	 @Autowired
	 private notebookDao notebookdao;
	//加载当前用户所有笔记本
	 public ResponseData<List<notebookPojo>> selectList(notebookPojo note){
		 ResponseData<List<notebookPojo>> data=new ResponseData<List<notebookPojo>>();
		 notebookPojo notebookpojo=new notebookPojo();
		 notebookpojo.setCn_user_id(note.getCn_user_id());
		 System.out.println(note.getCn_user_id());
		 List<notebookPojo> list=notebookdao.selectNoteBookList(notebookpojo);
		 System.out.println(list);
		
		 if(list!=null&&list.size()>0){
			 data.setMessage("笔记本查询成功");
			 data.setState(0);
			 data.setData(list);
		 }else{
			 data.setMessage("该用户笔记本为空");
			 data.setState(1);
		 }
		 return data;
	 }
	 
	 //新增笔记本
	 public ResponseData<notebookPojo> addNoteBook(notebookPojo note){
		 ResponseData<notebookPojo> data=new ResponseData<notebookPojo>();
		 notebookPojo notebook=new notebookPojo();
		 notebook.setCn_user_id(note.getCn_user_id());
		 notebook.setCn_notebook_name(note.getCn_notebook_name());
		 
		 Date date = new Date();       
		 Timestamp createtime = new Timestamp(date.getTime());
		 boolean ok=true;
		 String cn_notebook_id=null;
		 if(ok){
			 Random rand = new Random();  
			 cn_notebook_id=(rand.nextInt(100)+100)+"";
		     notebookPojo pojo=notebookdao.selectNoteBookById(cn_notebook_id);
		     if(pojo==null){
		    	 ok=false;
		     }
		 }
		 
		 notebook.setCn_notebook_id(cn_notebook_id);
		 notebook.setCn_notebook_createtime(createtime);
		 notebookdao.addNoteBook(notebook);
		 
		 data.setMessage("新增笔记本成功");
		 data.setState(0);
		 data.setData(notebook);
		return data;
	 }
	 
	 //删除笔记本
	 public ResponseData<notebookPojo> moveNoteBook(notebookPojo note){
		 ResponseData<notebookPojo> data=new ResponseData<notebookPojo>();
		 notebookdao.deleteNoteBook(note);
		 data.setState(0);
		return data;
	 }
	 
	 //页面搜索
	 public ResponseData<notebookPojo> serach(notebookPojo note){
		 ResponseData<notebookPojo> data=new ResponseData<notebookPojo>();
		
		return data;
	 }
}
