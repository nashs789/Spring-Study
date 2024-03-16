package com.example.messagingstompwebsocket;

import com.example.messagingstompwebsocket.dto.MessageDto;
import com.example.messagingstompwebsocket.entity.Message;
import com.example.messagingstompwebsocket.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Slf4j
@Controller
@AllArgsConstructor
public class GreetingController {

	final private MessageRepository messageRepository;
	final private SimpMessagingTemplate template;

	@MessageMapping("/chatting")
	@SendTo()
	public void greeting(MessageDto msgDto) throws Exception {

		Message message = Message.builder()
				.userName(msgDto.getUserName())
				.content(msgDto.getContent())
				.roomNo(msgDto.getRoomNo())
				.build();

		Message savedMsg = messageRepository.save(message);
		StringBuilder sb = new StringBuilder();

		String resMsg = sb.append(savedMsg.getUserName())
						  .append(": ")
						  .append(savedMsg.getContent())
						  .toString();

		Thread.sleep(500); // simulated delay
		log.info("msg = {} ", savedMsg);

		template.convertAndSend("/topic/greetings/" + savedMsg.getRoomNo(), resMsg);
	}

	/*
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public ResponseEntity greeting(MessageDto msgDto) throws Exception {

		Message message = Message.builder()
				.user_name(msgDto.getUser_name())
				.content(msgDto.getContent())
				.build();

		Message savedMsg = messageRepository.save(message);
		StringBuilder sb = new StringBuilder();

		String resMsg = sb.append(savedMsg.getUser_name())
						  .append(": ")
						  .append(savedMsg.getContent())
						  .toString();

		Thread.sleep(500); // simulated delay

		return ResponseEntity.status(HttpStatus.OK)
							 .body(resMsg);
	}
	 */
}
