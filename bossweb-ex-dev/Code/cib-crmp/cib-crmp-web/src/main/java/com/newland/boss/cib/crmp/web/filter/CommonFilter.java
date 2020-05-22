package com.newland.boss.cib.crmp.web.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.newland.global_common.util.PropertiesUtils;

public class CommonFilter implements Filter {
	
	protected static Logger log = Logger.getLogger(CommonFilter.class);
	
	private static Properties property = new Properties();
	protected String[] refererFilters = null;
	
	@Override
	public void destroy() {
		refererFilters = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String referer = request.getHeader("Referer");
		if(referer != null) {
			boolean flag = false;
			for (String refererFilter : refererFilters) {
				if(referer.startsWith(refererFilter)) {
					flag = true;
				}
			}
			if(!flag) {
				request.getRequestDispatcher("/rest/commonError").forward(req, resp);
				return;
			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		log.debug("CommonFilter init");
		if(refererFilters == null) {
			Object in = null;
			try {
				ClassLoader e = Thread.currentThread().getContextClassLoader();
				in = e.getResourceAsStream("refererFilter.properties");
				if (in == null) {
					String path = PropertiesUtils.class.getClassLoader().getResource("").getPath();
					in = new FileInputStream(path + "refererFilter.properties");
				}

				property.load((InputStream) in);
				String refererFilter = property.getProperty("refererFilter");
				refererFilters = refererFilter.split("[|]");
				log.debug("CommonFilter refererFilter -- " + refererFilter);
			} catch (IOException arg10) {
				log.error(arg10);
			} finally {
				if (in != null) {
					try {
						((InputStream) in).close();
					} catch (IOException arg9) {
						log.error(arg9);
					}
				}

			}
			
		}
	}
}
