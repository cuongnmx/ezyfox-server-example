package com.tvd12.ezyfoxserver.chat.command;

import com.tvd12.ezyfox.builder.EzyBuilder;
import com.tvd12.ezyfoxserver.command.EzyCommand;
import com.tvd12.ezyfoxserver.entity.EzySession;
import com.tvd12.ezyfoxserver.entity.EzyUser;

public interface EzyChatResponse<T extends EzyChatResponse<T>> extends EzyCommand<Boolean> {

	T data(Object data);
	
	T command(String command);
    
    T user(EzyUser user);
    
    T users(EzyUser... users);
    
    T users(Iterable<EzyUser> users);
    
    T username(String username);
    
    T usernames(String... usernames);
    
    T usernames(Iterable<String> usernames);
    
    T session(EzySession session);
    
    T sessions(EzySession... sessions);
    
    T sessions(Iterable<EzySession> sessions);
    
    default T data(EzyBuilder<?> builder) {
        return data(builder.build());
    }
	
}
