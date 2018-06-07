package site.binghai.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.entity.Diary;

import java.util.List;

/**
 * Created by IceSea on 2018/5/13.
 * GitHub: https://github.com/IceSeaOnly
 */
public interface DiaryDao extends JpaRepository<Diary,Long> {
    List<Diary> findAllByOrderByCreatedDesc(Pageable pageable);
}
