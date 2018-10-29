package com.example.rabbitmq.controller;


import com.example.rabbitmq.model.Employee;
import com.example.rabbitmq.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/rabbit")
public class RabbitMqWebController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @ResponseBody
    @RequestMapping(path = "/sendMsg", method = RequestMethod.GET)
    public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {
        Employee emp = new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);

        rabbitMQSender.send(emp);
        return "Message sent successfully";
    }

}
