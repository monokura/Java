package com.example.webchat.webapp.struts.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.example.webchat.db.bean.MessageBean;
import com.example.webchat.db.dao.MessageDao;
import com.example.webchat.db.dao.MessageDaoOnMemoryImpl;

public class SaveMessageAction extends Action{
	public ActionForward execute(
			ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response){
		DynaActionForm form = (DynaActionForm) actionForm;
		Date date = new Date();
		String name = form.getString("name");
		String message = form.getString("message");
		MessageDao messageDao = MessageDaoOnMemoryImpl.getInstance();
		messageDao.save(new MessageBean(date, name, message));

		// 発言フォームの「名前」の値はそのまま残し, 「メッセージ」の値はクリアする.
		form.set("message", "");

		return mapping.findForward("success");
	}
}