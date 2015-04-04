package tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
public class CopyObject {

	public static Object copyProperties(Object rtuObject, Object object){
		Class classType = object.getClass();
		Class rtuClassType = rtuObject.getClass();


		Field fields[] =classType.getDeclaredFields();

		for(int i=0;i<fields.length;i++){
				Field field = fields[i];
				String fieldName = field.getName();
				String firstLetter = fieldName.substring(0,1).toUpperCase();

				String getMethodName = "get"+firstLetter+fieldName.substring(1);
				String setMethodName = "set"+firstLetter+fieldName.substring(1);


			try {

				Method getMethod = classType.getMethod(getMethodName,new Class[]{});
				Method setMethod = rtuClassType.getMethod(setMethodName,new Class[]{field.getType()});
				Object value = getMethod.invoke(object,new Object[]{});
				if (null!=value){
					setMethod.invoke(rtuObject,new Object[]{value});
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rtuObject;
	}
}