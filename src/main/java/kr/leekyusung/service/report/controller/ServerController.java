package kr.leekyusung.service.report.controller;

import kr.leekyusung.service.report.dto.ServerResDto;
import kr.leekyusung.service.report.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/server")
@RequestMapping
@RequiredArgsConstructor
public class ServerController {
    private final ServerService serverService;

    @GetMapping
    public ResponseEntity<List<ServerResDto>> getServer() {
        return ResponseEntity.ok().body(serverService.getServer());
    }
}
