package cn.edu.abtc.iem.dao;

import java.util.List;
public interface AdminDao<T> extends BaseDao<T>{
	//�û�У��
	public List<T> check(String userName,String password);
	//�����û�����ѯ�û���ģ����ѯ��
	public List<T> queryByName(String userName);
	//�����û�����ѯ�û���׼ȷ��ѯ��
	public List<T> findByName(String userName);
	
}
