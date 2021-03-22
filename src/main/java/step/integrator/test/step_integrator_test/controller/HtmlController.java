package step.integrator.test.step_integrator_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import step.integrator.test.step_integrator_test.exception.FailedToFetchDataException;
import step.integrator.test.step_integrator_test.exception.IllegalRequestParametersException;
import step.integrator.test.step_integrator_test.repo.LogWriter;
import step.integrator.test.step_integrator_test.service.StepIntegratorTestService;

@Controller
@RequestMapping(value = "/step")
public class HtmlController {

    private StepIntegratorTestService service;
    private LogWriter logWriter;

    @Autowired
    public HtmlController(StepIntegratorTestService service, LogWriter logWriter) {
        this.service = service;
        this.logWriter = logWriter;
    }


    @GetMapping("")
    public String hello(@RequestParam(name = "email", required = false) String email,
                        @RequestParam(name = "phone", required = false) String phone,
                        Model model) throws IllegalRequestParametersException, FailedToFetchDataException {
        if (email == null && phone == null) {
            throw new IllegalRequestParametersException("Specify phone number or email", null, null);
        } else if (email == null) { //phone is not null
            model.addAttribute("username", service.getUsernameByPhone(phone));
        } else if (phone == null) { //email is not null
            model.addAttribute("username", service.getUsernameByEmail(email));
        } else {
            throw new IllegalRequestParametersException("Provide e-mail or phone number, not both simultaneously", phone, email);
        }
        return "ok";
    }

    @ExceptionHandler(IllegalRequestParametersException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleIoException(IllegalRequestParametersException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        logWriter.fail(400, ex.getPhone(), ex.getEmail());
        return "fail";
    }

    @ExceptionHandler(FailedToFetchDataException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleIoException(FailedToFetchDataException ex,  Model model) {
        model.addAttribute("message", "Couldn't fetch data from rest service");
        logWriter.fail(500, ex.getPhone(), ex.getEmail());
        return "fail";
    }

}
