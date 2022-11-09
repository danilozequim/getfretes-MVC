package br.com.global.mobility.Controller.Web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.global.mobility.Model.Request;
import br.com.global.mobility.Model.RequestItem;
import br.com.global.mobility.Service.RequestService;

@Controller
@RequestMapping("/request")
public class RequestWebController {

    @Autowired
    RequestService service;

    @GetMapping
    public ModelAndView index(@PageableDefault(size = 3) Pageable pageable){

        return
            new ModelAndView("/request/index")
            .addObject("requests", service.listAll(pageable));

    }

    @GetMapping("new")
        public String form(Request request){
        return "/request/form";
    }

    @PostMapping
    public String create(@Valid Request request, BindingResult binding,  RedirectAttributes redirect){

        if (binding.hasErrors()) return "/request/form";
        String message = (request.getId() == null)?"Pedido cadastrada com sucesso":"Pedido alterada com sucesso";

        for(RequestItem l : request.getRequestItemList()){
            if(l.getRequest() == null){
                l.setRequest(request);
            }
        }

        service.save(request);
        
        redirect.addFlashAttribute("message", message);
        return "redirect:/request";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirect){
        service.deleteById(id);
        redirect.addFlashAttribute("message", "Pedido apagado com sucesso");
        return "redirect:/request";
    }

    @GetMapping("{id}")
    public ModelAndView edit(@PathVariable Integer id){
        Request request = service.findById(id).get();
        return new ModelAndView("/request/form").addObject("request", request);
    }
    
}
