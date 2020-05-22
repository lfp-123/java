package com.newland.boss.cib.crmp.web.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.newland.boss.kpi.admin.model.Operator;

public class LDAPUtil {

    protected static Logger logger = LogManager.getLogger(LDAPUtil.class);
    
    private LDAPUtil() {
    	
    }
    
	//获取属性
    private static final String[] RETURNED_ATTS = {"sAMAccountName", "sn", "distinguishedName", "ipPhone", "mobile"};
    
    private static String departmentName;
    private static Operator operator;
    
    public static boolean verifyNotesUser(String ldapURL, String notesId, String password) {
    	if(notesId == null || "".equals(notesId)) {
    		return false;
    	}
    	if(!notesId.matches("[0-9a-zA-Z]*")) {
    		return false;
    	}
        Properties env = new Properties();
        String adminName = "cibg\\" + notesId;//认证帐号
        String adminPassword = password;//认证密码
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//"none","simple","strong"
        env.put(Context.SECURITY_PRINCIPAL, adminName);
        env.put(Context.SECURITY_CREDENTIALS, adminPassword);
        env.put(Context.PROVIDER_URL, ldapURL);
        try {
            LdapContext ctx = new InitialLdapContext(env, null);
            SearchControls searchCtls = new SearchControls();
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String searchFilter = "(&(objectCategory=person)(objectClass=user)(saMAccountName=" + notesId + "))";//过滤条件
            String searchBase = "DC=cibg,DC=com";

            searchCtls.setReturningAttributes(RETURNED_ATTS);
            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchCtls);
            while (answer.hasMoreElements()) {
                SearchResult sr = answer.next();
                logger.info(sr.getName());
                Attributes attrs = sr.getAttributes();
                Object obj = attrs.get(RETURNED_ATTS[0]).get();
                if(obj != null) {
                	setResult(attrs);
                	ctx.close();
                	return true;
                }
            }
            ctx.close();
        } catch (NamingException e) {
        	logger.error("Problem searching directory: ", e);
        }
        return false;
    }

    private static void setResult(Attributes attrs) throws NamingException {
    	if(attrs == null) {
    		return;
    	}
    	
        departmentName = attrs.get(RETURNED_ATTS[2]).get() == null ? null : attrs.get(RETURNED_ATTS[2]).get().toString();
    	operator = new Operator();
    	String loginName = attrs.get(RETURNED_ATTS[0]).get() == null ? null : attrs.get(RETURNED_ATTS[0]).get().toString();
    	operator.setLoginName(loginName);
    	operator.setDirectNumber(attrs.get(RETURNED_ATTS[3]).get() == null ? null : attrs.get(RETURNED_ATTS[3]).get().toString());
    	operator.setMobileNumber(attrs.get(RETURNED_ATTS[4]).get() == null ? null : attrs.get(RETURNED_ATTS[4]).get().toString());
    	operator.setOperatorName(attrs.get(RETURNED_ATTS[1]).get() == null ? loginName : attrs.get(RETURNED_ATTS[1]).get().toString());
    }
    
	public static String getDepartmentName() {
		return departmentName;
	}

	public static void setDepartmentName(String departmentName) {
		LDAPUtil.departmentName = departmentName;
	}

	public static Operator getOperator() {
		return operator;
	}

	public static void setOperator(Operator operator) {
		LDAPUtil.operator = operator;
	}
    
}
