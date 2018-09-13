/** */
package demo.note;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import demo.util.ResponseData;

/**
 * @功能:
 * @项目名:myspringboot
 * @作者:wangjz
 * @日期:2018年6月25日上午11:34:47
 */
@RestController
@RequestMapping("/note")
public class noteController {
	@Autowired
	private noteService noteservice;
	//查询所有笔记本
	@RequestMapping("/selectAllNote")
	public ResponseData<List<notePojo>> selectAllNote(notePojo notepojo, HttpServletRequest request,HttpServletResponse response) {
		return noteservice.selectAllNote(notepojo);
	}
	
	//查询笔记内容
	@RequestMapping("/queryNoteContent")
	public ResponseData<notePojo> queryNoteContent(notePojo notepojo, HttpServletRequest request,HttpServletResponse response) {
		  
		return noteservice.queryContent(notepojo);
	}
	//查询笔记内容
	@RequestMapping("/insertNote")
	public ResponseData<notePojo> insertNote(notePojo notepojo, HttpServletRequest request,HttpServletResponse response) {
		  
		return noteservice.insertNote(notepojo);
	}
		
	//保存或修改笔记内容
	@RequestMapping("/updateOrSave")
	public ResponseData<notePojo> updateOrSave(notePojo notepojo, HttpServletRequest request,HttpServletResponse response) {
		  
		return noteservice.saveOrUpdate(notepojo);
	}
	
	//根据关键词搜索笔记
	@RequestMapping("/selectByKey")
	public ResponseData<List<notePojo>> selectByKey(String name, HttpServletRequest request,HttpServletResponse response) {
		  
		return noteservice.serachNotesByKey(name);
	}
}
