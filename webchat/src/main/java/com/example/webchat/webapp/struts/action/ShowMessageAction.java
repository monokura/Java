package com.example.webchat.webapp.struts.action;

import com.example.webchat.db.dao.MessageDao;
import com.example.webchat.db.dao.MessageDaoOnMemoryImpl;

public class ShowMessageAction extends Action{
	public ActionForward execute(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServlet response){
		MessageDao messageDao = MessageDaoOnMemoryImpl.getInstance();

	}
}
