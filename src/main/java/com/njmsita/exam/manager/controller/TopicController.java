package com.njmsita.exam.manager.controller;

import com.njmsita.exam.manager.service.ebi.TopicEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TopicController
{
    @Autowired
    private TopicEbi topicEbi;
}
