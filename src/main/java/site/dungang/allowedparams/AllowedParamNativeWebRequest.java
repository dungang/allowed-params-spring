package site.dungang.allowedparams;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.web.context.request.NativeWebRequest;

public class AllowedParamNativeWebRequest implements NativeWebRequest  {
	
	private NativeWebRequest nativeWebRequest;
	
	private Map<String, Boolean> allowedMap = new HashMap<String, Boolean>();

	public AllowedParamNativeWebRequest(NativeWebRequest nativeWebRequest, String[] allowedParams) {
		super();
		this.nativeWebRequest = nativeWebRequest;
		if (null != allowedParams) {
			for(String param : allowedParams) {
				 allowedMap.put(param, true);
			}
		}
	}

	/**
	 * @return the nativeWebRequest
	 */
	public NativeWebRequest getNativeWebRequest() {
		return nativeWebRequest;
	}

	@Override
	public String getHeader(String headerName) {
		return nativeWebRequest.getHeader(headerName);
	}

	@Override
	public String[] getHeaderValues(String headerName) {
		return nativeWebRequest.getHeaderValues(headerName);
	}

	@Override
	public Iterator<String> getHeaderNames() {
		return nativeWebRequest.getHeaderNames();
	}

	@Override
	public String getParameter(String paramName) {
		return nativeWebRequest.getParameter(paramName);
	}

	@Override
	public String[] getParameterValues(String paramName) {
		return nativeWebRequest.getParameterValues(paramName);
	}

	/**
	 * 只允许在 allowed params 才可以被获取
	 */
	@Override
	public Iterator<String> getParameterNames() {
		List<String> names = new ArrayList<String>();
		Iterator<String> it = nativeWebRequest.getParameterNames();
		while(it.hasNext()) {
			String name = it.next();
			if(null !=allowedMap.get(name)) {
				names.add(name);
			}
		}
		return  names.iterator();
	}

	/**
	 * 只允许在 allowed params 才可以被获取
	 */
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = nativeWebRequest.getParameterMap();
		for(String name : map.keySet()) {
			if ( null == allowedMap.get(name)) {
				map.remove(name);
			}
		}
		return map;
	}

	@Override
	public Locale getLocale() {
		return nativeWebRequest.getLocale();
	}

	@Override
	public String getContextPath() {
		return nativeWebRequest.getContextPath();
	}

	@Override
	public String getRemoteUser() {
		return nativeWebRequest.getRemoteUser();
	}

	@Override
	public Principal getUserPrincipal() {
		return nativeWebRequest.getUserPrincipal();
	}

	@Override
	public boolean isUserInRole(String role) {
		return nativeWebRequest.isUserInRole(role);
	}

	@Override
	public boolean isSecure() {
		return nativeWebRequest.isSecure();
	}

	@Override
	public boolean checkNotModified(long lastModifiedTimestamp) {
		return nativeWebRequest.checkNotModified(lastModifiedTimestamp);
	}

	@Override
	public boolean checkNotModified(String etag) {
		return nativeWebRequest.checkNotModified(etag);
	}

	@Override
	public boolean checkNotModified(String etag, long lastModifiedTimestamp) {
		return nativeWebRequest.checkNotModified(etag, lastModifiedTimestamp);
	}

	@Override
	public String getDescription(boolean includeClientInfo) {
		return nativeWebRequest.getDescription(includeClientInfo);
	}

	@Override
	public Object getAttribute(String name, int scope) {
		return nativeWebRequest.getAttribute(name, scope);
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {
		nativeWebRequest.setAttribute(name, value, scope);
	}

	@Override
	public void removeAttribute(String name, int scope) {
		nativeWebRequest.removeAttribute(name, scope);
	}

	@Override
	public String[] getAttributeNames(int scope) {
		return nativeWebRequest.getAttributeNames(scope);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback, int scope) {
		nativeWebRequest.registerDestructionCallback(name, callback, scope);
	}

	@Override
	public Object resolveReference(String key) {
		return nativeWebRequest.resolveReference(key);
	}

	@Override
	public String getSessionId() {
		return nativeWebRequest.getSessionId();
	}

	@Override
	public Object getSessionMutex() {
		return nativeWebRequest.getSessionMutex();
	}

	@Override
	public Object getNativeRequest() {
		return nativeWebRequest.getNativeRequest();
	}

	@Override
	public Object getNativeResponse() {
		return nativeWebRequest.getNativeResponse();
	}

	@Override
	public <T> T getNativeRequest(Class<T> requiredType) {
		return nativeWebRequest.getNativeRequest(requiredType);
	}

	@Override
	public <T> T getNativeResponse(Class<T> requiredType) {
		return nativeWebRequest.getNativeResponse(requiredType);
	}

	
}