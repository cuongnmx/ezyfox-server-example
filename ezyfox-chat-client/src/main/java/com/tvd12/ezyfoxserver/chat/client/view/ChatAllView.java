/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tvd12.ezyfoxserver.chat.client.view;

import com.tvd12.ezyfoxserver.chat.client.constant.ChatEventType;
import com.tvd12.ezyfoxserver.chat.client.constant.ChatViewPath;
import com.tvd12.ezyfoxserver.chat.client.controller.ChatAllController;
import com.tvd12.ezyfoxserver.chat.client.controller.ChatController;
import com.tvd12.ezyfoxserver.chat.client.data.ChatMessage;
import com.tvd12.ezyfoxserver.chat.client.model.ChatModel;

import javafx.scene.control.ListView;

/**
 *
 * @author MyPC
 */
@SuppressWarnings("restriction")
public class ChatAllView extends ChatAbstractView {

	@Override
	protected void beforeShow() {
		getView(ChatEventType.LOGIN, ChatModel.SUCCESS).hide();
	}
	
	@Override
	public void update(Object data) {
		ListView<ChatMessage> listView  = getMessageListView();
		listView.getItems().add((ChatMessage) data);
	}
	
	@SuppressWarnings("unchecked")
	private ListView<ChatMessage> getMessageListView() {
		return (ListView<ChatMessage>) loader.getNamespace().get("messageList");
	}
	
	@Override
	protected ChatController getController() {
		return new ChatAllController();
	}
	
	@Override
	protected String getViewPath() {
		return ChatViewPath.CHAT_ALL;
	}
	
}
