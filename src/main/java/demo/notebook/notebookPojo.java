/** */
package demo.notebook;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 * @功能:
 * @项目名:itcast-springboot2
 * @作者:wangjz
 * @日期:2018年6月20日下午2:53:50
 */
public class notebookPojo implements Serializable{
private static final long serialVersionUID = -8341731366267651270L;
	
	private String cn_notebook_id;
	private String cn_notebook_name;
	private String cn_notebook_type_id;
	private String cn_user_id;
	private String cn_notebook_desc;
	private Timestamp cn_notebook_createtime;
	/**要删除的笔记本id串**/
	private String ids;
	
	
	public String getIds() {
		return ids;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public notebookPojo(){
		
	}
	/**
	 * 构造函数
	 * @param cn_notebook_id
	 * @param cn_notebook_name
	 * @param cn_notebook_type_id
	 * @param cn_user_id
	 * @param cn_notebook_desc
	 * @param cn_notebook_createtime
	 */
	public notebookPojo(String cn_notebook_id, String cn_notebook_name, String cn_notebook_type_id, String cn_user_id,
			String cn_notebook_desc, Timestamp cn_notebook_createtime) {
		super();
		this.cn_notebook_id = cn_notebook_id;
		this.cn_notebook_name = cn_notebook_name;
		this.cn_notebook_type_id = cn_notebook_type_id;
		this.cn_user_id = cn_user_id;
		this.cn_notebook_desc = cn_notebook_desc;
		this.cn_notebook_createtime = cn_notebook_createtime;
	}
	public String getCn_notebook_id() {
		return cn_notebook_id;
	}
	
	public void setCn_notebook_id(String cn_notebook_id) {
		this.cn_notebook_id = cn_notebook_id;
	}
	
	public String getCn_notebook_name() {
		return cn_notebook_name;
	}
	
	public void setCn_notebook_name(String cn_notebook_name) {
		this.cn_notebook_name = cn_notebook_name;
	}
	
	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}
	
	public void setCn_notebook_type_id(String cn_notebook_type_id) {
		this.cn_notebook_type_id = cn_notebook_type_id;
	}
	
	public String getCn_user_id() {
		return cn_user_id;
	}
	
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	
	public String getCn_notebook_desc() {
		return cn_notebook_desc;
	}
	
	public void setCn_notebook_desc(String cn_notebook_desc) {
		this.cn_notebook_desc = cn_notebook_desc;
	}
	
	public Timestamp getCn_notebook_createtime() {
		return cn_notebook_createtime;
	}
	
	public void setCn_notebook_createtime(Timestamp cn_notebook_createtime) {
		this.cn_notebook_createtime = cn_notebook_createtime;
	}
	
	
	
	
}
