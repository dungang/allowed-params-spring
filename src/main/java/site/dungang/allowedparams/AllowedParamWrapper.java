package site.dungang.allowedparams;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AllowedParamWrapper extends  HttpServletRequestWrapper  {
			
	protected Map<String, Boolean> allowedMap = new HashMap<String, Boolean>();


	public AllowedParamWrapper(HttpServletRequest request, String[] allowedParams) {
		super(request);
		if (null != allowedParams) {
			for(String param : allowedParams) {
				 allowedMap.put(param, true);
			}
		}
	}

	@Override
	public String getParameter(String name) {
		if(null !=allowedMap.get(name)) {
			return super.getParameter(name);
		}
		return null;
	}


	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = super.getParameterMap();
		for(String name : map.keySet()) {
			if ( null == allowedMap.get(name)) {
				map.remove(name);
			}
		}
		return map;
	}


	@Override
	public Enumeration<String> getParameterNames() {
		Vector<String> names = new Vector<String>();
		Enumeration<String> enumer = super.getParameterNames();
		while(enumer.hasMoreElements()) {
			String name = enumer.nextElement();
			if(null !=allowedMap.get(name)) {
				names.addElement(name);
			}
		}
		return names.elements();
	}


	@Override
	public String[] getParameterValues(String name) {
		if ( null == allowedMap.get(name)) {
			return super.getParameterValues(name);
		}
		return null;
	}
	
}