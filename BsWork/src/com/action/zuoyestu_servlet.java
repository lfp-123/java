package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DB;
import com.orm.Tstu;
import com.orm.Tzuoye;
import com.orm.Tzuoyestu;
import com.service.liuService;

public class zuoyestu_servlet  extends HttpServlet
{
	@Override
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("zuoyestu_tijiao"))
		{
			zuoyestu_tijiao(req, res);
		}
		if(type.endsWith("zuoyestu_mime"))
		{
			zuoyestu_mime(req, res);
		}
		if(type.endsWith("zuoyestu_all"))
		{
			zuoyestu_all(req, res);
		}
		if(type.endsWith("zuoyestu_pingyue"))
		{
			zuoyestu_pingyue(req, res);
		}
	}
	
	
	public void zuoyestu_tijiao(HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session=req.getSession();
		Tstu stu=(Tstu)session.getAttribute("stu");
		
		String mingcheng=req.getParameter("mingcheng");
		String fujian=req.getParameter("fujian");
		String fujianYuanshiming=req.getParameter("fujianYuanshiming");
		
		String shijian_shangchuan=new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
	    String piyueneirong="";
	    String shijian_pinyue="";
	    int stu_id=stu.getId();
		
		String sql="insert into t_zuoyestu values(?,?,?,?,?,?,?)";
		Object[] params={mingcheng,fujian,fujianYuanshiming,shijian_shangchuan,piyueneirong,shijian_pinyue,stu_id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
        
	}
	

	public void zuoyestu_mime(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		Tstu stu=(Tstu)session.getAttribute("stu");
		
		List zuoyestuList=new ArrayList();
		String sql="select * from t_zuoyestu where stu_id=?";
		Object[] params={stu.getId()};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tzuoyestu zuoyestu=new Tzuoyestu();
				
				zuoyestu.setId(rs.getInt("id"));
				zuoyestu.setMingcheng(rs.getString("mingcheng"));
				zuoyestu.setFujian(rs.getString("fujian"));
				zuoyestu.setFujianYuanshiming(rs.getString("fujianYuanshiming"));
				
				zuoyestu.setShijian_shangchuan(rs.getString("shijian_shangchuan"));
				zuoyestu.setPiyueneirong(rs.getString("piyueneirong"));
				zuoyestu.setShijian_pinyue(rs.getString("shijian_pinyue"));
				zuoyestu.setStu_id(rs.getInt("stu_id"));
				
				zuoyestu.setStu(liuService.getStu(rs.getInt("stu_id")));
				
				zuoyestuList.add(zuoyestu);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("zuoyestuList", zuoyestuList);
		req.getRequestDispatcher("qiantai/zuoyestu/zuoyestu_mine.jsp").forward(req, res);
	}
	
	

	public void zuoyestu_all(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		
		List zuoyestuList=new ArrayList();
		String sql="select * from t_zuoyestu";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tzuoyestu zuoyestu=new Tzuoyestu();
				
				zuoyestu.setId(rs.getInt("id"));
				zuoyestu.setMingcheng(rs.getString("mingcheng"));
				zuoyestu.setFujian(rs.getString("fujian"));
				zuoyestu.setFujianYuanshiming(rs.getString("fujianYuanshiming"));
				
				zuoyestu.setShijian_shangchuan(rs.getString("shijian_shangchuan"));
				zuoyestu.setPiyueneirong(rs.getString("piyueneirong"));
				zuoyestu.setShijian_pinyue(rs.getString("shijian_pinyue"));
				zuoyestu.setStu_id(rs.getInt("stu_id"));
				
				zuoyestu.setStu(liuService.getStu(rs.getInt("stu_id")));
				
				zuoyestuList.add(zuoyestu);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("zuoyestuList", zuoyestuList);
		req.getRequestDispatcher("qiantai/zuoyestu/zuoyestu_all.jsp").forward(req, res);
	}
	
	
	public void zuoyestu_pingyue(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		req.setCharacterEncoding("utf-8");
		String piyueneirong=req.getParameter("piyueneirong");
		String shijian_pinyue=req.getParameter("shijian_pinyue");

		int id=Integer.parseInt(req.getParameter("id"));
		
		String sql="update t_zuoyestu set piyueneirong=?,shijian_pinyue=? where id=?";
		Object[] params={piyueneirong,shijian_pinyue,id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		req.setAttribute("msg", "作业评阅完毕");
		res.sendRedirect("/bs/zuoyestu?type=zuoyestu_all");
		
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
