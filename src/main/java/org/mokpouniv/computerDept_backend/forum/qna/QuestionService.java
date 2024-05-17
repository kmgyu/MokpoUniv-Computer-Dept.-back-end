package org.mokpouniv.computerDept_backend.forum.qna;

import lombok.RequiredArgsConstructor;
import org.mokpouniv.computerDept_backend.forum.ForumRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    final ForumRepository forumRepository;



}
