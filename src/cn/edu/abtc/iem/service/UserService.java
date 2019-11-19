package cn.edu.abtc.iem.service;

import java.io.Serializable;
import java.util.List;

public interface UserService<T> extends BaseService<T> {
	//��ѯ��������
	public List<T> findAll(Class<T> entityClazz);
	//�޸�����
	public void update(T entity,Serializable id,String userName,String password);
	//�����û�����ѯ�û�(ģ����ѯ)
	public List<T> qurey(String userName);
	//�����û�����ѯ�û�(׼ȷ��ѯ)
	public List<T> findByName(String userName);
	//�ж��Ƿ���ڸ��û���
	public boolean isExist(String userName);
	//�ж��Ƿ���ڸ��û�
	public boolean isExist(Serializable id);
	//����id��ѯ�û���Ϣ
	public T findUserById(Serializable id);
}
