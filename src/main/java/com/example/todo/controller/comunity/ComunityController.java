package com.example.todo.controller.comunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.todo.service.comunity.ComunityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/comunity")
public class ComunityController {
    @Autowired
    ComunityService comunityService;
    

    //コミュニティ画面
    @GetMapping("")
    public String comunity(Model model) {
        //すべての質問を取得
        model.addAttribute("questions", comunityService.findAllQuestion());
        return "comunity/comunity";
    }

    @GetMapping("/{questionId}")
    public String questionDetail(@PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("Id", questionId);
        return "comunity/detail";
    }

    @PostMapping("/post")
    public String postMethodName(ComunityForm form) {
        var entity = form.toEntity();
        //質問の保存
        comunityService.createQuestion(entity);
        return "redirect:/comunity";
    }
    
}
