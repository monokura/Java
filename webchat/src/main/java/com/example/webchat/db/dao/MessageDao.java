package com.example.webchat.db.dao;

import com.example.webchat.db.bean.MessageBean;

public interface MessageDao {
	// チャットでの発言を保存
	public void save(MessageBean message);

	// チャットでの発言データを返す
	public MessageBean[] getAll();
}
