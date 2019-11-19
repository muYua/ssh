package cn.edu.abtc.iem.dao;

import java.io.Serializable;
import java.util.List;

public interface UserDao<T> extends BaseDao<T>{
	//�û�У��
	public boolean check(String userName,String password);
	//�����û�����ѯ�û���ģ����ѯ��
	public List<T> queryByName(String userName);
	//�����û�����ѯ�û���׼ȷ��ѯ��
	public List<T> findByName(String userName);
	//�����û�id��ѯ�û���Ϣ
	public List<T> findById(Serializable id);
	
}
