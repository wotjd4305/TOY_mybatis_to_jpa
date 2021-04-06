package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatroomRequest {
	private long chatroomId;
	private int userId;
	
	@Builder
	public ChatroomRequest(long chatroomId, int userId) {
		this.chatroomId = chatroomId;
		this.userId = userId;
	}
}