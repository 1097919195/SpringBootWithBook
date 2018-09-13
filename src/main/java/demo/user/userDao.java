/** */
package demo.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface userDao {
	public userPojo findByUserAccount(userPojo user);
	public userPojo findByUserId(userPojo user);
	public void insert(userPojo user);
	public void updatePassword(userPojo user);
   
}