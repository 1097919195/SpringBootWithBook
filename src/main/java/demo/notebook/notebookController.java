/** */
package demo.notebook;

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
@RequestMapping("/notebook")
public class notebookController {
	@Autowired
	private notebbookService notebbookservice;
	//查询所有笔记本
	@RequestMapping("/select")
	public ResponseData<List<notebookPojo>> select(notebookPojo notebookpojo, HttpServletRequest request,
			HttpServletResponse response) {
		return notebbookservice.selectList(notebookpojo);
	}
	
   //新增笔记本
	@RequestMapping("/add")
	public ResponseData<notebookPojo> add(notebookPojo notebookpojo, HttpServletRequest request,
			HttpServletResponse response) {
		return notebbookservice.addNoteBook(notebookpojo);
	}
	//删除笔记本
	@RequestMapping("/delete")
	public ResponseData<notebookPojo> delete(notebookPojo notebookpojo, HttpServletRequest request,
			HttpServletResponse response) {
		return notebbookservice.moveNoteBook(notebookpojo);
	}
}
