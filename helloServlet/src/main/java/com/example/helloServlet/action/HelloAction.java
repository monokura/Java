package com.example.helloServlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.example.helloServlet.form.HelloForm;

public class HelloAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	        throws Exception {
			System.out.println("========== call Hello Action ==========");

			HelloForm helloForm = (HelloForm) form;
			helloForm.setMessage("Hello World! Struts");

			return mapping.findForward("success");
		}
}
