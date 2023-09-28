package kr.leekyusung.service.report.repository;

import kr.leekyusung.service.report.entity.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<ServerEntity, Long> {
}
