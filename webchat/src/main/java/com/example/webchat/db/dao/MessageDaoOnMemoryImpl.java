package com.example.webchat.db.dao;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.example.webchat.db.bean.MessageBean;

public class MessageDaoOnMemoryImpl implements MessageDao{
	// シングルトン
	private static final MessageDaoOnMemoryImpl instance = new MessageDaoOnMemoryImpl();

	// インスタンスを返却
	public static MessageDaoOnMemoryImpl getInstance(){
		return instance;
	}

	// リストの先頭が最も新しいデータ
	private List<MessageBean> messages = Collections.synchronizedList(new LinkedList<MessageBean>());

	private MessageDaoOnMemoryImpl(){
		// brank
	}

	// チャットでの発言を保存
	public void save(MessageBean message){
		messages.add(0, message);
	}

	public MessageBean[] getAll(){
		return messages.toArray(new MessageBean[messages.size()]);
	}
}
