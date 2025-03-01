package com.e.d.model.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IpService {

	public void ipPrint() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String n = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a HH:mm:ss"));
		String ip = req.getHeader("x-forwarded-for");
		if (ip == null || ip.isEmpty()) {
			ip = req.getRemoteAddr();
			if (ip.equals("0:0:0:0:0:0:0:1")) {
				try {
					ip = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(n + "	" + ip);
		System.out.println(n + "	" + parseBrowserInfo(req.getHeader("User-Agent")));
		System.out.println();
	}

	public String parseBrowserInfo(String userAgent) {
		String browserName = "";
		String version = "";

		userAgent = userAgent.toLowerCase();

		if (userAgent.contains("chrome") && !userAgent.contains("edg")) {
			browserName = "구글 크롬";
			version = extractVersion(userAgent, "chrome/");
		} else if (userAgent.contains("safari") && !userAgent.contains("chrome")) {
			browserName = "사파리";
			version = extractVersion(userAgent, "version/");
		} else if (userAgent.contains("firefox")) {
			browserName = "파이어폭스";
			version = extractVersion(userAgent, "firefox/");
		} else if (userAgent.contains("edg")) {
			browserName = "엣지";
			version = extractVersion(userAgent, "edg/");
		} else {
			browserName = "기타 브라우저";
		}

		return version.isEmpty() ? browserName : browserName + " " + version;
	}

	public String extractVersion(String userAgent, String keyword) {
		int start = userAgent.indexOf(keyword) + keyword.length();
		int end = userAgent.indexOf(" ", start);
		if (end == -1) {
			end = userAgent.length();
		}
		return userAgent.substring(start, end);
	}
	
}