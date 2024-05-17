package com.c_comachi.inused.domain.post.repository;

import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findAllByOrderByCreatedAtDesc();
    Optional<PostEntity> findById(Long id);
    List<PostEntity> findAllByOrderByLastRepostingDesc();
    List<PostEntity> findAllByTitleContainingOrContentContainingOrderByLastRepostingDesc(String title, String content);
    List<PostEntity> findAllByUserEmail(String email);
    List<PostEntity> findAllByCategoryId(Long categoryId);

    boolean existsById(Long id);

//    Optional<PostEntity> postOptional = postsRepository.findById(postId);
//    if (postOptional.isPresent()) {
//        PostEntity post = postOptional.get();
//        postsRepository.delete(post);
//        // 여기에서 post 객체 사용 가능
//    } else {
//        // post가 존재하지 않을 경우의 처리
//    }


    void deleteById(Long id);
}
