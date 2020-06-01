package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.Ttea;
import com.orm.Tzuoye;
import com.service.liuService;

public class zuoye_servlet  extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("zuoye_fabu"))
		{
			zuoye_fabu(req, res);
		}
		if(type.endsWith("zuoye_mana"))
		{
			zuoye_mana(req, res);
		}
		if(type.endsWith("zuoye_del"))
		{
			zuoye_del(req, res);
		}
		if(type.endsWith("zuoye_all"))
		{
			zuoye_all(req, res);
		}
	}
	
	
	public void zuoye_fabu(HttpServletRequest req,HttpServletResponse res)
	{
		Ttea tea=(Ttea)req.getSession().getAttribute("tea");
		
		String mingcheng=req.getParameter("mingcheng");
		String fujian=req.getParameter("fujian");
		String fujianYuanshiming=req.getParameter("fujianYuanshiming");
		
		String shijian=new Date().toLocaleString();
		int tea_id=tea.getId();
		
		
		String sql="insert into t_zuoye values(?,?,?,?,?)";
		Object[] params={mingcheng,fujian,fujianYuanshiming,shijian,tea_id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "作业发布完毕");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
        
	}
	
	
	public void zuoye_del(HttpServletRequest req,HttpServletResponse res)
	{
		String sql="delete from t_zuoye where id=?";
		Object[] params={Integer.parseInt(req.getParameter("id"))};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "作业删除完毕");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}

	public void zuoye_mana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List zuoyeList=new ArrayList();
		String sql="select * from t_zuoye";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tzuoye zuoye=new Tzuoye();
				zuoye.setId(rs.getInt("id"));
				zuoye.setMingcheng(rs.getString("mingcheng"));
				zuoye.setFujian(rs.getString("fujian"));
				zuoye.setFujianYuanshiming(rs.getString("fujianYuanshiming"));
				zuoye.setShijian(rs.getString("shijian"));
				zuoye.setTea_id(rs.getInt("tea_id"));
				zuoye.setTea(liuService.getTea(rs.getInt("tea_id")));
				
				zuoyeList.add(zuoye);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		req.setAttribute("zuoyeList", zuoyeList);
		req.getRequestDispatcher("qiantai/zuoye/zuoye_mana.jsp").forward(req, res);
	}
	
	
	public void zuoye_all(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List zuoyeList=new ArrayList();
		String sql="select * from t_zuoye";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tzuoye zuoye=new Tzuoye();
				
				zuoye.setId(rs.getInt("id"));
				zuoye.setMingcheng(rs.getString("mingcheng"));
				zuoye.setFujian(rs.getString("fujian"));
				zuoye.setFujianYuanshiming(rs.getString("fujianYuanshiming"));
				zuoye.setShijian(rs.getString("shijian"));
				zuoye.setTea_id(rs.getInt("tea_id"));
				zuoye.setTea(liuService.getTea(rs.getInt("tea_id")));
				
				zuoyeList.add(zuoye);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("zuoyeList", zuoyeList);
		req.getRequestDispatcher("qiantai/zuoye/zuoye_all.jsp").forward(req, res);
	}
	

	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
