package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

	private String mLibPath;

	public MyClassLoader(String path) {
		mLibPath = path;
	}
	
	//��дfindClass��������
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String fileName = getFileName(name);
		File file = new File(mLibPath, fileName);
		try {
			FileInputStream is = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			try {
				while ((len = is.read()) != -1) {
					bos.write(len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] data = bos.toByteArray();
			is.close();
			bos.close();
			
			//���ܽ�class����������ת����Class�������������Ҫ��Ļ��׳������쳣��
			return defineClass(name, data, 0, data.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}

	// ��ȡҪ���� ��class�ļ���
	private String getFileName(String name) {
		int index = name.lastIndexOf('.');
		if (index == -1) {
			return name + ".class";
		} else {
			return name.substring(index + 1) + ".class";
		}
	}

}