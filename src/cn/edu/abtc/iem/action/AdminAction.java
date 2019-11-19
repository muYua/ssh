package cn.edu.abtc.iem.action;
public interface AdminAction{
	//����һЩ��Ҫ�õ��Ľ���ַ���
	public static final String MAIN="main";//��תmain��ҳ
	public static final String TOMAIN="toMain";//��Ĭ�ϲ�����תmain��ҳ
	public static final String TOADMIN="toAdmin";//��Ĭ�ϲ�����������Ϣ����תadmin��ҳ
	public static final String ADMIN="admin";//ֱ����תadmin��ҳ
	public static final String REG="reg";//��תע��ҳ��
	public static final String ADDADMIN="addAdmin";//��תadmin�����ҳ��
	public static final String UPDATEADMIN="updateAdmin";//��תadmin�޸�ҳ��
	public static final String DEFAULT="default";//��תĬ�Ͻ���
	
	//��¼У��
	public String login();
	//ע��
	public String reg();
	//���
	public String insert();
	//ɾ��
	public String delete();
	//��ѯ��Ϣ
	public String query();
	//�޸�
	public String update();
	//ע��
	public String logout();
	//��ת��ҳ����,��ѯ�����û���Ϣ
	public String toAdmin();
	//�����޸��¼�����ת�޸Ľ���
	public String toUpdateAdmin();
	//��ת��ӹ���Ա��Ϣҳ��
	public String toAddAdmin();
	//��ת��ҳ
	public String toMain();
}
