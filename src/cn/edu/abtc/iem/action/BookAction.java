package cn.edu.abtc.iem.action;

public interface BookAction {
	//����һЩ��Ҫ�õ��Ľ���ַ���
//	public static final String ERROR="error";//��ת����ҳ��
//	public static final String LOGIN="login";//��ת��½ҳ��
	public static final String TOADMIN="toAdmin";//��Ĭ�ϲ�ѯ�����������Ϣ����ת
	public static final String ADMIN="admin";//��תadmin��ҳ
	public static final String TOBOOK="toBook";//��Ĭ�ϲ�ѯ�����������Ϣ����ת
	public static final String BOOK="book";//��תbook��ҳ
	public static final String TOADDBOOK="toAddBook";//������ת����Ӧ���ҳ��
	public static final String TOUPDATEBOOK="toUpdateBook";//������ת����Ӧ�޸�ҳ��
	public static final String DEFAULT="default";//��תĬ��ҳ��
	
	//���ͼ����Ϣ
	public String insert();
	//ɾ��ͼ����Ϣ
	public String delete();
	//��ѯͼ����Ϣ
	public String query();
	//�޸�ͼ����Ϣ
	public String update();
	//��תͼ����ҳ����ѯ����ͼ����Ϣ
	public String toBook();
	//�����޸��¼�����ת�޸Ľ���
	public String toUpdate();
	//��ת���ͼ����Ϣҳ��
	public String toAdd();
}
