package site.dungang.allowedparams;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllowedParamWrapper extends  HttpServletRequestWrapper  {
	
	private static Logger logger = LoggerFactory.getLogger(AllowedParamWrapper.class);
		
	protected Map<String, Boolean> allowedMap = new HashMap<String, Boolean>();


	public AllowedParamWrapper(HttpServletRequest request, String[] allowedParams) {
		super(request);
		if (null != allowedParams) {
			for(String param : allowedParams) {
				logger.debug("allowed params : " + param);
				 allowedMap.put(param, true);
			}
		}
	}


	@Override
	public Object getAttribute(String name) {
		logger.debug("getAttribute : " + name);
		return super.getAttribute(name);
	}


	@Override
	public Enumeration<String> getAttributeNames() {
		logger.debug("getAttributeNames : ");
		return super.getAttributeNames();
	}


	@Override
	public String getParameter(String name) {
		logger.debug("getParameter : " + name);
		return super.getParameter(name);
	}


	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = super.getParameterMap();
		for(String name : map.keySet()) {
			logger.debug("getParameterMap > checked param name : " + name);
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
			logger.debug("getParameterNames > checked param name : " + name);
			if(null !=allowedMap.get(name)) {
				names.addElement(name);
			}
		}
		return names.elements();
	}


	@Override
	public String[] getParameterValues(String name) {
		logger.debug("getParameterValues : " + name);
		return super.getParameterValues(name);
	}
	
}