package com.example.todo.controller.comunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.todo.exception.QuestionNotFoundException;
import com.example.todo.repository.AnswerRepository;
import com.example.todo.service.comunity.ComunityEntity;
import com.example.todo.service.comunity.ComunityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/comunity")
public class ComunityController {

    private final AnswerRepository answerRepository;
    @Autowired
    ComunityService comunityService;


    ComunityController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
    

    //コミュニティ画面
    // 質問一覧取得のページネーション対応
    @GetMapping("")
    public String comunity(Model model , @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5;
        //質問を取得
        Page<ComunityEntity> questionlist = comunityService.findQuestionPage(page, pageSize);
        var questionlist_content = questionlist.getContent().stream().map(ComunityDTO::toDTO).toList();
        model.addAttribute("questions", questionlist_content);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", questionlist.getTotalPages());
        model.addAttribute("hasNext", questionlist.hasNext());
        model.addAttribute("hasPrevious", questionlist.hasPrevious());
        return "comunity/comunity";
    }
    // @GetMapping("")
    // public String comunity(Model model) {
    //     //すべての質問を取得
    //     var questionlist = comunityService.findAllQuestion().stream().map(ComunityDTO::toDTO).toList();
    //     model.addAttribute("questions", questionlist);
    //     return "comunity/comunity";
    // }

    @GetMapping("/{questionId}")
    public String questionDetail(@PathVariable("questionId") Long questionId, Model model) {
        var question = ComunityDTO.toDTO(comunityService.findById(questionId).orElseThrow(() -> new QuestionNotFoundException(questionId)));
        //質問に対する回答を取得
        var answers = comunityService.findAnswerbyId(questionId).stream().map(AnswerDTO::toDTO).toList();
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        return "comunity/detail";
    }

    @PostMapping("")
    public String postMethodName(@Validated ComunityForm form,BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            return comunity(model,0);
        }
        var entity = form.toEntity();
        //質問の保存
        comunityService.createQuestion(entity);
        return "redirect:/comunity";
    }

    //質問に対する回答の投稿
    @PostMapping("/answer/{questionId}")
    public String answer(@PathVariable("questionId") Long questionId, @Validated AnswerForm form,BindingResult bindingResult,Model model
    ) {
        if (bindingResult.hasErrors()){
            var question = ComunityDTO.toDTO(comunityService.findById(questionId).orElseThrow());
            var answers = comunityService.findAnswerbyId(questionId).stream().map(AnswerDTO::toDTO).toList();
            model.addAttribute("question", question);
            model.addAttribute("answers", answers);
            return "comunity/detail";
        }
        var entity = form.toEntity(questionId);
        //質問の保存
        answerRepository.save(entity);
        return "redirect:/comunity/"+questionId;
    }

}
