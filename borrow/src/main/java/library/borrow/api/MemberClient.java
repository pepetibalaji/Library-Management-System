package library.borrow.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import library.borrow.config.FeignClientConfig;
import library.borrow.dto.MemberResponse;

@FeignClient(name = "gateway-service",contextId = "memberClient",url="http://localhost:8081",configuration = FeignClientConfig.class)
public interface MemberClient {
    @GetMapping("/profile")
    MemberResponse getMember();
}
