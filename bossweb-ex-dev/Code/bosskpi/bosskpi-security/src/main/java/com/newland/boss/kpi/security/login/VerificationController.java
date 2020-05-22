package com.newland.boss.kpi.security.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.newland.boss.kpi.constant.Constants;
import com.newland.boss.kpi.entity.User;
import com.newland.boss.kpi.server.AdminService;
import com.newland.boss.kpi.util.ResourceUtil;

@Controller
public class VerificationController {
																		 

	/**
     * 
     */
	private static final int count = 80;

	/**
	 * 定义图形大小(宽)
	 */
	private static final int width = 105;
	/**
	 * 定义图形大小(高)
	 */
	private static final int height = 35;
	/**
	 * 干扰线的长度=1.414*lineWidth
	 */
	private static final int lineWidth = 1;
	
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/VerificationCodeGenerate", method = { RequestMethod.POST, RequestMethod.GET })
	public void VerificationCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 在内存中创建图象
		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		final Graphics2D graphics = (Graphics2D) image.getGraphics();

		// 设定背景颜色
		graphics.setColor(Color.getColor("F8F8F8")); // ---1.Color.WHITE为白色
		graphics.fillRect(0, 0, width, height);// 填充衍射
		// 设定边框颜色
		// ---2.这是以数字型来设置颜色，颜色模式是指使用三种基色：红、绿、蓝，通过三种颜色的调整得出其它各种颜色，这三种基色的值范围为0～255
		graphics.drawRect(0, 0, width - 1, height - 1);
		
		Color[] colors = new Color[] { Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE,
				Color.CYAN };
		Color[] stringColors = new Color[] { Color.BLUE, Color.GREEN, Color.RED, Color.BLACK };
		
		final Random random = new Random();
		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < count; i++) {
			graphics.setColor(colors[random.nextInt(colors.length)]); // ---3.

			final int x = random.nextInt(width);
		    final int y = random.nextInt(height);
		    final int w = random.nextInt(20);
		    final int h = random.nextInt(20);
		    final int signA = random.nextBoolean() ? 1 : -1;
		    final int signB = random.nextBoolean() ? 1 : -1;
		    graphics.drawLine(x, y, x + w * signA, y + h * signB);
		}
		// 取随机产生的认证码(4位数字)
		final String resultCode = exctractRandCode();
		for (int i = 0; i < resultCode.length(); i++) {
			// 将认证码显示到图象中,调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			// 设置字体颜色
			graphics.setColor(stringColors[random.nextInt(stringColors.length)]);
			// 设置字体样式
			graphics.setFont(new Font("Times New Roman", Font.BOLD, 24));
			// 设置字符，字符间距，上边距
			graphics.drawString(String.valueOf(resultCode.charAt(i)), (23 * i) + 8, 26);
		}
		System.out.println(resultCode);
		// 将认证码存入SESSION
		request.getSession().setAttribute(Constants.SESSION_KEY_OF_RAND_CODE, resultCode);
		request.getSession().setAttribute(Constants.SESSION_KEY_OF_RAND_TIME, System.currentTimeMillis());
		// 图象生效
		graphics.dispose();

		// 输出图象到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/**
	 * @return 随机码
	 */
	private String exctractRandCode() {
		final String randCodeType = ResourceUtil.getValue("randCodeType");
		int randCodeLength = Integer.parseInt(ResourceUtil.getValue("randCodeLength"));
		if (randCodeType == null) {
			return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
		} else {
			switch (randCodeType.charAt(0)) {
			case '1':
				return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
			case '2':
				return RandCodeImageEnum.LOWER_CHAR.generateStr(randCodeLength);
			case '3':
				return RandCodeImageEnum.UPPER_CHAR.generateStr(randCodeLength);
			case '4':
				return RandCodeImageEnum.LETTER_CHAR.generateStr(randCodeLength);
			case '5':
				return RandCodeImageEnum.ALL_CHAR.generateStr(randCodeLength);
			default:
				return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
			}
		}
	}

	/**
	 * 描述：根据给定的数字生成不同的颜色
	 * 
	 * @param 这是以数字型来设置颜色
	 *            ，颜色模式是指使用三种基色：红、绿、蓝，通过三种颜色的调整得出其它各种颜色，这三种基色的值范围为0～255
	 * @param 这是以数字型来设置颜色
	 *            ，颜色模式是指使用三种基色：红、绿、蓝，通过三种颜色的调整得出其它各种颜色，这三种基色的值范围为0～255
	 * @return 描述：返回颜色
	 */
	public Color getRandColor(int fc, int bc) { // 取得给定范围随机颜色
		final Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}

		final int r = fc + random.nextInt(bc - fc);
		final int g = fc + random.nextInt(bc - fc);
		final int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);
	}
	
	@RequestMapping(value="/verifyUsernameAndPassword", method={RequestMethod.POST})
	public void verifyUsernameAndPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/json;charset=UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String line = "";
		StringBuffer sb = new StringBuffer("");
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String reqStr = sb.toString();
		br.close();
		
		String verifyStatus = "0";
		String verifyMessage = "校验成功！";
		
		String username = reqStr.split("\\|")[0];
		String password = reqStr.split("\\|")[1];
		String verifyCode = reqStr.split("\\|")[2];
		String sessionVerifyCode = (String)request.getSession().getAttribute(Constants.SESSION_KEY_OF_RAND_CODE);
        long sessionVerifyTime = Long.parseLong(String.valueOf(request.getSession().getAttribute(Constants.SESSION_KEY_OF_RAND_TIME)));
		
        if("false".equals(ResourceUtil.getValue("noCheckFalg"))) {
        	if ((null == verifyCode) || (null == sessionVerifyCode) || (!verifyCode.equalsIgnoreCase(sessionVerifyCode))) {
				verifyStatus = "1003";
				verifyMessage = "验证码错误！";
			} else if ((System.currentTimeMillis() - sessionVerifyTime) > 60*1000) {
				verifyStatus = "1004";
				verifyMessage = "验证码已失效！";
			}
        	if(!"0".equals(verifyStatus)) {
				write(verifyStatus, verifyMessage, null, response);
				return;
			}
        }
        
        String ret = adminService.checkUserLock(password, username, 0);
        if(ret != null) {
        	verifyStatus = "1005";
        	write(verifyStatus, ret, null, response);
			return;
        }
        
		User user = adminService.getOperatorById(Integer.parseInt(username));
		if (null == user) {
			verifyStatus = "1001";
			verifyMessage = "账号不存在或不可用！";
		} else {
			if((!password.equalsIgnoreCase(user.getPassword()))) {
				String errMsg = adminService.loginVerify(password, user.getOperatorId().toString(), 0);
				if(errMsg != null) {
					verifyStatus = "1002";
					verifyMessage = errMsg;
				}
			}
		}
		
		JSONObject json = new JSONObject();
		json.put("verifyStatus", verifyStatus);
		json.put("verifyMessage", verifyMessage);
		if(user != null) {
			json.put("operatorLevel", user.getOperatorLevel());
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	private void write(String verifyStatus, String verifyMessage, User user, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		json.put("verifyStatus", verifyStatus);
		json.put("verifyMessage", verifyMessage);
		if(user != null) {
			json.put("operatorLevel", user.getOperatorLevel());
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
}
