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
	
	//重写findClass（）方法
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
			
			//它能将class二进制内容转换成Class对象，如果不符合要求的会抛出各种异常。
			return defineClass(name, data, 0, data.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}

	// 获取要加载 的class文件名
	private String getFileName(String name) {
		int index = name.lastIndexOf('.');
		if (index == -1) {
			return name + ".class";
		} else {
			return name.substring(index + 1) + ".class";
		}
	}

}