package kr.leekyusung.service.report.service;

import kr.leekyusung.service.report.dto.ServerResDto;
import kr.leekyusung.service.report.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerService {
    private final ServerRepository serverRepository;

    @Cacheable(value = "server")
    public List<ServerResDto> getServer() {
        return serverRepository.findAll()
                .stream().map(serverEntity -> serverEntity.toServerResDto())
                .toList();
    }

    @CacheEvict(value = "server", allEntries = true)
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24) // 24시간 마다 실행
    public void evictCache() {
        // 캐시 삭제
    }
}
