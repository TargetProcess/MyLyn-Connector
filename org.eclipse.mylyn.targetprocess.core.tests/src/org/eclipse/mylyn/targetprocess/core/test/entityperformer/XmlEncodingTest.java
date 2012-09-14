package org.eclipse.mylyn.targetprocess.core.test.entityperformer;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;

public class XmlEncodingTest {
	
	StringBuffer xmlStrBuf = new StringBuffer();
	TransformerFactory tFactory = null;
	Transformer transformer = null;
	Document document = null;

	public static URL getClassLocation(Class<?> c)  
	{  
	    URL url = c.getResource(getShortName(c) + ".class");  
	    if (url == null)  
	    {  
	        return null;  
	    }  
	    String s = url.toExternalForm();  
	    // s most likely ends with a /, then the full class name with . replaced  
	    // with /, and .class. Cut that part off if present. If not also check  
	    // for backslashes instead. If that's also not present just return null  
	   
	    String end = "/" + c.getName().replaceAll("\\.", "/") + ".class";  
	    if (s.endsWith(end))  
	    {  
	        s = s.substring(0, s.length() - end.length());  
	    }  
	    else  
	    {  
	        end = end.replaceAll("/", "\\");  
	        if (s.endsWith(end))  
	        {  
	            s = s.substring(0, s.length() - end.length());  
	        }  
	        else  
	        {  
	            return null;  
	        }  
	    }  
	    // s is now the URL of the location, but possibly with jar: in front and  
	    // a trailing !  
	    if (s.startsWith( "jar:") && s.endsWith("!"))  
	    {  
	        s = s.substring(4, s.length() - 1);  
	    }  
	    try  
	    {  
	        return new URL(s);  
	    }  
	    catch (MalformedURLException e)  
	    {  
	        return null;  
	    }  
	} 
	
	private static String getShortName(Class<?> c) {
		String[] split = c.getName().split("\\.");
		return split[split.length-1];
	}

}
