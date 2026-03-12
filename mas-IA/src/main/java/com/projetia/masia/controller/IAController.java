package com.projetia.masia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ia")
public class IAController {

    @GetMapping("gpt")
    public String goToGPT() {
        return "redirect:https://chat.openai.com/";
    }

    @GetMapping("claude")
    public String goToClaude() {
        return "redirect:https://claude.com/";
    }

    @GetMapping("gemini")
    public String goToGemini() {
        return "redirect:https://gemini.google.com/app?hl=fr/";
    }

}
