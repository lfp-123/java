package com.newland.boss.cib.crmp.domain;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class DomainTest {
	
	private static Map<String, String> map = new HashMap<String, String>();	
	static {
		map.put("toString", "1");
	}
	
	@Test
	public void test() throws Exception {
		List<String> classList = new ArrayList<String>();
		String pack = "com.newland.boss.cib.crmp.domain";
		getAllClass(pack, classList);
		if(classList.isEmpty()) {
			return;
		}
		invokeMethod(classList);
	}
	
	private void getAllClass(String pack, List<String> classList) throws Exception {
		String packPath = pack.replaceAll("[.]", "/");
		Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packPath);
		File[] files = null;
		while (dirs.hasMoreElements()) {
			URL url = (URL) dirs.nextElement();
			if(url.getPath().contains("test-classes")) {
				continue;
			}
			String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
			File dir = new File(filePath);
			files = dir.listFiles();
		}
		if(files == null || files.length == 0) {
			return;
		}
		for (File file : files) {
			if(file.isFile()) {
				if(file.getName().contains("StandardCdrImpl")) {
					continue;
				}
				if(file.getName().contains("CdrFieldDef")) {
					continue;
				}
				if(file.getName().endsWith(".class")) {
					String className = file.getName().replace(".class", "");
					classList.add(pack + "." + className);
				}
			} else if(file.isDirectory()) {
				getAllClass(pack + "." + file.getName(), classList);
			}
		}
	}
	
	private void invokeMethod(List<String> classList) throws Exception {
		for (String className : classList) {
			Class<?> clazz = Class.forName(className);
			if (clazz.isInterface()) {
				continue;
			}
			boolean isAbs = Modifier.isAbstract(clazz.getModifiers());
			if (isAbs) {
				continue;
			}
			boolean isInner = false;
			if(className.contains("$")) {
				isInner = true;
			}
//			System.out.println("isInner = " + isInner);
			Object obj = null;
			if(isInner) {
				String outClassName = className.substring(0, className.indexOf("$"));
				Class<?> outClass = Class.forName(outClassName);
				if(clazz.getDeclaredConstructors()[0].getParameterTypes().length == 1) {
					obj = clazz.getDeclaredConstructor(outClass).newInstance(outClass.newInstance());
				} else if (clazz.getDeclaredConstructors()[0].getParameterTypes().length == 0) {
					obj = clazz.getDeclaredConstructor().newInstance();
				}
			} else {
				obj = invokeNewInstance(clazz);
			}
			System.out.println(className);
			Method[] methons = clazz.getMethods();
			invokeSetter(obj, methons);
			invokeGetter(obj, methons);
			for (Method method : methons) {
				Class<?>[] paramClass = method.getParameterTypes();
				if(paramClass != null && paramClass.length == 0) {
					String methodName = method.getName();
					if(map.get(methodName) != null) {
						method.invoke(obj);
					} else if ("equals".equals(methodName)) {
						method.invoke(obj, obj);
					}
				}
			}
		}
	}
	
	private Object invokeNewInstance(Class<?> clazz) throws Exception {
		Constructor<?>[] constructors = clazz.getConstructors();
		Object obj = null;
		if(constructors != null && constructors.length > 0) {
			for (Constructor<?> constructor : constructors) {
				if(constructor.getParameterTypes().length > 0) {
					Object[] param = new Object[constructor.getParameterTypes().length];
					for (int i = 0; i < constructor.getParameterTypes().length; i++) {
						Class<?> paramClass = constructor.getParameterTypes()[i];
						String paramClassName = paramClass.getName();
						if("java.lang.Integer".equals(paramClassName) || "int".equals(paramClassName)) {
							param[i] = 1;
						} else if("java.lang.Long".equals(paramClassName) || "long".equals(paramClassName)) {
							param[i] = 2L;
						} else if("java.lang.String".equals(paramClassName)) {
							param[i] = "2";
						} else if("java.lang.Class".equals(paramClassName)) {
							param[i] = java.lang.Class.class;
						} else {
							param[i] = paramClass.newInstance();
						}
					}
					obj = constructor.newInstance(param);
				} else {
					obj = clazz.newInstance();
				}
			}
		}
		return obj;
	}
	
	private void invokeSetter(Object obj, Method[] methons) throws Exception {
		for (Method method : methons) {
			String methodName = method.getName();
			if(methodName.startsWith("set") || methodName.startsWith("append")) {
				Class<?>[] paramClass = method.getParameterTypes();
				if(paramClass != null && paramClass.length == 1) {
					String paramClassName = paramClass[0].getName();
					Object val = getDefaultVal(paramClassName);
					if(val != null) {
						method.invoke(obj, val);
					} else {
						if("java.util.List".equals(paramClassName)) {
							if(method.getGenericParameterTypes().length == 1) {
								Type genericType = method.getGenericParameterTypes()[0];
								if(genericType instanceof ParameterizedType){
									ParameterizedType pt = (ParameterizedType) genericType;
									Class<?> genericClass = (Class<?>)pt.getActualTypeArguments()[0];
									val = getDefaultVal(genericClass.getName());
									List a = new ArrayList();
									if(val != null) {
										a.add(val);
									} else {
										Object p = invokeNewInstance(genericClass);
										a.add(p);
									}
									method.invoke(obj, a);
								}
							} else {
								method.invoke(obj, new ArrayList());
							}
						} else if("java.util.Map".equals(paramClassName)) {
							Map a = new HashMap();
							method.invoke(obj, a);
						} else if("java.lang.Class".equals(paramClassName)) {
							method.invoke(obj, java.lang.Class.class);
						} else if("java.sql.Blob".equals(paramClassName)) {
							//method.invoke(obj, new byte[0])
						} else if("java.io.File".equals(paramClassName)) {
							method.invoke(obj, new File(""));
						} else if(paramClass[0].isArray()) {
							method.invoke(obj, Array.newInstance(paramClass[0].getComponentType(), 20));
						} else {
							method.invoke(obj, paramClass[0].newInstance());
						}
					}
				}
			}
		}
	}
	
	private Object getDefaultVal(String className) {
		Object val = null;
		if("java.lang.Integer".equals(className) || "int".equals(className)) {
			val = 1;
		} else if ("java.lang.Long".equals(className) || "long".equals(className)) {
			val = 2L;
		} else if ("java.lang.Float".equals(className) || "float".equals(className)) {
			val = 2F;
		} else if ("java.lang.Double".equals(className) || "double".equals(className)) {
			val = 2D;
		} else if ("java.lang.Boolean".equals(className) || "boolean".equals(className)) {
			val = false;
		} else if ("java.lang.Byte".equals(className) || "byte".equals(className)) {
			val = 1;
		} else if("java.lang.String".equals(className)) {
			val = "2";
		}
		return val;
	}
	
	private void invokeGetter(Object obj, Method[] methons) throws Exception {
		for (Method method : methons) {
			String methodName = method.getName();
			if(methodName.startsWith("get")) {
				Class<?>[] paramClass = method.getParameterTypes();
				if(paramClass != null && paramClass.length == 0) {
					method.invoke(obj);
				} else if(paramClass != null && paramClass.length == 1) {
					String paramClassName = paramClass[0].getName();
					Object val = getDefaultVal(paramClassName);
					if(val != null) {
						method.invoke(obj, val);
					} else {
						if("java.util.List".equals(paramClassName)) {
							if(method.getGenericParameterTypes().length == 1) {
								Type genericType = method.getGenericParameterTypes()[0];
								if(genericType instanceof ParameterizedType){
									ParameterizedType pt = (ParameterizedType) genericType;
									Class<?> genericClass = (Class<?>)pt.getActualTypeArguments()[0];
									val = getDefaultVal(genericClass.getName());
									List a = new ArrayList();
									if(val != null) {
										a.add(val);
									} else {
										Object p = invokeNewInstance(genericClass);
										a.add(p);
									}
									method.invoke(obj, a);
								}
							} else {
								method.invoke(obj, new ArrayList());
							}
						} else if(paramClass[0].isArray()) {
							method.invoke(obj, Array.newInstance(paramClass[0].getComponentType(), 20));
						} else {
							method.invoke(obj, paramClass[0].newInstance());
						}
					}
				}
			}
		}
	}
	
}
