package cn.edu.abtc.iem.action;
public interface UserAction{
	//����һЩ��Ҫ�õ��Ľ���ַ���
//	public static final String ERROR="error";//��ת����ҳ��
	public static final String MAIN="toMain";//��ת��ҳ����Ĭ�ϲ�����
//	public static final String LOGIN="login";//��ת��½ҳ��
	public static final String REG="reg";//��תע��ҳ��
	public static final String ADD="add";//��ת���ҳ��
	public static final String UPDATE="update";//��ת�޸�ҳ��
	public static final String QUERY="query";//��ת��ѯ���ҳ��main��ҳ
	public static final String DEFAULT="default";//Ĭ����ת��ʽ
	
	//����û�
	public String insertMethod();
	//ɾ���û�
	public String deleteMethod();
	//�����û�����ѯ�û���Ϣ
	public String queryMethod();
	//�޸��û�
	public String updateMethod();
	//ע��
//	public String logoutMethod();
	//��ת��ҳ����,��ѯ�����û���Ϣ
	public String toMainMethod();
	//�����޸��¼�����ת�޸Ľ���
	public String toUpdateMethod();
	//��ת����û���Ϣҳ��
	public String toAddMethod();

	
}
